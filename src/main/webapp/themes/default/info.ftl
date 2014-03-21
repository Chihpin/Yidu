<#include "common.ftl"/>

<#macro titleContent>
<title>${article.articlename}|${article.articlename}最新章节|${article.articlename}TXT下载</title>
<meta name="keywords" content="${article.articlename},${article.articlename}最新章节,${article.articlename}TXT下载,${article.articlename}无广告,${getText("label.system.name")}" />
<meta name="description" content="《${article.articlename}》情节跌宕起伏、扣人心弦，是一本情节与文笔俱佳的<#if article.category!=0>${categorymap[article.category?c]}</#if>小说，${getText("label.system.name")}免费提供${article.articlename}最新的清爽干净的文字章节在线阅读!" />
</#macro>

<#macro content>
    <#if adEffective?? && adEffective>
    <script type="text/javascript" src="${contextPath}/ad/info1.js"></script>
    </#if>
    <div class="mainnav"><div class="main-index"> > 
        <a href="${encodeURL("/articleList?category=${article.category}")}" class="c009900">
        ${categorymap[article.category?c]}</a> > 
        ${article.articlename}
    </div>
    <section class="main b-detail">
        <div class="detail">
            <#if article.fullflag><img src="${contextPath}/themes/${themeName}/images/only.png" class="leftso png_bg"><#else><img src="${contextPath}/themes/${themeName}/images/only2.png" class="leftso png_bg"></#if>
            <a href="${encodeURL("/info?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}"  class="l mr11">
                 <img src="${article.imgUrl}" style="width: 120px; height: 150px"/></a>
        <div class="b-info">
          <h1>${article.articlename}</h1>
          <div class="infoDetail">
                <div id="waa" style="height:72px;width:520px;overflow:hidden;">介绍:${article.intro}</div>
                <a href="javascript:void(0)" id="show"  onclick="document.getElementById('waa').style.height='';document.getElementById('hidden').style.display='';document.getElementById('show').style.display='none';" style="float:right;">+ 展开全部</a>
                <a href="javascript:void(0)" id="hidden" style="display:none;float:right;" onclick="document.getElementById('waa').style.height='72px';document.getElementById('hidden').style.display='none';document.getElementById('show').style.display='';">- 收回介绍</a> 
          </div>
          <div class="b-oper">
              <a href="#chapters" class="reader" title="${article.articlename}免费阅读">开始阅读</a>
              <a href="${encodeURL("/user/bookcase!add?articleno=${article.articleno?c}")}" target="_blank" class="mehide" >加入书架</a>
              <a href="${encodeURL("/user/vote?articleno=${article.articleno?c}")}" target="_blank"  id="toupiao"  class="toupiao">给本书投票</a>
              <a href="#" id="toupiaonum" class="meNum">${article.allvote}</a>
              <a href="javascript:;"  class="error">内容报错</a>
          </div>
        </div>

        <div class="scores">
            <h2>评分</h2>
            <p id="fenshuview">10.0<br>
                <img src="${contextPath}/themes/${themeName}/images/s2.png">
                <img src="${contextPath}/themes/${themeName}/images/s2.png">
                <img src="${contextPath}/themes/${themeName}/images/s2.png">
                <img src="${contextPath}/themes/${themeName}/images/s2.png">
                <img src="${contextPath}/themes/${themeName}/images/s2.png">
            </p>
            <div class="sets">
               <div class="scoreSet">
                   <span>
                        <img src="${contextPath}/themes/${themeName}/images/s1.png">
                        <img src="${contextPath}/themes/${themeName}/images/s1.png">
                        <img src="${contextPath}/themes/${themeName}/images/s1.png">
                        <img src="${contextPath}/themes/${themeName}/images/s1.png">
                   </span>
                    <input type="hidden" value="8" id="fenshu" />
                    <input type="hidden" value="7940" id="articleid" />
                    <ul id="xingxing">
                        <li value="10"><img src="/images/s1.png"><img src="/images/s1.png"><img src="/images/s1.png"><img src="/images/s1.png"><img src="/images/s1.png"></li>
                        <li value="2"><img src="${contextPath}/themes/${themeName}/images/s1.png"></li>
                        <li value="4"><img src="${contextPath}/themes/${themeName}/images/s1.png"><img src="${contextPath}/themes/${themeName}/images/s1.png"></li>
                        <li value="6"><img src="${contextPath}/themes/${themeName}/images/s1.png"><img src="${contextPath}/themes/${themeName}/images/s1.png"><img src="${contextPath}/themes/${themeName}/images/s1.png"></li>
                        <li value="8"><img src="${contextPath}/themes/${themeName}/images/s1.png"><img src="${contextPath}/themes/${themeName}/images/s1.png"><img src="${contextPath}/themes/${themeName}/images/s1.png"><img src="${contextPath}/themes/${themeName}/images/s1.png"></li>
                        <li value="10"><img src="${contextPath}/themes/${themeName}/images/s1.png"><img src="${contextPath}/themes/${themeName}/images/s1.png"><img src="${contextPath}/themes/${themeName}/images/s1.png"><img src="${contextPath}/themes/${themeName}/images/s1.png"><img src="${contextPath}/themes/${themeName}/images/s1.png"></li>
                    </ul>
                </div>
                <input type="button" id="submitfenshu" value="确定"/>
           </div>
        </div>
        <div class="clear"></div>
        <div class="author">
            <div class="bookDetail">
                <dl><dt>类别：</dt><dd><a href="${encodeURL("/articleList?category=${article.category}&page=1")}"
                    target="_blank" title="<#if article.category!=0>${categorymap[article.category?c]}</#if>"><#if article.category!=0>${categorymap[article.category?c]}</#if></a></dd></dl>
                <dl><dt>状态：</dt><dd><#if article.fullflag>完结<#else>连载中</#if></dd></dl>
                <dl class="bookso"><dt>作      者：</dt><dd> <a href="${encodeURL("/articleList?author=${article.author}")}">
                    ${article.author}</a></dd></dl>
                <dl><dt>全文长度：</dt><dd>${article.size}字</dd></dl>
                <dl><dt>总点击量：</dt><dd>${article.allvisit}</dd></dl>
                <dl><dt>TXT下载：</dt><dd><a href="${encodeURL("/download?articleno=${article.articleno}")} target="_blank"><font color="red" >全本下载</font></a></dd></dl>
                <dl class="bookNew"><dt>最新章节：</dt>
                    <dd>
                    <a href="${encodeURL("/reader?subdir=${article.subdir?c}&articleno=${article.articleno?c}&chapterno=${article.lastchapterno?c}")}" target="_blank"><#if article.lastchapter?? >${article.lastchapter}</#if></a>
                    <em>更新于:[${article.lastupdate?string("MM-dd HH:mm")}]</em><br />
                    </dd>
                </dl>
            </div>
            <div class="adv"><#if adEffective?? && adEffective><script src="${contextPath}/ad/info2.js" type="text/javascript"></#if></script></div>
            <div class="clear"></div>
        </div>
    </section>
    </div>
    <#if adEffective?? && adEffective>
    <div class="bookNew"><script src="${contextPath}/ad/info3.js" type="text/javascript"></script></div>
    </#if>
    <div class="chapterNum">
        <a name="chapters"></a>
          <ul>
            <h1>章节列表</h1>
            <#list chapterList as chapter>
                <#if chapter.chaptertype == 0>
                    <li>
                       <a href="${encodeURL("/reader?subdir=${article.subdir?c}&articleno=${article.articleno?c}&chapterno=${chapter.chapterno?c}")}" title="${chapter.chaptername}">${chapter.chaptername}</a>
                    </li>
                </#if>
            </#list>
          </ul>
        </div>
    </div>
    <script type="text/javascript" src="${contextPath}/ad/info4.js"></script>
</#macro>

<#macro customizefooter> 
    <div id="full2" style="width:37px; height:22px; position:fixed; left:50%; top:425px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
    <a class="get_top" alt="返回顶部"></a>
    </div>
    
    <div id="full" style="width:37px; height:22px; position:fixed; left:50%; top:562px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
    <a class="get_bottom" alt="跳至页尾"></a>
    </div>
    <script src="${contextPath}/themes/${themeName}/js/news_top.js" type="text/javascript"></script>
</#macro>
