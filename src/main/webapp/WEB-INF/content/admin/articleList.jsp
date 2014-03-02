<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
  <form namespace="/admin" action="articleList" method="post" name="paginationForm">
    <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
    <table align="center">
        <tr>
            <td>
            小说搜索（支持小说名，作者名模糊检索）:
            </td>
            <td>
                <s:textfield name = "key" id= "key" maxLength="30" size = "30"/>
            </td>
            <td>&nbsp;&nbsp;</td>
            <td>
                <input type="submit" id="searchbuttom" value="搜索">
            </td>
        </tr>
    </table>
    
    <br>
    <table class="yidu-table" align="center">
        <colgroup>
            <col width="120px">
            <col width="150px">
            <col width="100px">
            <col width="120px">
            <col width="180px">
            <col width="140px">
            <col width="140px">
        </colgroup>
        <tr>
            <th class="sortable <s:if test="pagination.sortColumn.equals('articleno')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'articleno');"><s:text name="label.admin.article.list.articleno" /></a></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('articlename')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'articlename');"><s:text name="label.admin.article.list.articlename" /></a></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('category')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'category');"><s:text name="label.admin.article.list.category" /></a></th>
            
            <th class="sortable <s:if test="pagination.sortColumn.equals('author')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'author');"><s:text name="label.admin.article.list.author" /></a></th>
            
            <th class="sortable"><s:text name="label.admin.article.list.lastchapter" /></th>
            <th class="sortable"><s:text name="label.admin.article.list.lastupdatetime" /></th>
            <th class="sortable"><s:text name="label.admin.list.operate" /></th>
        </tr>
        <s:iterator value="articleList" id="article" status="rowstatus">
        <s:if test="#rowstatus.even == true">
        <tr class="ac_odd">
        </s:if>
        <s:else>
        <tr>
        </s:else>
            <td><s:property  value="#article.articleno" /></td>
            <td><s:property  value="#article.articlename" /></td>
            <td><s:property  value="collections['collectionProperties.article.category'][#article.category]" /></td>
            <td><s:property  value="#article.author" /></td>
            <td><s:property  value="#article.lastchapter" /></td>
            <td><s:date name="#article.lastupdate" format="yyyy/MM/dd HH:mm" /></td>
            <td>
                <a href="<s:property value="contextPath" />/admin/articleEdit?articleno=<s:property value='#article.articleno' />"><s:text name="label.admin.list.modify" /></a>
                <a href="javascript:confirmDelete('<s:property value="contextPath" />/admin/articleList!delete?articleno=<s:property value='#article.articleno' />')"><s:text name="label.admin.list.delete" /></a>
                <a href="<s:property value="contextPath" />/admin/chapterList?articleno=<s:property value='#article.articleno' />"><s:text name="label.admin.list.chapter" /></a>
            </td>
        </tr>
        </s:iterator>
    </table>
    <table width="950px" align="center">
        <tr>
            <td class="pagination-label" width="100%" nowrap="nowrap">
                    <jsp:include page="/WEB-INF/content/admin/commom/pagination.jsp" />
            </td>
        </tr>
        <tr>
            <td class="pagination-label" width="100%" nowrap="nowrap">
                <a href="<s:property value="contextPath" />/admin/articleEdit"><s:text name="label.admin.list.add" /></a>
            </td>
        </tr>
    </table>
    </div>
     </form>
    <jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>