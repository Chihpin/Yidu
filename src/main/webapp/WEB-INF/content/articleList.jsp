<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <jsp:include page="/WEB-INF/content/commom/header.jsp" />
    <div id="container">
    <script type="text/javascript" src="<s:property value="contextPath" />/ad/list1.js"></script>
    <script>
    $(document).ready(function(){
        $("#sort").change(function(){
            var paixu = $(this).val();
            var url = window.location.href;
            location.href=url+"&sort="+paixu;
        })
    })
    </script>
<div class="mainnav" id="navList"><div class="main-index">
   
位置： <a href="#"><s:if test="fullflag" >全本小说</s:if><s:elseif test="category!=null"><s:property  value="collections['collectionProperties.article.category'][category]" /></s:elseif><s:else><s:property value="author" />的小说</s:else></a></div>
    <section class="section board-list board-list-collapse">
    
    <ul class="seeWell cf">
	    <s:iterator value="articleList" id="article">
	    <li>
	       <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>"  title="<s:property  value="#article.articlename" />" class="l mr10">
	            <img src="<s:property value="#article.imgUrl" />" style="width: 120px; height: 150px"/></a>
	       <s:if test="#article.fullflag"><img src="/images/only.png" class="topss png_bg"></s:if><s:else><img src="/images/only2.png" class="topss png_bg"></s:else>
	       <span class="l">
	          <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>"  title="<s:property  value="#article.articlename" />" class="clearfix stitle"><s:property  value="#article.articlename" /></a>
	          作者：<a href="<s:url value="/articleList" ><s:param name="author" value="#article.author" /></s:url>"><s:property value="#article.author" /></a>
	          <em class="c999 clearfix"><s:property  value="#article.intro" /></em>
	          更新：<a href="<s:url value="/reader" escapeAmp="false"><s:param name="articleno" value="#article.articleno" /><s:param name="chapterno" value="#article.lastchapterno" /></s:url>"  title="<s:property value="#article.lastchapter" />"><s:property value="#article.lastchapterOmit" /></a>
	          <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>" class="readTo"  title="<s:property  value="#article.articlename" />">马上阅读</a>
	       </span>
	    </li>
	    </s:iterator>
	</ul>

      <script type="text/javascript" src="<s:property value="contextPath" />/ad/list2.js"></script>
      <div class="pages">
            <div class="pagelink" id="pagelink">
            <s:if test="fullflag">
                <em id="pagestats"><s:property value="pagination.pageNumber" />/<s:property value="pagination.totalPages" /></em>
                <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="fullflag" value="true" /><s:param name="page" value="1" /></s:url>" class="first">1</a>
                <s:iterator value="pagination.pageNumberList" id="page" status="rowstatus">
                    <s:if test="#rowstatus.index ==0 " >
                        <s:if test="#page > 1">
                           <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="fullflag" value="true" /><s:param name="page" value="#page-1" /></s:url>" class="prev">&lt;</a>
                        </s:if>
                    </s:if>
                    <s:if test = "#page == pagination.pageNumber ">
                        <strong><s:property value="#page"/></strong>
                    </s:if>
                    <s:else>
                        <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="fullflag" value="true" /><s:param name="page" value="#page" /></s:url>"><s:property value="#page"/></a>
                    </s:else>
                </s:iterator>
                <s:if test="#page < pagination.totalPages">
                    <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="fullflag" value="true" /><s:param name="page" value="#page+1" /></s:url>" class="next">&gt;</a>
                </s:if>
                <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="fullflag" value="true" /><s:param name="page" value="pagination.totalPages" /></s:url>" class="last"><s:property value="pagination.totalPages" /></a>
                <kbd>
                    <input name="page" type="text" size="4" maxlength="6" onkeydown="if(event.keyCode==13){window.location='/wanben/'+this.value; return false;}" /></kbd>
            </s:if>
            <s:elseif test="category!=null">
                <em id="pagestats"><s:property value="pagination.pageNumber" />/<s:property value="pagination.totalPages" /></em>
                <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="category" value="category" /><s:param name="page" value="1" /></s:url>" class="first">1</a>
                <s:iterator value="pagination.pageNumberList" id="page" status="rowstatus">
                    <s:if test="#rowstatus.index ==0 " >
                        <s:if test="#page > 1">
                           <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="category" value="category" /><s:param name="page" value="#page-1" /></s:url>" class="prev">&lt;</a>
                        </s:if>
                    </s:if>
                    <s:if test = "#page == pagination.pageNumber ">
                        <strong><s:property value="#page"/></strong>
                    </s:if>
                    <s:else>
                        <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="category" value="category" /><s:param name="page" value="#page" /></s:url>"><s:property value="#page"/></a>
                    </s:else>
                </s:iterator>
                <s:if test="#page < pagination.totalPages">
                    <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="category" value="category" /><s:param name="page" value="#page+1" /></s:url>" class="next">&gt;</a>
                </s:if>
                <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="category" value="category" /><s:param name="page" value="pagination.totalPages" /></s:url>" class="last"><s:property value="pagination.totalPages" /></a>
                <kbd>
                    <input name="page" type="text" size="4" maxlength="6" onkeydown="if(event.keyCode==13){window.location='/list/<s:property value="category" />/'+this.value; return false;}" /></kbd>
             </s:elseif>
             <s:elseif test="!author.equals('')">
                <em id="pagestats"><s:property value="pagination.pageNumber" />/<s:property value="pagination.totalPages" /></em>
                <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="author" value="author" /><s:param name="page" value="1" /></s:url>" class="first">1</a>
                <s:iterator value="pagination.pageNumberList" id="page" status="rowstatus">
                    <s:if test="#rowstatus.index ==0 " >
                        <s:if test="#page > 1">
                           <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="category" value="category" /><s:param name="page" value="#page-1" /></s:url>" class="prev">&lt;</a>
                        </s:if>
                    </s:if>
                    <s:if test = "#page == pagination.pageNumber ">
                        <strong><s:property value="#page"/></strong>
                    </s:if>
                    <s:else>
                        <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="author" value="author" /><s:param name="page" value="#page" /></s:url>"><s:property value="#page"/></a>
                    </s:else>
                </s:iterator>
                <s:if test="#page < pagination.totalPages">
                    <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="author" value="author" /><s:param name="page" value="#page+1" /></s:url>" class="next">&gt;</a>
                </s:if>
                <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="author" value="author" /><s:param name="page" value="pagination.totalPages" /></s:url>" class="last"><s:property value="pagination.totalPages" /></a>
                <kbd>
                    <input name="page" type="text" size="4" maxlength="6" onkeydown="if(event.keyCode==13){window.location='/list/<s:property value="author" />/'+this.value; return false;}" /></kbd>
             </s:elseif>
             </div>
        </div>
    </section>
  </div>
  <script type="text/javascript" src="<s:property value="contextPath" />/ad/list3.js"></script>
  </div>
  <jsp:include page="/WEB-INF/content/commom/footer.jsp" />
  </body>
</html>
