<#include "common.ftl"/>

<#macro titleContent>
<title>${article.articlename}|${article.articlename}最新章节|${article.articlename}TXT下载</title>
<meta name="keywords" content="${article.articlename},${article.articlename}最新章节,${article.articlename}TXT下载,${article.articlename}无广告,${getText("label.system.name")}" />
<meta name="description" content="《${article.articlename}》情节跌宕起伏、扣人心弦，是一本情节与文笔俱佳的<#if article.category!=0>${categorymap[article.category?c]}</#if>小说，${getText("label.system.name")}免费提供${article.articlename}最新的清爽干净的文字章节在线阅读!" />
  <!--360结构化-->
   <meta property="og:type" content="novel"/>
   <meta property="og:title" content="${article.articlename?html}"/>
   <meta property="og:description" content="<#if (article.intro ?length != 0)>${article.intro?html}<#else>暂无简介</#if>"/>
   <meta property="og:image" content="${article.imgUrl}"/>
   <meta property="og:novel:category" content="<#if article.category!=0>${categorymap[article.category?c]}</#if>"/>
   <meta property="og:novel:author" content="${article.author?html}"/>
   <meta property="og:novel:book_name" content="${article.articlename?html}"/>
   <meta property="og:novel:read_url" content="${article.url}"/>

   <!--选填-->
   <meta property="og:novel:status" content="<#if article.fullflag>完结<#else>连载中</#if>"/>
   <meta property="og:novel:update_time" content="${article.lastupdate?string("yyyy-MM-dd HH:mm")}"/>
   <meta property="og:novel:click_cnt" content="${article.allvisit?c}"/>
   <meta property="og:novel:latest_chapter_name" content="${article.lastchapter}"/>
   <meta property="og:novel:latest_chapter_url" content="${article.lastChapterUrl}"/>

</#macro>

<#macro customizeimport>  
<script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/review.js"></script>
</#macro>

<#macro content>
    <#if adEffective?? && adEffective>
    <script type="text/javascript" src="${contextPath}/ad/info1.js"></script>
    </#if>
    <div class="mainnav"><div class="main-index"> 位置：  &nbsp; > &nbsp; 
        <a href="${encodeURL("/articleList?category=${article.category}")}" class="c009900">
        ${categorymap[article.category?c]}</a> &nbsp; > &nbsp; 
        ${article.articlename}
    </div>
    <section class="main b-detail">
        <div class="detail">
            <#if article.fullflag><img src="${contextPath}/themes/${themeName}/pc/images/only.png" class="leftso png_bg" alt="完本图标"><#else><img src="${contextPath}/themes/${themeName}/pc/images/only2.png" class="leftso png_bg"  alt="连载中图标"></#if>
            <a href="${encodeURL("/info?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}"  class="l mr11">
                 <img src="<#if article.imgUrl ?? >${article.imgUrl}</#if>" style="width: 120px; height: 150px" alt="${article.articlename}"/></a>
        <div class="b-info">
          <h1>${article.articlename}</h1>
          <div class="infoDetail">
                <div id="waa" style="height:72px;width:520px;overflow:hidden;">介绍:${article.introForHtml}</div>
                <a href="javascript:void(0)" id="show"  onclick="document.getElementById('waa').style.height='';document.getElementById('hidden').style.display='';document.getElementById('show').style.display='none';" style="float:right;">+ 展开全部</a>
                <a href="javascript:void(0)" id="hidden" style="display:none;float:right;" onclick="document.getElementById('waa').style.height='72px';document.getElementById('hidden').style.display='none';document.getElementById('show').style.display='';">- 收回介绍</a> 
          </div>
          <div class="b-oper">
              <a href="<#if !enableChapterIndexPage >#chapters<#else>${article.chapterListUrl}</#if>" class="reader" title="${article.articlename}免费阅读">开始阅读</a>
              <a href="${encodeURL("/user/bookcase!add?articleno=${article.articleno?c}")}" target="_blank" class="mehide" rel="nofollow" >加入书架</a>
              <a href="${encodeURL("/user/subscribe!add?articleno=${article.articleno?c}")}" target="_blank" class="subscribe" rel="nofollow" >订阅本书</a>
              <a href="${encodeURL("/user/vote?articleno=${article.articleno?c}")}" target="_blank"  id="toupiao"  class="toupiao" rel="nofollow">给本书投票</a>
              <a href="/user/messageEdit?title=${article.articlename}-章节错误&content=举报原因如下：  "  class="error" target="_blank" rel="nofollow">内容报错</a>
          </div>
        </div>

        <div class="scores">
            <h2>评分</h2>
            <p id="fenshuview">10.0<br>
                <img src="${contextPath}/themes/${themeName}/pc/images/s2.png" alt="评分图标">
                <img src="${contextPath}/themes/${themeName}/pc/images/s2.png" alt="评分图标">
                <img src="${contextPath}/themes/${themeName}/pc/images/s2.png" alt="评分图标">
                <img src="${contextPath}/themes/${themeName}/pc/images/s2.png" alt="评分图标">
                <img src="${contextPath}/themes/${themeName}/pc/images/s2.png" alt="评分图标">
            </p>
            <div class="sets">
               <div class="scoreSet">
                   <span>
                        <img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标">
                        <img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标">
                        <img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标">
                        <img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标">
                   </span>
                    <input type="hidden" value="8" id="fenshu" />
                    <input type="hidden" value="7940" id="articleid" />
                    <ul id="xingxing">
                        <li value="10"><img src="/images/s1.png" alt="评分图标"><img src="/images/s1.png" alt="评分图标"><img src="/images/s1.png" alt="评分图标"><img src="/images/s1.png" alt="评分图标"><img src="/images/s1.png" alt="评分图标"></li>
                        <li value="2"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"></li>
                        <li value="4"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"></li>
                        <li value="6"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"></li>
                        <li value="8"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"></li>
                        <li value="10"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"><img src="${contextPath}/themes/${themeName}/pc/images/s1.png" alt="评分图标"></li>
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
                <dl><dt>全文长度：</dt><dd><#if article.size ??>${article.size}<#else>0</#if>字</dd></dl>
                <dl><dt>总点击量：</dt><dd>${article.allvisit}</dd></dl>
                <dl><dt>总推荐量：</dt><dd>${article.allvote}</dd></dl>
                <dl><dt>TXT下载：</dt><dd><#if loginFlag><a href="${article.downloadUrl}" target="_blank"><font color="red" >全本下载</font></a><#else><font color="red" >不好意思，登录后才可以下载：）</#if></font></dd></dl>
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
    
    <div class="clear"></div>
    <div class="comment_left">
        <div class="commenthead">
            <div class="ti">
                <h2>《${article.articlename}》的评论</h2>
                <div class="par">共有评论<a target="blank" href="${encodeURL("/reviewList?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}">${reviewCount?c}</a>条</div>
                <div class="par2"><a target="_blank" href="${encodeURL("/reviewList?subdir=${article.subdir?c}&articleno=${article.articleno?c}")}">[全部评论]</a></div>
            </div>
        </div>
        <ul class="commentslist">
            <#list reviewList as review>
                <li class="line">
                <div class="has_avatar">
                    <a target="_blank" class="a_avatar50" href="${encodeURL("/userInfo?userno=${review.userno?c}")}"><img width="50" height="50" alt="${review.loginid}" src="${contextPath}/themes/${themeName}/pc/images/90_avatar_middle.jpg"></a>
                </div>
                <div class="replycontent">
                    <div class="t_t">
                        <div>
                            <a target="_blank" title="${review.loginid}" class="commenter" href="${encodeURL("/userInfo?userno=${review.userno?c}")}">${review.loginid}</a>
                            <span class="time">评论于：${review.postdate?string("yyyy-MM-dd HH:mm")}</span>
                        </div>
                    </div>
                    <ul class="Reviewer">
                        <li class="txt">${review.review?html}
                        </li>
                    </ul>
                  </div>
                </li>
            </#list>
        </ul>
        <div class="blank"></div>
        <!-- 我的回复框 -->
        <div id="commentbox" class="talker_form">
            <#if !loginFlag>
            <div class="logintip">您还未登录，请登录或注册后再发表回复</div>
            </#if>
            <div class="form_t"> 评论内容： </div>
            <div class="form_b">
                <div class="smtextarea">
                    <textarea class="comment_content" onkeyup="stat_text_word(this);" rows="5" cols="60" name="review" id="review">既然来了，就留下几句话吧</textarea>
                    <input type="hidden" value="${articleno?c}" id="articleno" name="articleno">
                    <input type="hidden" value="false" name="isFromForm" id="isFromForm" >
                </div>
                <div class="form_f">
                    <div class="box_l"> 
                        <div>已输入字数：<span id="comment_text_word">0</span>  (评论最少5字最多500字) </div>
                    </div>
                    <input type="submit" class="release_btn submit_comment_btn" value=" " id= "submitbtn" name="submitbtn">
                </div>
            </div>
        </div>
    </div>
    <#if adEffective?? && adEffective>
    <div class="bookNew"><script src="${contextPath}/ad/info4.js" type="text/javascript"></script></div>
    </#if>

    <#if !enableChapterIndexPage >
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
    <script type="text/javascript" src="${contextPath}/ad/info5.js"></script>
    </#if>
    </#if>
    
    
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
