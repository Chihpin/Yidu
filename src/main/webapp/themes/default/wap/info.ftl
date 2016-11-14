<#include "base.ftl"/>

<#macro titleContent>
<title>${article.articlename?html}|${article.articlename?html}最新章节|${article.articlename?html}TXT下载</title>
<meta name="keywords" content="${article.articlename?html},${article.articlename?html}最新章节,${article.articlename?html}TXT下载,${article.articlename?html}无广告,${getText("label.system.name")}" />
<meta name="description" content="《${article.articlename?html}》情节跌宕起伏、扣人心弦，是一本情节与文笔俱佳的<#if article.category!=0>${article.categoryStr}</#if>小说，${getText("label.system.name")}免费提供${article.articlename?html}最新的清爽干净的文字章节在线阅读!" />
</#macro>

<#macro content>
    <div class="box m10" style="padding:10px 0">
        <div class="info i4" style="border-bottom:none;">
            <a href="javascript:;">
                <img src="<#if article.imgUrl ?? >${article.imgUrl}</#if>" />
                <h3>${article.articlename?html}</h3>
                <p>作者：${article.author?html}</p>
                <p>类别：<#if article.category!=0>${article.categoryStr}</#if></p>
                <p>字数：<#if article.size ??>${article.size}<#else>0</#if></p>
                <p>点击：${article.allvisit}</p>
                <em id="isWanjie" class="wj4"></em>
            </a>
        </div>
        <div class="m10">
            <ul class="group g3">
                <li><a class="button blue r3" href="#chapters">开始阅读</a></li>
                <li><a class="button color2 r3" href="javascript:setShelf();" id="lnkShelf" data-value="0">加入书架</a></li>
            </ul>
        </div>
    </div>
    
    <div id="panels" class="m2">
        <div class="box"> 
            <div class="new">
                <a href="${article.lastChapterUrl}">
                    <p class="name">最新章节：<#if article.lastchapter?? >${article.lastchapter?html}</#if></p>
                    <p class="time">更新时间：${article.lastupdate?string("yyyy-MM-dd HH:mm:ss")}</p>
                </a>
            </div>
            <div class="line"></div>
            <div class="intro box">
                ${article.introForHtml}
            </div>
            <div class="sw">
                <a id="showAll" href="javascript:;" data-value="0">显示全部</a>
            </div>
        </div>
    </div>
    <a name="chapters"></a>
    <div class="m10">
            <ul id="tejia" class="group g1">
                <li><span class="button blue r3">章节列表</span></li>
            </ul>
    </div>

    <div class="m2">
        <div class="title">
            共<span id="spnChapters"><#if chapterList?? >${chapterList?size}<#else>0 </#if></span>章节
            <a id="sort" class="more2" data-value="1">↑倒序排列</a>
        </div>
        <div class="m2">
            <ul class="list" id="chapterlist"></ul>
        </div>
        <div class="paging">
            <ul>
                <li data-value="1"></li>
                <li data-value="2"></li>
                <li><select id="selPage"></select></li>
                <li data-value="3"></li>
                <li data-value="4"></li>
            </ul>
        </div>
    </div>
</#macro>

<#macro customizefooter> 
    <script type="text/javascript">

        var _articleno = parseInt(${article.articleno?c});
        var _size = parseInt(10);
        var _index = 0;
        var _hisgoryId = 0;
        var _sort = 0;
        var _isFirst = 0;
        var _pages = 0;
        var _userid = 0;
        <#if loginUser?? >
            _userid = parseInt(${loginUser.userno?c});
        </#if>
        // 加载第一页
        getDirectory(0);
    </script>

</#macro>
