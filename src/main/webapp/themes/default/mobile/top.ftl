<#include "base.ftl"/>

<#macro titleContent>  
<title>小说排行榜</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>

<#macro content>
    <div class="m10">
        <div class="tab t4">
            <ul><li class="animbg"></li></ul>
            <ul id="bankType">
                <li data-index="0" data-value="1" class="curr">更新榜</li>
                <li data-index="1" data-value="2">点击榜</li>
                <li data-index="2" data-value="3">推荐榜</li>
                <li data-index="3" data-value="4">字数榜</li>
            </ul>
        </div>
        <div class="box m2">
            <ul id="books"></ul>
            <a id="lnkMore" class="loading" onclick="getNextBank();">点击查看更多</a>
        </div>
    </div>

    <script type="text/javascript">

        var _index = 0;
        var _size = 10;
        var _type = 1;
        var _timer;
        var _isLoading = 0;
        
        var formatNumber = function (number) {
            number = parseInt(number);
            if (number > 10000) return parseInt(number / 10000) + "万";
            return number + "";
        }
        

        // 获取榜单数据
        var getBank = function (callback) {

            _isLoading = 1;
            if (_index == 0)
                Util.Loading();
            $.get("${encodeURL("/ajaxService")}", 
                $.param({action:"toplist",index: _index, size: _size, type: _type })
                , function (res) {
                    Util.LoadingClear();
                    _isLoading = 0;
                    var temp = [];
                    for (var i = 0; i < res.items.length; i++) {
                        var item = res.items[i];

                        var pVal = 0;
                        if (_type == 1) pVal = item.lastupdateMin;
                        if (_type == 2) pVal = formatNumber(item.allvisit);
                        if (_type == 3) pVal = formatNumber(item.allvote);
                        if (_type == 4) pVal = formatNumber(item.size);

                        temp.push('<li>');
                        temp.push('<div class="info i3">');
                        temp.push('    <a href="/info/'+Math.floor(item.articleno/1000)+'/'+item.articleno+'.html">');
                        temp.push('        <img src="'+item.imgUrl+'" />');
                        temp.push('        <h3>' + item.articlename + '</h3>');
                        temp.push('        <p>作者：' + item.author + '</p>');
                        temp.push('        <p>类别：' + item.categoryStr + '</p>');
                        temp.push('        <span class="daoju s' + _type + '">' + pVal + '</span>');
                        if (item.fullflag)
                            temp.push('<em class="wj3"></em>');
                        temp.push('    </a>');
                        temp.push('</div>');
                        temp.push('</li>');
                    }

                    $("#books").append(temp.join(""));
                    if (typeof callback == "function")
                        callback();
                });
        }
        // 获取榜单下一页数据
        var getNextBank = function () {
            if (_isLoading == 1)
                return;
            _isLoading = 1;
            _index++;
            $("#lnkMore").addClass("ldg").html("正在加载....");
            getBank(function () { _isLoading = 0; $("#lnkMore").removeClass("ldg").html("点击查看更多"); });
        }
        // 初始化
        $(function () {

            getBank();

            $("#bankType > li").click(
                function () {
                    var sel = $(this);
                    var idx = parseInt(sel.attr("data-index"));
                    sel.parent().siblings("ul").find(".animbg").animate({ marginLeft: (idx * 25) + "%" }, 200
                        , function () { sel.addClass("curr").siblings().removeClass("curr"); });
                    var myType = parseInt(sel.attr("data-value"));
                    if (_type != myType) {
                        _index = 0;
                        _type = myType;
                        $("#books").html("");
                        getBank();
                    }
                });
            _timer = setInterval(function () {
                if (_isLoading == 1)
                    return;
                if ($(window).height() + $(window).scrollTop() > $("#lnkMore").offset().top) {
                    if (_index < 6)
                        getNextBank();
                }
            }, 200);
        });

    </script>

</#macro>
