<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/content/commom/header.jsp" />
<div id="container">
    <p class="b-all-switch normal">网站地图:</p>
    <div class="mainLink">
        <s:iterator value="articleList" id="article">
            <a class="poptext" href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>" title="<s:property  value="#article.articlename" />最新章节" class="f14"><s:property value="#article.articlename" /></a>
        </s:iterator>
    </div>
    <jsp:include page="/WEB-INF/content/commom/footer.jsp" />
</div>
</body>
</html>