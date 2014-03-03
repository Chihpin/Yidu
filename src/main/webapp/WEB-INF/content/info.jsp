<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <jsp:include page="/WEB-INF/content/commom/header.jsp" />
    <div id="container">
    <script type="text/javascript" src="/ad/info1.js"></script>
    <div class="mainnav"><div class="main-index"> > 
        <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="category" value="article.category" /></s:url>" class="c009900">
        <s:property  value="collections['collectionProperties.article.category'][article.category]" /></a> > 
        <s:property  value="article.articlename" />
    </div>
    <section class="main b-detail">
        <div class="detail">
            <s:if test="#article.fullflag"><img src="/images/only.png" class="leftso png_bg"></s:if><s:else><img src="/images/only2.png" class="leftso png_bg"></s:else>
            <a href="<s:url value="/info" ><s:param name="articleno" value="article.articleno" /></s:url>"  class="l mr11">
                 <img src="<s:property value="article.imgUrl" />" style="width: 120px; height: 150px"/></a>
        <div class="b-info">
          <h1><s:property  value="article.articlename" /></h1>
          <div class="infoDetail">
                <div id="waa" style="height:72px;width:520px;overflow:hidden;">介绍:&nbsp;&nbsp;&nbsp;&nbsp;<s:property  value="article.intro" escape="false"/></div>
                <a href="javascript:void(0)" id="show"  onclick="document.getElementById('waa').style.height='';document.getElementById('hidden').style.display='';document.getElementById('show').style.display='none';" style="float:right;">+ 展开全部</a>
                <a href="javascript:void(0)" id="hidden" style="display:none;float:right;" onclick="document.getElementById('waa').style.height='72px';document.getElementById('hidden').style.display='none';document.getElementById('show').style.display='';">- 收回介绍</a> 
          </div>
          <div class="b-oper">
              <a href="#chapters" class="reader" title="<s:property  value="article.articlename" />免费阅读">开始阅读</a>
              <a href="<s:url value="/bookcase!add" escapeAmp="false"><s:param name="articleno" value="article.articleno" /></s:url>" target="_blank" class="mehide" >加入书架</a>
              <a href="<s:url value="/vote" escapeAmp="false"><s:param name="articleno" value="article.articleno" /></s:url>" target="_blank"  id="toupiao"  class="toupiao">给本书投票</a>
              <a href="#" id="toupiaonum" class="meNum"><s:property  value="article.allvote" /></a>
              <a href="javascript:;"  class="error">内容报错</a>
          </div>
        </div>

        <div class="scores">
            <h2>评分</h2>
            <p id="fenshuview">10.0<br>
                <img src="/images/s2.png">
                <img src="/images/s2.png">
                <img src="/images/s2.png">
                <img src="/images/s2.png">
                <img src="/images/s2.png">
            </p>
            <div class="sets">
               <div class="scoreSet">
                   <span>
                        <img src="/images/s1.png">
                        <img src="/images/s1.png">
                        <img src="/images/s1.png">
                        <img src="/images/s1.png">
                   </span>
                    <input type="hidden" value="8" id="fenshu" />
                    <input type="hidden" value="7940" id="articleid" />
                    <ul id="xingxing">
                        <li value="2"><img src="/images/s1.png"></li>
                        <li value="4"><img src="/images/s1.png"><img src="/images/s1.png"></li>
                        <li value="6"><img src="/images/s1.png"><img src="/images/s1.png"><img src="/images/s1.png"></li>
                        <li value="8"><img src="/images/s1.png"><img src="/images/s1.png"><img src="/images/s1.png"><img src="/images/s1.png"></li>
                        <li value="10"><img src="/images/s1.png"><img src="/images/s1.png"><img src="/images/s1.png"><img src="/images/s1.png"><img src="/images/s1.png"></li>
                    </ul>
                </div>
                <input type="button" id="submitfenshu" value="确定"/>
           </div>
        </div>
        <div class="clear"></div>
        <div class="author">
            <div class="bookDetail">
                <dl><dt>类别：</dt><dd><a href="<s:url value="/articleList" escapeAmp="false" ><s:param name="category" value="article.category" /></s:url>"
                    target="_blank" title="<s:property  value="collections['collectionProperties.article.category'][article.category]" />"><s:property
                            value="collections['collectionProperties.article.category'][article.category]" /></a></dd></dl>
                <dl><dt>状态：</dt><dd>连载中</dd></dl>
                <dl class="bookso"><dt>作      者：</dt><dd> <a href="<s:url value="/articleList" ><s:param name="author" value="article.author" /></s:url>">
                    <s:property value="article.author" /></a></dd></dl>
                <dl><dt>全文长度：</dt><dd><s:property value="article.size" />字</dd></dl>
                <dl><dt>总点击量：</dt><dd>93567</dd></dl>
                <dl><dt>TXT下载：</dt><dd><a href="<s:url value="/download" escapeAmp="false"><s:param name="articleno" value="article.articleno" /></s:url>" target="_blank"><font color="red" >全本下载</font></a></dd></dl>
                <dl class="bookNew"><dt>最新章节：</dt>
                    <dd>
                    <a href="<s:url value="/reader"  escapeAmp="false"><s:param name="articleno" value="article.articleno" /><s:param name="chapterno" value="article.lastchapterno" /></s:url>" target="_blank"><s:property value="article.lastchapter" /></a>
                    <em>更新于:[<s:date name="article.lastupdate" format="yyyy/MM/dd HH:mm" />]</em><br />
                    </dd>
                </dl>
            </div>
            <div class="adv"><script src="/ad/info2.js" type="text/javascript"></script></div>
            <div class="clear"></div>
        </div>
    </div>
    </section>
    </div>
    <div class="bookNew"><script src="/ad/info3.js" type="text/javascript"></script></div>
    <div class="chapterNum">
        <a name="chapters"></a>
          <ul>
            <h1>章节列表</h1>
            <s:iterator value="chapterList" id="chapter">
                <s:if test="#chapter.chaptertype == 0">
                    <li>
                       <a href="<s:url value="/reader"  escapeAmp="false"><s:param name="articleno" value="article.articleno" /><s:param name="chapterno" value="#chapter.chapterno" /></s:url>" title="<s:property value="#chapter.chaptername" />"><s:property value="#chapter.chaptername" /></a>
                    </li>
                </s:if>
            </s:iterator>
          </ul>
        </div>
    </div>
    <script type="text/javascript" src="/ad/info4.js"></script>
 
<jsp:include page="/WEB-INF/content/commom/footer.jsp" />

    <div id="full2" style="width:37px; height:22px; position:fixed; left:50%; top:425px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
    <a class="get_top" alt="返回顶部"></a>
    </div>
    
    <div id="full" style="width:37px; height:22px; position:fixed; left:50%; top:562px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
    <a class="get_bottom" alt="跳至页尾"></a>
    </div>
    <script src="<s:property value="contextPath" />/js/news_top.js" type="text/javascript"></script>
</body>
</html>
