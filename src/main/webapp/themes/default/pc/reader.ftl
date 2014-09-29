<#include "common.ftl"/>
<#macro titleContent>  
<title>${chapter.articlename}-${chapter.chaptername}</title>
<meta name="keywords" content="${chapter.chaptername},${chapter.articlename}最新章节,${chapter.articlename}TXT下载,${chapter.articlename}无广告,${getText("label.system.name")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  
<#macro customizeimport>  
<link href="${contextPath}/themes/${themeName}/pc/css/readtools.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/tools.js"></script>
<script src="${contextPath}/themes/${themeName}/pc/js/lib/jquery.tools.min1.2.5.js"></script>
<script type="text/javascript">
    <!--
    var preview_page = '<#if chapter.preChapterno ==0>${encodeURL("/info?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}")}<#else>${encodeURL("/reader?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}&chapterno=${chapter.preChapterno?c}")}</#if>';
    var next_page = '<#if chapter.nextChapterno ==0>${encodeURL("/info?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}")}<#else>${encodeURL("/reader?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}&chapterno=${chapter.nextChapterno?c}")}</#if>';
    var index_page = '<#if enableChapterIndexPage>${article.chapterListUrl}<#else>${article.url}</#if>';
    var article_id = '${chapter.articleno?c}';
    var chapter_id = '${chapter.chapterno?c}';
    function jumpPage() {
      var event = document.all ? window.event : arguments[0];
      if (event.keyCode == 37) document.location = preview_page;
      if (event.keyCode == 39) document.location = next_page;
    }
    document.onkeydown=jumpPage;

    $(document).ready(function(){
       var readhistory = $.cookie("readhistory");
       if(! readhistory ){
            readhistory = new Array();
       }else{
            readhistory = JSON.parse(readhistory);
       }
       var readObject = new Object();
       readObject.chapterno = ${chapter.chapterno?c};
       readObject.articleno = ${chapter.articleno?c};
       readObject.chaptername = "${chapter.chaptername}";
       readObject.articlename = "${chapter.articlename}";
       readObject.imgUrl = "${article.imgUrl}";
       var index = readObject.articleno.in_array(readhistory);
       if(index != -1){
            readhistory.splice(index,1);
       }
       readhistory.splice(0,0,readObject);
       if(readhistory.length > 10 ){
            readhistory.splice(9,readhistory.length - 10);
       }
       $.cookie("readhistory",JSON.stringify(readhistory),{path:'/' ,expires: 365});
    })
    -->
</script>

</#macro>

<#macro content>
<div class="mainnav">
        <div class="main-index" id="direct">
            <span class="r mr10">
                <form action="${contextPath}/search" method="get" >
                    搜小说：<input type="text" name="key" value="" />
                    <button type="submit" >搜</button>
                </form>
            </span>
    位置：&nbsp; > &nbsp; <a href="${contextPath}/" class="c009900">${getText("label.system.name")}</a> > 
    <a href="${article.url}" class="article_title">${chapter.articlename}</a>  > 
    <#if enableChapterIndexPage><a href="${article.chapterListUrl}" class="article_title">${chapter.articlename}章节目录</a> > </#if>
    ${chapter.chaptername}</div>
    <section class="main b-detail" id="directs">
        <div class="bookInfo">
            <h1>
                <span class="r"></span>
                <em class="l">《${chapter.articlename}》</em>
                <strong class="l jieqi_title">${chapter.chaptername}</strong>
                <a href="${encodeURL("/user/bookcase!add?articleno=${chapter.articleno?c}&chapterno=${chapter.chapterno?c}")}"  target="_blank" title="加入书签" class="l">加入书签</a>
                <a href="${encodeURL("/user/vote?articleno=${chapter.articleno?c}")}"  target="_blank" title="推荐本书" class="l">推荐本书</a>
                <a href="${article.subscribeUrl}"  target="_blank" title="订阅本书" class="l">订阅本书</a>
                <div class="clear"></div>
            </h1>
            <div class="toolbar">
            <ul>
                <li>
                    <span class="fl">背景：</span>
                    <div class="fl">
                        <input id="bg1" onclick="setBG('#dcecf5')" type="button" class="setBG" />
                        <input id="bg2" onclick="setBG('#e1ffe6')" type="button" class="setBG" />
                        <input id="bg3" onclick="setBG('#edf6d0')" type="button" class="setBG" />
                        <input id="bg4" onclick="setBG('#eae8f7')" type="button" class="setBG" />
                        <input id="bg5" onclick="setBG('#f5f1e7')" type="button" class="setBG" />
                        <input id="bg6" onclick="setBG('#ebf4ef')" type="button" class="setBG" />
                        <input id="bg7" onclick="setBG('#FFFFFF')" type="button" class="setBG" />
                    </div>
                </li>
                <li>
                    <span class="fl">字体大小：</span>
                    <input type='range' name='fontsize' id='fontsize' value='14' style='display:none' readonly min='12' max='30' />
                </li>
                <li>
                    <span class="fl">字体颜色：</span>
                    <div class="fl">
                        <select onchange="setFontColor(this.value)" id="txtcolor" name="txtcolor">
                            <option value="#000000">黑色</option>
                            <option value="#ff0000">红色</option>
                            <option value="#006600">绿色</option>
                            <option value="#0000ff">蓝色</option>
                            <option value="#660000">棕色</option>
                        </select>
                    </div>
                </li>
                <li id="sudu">
                    <span class="fl">滚动速度：</span>
                    <a id="sudu50" href="javascript:setSpeed(50);">快</a>
                    <a id="sudu100" href="javascript:setSpeed(100);" class="this">中</a>
                    <a id="sudu150" href="javascript:setSpeed(150);">慢</a>
                </li>
            </ul>
        </div>
        <#if adEffective?? && adEffective>
        <script type="text/javascript" src="${contextPath}/ad/reader1.js"></script>
        </#if>
        <div class="mainContenr"   id="content">
            <#if chapter.content??>${chapter.content}</#if>
        </div>
        <#if adEffective?? && adEffective>
        <script type="text/javascript" src="${contextPath}/ad/reader2.js"></script>
        </#if>
        <div class="backs">
            <a href="<#if chapter.preChapterno ==0>${encodeURL("/info?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}")}<#else>${encodeURL("/reader?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}&chapterno=${chapter.preChapterno?c}")}</#if>" class="pre">上一章</a>
            <a href="<#if enableChapterIndexPage>${article.chapterListUrl}<#else>${article.url}</#if>" class="backfor">返回目录</a>
            <a href="<#if chapter.nextChapterno ==0>${encodeURL("/info?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}")}<#else>${encodeURL("/reader?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}&chapterno=${chapter.nextChapterno?c}")}</#if>" class="next">下一章</a>
            <p>小提示： 按←键返回上一页，按→键进入上一页,您还可以
                 <a href="${encodeURL("/user/bookcase!add?articleno=${chapter.articleno?c}&chapterno=${chapter.chapterno?c}")}" title="加入书签"  target="_blank">加入书签</a>
            </p></div>
        <#if adEffective?? && adEffective>
        <script type="text/javascript" src="${contextPath}/ad/reader3.js"></script>
        </#if>
        </div>
       </section>
       <div class="attention">
            <em>阅读提示：</em><br/>
            1、本站会员登录后，将免费体会到最顺畅的阅读方式[<em>最少广告</em>]。<br/>
            2、<em>注册本站会员</em>，将《<a href="${encodeURL("/info?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}")}" class="article_title"><em>${chapter.articlename}</em></a>》加入书架，可以通过书架更快的了解更新信息。<br/>
            3、免费小说《<a href="${encodeURL("/info?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}")}" class="article_title"><em>${chapter.articlename}</em></a>》 ${chapter.chaptername}所描述的内容只是作者个人观点，与本站的立场无关，本站只为广大用户提供阅读平台。
        </div>
        
     <div class="navTab">
        <ul>
            <li onmouseover="replaces(1,2)" id="for1" class="select"><a href="#">站长推荐</a></li>
            <li onmouseover="replaces(2,2)" id="for2" class ><a href="#">猜你喜欢</a></li>
        </ul>
    </div>

    <div class="tabMain">
        <#if recommendArticleList ?? > 
        <ul id="content1">
            <#list recommendArticleList as article>
            <li><a href="${article.url}" title="${article.articlename}"><img src="${article.imgUrl}" width="111px;" height="146px;" alt="${article.articlename}"></a>
            <#if article.fullflag>
                <img src="${contextPath}/themes/${themeName}/pc/images/only.png" class="topss png_bg" alt="完本图标">
            <#else>
                <img src="${contextPath}/themes/${themeName}/pc/images/only2.png" class="topss png_bg"  alt="连载中图标">
            </#if>
            <a href="${article.url}">${article.articlename}</a></li>
            </#list>
        </ul>
        </#if>
        <#if randomRecommendArticleList ?? > 
        <ul id="content2" style="display:none;">
            <#list randomRecommendArticleList as article>
           <li><a href="${article.url}" title="${article.articlename}"><img src="${article.imgUrl}" width="111px;" height="146px;" alt="${article.articlename}"></a>
            <#if article.fullflag>
                <img src="${contextPath}/themes/${themeName}/pc/images/only.png" class="topss png_bg" alt="完本图标">
            <#else>
                <img src="${contextPath}/themes/${themeName}/pc/images/only2.png" class="topss png_bg"  alt="连载中图标">
            </#if>
            <a href="${article.url}">${article.articlename}</a></li>
            </#list>
        </ul>
        </#if>
    </div>
    </div>
</div>
<script language="JavaScript" type="text/JavaScript"> 

</script>
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
