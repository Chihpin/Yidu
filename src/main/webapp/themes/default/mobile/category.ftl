<#include "base.ftl"/>

<#macro titleContent>  
<title>作品分类</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>

<#macro content>
    <div class="m10">
        <ul class="cate">
            <li id="categories">
                    <a data-index="0" data-value="1">玄幻</a><a data-index="1" data-value="2">武侠</a>
                    <a data-index="2" data-value="3">都市</a><a data-index="3" data-value="4">历史</a>
                    <a data-index="4" data-value="5">推理</a><a data-index="5" data-value="6">网游</a>
                    <a data-index="6" data-value="7">科幻</a><a data-index="7" data-value="8">恐怖</a>
                    <a data-index="8" data-value="9">散文</a><a data-index="9" data-value="10">其他</a>
                    <a data-index="11" data-value="99">完本</a>
                   
            </li>
            <li id="books"></li>
        </ul>
        <div class="m1">
            <a id="lnkMore" class="loading ww" onclick="showNextPage();">点击查看更多</a>
        </div>
    </div>
    
    <script type="text/javascript">

        var _cid = parseInt(1);
        var _index = parseInt(0);
        var _size = parseInt(10);
        var _sort = 6;
        var _isLoading = 0;
        var _timer;
        var _categoriesIndex = 0;

        var _categories = [];
        $("#categories a").each(function () {
            _categories.push({ index: 0, items: [] });
        });

        var formatNumber = function (number) {
            number = parseInt(number);
            if (number > 10000) return parseInt(number / 10000) + "万";
            return number + "";
        }
        // 获取书
        var getBooks = function (callback) {
            _isLoading = 1;
            $.get("${encodeURL("/ajaxService")}"
                , {action:"categorylist",category: _cid, sort: _sort, index: _index, size: _size }
                , function (res) {
                    _isLoading = 0;
                    var temp = [];
                    for (var i = 0; i < res.items.length; i++) {
                        var item = res.items[i];
                        temp.push('<div class="info i3">');
                        temp.push('    <a href="/info/'+Math.floor(item.articleno/1000)+'/'+item.articleno+'.html">');
                        temp.push('        <img src="'+item.imgUrl+'" />');
                        temp.push('        <h3>' + item.articlename + '</h3>');
                        temp.push('        <p>作者：' + item.author + '</p>');
                        temp.push('        <p>字数：' + formatNumber(item.size) + '(有' + formatNumber(item.allvisit) + '人阅读)</p>');
                        if (item.fullflag)
                            temp.push('<em class="wj3"></em>');
                        temp.push('    </a>');
                        temp.push('</div>');
                    }
                    _categories[_categoriesIndex].index = _index;
                    _categories[_categoriesIndex].items.push(temp.join(""));
                    $("#books").append(temp.join(""));
                    if (typeof callback == "function") 
                        callback();

                    if (_index >= res.pages - 1) {
                        $("#lnkMore").removeClass("ldg").html("前往搜索页查看更多作品");
                        $("#lnkMore").attr("href", "search.aspx");
                    } else {
                        $("#lnkMore").removeClass("ldg").html("点击查看更多");
                        $("#lnkMore").attr("href", "javascript:;");
                    }

                });
        }
        // 下一页
        var showNextPage = function () {
            if (_isLoading == 1)
                return;
            _isLoading = 1;
            _index++;
            $("#lnkMore").addClass("ldg").html("正在加载...");
            getBooks(function () {
                $("#lnkMore").removeClass("ldg").html("点击查看更多");
                _isLoading = 0;
            });
        }
        // 初始化
        $(function () {

            $("#categories > a").each(function () {
                var id = parseInt($(this).attr("data-value"));
                if (id == _cid) {
                    $(this).addClass("curr");
                    _categoriesIndex = parseInt($(this).attr("data-index"));
                }
            });

            $("#categories > a").click(
                function () {

                    $(this).addClass("curr").siblings().removeClass("curr");
                    _categoriesIndex = parseInt($(this).attr("data-index"));
                    _index = _categories[_categoriesIndex].index;

                    $("#books").html(_categories[_categoriesIndex].items.join(""));
                    _cid = parseInt($(this).attr("data-value"));

                    if (_index == 0
                        && _categories[_categoriesIndex].items.length == 0) {
                        Util.Loading();
                        getBooks(function () { Util.LoadingClear(); });
                    }

                });

            $("#lnkMore").addClass("ldg").html("正在加载...");
            getBooks(function () {
                $("#lnkMore").removeClass("ldg").html("点击查看更多");
                _timer = setInterval(function () {
                    if (_isLoading == 1)
                        return;
                    if ($(window).height() + $(window).scrollTop() > $("#lnkMore").offset().top) {
                        if (_index < 6)
                            showNextPage();
                    }
                }, 200);
            });

            $("#categories").height($(window).height() - 96);
            $(window).scroll(function () {
                ($(window).scrollTop() >= 96)
                    ? $("#categories").css({ top: 0 })
                    : $("#categories").css({ top: "96px" });
            });

        });

    </script>

</#macro>
