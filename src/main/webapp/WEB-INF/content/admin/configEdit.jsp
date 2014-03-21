<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
<s:form namespace="/admin" action="configEdit" method="post" validate="true">
        <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
<table class="grid" align="center" cellspacing="1" width="800">
<caption><s:text name="label.admin.config.edit.caption1" /></caption>
<tbody>
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.config.edit.filePath" /></td>
       <td class="even"><s:textfield name ="filePath" size="40" maxlength="100" cssClass="text" theme="simple"/><span class="hottext">填写绝对路径</span></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.config.edit.relativeIamgePath" /></td>
       <td class="even"><s:textfield name ="relativeIamgePath" size="25" maxlength="100"  cssClass="text" theme="simple"/><span class="hottext">请填写相对路径</span></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.config.edit.themeName" /></td>
       <td class="even"><s:textfield name ="themeName" size="25" maxlength="30"  cssClass="text" theme="simple"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.config.edit.countPerPage" /></td>
       <td class="even"><s:textfield name ="countPerPage" size="25" maxlength="100"  cssClass="text"/></td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.config.edit.maxBookcase" /></td>
       <td class="even"><s:textfield name ="maxBookcase" size="25" maxlength="100"  cssClass="text"/></td>
    </tr>
</tbody>
</table>
<br />
<table class="grid" align="center" cellspacing="1" width="800">
<caption><s:text name="label.admin.config.edit.caption2" /></caption>
<tbody>
    <tr align="left" valign="middle">
        <td class="odd" width="20%"><s:text name="label.admin.config.edit.dburl" /></td>
       <td class="even"><s:textfield name ="dburl" size="25" maxlength="100" cssClass="text" theme="simple"/></td>
    </tr>
    <tr align="left" valign="middle">
        <td class="odd" width="20%"><s:text name="label.admin.config.edit.username" /></td>
        <td class="even">
            <s:textfield name ="username" size="25" maxlength="100" cssClass="text" theme="simple"/>
        </td>
    </tr>
    <tr align="left" valign="middle">
        <td class="odd" width="20%"><s:text name="label.admin.config.edit.password" /></td>
        <td class="even">
            <s:textfield name ="password" size="25" maxlength="100" cssClass="text" theme="simple"/><span class="hottext">留空表示不修改密码</span>
        </td>
    </tr>
</tbody>
</table>
<br />
<table class="grid" align="center" cellspacing="1" width="800">
<caption><s:text name="label.admin.config.edit.caption3" /></caption>
<tbody>
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.config.edit.skipAuthCheck" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="skipAuthCheck" ></s:radio>
       </td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.config.edit.cleanUrl" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="cleanUrl" ></s:radio>
       </td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.config.edit.cacheEffective" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="cacheEffective" ></s:radio>
       </td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.config.edit.gzipEffective" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="gzipEffective" theme = "simple"></s:radio> <span class="hottext">压缩输出有利于提高浏览速度</span>
       </td>
    </tr>
    
    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.config.edit.adEffective" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="adEffective" ></s:radio>
       </td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.config.edit.createIndexPage" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="createIndexPage" ></s:radio>
       </td>
    </tr>

    <tr align="left" valign="middle">
       <td class="odd" width="20%"><s:text name="label.admin.config.edit.createSiteMap" /></td>
       <td class="even">
           <s:radio list="collections['collectionProperties.boolean']" name="createSiteMap" ></s:radio>
       </td>
    </tr>
    <tr align="left" valign="middle">
       <td class="odd" colspan="2">
            <s:submit name="submit" value="%{getText('label.admin.edit.add')}" method="save" cssClass="submitButton" theme = "simple"/>
       </td>
    </tr>
</tbody>
</table>
</div>
</s:form>
<jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
</body>
</html>
