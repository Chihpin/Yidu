<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ユーザ追加・編集画面</title>

<link rel="stylesheet" type="text/css" href="/css/default.css">
</head>
<body>
    <s:form namespace="/" action="userEdit" method="post" validate="true">
        <br>ユーザ追加・編集画面<br>
        <s:actionerror />
        <br>
        <s:hidden id = "uid" name = "uid" /> 
        <table class="jieiqi-table">
            <tr>
                <td>ユーザ名</td>
                <td><s:textfield name="uname" id="uname" maxlength="32"/></td>
            </tr>
            <tr>
                <td>パスワード</td>
                <td><s:password name="pass" id="pass" maxlength="32" /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><s:textfield name="email" id="email" maxlength="32"/></td>
            </tr>
        </table>
        <br>
        <s:submit method="save" value="保存"/>
    </s:form>
</body>
</html>
