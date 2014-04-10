<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
    <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
    <s:hidden name="articleno"/>
    <br>
    <table class="yidu-table" align="center">
        <colgroup>
            <col width="240px">
            <col width="240px">
            <col width="240px">
            <col width="240px">
        </colgroup>
        <tr>
            <td class="ac_odd" colspan="4">《<s:property value= "article.articlename"/>》-<s:property value= "article.author"/> 
            &nbsp;&nbsp;&nbsp;[新建分卷] [增加章节] [编辑文章] [删除文章] [清空文章] [管理书评]  [<a href="<s:property value="contextPath" />/admin/chapterEdit?articleno=<s:property value="article.articleno" />"><s:text name="label.admin.list.add" /></a>]
		   <input type="hidden" name="articleid" id="articleid" value="45911">
		   <input type="button" name="allcheck" value="选择全部章节" class="button" onclick="javascript: for (var i=0;i&lt;this.form.elements.length;i++){ this.form.elements[i].checked = true; }">&nbsp;&nbsp;<input type="button" name="nocheck" value="取消全部选中" class="button" onclick="javascript: for (var i=0;i&lt;this.form.elements.length;i++){ this.form.elements[i].checked = false; }">&nbsp;&nbsp;<input type="button" name="delcheck" value="批量删除章节" class="button" onclick="javascript:if(confirm('确实要批量删除章节么？')){this.form.submit();}">
        </td>
        </tr>
        <s:iterator value="chapterList" id="chapter" status="rowstatus">
        <s:if test="#rowstatus.index % 4 ==0 ">
        <s:if test="#rowstatus.index / 4 % 2 == 1">
        <tr class="ac_odd">
        </s:if>
        <s:else>
        <tr>
        </s:else>
        </s:if>
        <td>
        <a href="<s:property value="contextPath" />/admin/chapterEdit?chapterno=<s:property value='#chapter.chapterno' />"><s:property  value="#chapter.chaptername" /></a>
        <a href="javascript:confirmDelete('<s:property value="contextPath" />/admin/chapterList!delete?chapterno=<s:property value='#chapter.chapterno' />')" style="color:red"><s:text name="label.admin.list.delete" /></a>
        </td>
        <s:if test="#rowstatus.index % 4 ==3 ">
        </tr>
        </s:if>
        </s:iterator>
    </table>
    </div>
    <jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>