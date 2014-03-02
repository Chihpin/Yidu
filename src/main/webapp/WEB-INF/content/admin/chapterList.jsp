<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
    <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
    <br>
    <table class="yidu-table" align="center">
        <colgroup>
            <col width="120px">
            <col width="140px">
            <col width="300px">
            <col width="140px">
            <col width="100px">
        </colgroup>
        <tr>
            <th class="sortable"><s:text name="label.admin.chapter.list.chapterno" /></th>
            <th class="sortable"><s:text name="label.admin.chapter.list.articlename" /></th>
            <th class="sortable"><s:text name="label.admin.chapter.list.chaptername" /></th>
            <th class="sortable"><s:text name="label.admin.chapter.list.postdate" /></th>
            <th class="sortable"><s:text name="label.admin.list.operate" /></th>
        </tr>
        <s:iterator value="chapterList" id="chapter" status="rowstatus">
        <s:if test="#rowstatus.even == true">
        <tr class="ac_odd">
        </s:if>
        <s:else>
        <tr>
        </s:else>
            <td><s:property  value="#chapter.chapterno" /></td>
            <td><s:property  value="#chapter.articlename" /></td>
            <td><s:property  value="#chapter.chaptername" /></td>
            <td><s:date name="#chapter.postdate" format="yyyy/MM/dd HH:mm" /></td>
            <td>
                <a href="<s:property value="contextPath" />/admin/chapterEdit?chapterno=<s:property value='#chapter.chapterno' />"><s:text name="label.admin.list.modify" /></a>
                <a href="javascript:confirmDelete('<s:property value="contextPath" />/admin/chapterList!delete?chapterno=<s:property value='#chapter.chapterno' />')"><s:text name="label.admin.list.delete" /></a>
            </td>
        </tr>
        </s:iterator>
    </table>
    <table width="800px" align="center">
        <tr>
            <td class="pagination-label" width="100%" nowrap="nowrap">
                <a href="<s:property value="contextPath" />/admin/chapterEdit"><s:text name="label.admin.list.add" /></a>
            </td>
        </tr>
    </table>
    </div>
    <jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>