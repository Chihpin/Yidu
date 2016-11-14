/** 初始化处理 */
$(document).ready(function() {

	//初始化搜索栏
	initSearchText();

	//阅读页简介showall
	initInfoShowAll();	
	
	//阅读页简介showall
	initInfoPageAction();
	
	//阅读页简介showall
	addADInfo();
	
	//阅读页简介showall
	initSearchPageAction();
});

function initSearchText(){
    $("#txtKeyword").click(function () {
        if ($(this).val() == $(this).attr("data-text"))
            $(this).val("");
    }).blur(function () {
        if ($(this).val() == "")
            $(this).val($(this).attr("data-text"));
    });
}

//简介页用的js处理-------------开始
function initInfoShowAll(){
	$("#showAll").click(function () {
	    var value = $(this).attr("data-value");
	    if (value == 0) {
	        $(this).attr("data-value", "1");
	        $(this).html("隐藏部分");
	        $(".intro").css({ height: "auto" });
	        return;
	    }
	    $(this).attr("data-value", "0");
	    $(this).html("显示全部");
	    $(".intro").css({ height: "132px" });
	});
}

var _articleno = 0;
var _size = parseInt(10);
var _index = 0;
var _hisgoryId = 0;
var _sort = 1;
var _isFirst = 0;
var _pages = 0;
var _userid = 0;
var   _userid = 0;
// 获取历史浏览记录
var getHistoryId = function () {
    var value = Util.CookieValue("READ_HISTORY");

    if (value != "") {
        var items = value.split("|");
        for (var i = 0; i < items.length; i++) {
            var arr = items[i].split(",");
            if (parseInt(arr[0]) == _articleno)
                return parseInt(arr[1]);
        }
    }
    return 0;
}

 // 设置书架
var setShelf = function () {
    if (_userid == 0) {
        Site.showLoginbox(function (uid) {
            _userid = uid;
            $.get("/ajaxService", $.param({ action: "bookcaseisexists", articleno: _articleno })
                , function (res) {
                    if (res.code == 0) {
                        $("#lnkShelf").attr("data-value", res.result);
                        $("#lnkShelf").html(res.result == 1 ? "取消收藏" : "加入书架");
                    }
                });
        });
        return;
    }

    var temp = $("#lnkShelf");
    var value = parseInt(temp.attr("data-value"));

    if (value == 1) {
        temp.html("删除中...");
        $.get("/ajaxService", $.param({ action: "deletebookcasebyarticle", articleno: _articleno })
            , function (res) {
                if (res.code == 0) {
                    $("#lnkShelf").html("加入书架");
                    $("#lnkShelf").attr("data-value", 0);
                    Util.Alert("成功取消收藏");
                    return;
                }

                $("#lnkShelf").html("取消收藏");
                Util.Alert("取消收藏时发生了错误");
            });
        return;
    }

    temp.html("添加中...");
    $.get("/ajaxService", $.param({ action: "addbookcase", articleno: _articleno })
        , function (res) {
            if (res.code == 0) {
                $("#lnkShelf").html("取消收藏");
                $("#lnkShelf").attr("data-value", 1);
                Util.Alert("成功加入书架");
                return;
            }
            $("#lnkShelf").html("加入书架");
            Util.Alert("加入书架时发生了错误");
        });
}

// 获取章节目录
var getDirectory = function (index , callback) {
    Util.Loading();
    $.get("/ajaxService"
        , $.param({ action:"chapterlist",articleno: _articleno, index: index, size: _size, sort: _sort })
        , function (result) {
            Util.LoadingClear();
            $("#spnChapters").html(result.total);
            _pages = result.pages;

            if (result.items.length > 0) {
                var tmp = [];
                for (var i = 0; i < result.items.length; i++) {
                    var item = result.items[i];
                    var link = "/reader/"+Math.floor(_articleno/1000)+"/"+_articleno+"/"+item.chapterno+".html";
                    tmp.push('<li>');
                    tmp.push('<a href="' + link + '">' + item.chaptername + '</a>');
                    if (_hisgoryId == item.chapterno) tmp.push('<em class="sq"></em>');
                    
                    tmp.push('</li>');
                }
                $("#chapterlist").html(tmp.join(""));
                _index = index;

                if (_isFirst == 0) {
                    var sel = $("#selPage");
                    sel.empty();
                    for (var i = 0; i < _pages ; i++)
                        sel.append('<option value="' + i + '">第 ' + (i + 1) + '/' + _pages + ' 页</option>');
                    _isFirst = 1;
                }

                if (typeof callback == "function") {
                    callback(index);
                }

                return;
            }

            Util.Alert("已到达末页");

        });
}
// 初始化
function initInfoPageAction(){

    // 分页选择框事件
    $("#selPage").change(function () {
        var index = parseInt($(this).val());
        getDirectory(index);
    });

    // 绑定其他分页按钮事件
    $(".paging").find("li[data-value]").click(function () {

        var value = parseInt($(this).attr("data-value"));
        var index = 0;

        switch (value) {
            case 2:
                index = _index - 1;
                if (index < 0) index = 0;
                break;
            case 3:
                index = _index + 1;
                if (index > _pages - 1) index = _pages - 1;
                break;
            case 4: index = _pages - 1; break;
        }
        getDirectory(index, function (idx) {
            $("#selPage").val(idx);
        });

    });

    //倒序、正序排列
    $("#sort").click(function () {
        var value = parseInt($(this).attr("data-value"));
        _sort = value;
        $(this).attr("data-value", _sort == 1 ? 0 : 1);
        $(this).html(_sort == 1 ? "↓正序排列" : "↑倒序排列");
        getDirectory(0, function (idx) {
            $("#selPage").val(idx);
        });
    });
}
//简介页用的js处理-------------结束


//搜索页用的js处理-------------开始

var _type = 0;
var _status = 0;
var _keyword = "";

var search = function (callback) {
    _keyword = $("#txtKeyword").val();
    if (_keyword == $("#txtKeyword").attr("data-text"))
        _keyword = "";
    if (_index == 0)
        Util.Loading();
    $.get("ajaxService", $.param({ action: "search", key: _keyword, category: _type, sort: _sort, status: _status, index: _index, size: _size })
        , function (res) {
            Util.LoadingClear();
            if (res.total == 0) {
                $(".loading2").show();
                $("#books").html("");
                $(".loading").hide();
                $("#spnSearchCount").html(0);
                return;
            }

            if (_keyword == "" && _type == 0 ) {
                $("#divHot").show().siblings().hide();
                $("#lnkMore").hide();
            } else {
                $("#divHot").hide().siblings().show();
                $("#lnkMore").show();
            }

            $(".loading2").hide();
            $("#spnSearchCount").html(res.total);
            $("#lnkMore").removeClass("ldg").html(res.hasNext ? "加载更多" : "全部加载完毕");

            var str = [];
            for (var i = 0 ; i < res.items.length; i++) {
                var item = res.items[i];
                str.push('<div class="info i2">');
                str.push('    <a href="/info/'+Math.floor(item.articleno/1000)+'/'+item.articleno+'.html">');
                str.push('        <img src="'+item.imgUrl+'" />');
                str.push('        <h3>' + item.articlename + '</h3>');
                str.push('        <p>作者：' + item.author + '</p>');
                str.push('        <p>分类：' + item.categoryStr + '</p>');
                str.push('        <p>字数：' + Util.NumberFormat(item.size) + '(有' + Util.NumberFormat(item.allvisit) + '人阅读)</p>');
                if (item.fullflag)
                    str.push('<em class="wj22"></em>');
                str.push('    </a>');
                str.push('</div>');
            }
            if (_index == 0)
                $("#books").html("");
            $("#books").append(str.join(""));
            if (typeof callback == "function") callback();
        });
}

var getNextPage = function (elem) {
    _index++;
    $(elem).addClass("ldg").html("正在加载.....");
    search();
}

function initSearchPageAction() {

    $("#moreTiaojian").click(function () {
        var value = $(this).attr("data-value");
        if (value == "0") {
            $(this).html("收起");
            $(this).attr("data-value", 1);
            $("#divTiaojian").stop().show(100);
        } else {
            $(this).html("展开");
            $(this).attr("data-value", 0);
            $("#divTiaojian").stop().hide(100);
        }
    });

    $("#lnkSearch").click(function () {
        $("#books").html("");
        _index = 0;
        search();
    });
    
    $(".tiaojian a").click(
        function () {
            $(this).addClass("curr").siblings().removeClass("curr");
            var type = parseInt($(this).attr("data-type"));
            switch (type) {
                case 0:
                    var t2 = parseInt($(this).attr("data-value"));
                    if (_type == t2) {
                        _type = 0;
                        $(this).removeClass("curr");
                    } else {
                        _type = t2;
                    }
                    break;
                case 3:
                    var s2 = parseInt($(this).attr("data-value"));
                    if (_sort == s2) {
                        _sort = 1;
                        $(this).removeClass("curr");
                    } else {
                        _sort = s2;
                    }
                    break;
            }
            _index = 0;
            $("#books").html("");
            search();
        });
    $("#chStatus").click(function () {
        _status = parseInt($(this).val());
        _index = 0;
        $("#books").html("");
        search();
        if (_status == 1) $(this).val(0);
        if (_status == 0) $(this).val(1);
    });
}

//搜索页用的js处理-------------结束
