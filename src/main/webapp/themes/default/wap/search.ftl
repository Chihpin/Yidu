<#include "base.ftl"/>

<#macro titleContent>  
<title>小说搜索</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>

<#macro content>
    <div class="title m10">筛选条件<a id="moreTiaojian" class="more" data-value="0">展开</a></div>
    <div id="divTiaojian" class="box m2" style="padding:10px 0; display:none; ">
        <ul class="tiaojian">
            <li>分类：</li>
            <li>
                <a href="javascript:;" data-value="0" data-type="0" class="curr">全部</a>
                <a href="javascript:;" data-value="1" data-type="0">玄幻</a>
                <a href="javascript:;" data-value="2" data-type="0">武侠</a>
                <a href="javascript:;" data-value="3" data-type="0">都市</a>
                <a href="javascript:;" data-value="4" data-type="0">历史</a>
                <a href="javascript:;" data-value="5" data-type="0">推理</a>
                <a href="javascript:;" data-value="6" data-type="0">网游</a>
                <a href="javascript:;" data-value="7" data-type="0">科幻</a>
                <a href="javascript:;" data-value="8" data-type="0">恐怖</a>
                <a href="javascript:;" data-value="9" data-type="0">散文</a>
                <a href="javascript:;" data-value="11" data-type="0">其他</a>
            </li>
        </ul>
        <ul class="tiaojian">
            <li>排序：</li>
            <li>
                <a href="javascript:;" data-value="1" data-type="3" class="curr">更新</a>
                <a href="javascript:;" data-value="2" data-type="3">点击</a>
                <a href="javascript:;" data-value="3" data-type="3">推荐</a>
                <a href="javascript:;" data-value="4" data-type="3">字数</a>
            </li>
        </ul>
    </div>

    <div class="title m10">
        <div id="divCount" style="display:none;">共<span id="spnSearchCount">0</span>条记录</div>
        <div id="divHot">热门推荐</div>
        <div id="divStatus" style=" display:none; position:absolute; top:0; right:5%; font-weight:normal;"><input id="chStatus" type="checkbox" value="1" /><label for="chStatus">显示完结作品</label></div>
    </div>
    <div class="m2">
        <div id="books"><div class="loading2">正在加载.....</div></div>
        <div class="loading2" style="display:none;">暂无任何记录</div>
        <div><a id="lnkMore" class="loading" onclick="getNextPage(this);" style="display:none;">加载更多</a></div>
    </div>
</#macro>

<#macro customizefooter>

<script type="text/javascript">
    var _sort = 1;
    var _index = 0;
    var _size = 10;
    var _type = 0;
    var _status = 0;
    <#if key??>
    var _keyword = "${key}";
    <#else>
    var _keyword = "";
    </#if>
    search();
</script>

</#macro>

