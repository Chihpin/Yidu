<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
  <form namespace="/admin" action="articleList" method="post" name="paginationForm">
    <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
    <s:hidden name="category"></s:hidden>
	<div id="channel-header" class="clearfix">
	    <div class="channel-header-wrapper">
	      <nav class="channel-nav">
	        <ul class="channel-nav-list">
	            <li <s:if test="category==0">class="current"</s:if> ><a href="<s:url value="/admin/articleList" ></s:url>"><s:text name="label.admin.article.list.allarticle"/></a></li>
	            <s:iterator value="collections['collectionProperties.article.category']" id="categoryinfo">
	                <li <s:if test="key==category">class="current"</s:if> ><a href="<s:url value="/admin/articleList" escapeAmp="false"><s:param name="category" value="key" /></s:url>"><s:property value="value"/></a></li>
	            </s:iterator>
	        </ul>
	      </nav>
	    </div>
	</div>
	<table align="center">
        <tr>
            <td>
            小说搜索（支持小说名，作者名模糊检索）
            </td>
            <td>&nbsp;&nbsp;</td>
            <td>
                <s:textfield name = "key" id= "key" maxLength="30" size = "30"/>
            </td>
            <td>&nbsp;&nbsp;</td>
            <td>
                <input type="submit" id="searchbuttom" value="搜索">
            </td>
            <td>&nbsp;&nbsp;</td>
            <td>待审文章 | 已审文章 | 隐藏文章 | 冷门文章 | 空文章
            </td>
        </tr>
    </table>
    <table align="center">
    	<tr>
    		<td style="width:180px;">
	    	<s:select list="#{1:'删除'}" listKey="key" listValue="value" style="width:90%;"
	    			headerKey="" headerValue="选择操作" id="operate"></s:select>
	    	</td>
	    	<td><input type="button" name="oper_apply" id="oper_apply" value="应用"/></td>
	    	<td style="width:180px;"></td>
	    	<td style="width:180px;"></td>
	    	<td style="width:180px;"></td>
	    	<td style="width:180px;"></td>
    	</tr>
    </table>
    <table class="yidu-table" align="center">
        <colgroup>
        	<col width="30px">
            <col width="150px">
            <col width="100px">
            <col width="120px">
            <col width="200px">
            <col width="120px">
            <col width="230px">
        </colgroup>
        <tr>
        	<th><input type="checkbox" id="selectAll" /></th>
        
            <th class="sortable <s:if test="pagination.sortColumn.equals('articlename')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'articlename');"><s:text name="label.admin.article.list.articlename" /></a></th>

            <th class="sortable <s:if test="pagination.sortColumn.equals('category')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'category');"><s:text name="label.admin.article.list.category" /></a></th>

            <th class="sortable <s:if test="pagination.sortColumn.equals('author')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'author');"><s:text name="label.admin.article.list.author" /></a></th>

            <th class="sortable"><s:text name="label.admin.article.list.lastchapter" /></th>

            <th class="sortable <s:if test="pagination.sortColumn.equals('lastupdate')">sorted <s:property value="pagination.sortClass"/> </s:if>">
            <a href="#" onclick="fnPagination(6,'lastupdate');"><s:text name="label.admin.article.list.lastupdate" /></a></th>

            <th class="sortable"><s:text name="label.admin.list.operate" /></th>
        </tr>
        <s:iterator value="articleList" id="article" status="rowstatus">
        <s:if test="#rowstatus.even == true">
        <tr class="ac_odd">
        </s:if>
        <s:else>
        <tr>
        </s:else>
        	<td><s:checkbox name="article_articleno" fieldValue="%{#article.articleno }"/> </td>
            <td><s:property value="#article.articlename" /></td>
            <td><s:property value="collections['collectionProperties.article.category'][#article.category]" /></td>
            <td><s:property value="#article.author" /></td>
            <td><s:property value="#article.lastchapter" /></td>
            <td><s:date name="#article.lastupdate" format="yyyy/MM/dd HH:mm" /></td>
            <td>待审 推荐/不荐
                <a href="<s:property value="contextPath" />/admin/articleEdit?articleno=<s:property value='#article.articleno' />"><s:text name="label.admin.list.modify" /></a>
                <a href="javascript:confirmDelete('<s:property value="contextPath" />/admin/articleList!delete?articleno=<s:property value='#article.articleno' />')"><s:text name="label.admin.list.delete" /></a>
                <a href="<s:property value="contextPath" />/admin/chapterList?articleno=<s:property value='#article.articleno' />"><s:text name="label.admin.article.list.chapter" /></a>
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