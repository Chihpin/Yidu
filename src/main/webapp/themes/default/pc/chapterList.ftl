<#include "common.ftl"/>

<#macro titleContent>
<title>${article.articlename}|${article.articlename}最新章节|${article.articlename}TXT下载</title>
<meta name="keywords" content="${article.articlename},${article.articlename}最新章节,${article.articlename}TXT下载,${article.articlename}无广告,${getText("label.system.name")}" />
<meta name="description" content="《${article.articlename}》情节跌宕起伏、扣人心弦，是一本情节与文笔俱佳的<#if article.category!=0>${categorymap[article.category?c]}</#if>小说，${getText("label.system.name")}免费提供${article.articlename}最新的清爽干净的文字章节在线阅读!" />
 
   <meta property="og:novel:read_url" content="${article.url}"/>

   <!--选填-->
   <meta property="og:novel:status" content="<#if article.fullflag>完结<#else>连载中</#if>"/>
   <meta property="og:novel:update_time" content="${article.lastupdate?string("yyyy-MM-dd HH:mm")}"/>
   <meta property="og:novel:click_cnt" content="${article.allvisit?c}"/>
   <meta property="og:novel:latest_chapter_name" content="${article.lastchapter}"/>
   <meta property="og:novel:latest_chapter_url" content="${article.lastChapterUrl}"/>

</#macro>



<#macro content>
    <#if adEffective?? && adEffective>
    <script type="text/javascript" src="${contextPath}/ad/chapterList1.js"></script>
    </#if>
    <div class="mainnav"><div class="main-index"> 位置：  &nbsp; > &nbsp; 
        <a href="${encodeURL("/articleList?category=${article.category}")}" class="c009900">
        ${categorymap[article.category?c]}</a> &nbsp; > &nbsp; 
        <a href="${article.url}" class="c009900">${article.articlename}</a>
    </div>
    <#if adEffective?? && adEffective>
    <script type="text/javascript" src="${contextPath}/ad/chapterList2.js"></script>
    </#if>
    <div class="clear"></div>
    <div class="chapterNum">
        <a name="chapters"></a>
          <ul>
            <h1>《${article.articlename}》分卷阅读<#if !loginFlag>([<a href="${encodeURL("/login")}">登陆</a>]后开放)</#if></h1>
            <#list chapterList as chapter>
                <#if chapter.chaptertype == 0>
					<#if loginFlag>
						<#if chapter_index % 30 == 0>
							<li style="width:100%;background-color:#f2f9f1;margin-left:0;text-align:center;">
								<#if (chapter_index + 30 lt chapterList?size)>
									<a href="${encodeURL("/reader?subdir=${article.subdir?c}&articleno=${article.articleno?c}&chapterno=${chapterList[chapter_index].chapterno?c}&toChapterno=${chapterList[chapter_index+29].chapterno?c}")}">分段阅读</a>
								<#else>
									<a href="${encodeURL("/reader?subdir=${article.subdir?c}&articleno=${article.articleno?c}&chapterno=${chapterList[chapter_index].chapterno?c}&toChapterno=${chapterList[chapterList?size-1].chapterno?c}")}">分段阅读</a>
								</#if>
							</li>
						</#if>
					</#if>
                    <li>
                    <a href="${chapter.url}" title="${chapter.chaptername}">${chapter.chaptername}</a>
                    </li>
                </#if>
            </#list>
          </ul>
        </div>
    </div>
    <#if adEffective?? && adEffective>
    <script type="text/javascript" src="${contextPath}/ad/chapterList3.js"></script>
    </#if>
</#macro>

<#macro customizefooter> 
    <div id="full2" style="width:37px; height:22px; position:fixed; left:50%; top:425px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
    <a class="get_top" alt="返回顶部"></a>
    </div>
    
    <div id="full" style="width:37px; height:22px; position:fixed; left:50%; top:562px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
    <a class="get_bottom" alt="跳至页尾"></a>
    </div>
    <script src="${contextPath}/themes/${themeName}/pc/js/news_top.js" type="text/javascript"></script>
</#macro>
