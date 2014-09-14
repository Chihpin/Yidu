<#include "common.ftl"/>

<#macro titleContent>  
<title>${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro recommendblock articleList id style> 
<ul class="seeWell cf" id="${id}" style="${style}">
    <#list articleList as article>
    <li>
       <a href="${encodeURL("/info?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}"  title="${article.articlename}" class="l mr10">
           <img src="${contextPath}${article.imgUrl}" style="width: 120px; height: 150px" alt="${article.articlename}"/></a>
       <#if article.fullflag ><img src="${contextPath}/themes/${themeName}/pc/images/only.png" alt="完本图标" class="topss png_bg"><#else><img src="${contextPath}/themes/${themeName}/pc/images/only2.png" class="topss png_bg" alt="连载中图标"></#if>
       <span class="l">
          <a href="${encodeURL("/info?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}"  title="${article.articlename}" class="clearfix stitle">${article.articlename}</a>
          作者：<a href="${encodeURL("/articleList?author=${article.author}")}">${article.author}</a>
          <em class="c999 clearfix">${article.introForHtml}</em>
          更新：<a href="${encodeURL("/reader?subdir=${article.subdir?c}&articleno=${article.articleno?c}&chapterno=${article.lastchapterno?c}")}"  title="${article.lastchapter}">${article.lastchapterOmit}</a>
          <a href="${encodeURL("/info?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}" class="readTo"  title="${article.articlename}">马上阅读</a>
       </span>
    </li>
    </#list>
</ul>
</#macro>

<#macro lastupdateblock articleList> 
<section class="box" id="news">
    <h2>刚刚更新的：</h2>
    <ol>
        <#list articleList as article>
            <li>
                <em class="r">${article.lastupdate?string("MM-dd HH:mm")}</em>
                <a href="${encodeURL("/articleList?category=${article.category}&page=1")}" title = "<#if article.category!=0>${categorymap[article.category?c]}</#if>">[&nbsp;<#if article.category!=0>${categorymap[article.category?c]}</#if>&nbsp;]</a>
                <a class="poptext" href="${encodeURL("/info?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}"
                    title="${article.articlename}最新章节" class="f14">${article.articlename}</a>
                <a class="poptext" href="${encodeURL("/reader?subdir=${article.subdir?c}&articleno=${article.articleno?c}&chapterno=${article.lastchapterno?c}")}" ><#if article.lastchapter?? >${article.lastchapter}</#if></a>
                作者：<a class="poptext" href="${encodeURL("/articleList?author=${article.author}")}" title="${article.author}的小说" class="f14">${article.author}</a>
            </li>
        </#list>
    </ol>
</section>
</#macro>

<#macro lastinsertblock articleList> 
<section class="box" id="sorts">
    <h2>最新入库小说：</h2>
    <ol>
        <#list articleList as article>
        <li class="top3">
            <span class="order">${article_index+1}</span>
            <span class="chapter"><a href="${encodeURL("/articleList?category=${article.category}")}" title="<#if article.category!=0>${categorymap[article.category?c]}</#if>" target="_blank">[<#if article.category!=0>${categorymap[article.category?c]}</#if>]</a></span>
            <a target="_blank" href="${encodeURL("/info?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}" title="${article.articlename}最新章节">${article.articlename}</a> 
            <span class="chapter"><a href="${encodeURL("/reader?subdir=${article.subdir?c}&articleno=${article.articleno?c}&chapterno=${article.lastchapterno?c}")}" title="${article.lastchapter}">${article.lastchapter}</a></span>
        </li>
        </#list>
    </ol>
</section>
</#macro>


<#macro content>
   <#if adEffective?? && adEffective>
   <script type="text/javascript" src="${contextPath}/ad/index1.js"></script>
   </#if>
   <p class="b-all-switch normal">好看的:
      <a href="javascript:;" class="select" id="ask1" onMouseOver="repales_rell1(1,5)">都市言情</a>
      <a href="javascript:;" id="ask2" onMouseOver="repales_rell1(2,5)">玄幻魔法</a>
      <a href="javascript:;" id="ask3" onMouseOver="repales_rell1(3,5)">历史军事</a>
      <a href="javascript:;" id="ask4" onMouseOver="repales_rell1(4,5)">恐怖灵异</a>
      <a href="javascript:;" id="ask5" onMouseOver="repales_rell1(5,5)">武侠修真</a>
   </p>

    <#if recommendblock?exists>
          <@recommendblock articleList=blocks.index_yanqing_tuijian id="cttd1" style=""/>
    </#if>
    <#if recommendblock?exists>
          <@recommendblock articleList=blocks.index_xuanhuan_tuijian id="cttd2" style="display:none;"/>
    </#if>
    <#if recommendblock?exists>
          <@recommendblock articleList=blocks.index_junshi_tuijian id="cttd3" style="display:none;"/>
    </#if>
    <#if recommendblock?exists>
          <@recommendblock articleList=blocks.index_kongbu_tuijian id="cttd4" style="display:none;"/>
    </#if>
    <#if recommendblock?exists>
          <@recommendblock articleList=blocks.index_wuxia_tuijian id="cttd5" style="display:none;"/>
    </#if>

    <#if adEffective?? && adEffective>
    <script type="text/javascript" src="/ad/index2.js"></script>
    </#if>
        <div id="J_random_board" class="random-board cf board">
            <#if lastupdateblock?exists>
              <@lastupdateblock articleList=blocks.last_update_list/>
            </#if>
            <#if lastinsertblock?exists>
              <@lastinsertblock articleList=blocks.last_insert_list/>
            </#if>
        </div> 
        <div class="clear"></div>
        <p class="b-all-switch normal">友情链接:</p>
        <div class="mainLink">
            ${blocks.friend_link}
        </div>
</#macro>
