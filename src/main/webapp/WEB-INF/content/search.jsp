<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <jsp:include page="/WEB-INF/content/commom/header.jsp" />
    <div id="container">
       <table class="grid" width="100%" align="center">
        <caption>搜索结果</caption>
          <tr align="center">
            <th width="20%">文章名称</th>
            <th width="40%">最新章节</th>
            <th width="15%">作者</th>
            <th width="9%">字数</th>
            <th width="10%">更新</th>
            <th width="6%">状态</th>
          </tr>
          <s:iterator value="articleList" id="article">
          <tr>
            <td class="even"><a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>"><s:property value="#article.articlename"/></a></td>
            <td class="even"><a href="<s:url value="/reader"  escapeAmp="false"><s:param name="articleno" value="#article.articleno" /><s:param name="chapterno" value="#article.lastchapterno" /></s:url>" target="_blank"> <s:property value="#article.lastchapter"/></a></td>
            <td class="odd"><a href="<s:url value="/articleList" ><s:param name="author" value="#article.author" /></s:url>">
                     <s:property value="#article.author" /></a></td>
            <td class="odd"><s:property value="#article.size" /></td>
            <td class="odd" align="center"><s:date name="#article.lastupdate" format="MM-dd HH:mm" /></td>
            <td class="even" align="center"><s:if test="#article.fullflag">完成</s:if><s:else >连载中</s:else></td>
          </tr>
          </s:iterator>
        </table>
    <div class="mainnav" id="navList">
        <div class="pages">
            <div class="pagelink" id="pagelink">
                <em id="pagestats"><s:property value="pagination.pageNumber" />/<s:property value="pagination.totalPages" /></em>
                <a href="<s:url value="/search" escapeAmp="false"><s:param name="key" value="key" /><s:param name="page" value="1" /></s:url>" class="first">1</a>
                <s:iterator value="pagination.pageNumberList" id="page" status="rowstatus">
                    <s:if test="#rowstatus.index ==0 " >
                        <s:if test="#page > 1">
                           <a href="<s:url value="/search" escapeAmp="false"><s:param name="key" value="key" /><s:param name="page" value="#page-1" /></s:url>" class="prev">&lt;</a>
                        </s:if>
                    </s:if>
                    <s:if test = "#page == pagination.pageNumber ">
                        <strong><s:property value="#page"/></strong>
                    </s:if>
                    <s:else>
                        <a href="<s:url value="/search" escapeAmp="false"><s:param name="key" value="key" /><s:param name="page" value="#page" /></s:url>"><s:property value="#page"/></a>
                    </s:else>
                </s:iterator>
                <s:if test="#page < pagination.totalPages">
                    <a href="<s:url value="/search" escapeAmp="false"><s:param name="key" value="key" /><s:param name="page" value="#page+1" /></s:url>" class="next">&gt;</a>
                </s:if>
                <a href="<s:url value="/search" escapeAmp="false"><s:param name="key" value="key" /><s:param name="page" value="pagination.totalPages" /></s:url>" class="last"><s:property value="pagination.totalPages" /></a>
                <kbd>
                    <input name="page" type="text" size="4" maxlength="6" onkeydown="if(event.keyCode==13){window.location='/search/<s:property value="key" />/'+this.value; return false;}" /></kbd>
             </div>
        </div>
    </div>
    </div>
    <jsp:include page="/WEB-INF/content/commom/footer.jsp" />
  </body>
</html>
