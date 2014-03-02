<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
    <s:form namespace="/admin" action="articleEdit" method="post" validate="true">
        <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
        <s:hidden name="articleno" />
        <br>
        <table class="yidu-table" align="center">
            <colgroup>
                <col width="120px">
                <col width="580px">
            </colgroup>
            <tbody>
                <tr>
                    <th colspan="2"><s:text name="label.admin.article.edit.title" /></th>
                </tr>
                <s:if test="articleno!=0">
                <tr>
                    <td><s:text name="label.admin.article.edit.articleno" /></td>
                    <td>
                        <s:property value="articleno" />
                    </td>
                </tr>
                </s:if>
                <tr>
                    <td><s:text name="label.admin.article.edit.articlename" /></td>
                    <td>
                        <s:textfield name="articlename" id="articlename" maxlength="32" cssClass="tb-rounded" />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.category" /></td>
                    <td>
                        <s:select
                            id="category"
                            name="category"
                            list="collections['collectionProperties.article.category']"
                            />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.author" /></td>
                    <td>
                        <s:textfield id="author" name="author" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.intro" /></td>
                    <td>
                        <s:textarea id="intro" name="intro" cssClass="tb-rounded"  cols="30" rows="5"/> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.keywords" /></td>
                    <td>
                        <s:textfield id="keywords" name="keywords" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.fullflag" /></td>
                    <td>
                        <s:checkbox name="fullflag" value="fullflag"/>
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.article.edit.postdate" /></td>
                    <td>
                        <s:textfield id="postdateStr" name="postdateStr" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                
                <tr>
                    <td colspan="2" style="text-align: center; padding: 2px">
                        <s:submit name="submit" value="%{getText('label.admin.edit.back')}" method="back" cssClass="submitButton" theme = "simple" onclick="goback()"/>
                        <s:if test="articleno==0">
                            <s:submit name="submit" value="%{getText('label.admin.edit.add')}" method="save" cssClass="submitButton" theme = "simple"/>
                        </s:if>
                        <s:else>
                            <s:submit name="submit" value="%{getText('label.admin.edit.modify')}" method="save" cssClass="submitButton" theme = "simple"/>
                        </s:else>
                    </td>
                </tr>
            </tbody>
        </table>
    </s:form>
    </div>
    <jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>
