<#include "common.ftl"/>

<#macro assignContent> 
    <#assign topnamemap = topNameJsonData?eval>
</#macro>

<#macro titleContent>  
<title>${topnamemap[sortColumn]}|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>

<#macro content>
    <#if adEffective?? && adEffective>
    <script type="text/javascript" src="${contextPath}/ad/list1.js"></script>
    </#if>
    
    <div class="topLefft">
        <h1>排行榜</h1>
        <ul>
            <#list topnamemap?keys as topkey>
                <li <#if sortColumn??><#if topkey==sortColumn>class="select"</#if></#if>><a href="${encodeURL("/top?sortColumn=${topkey}")}">${topnamemap[topkey]}</a></li>
            </#list>
        </ul>
    </div>
    <div class="topRight">
        <h1 class="allClick"><span>${topnamemap[sortColumn]}</span></h1>
        <ul class="seeWell cf">
        <#list articleList as article>
        <#if article_index lt 3>
            <li><a class="l mr10" title="${article.articlename}" href="${encodeURL("/info?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}" target="_blank">
            <img style="width: 120px; height: 150px" src="${article.imgUrl}" alt="${article.articlename}"></a>
            <#if article.fullflag ><img src="${contextPath}/themes/${themeName}/images/only.png" class="topss png_bg"><#else><img src="${contextPath}/themes/${themeName}/images/only2.png" class="topss png_bg"></#if>
            <span class="l"><a class="clearfix stitle" href="${encodeURL("/info?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}">
            <em <#if article_index ==0 >class="first"</#if>>${article_index + 1}</em>${article.articlename}</a>
            类型：<a href="${encodeURL("/articleList?category=${article.category}&page=1")}"><#if article.category!=0>${categorymap[article.category?c]}</#if></a><br/> 
            作者：<a href="${encodeURL("/articleList?author=${article.author}")}">${article.author}</a>
            <br>点击数：<label>${article.allvisit}</label>
            <a class="readTo" href="${encodeURL("/info?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}">马上阅读</a></span></li>
        </#if>
        </#list>
        </ul>
        <div class="allList">
        <#list articleList as article>
        <#if article_index gt 2 >
          <dl><dt><em>${article_index + 1}</em>[
          <a href="${encodeURL("/articleList?category=${article.category}&page=1")}"> <#if article.category!=0>${categorymap[article.category?c]}</#if></a>]</dt>
          <dd class="title"><a href="${encodeURL("/info?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}">${article.articlename}</a></dd>
          <dd class="state">最新章节：<a href="${encodeURL("/reader?subdir=${article.subdir?c}&articleno=${article.articleno?c}&chapterno=${article.lastchapterno?c}")}"><#if article.lastchapter?? >${article.lastchapter}</#if>（ </a> [${article.lastupdate?string("MM-dd HH:mm")}]</dd>
          <dd class="authors">作者：<a href="${encodeURL("/articleList?author=${article.author}")}">${article.author}</a></dd></dl>
        </#if>
        </#list>
        <div class="mainnav" id="navList">
        <div class="pages">
              <div class="pagelink" id="pagelink">
                <#assign listurl = "/top?sortColumn=${sortColumn}&page=">
                <#assign listurlforjs = "${contextPath}/top/${sortColumn}/" >
                <em id="pagestats">${pagination.pageNumber}/${pagination.totalPages?c}</em>
                <a href="${encodeURL(listurl +"1")}" class="first">1</a>
                <#list pagination.pageNumberList as pagenum >
                    <#if pagenum_index == 0 && (pagenum > 1 )>
                        <a href="${encodeURL(listurl+ (pagenum-1)?c)}" class="prev">&lt;</a>
                    </#if>
                    <#if pagenum == pagination.pageNumber>
                        <strong>${pagenum?c}</strong>
                    <#else>
                        <a href="${encodeURL(listurl + pagenum?c)}"> ${pagenum?c} </a>
                    </#if>
                    <#assign mxpagenum = pagenum >
                </#list>
                <#if mxpagenum?? && (mxpagenum < pagination.totalPages)>
                     <a href="${encodeURL(listurl + (mxpagenum+1)?c)}" class="next">&gt;</a>
                </#if>
                <a href="${encodeURL(listurl + pagination.totalPages?c)}">${pagination.totalPages?c}</a>
                <kbd>
                    <input name="page" type="text" size="4" maxlength="6" onkeydown="if(event.keyCode==13){window.location='${listurlforjs}'+this.value+'.html'; return false;}" /></kbd>
             </div>
        </div>
        </div>
        </div>
    </div>
    <#if adEffective?? && adEffective>
    <script type="text/javascript" src="${contextPath}/ad/list3.js"></script>
    </#if>
</div>
</#macro>
