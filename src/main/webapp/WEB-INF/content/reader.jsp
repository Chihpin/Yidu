<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <jsp:include page="/WEB-INF/content/commom/header.jsp" />
    <link href="<s:property value="contextPath" />/css/readtools.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<s:property value="contextPath" />/js/tools.js"></script>
    <script type="text/javascript" src="<s:property value="contextPath" />/js/lib/jquery.cookie.js"></script>
    <script type="text/javascript" src="<s:property value="contextPath" />/js/style5.js"></script>
    <script type="text/javascript" src="<s:property value="contextPath" />/js/lib/jquery.tools.min1.2.5.js"></script>
    
    <script type="text/javascript">
        <!--
        var preview_page = '<s:if test="chapter.preChapterno ==0 "><s:url value="/info" ><s:param name="articleno" value="chapter.articleno" /></s:url></s:if><s:else><s:url value="/reader"  escapeAmp="false"><s:param name="articleno" value="chapter.articleno" /><s:param name="chapterno" value="chapter.preChapterno" /></s:url></s:else>';
        var next_page = '<s:if test="chapter.nextChapterno ==0 "><s:url value="/info" ><s:param name="articleno" value="chapter.articleno" /></s:url></s:if><s:else><s:url value="/reader"  escapeAmp="false"><s:param name="articleno" value="chapter.articleno" /><s:param name="chapterno" value="chapter.nextChapterno" /></s:url></s:else>';
        var index_page = '<s:url value="/info" ><s:param name="articleno" value="chapter.articleno" /></s:url>';
        var article_id = '<s:property value="chapter.articleno"/>';
        var chapter_id = '<s:property value="chapter.chapterno"/>';
        function jumpPage() {
          var event = document.all ? window.event : arguments[0];
          if (event.keyCode == 37) document.location = preview_page;
          if (event.keyCode == 39) document.location = next_page;
        }
        document.onkeydown=jumpPage;
        -->
</script>
<div id="container">
    <div class="mainnav">
        <div class="main-index" id="direct">
            <span class="r mr10"> 
                <form action="/search" method="get" >
                    搜小说：<input type="text" name="key" value="" />
                    <button type="submit" >搜</button>
                </form>
            </span>
    位置：<a href="/" class="c009900"><s:text name="label.system.name" /></a> > 
    <a href="<s:url value="/info" ><s:param name="articleno" value="chapter.articleno" /></s:url>" class="article_title"><s:property value="chapter.articlename"/></a>  > 
    <s:property value="chapter.chaptername"/></div>
    <section class="main b-detail" id="directs">
        <div class="bookInfo">
            <h1>
                <span class="r"></span>
                <em class="l">《<s:property value="chapter.articlename"/>》</em>
                <strong class="l jieqi_title"><s:property value="chapter.chaptername"/></strong>
                <a href="<s:url value="/bookcase!add" escapeAmp="false"><s:param name="articleno" value="chapter.articleno" /><s:param name="chapterno" value="chapter.chapterno" /></s:url>"  target="_blank" title="加入书签" class="l">加入书签</a>
                <a href="<s:url value="/vote" ><s:param name="articleno" value="chapter.articleno" /></s:url>"  target="_blank" title="推荐本书" class="l">推荐本书</a>
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
        <script type="text/javascript" src="/ad/reader1.js"></script>
        <div class="mainContenr"   id="content">
            <s:property value="chapter.content" escape="false" />
        </div>
        <script type="text/javascript" src="/ad/reader2.js"></script>
        <div class="backs">
            <a href="<s:if test="chapter.preChapterno ==0 "><s:url value="/info" ><s:param name="articleno" value="chapter.articleno" /></s:url></s:if><s:else><s:url value="/reader"  escapeAmp="false"><s:param name="articleno" value="chapter.articleno" /><s:param name="chapterno" value="chapter.preChapterno" /></s:url></s:else>" class="pre">上一章</a>
            <a href="<s:url value="/info" ><s:param name="articleno" value="chapter.articleno" /></s:url>" class="backfor">返回目录</a>
            <a href="<s:if test="chapter.nextChapterno ==0 "><s:url value="/info" ><s:param name="articleno" value="chapter.articleno" /></s:url></s:if><s:else><s:url value="/reader"  escapeAmp="false"><s:param name="articleno" value="chapter.articleno" /><s:param name="chapterno" value="chapter.nextChapterno" /></s:url></s:else>" class="next">下一章</a>
            <p>小提示： 按←键返回上一页，按→键进入上一页,您还可以
                 <a href="/modules/article/addbookcase.php?bid=17664&cid=9608537" title="加入书签"  target="_blank">加入书签</a>
            </p></div>
        <script type="text/javascript" src="/ad/reader3.js"></script>
        </div>
       </section>
       <div class="attention">
            <em>阅读提示：</em><br/>
            1、本站会员登录后，将免费体会到最顺畅的阅读方式[<em>最少广告</em>]。<br/>
            2、<em>注册本站会员</em>，将《<a href="<s:url value="/info" ><s:param name="articleno" value="chapter.articleno" /></s:url>" class="article_title"><em><s:property value="chapter.articlename"/></em></a>》加入书架，可以通过书架更快的了解更新信息。<br/>
            3、免费小说《<a href="<s:url value="/info" ><s:param name="articleno" value="chapter.articleno" /></s:url>" class="article_title"><em><s:property value="chapter.articlename"/></em></a>》 <s:property value="chapter.chaptername"/> 所描述的内容只是作者 作者个人观点，与本站的立场无关，本站只为广大用户提供阅读平台。
        </div>
    </div>
</div>
<script language="JavaScript" type="text/JavaScript"> 

    window.onload = function() {
        var str = document.getElementById("content").innerHTML;//这里是整个页面代码 ,也可以指定id
        str = str.replace(/\<script[\s\S]+?\<\/script\>/gi, "");
        str = str.replace(/\<styltyp[\s\S]+?\<\/styl\>/gi, "");
        str = str.replace(/\<style[\s\S]+?\<\/style\>/gi, "");
        str = str.replace(/\<a[\s\S].+?\<\/a\>/gi, "");
        str = str.replace(/Www.+?ggyy\.net/gi, "");
        str = str.replace(/Www.+?Com/gi, "");
        str = str.replace(/Www.+?net/gi, "");
        str = str.replace(/Www.+?cc/gi, "");
        str = str.replace(/&lt;br.+?&gt;/gi, "<br />");
        str = str.replace(/&amp;hllp;/gi, "&hellip;").replace(/&amp;ldqo;/gi,
                "&ldquo;").replace(/ldqo/gi, "ldquo").replace(/&amp;rdqo;/gi,
                "&rdquo;").replace(/&amp;dash;/gi, "&mdash;");
        document.getElementById("content").innerHTML = str;
    }
</script>
<jsp:include page="/WEB-INF/content/commom/footer.jsp" />
    <div id="full2" style="width:37px; height:22px; position:fixed; left:50%; top:425px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
    <a class="get_top" alt="返回顶部"></a>
    </div>
    
    <div id="full" style="width:37px; height:22px; position:fixed; left:50%; top:562px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
    <a class="get_bottom" alt="跳至页尾"></a>
    </div>
    <script src="<s:property value="contextPath" />/js/news_top.js" type="text/javascript"></script>

</body></html>
