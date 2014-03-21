<#include "common.ftl"/>

<#macro titleContent>  
<#if category?? >
<title>${categorymap[category?c]}小说|${getText("label.system.title")}</title>
<meta name="keywords" content="${categorymap[category?c]},${categorymap[category?c]}小说,${getText("label.system.siteKeywords")}" />
<#elseif author?? >
<title>${author}的小说|${getText("label.system.title")}</title>
<meta name="keywords" content="${author}的小说,${getText("label.system.siteKeywords")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
<#else>
<title>完本小说|${getText("label.system.title")}</title>
<meta name="keywords" content="完本小说,"${getText("label.system.siteKeywords")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#if>
</#macro>


<#macro content>
<#if adEffective?? && adEffective>
<script type="text/javascript" src="${contextPath}/ad/list1.js"></script>
</#if>
<div class="mainnav" id="navList">
<div class="main-index">位置： <a href="#"><#if fullflag ?? && fullflag>全本小说<#elseif category ??>${categorymap[category?c]}<#else>${author}的小说</#if></a></div>
    <section class="section board-list board-list-collapse">
    <ul class="seeWell cf">
        <#list articleList as article>
        <li>
           <a href="${encodeURL("/info?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}"  title="${article.articlename}" class="l mr10">
                <img src="${article.imgUrl}" style="width: 120px; height: 150px"/></a>
           <#if article.fullflag ><img src="${contextPath}/themes/${themeName}/images/only.png" class="topss png_bg"><#else><img src="${contextPath}/themes/${themeName}/images/only2.png" class="topss png_bg"></#if>
           <span class="l">
              <a href="${encodeURL("/info?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}"  title="${article.articlename}" class="clearfix stitle">${article.articlename}</a>
              作者：<a href="${encodeURL("/articleList?author=${article.author}")}">${article.author}</a>
              <em class="c999 clearfix">${article.intro}</em>
              更新：<a href="${encodeURL("/reader?subdir=${article.subdir?c}&articleno=${article.articleno?c}&chapterno=${article.lastchapterno?c}")}"  title="${article.lastchapter}">${article.lastchapterOmit}</a>
              <a href="${encodeURL("/info?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}" class="readTo"  title="${article.articlename}">马上阅读</a>
           </span>
        </li>
        </#list>
    </ul>
    <#if adEffective?? && adEffective>
      <script type="text/javascript" src="${contextPath}/ad/list2.js"></script>
    </#if>  
          <div class="pages">
              <div class="pagelink" id="pagelink">
                <#if fullflag?? && fullflag>
                <#assign listurl = "/articleList?fullflag=true&page=" >
                <#assign listurlforjs = "${contextPath}/wanben/" >
                <#elseif category??>
                <#assign listurl = "/articleList?category=${category}&page=">
                <#assign listurlforjs = "${contextPath}/list/${category}/" >
                <#else>
                <#assign listurl = "/articleList?author=${author}&page=">
                <#assign listurlforjs = "${contextPath}/list/${author}/" >
                </#if>
                <em id="pagestats">${pagination.pageNumber}/${pagination.totalPages}</em>
                <a href="${encodeURL(listurl +"1")}" class="first">1</a>
                <#list pagination.pageNumberList as pagenum >
                    <#if pagenum_index == 0 && (pagenum > 1 )>
                        <a href="${encodeURL(listurl+ (pagenum-1)?c)}" class="prev">&lt;</a>
                    </#if>
                    <#if pagenum == pagination.pageNumber>
                        <strong>${pagenum?c}</strong>
                    <#else>
                        <a href="${encodeURL(listurl + pagenum)}"> ${pagenum?c} </a>
                    </#if>
                    <#assign mxpagenum = pagenum >
                </#list>
                <#if mxpagenum?? && (mxpagenum < pagination.totalPages)>
                     <a href="${encodeURL(listurl + (mxpagenum+1)?c)}" class="next">&gt;</a>
                </#if>
                <a href="${encodeURL(listurl + pagination.totalPages)}">${pagination.totalPages}</a>
                <kbd>
                    <input name="page" type="text" size="4" maxlength="6" onkeydown="if(event.keyCode==13){window.location='${listurlforjs}'+this.value+'.html'; return false;}" /></kbd>
             </div>
        </div>
    </section>
  </div>
  <#if adEffective?? && adEffective>
  <script type="text/javascript" src="${contextPath}/ad/list3.js"></script>
  </#if>
  </div>
</#macro>
