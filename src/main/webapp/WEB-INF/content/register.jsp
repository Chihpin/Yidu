<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <jsp:include page="/WEB-INF/content/commom/header.jsp" />
    <div id="container">
        <s:form action="register" method="register" validate="true">
            <center>
                <span id="ErrorList"><s:fielderror /> <s:actionerror /></span>
            </center>
            <table class="grid" align="center" width="410">
              <caption>
             新用户注册
              </caption>
              <tbody>
                 <tr>
                  <td class="odd userN" width="25%">用&nbsp;&nbsp;户&nbsp;&nbsp;名:</td>
                  <td class="even">
                      <s:textfield name="loginid" id = "loginid" cssStyle="160px;"  cssClass="text username"  size="25" maxlength="32"/>
                  </td>
                </tr>
                <tr>
                  <td class="odd psd" width="25%">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
                  <td class="even">
                      <s:password name="password" id = "password" cssStyle="160px;"  cssClass="text password"  size="25" maxlength="32"/>
                  </td>
                </tr>
                <tr>
                  <td class="odd again" width="25%">再次输入：</td>
                  <td class="even">
                      <s:password name="repassword" id = "repassword" cssStyle="160px;"  cssClass="text password"  size="25" maxlength="32"/>
                  </td>
                </tr>
                <tr>
                  <td class="odd emails" width="25%">电子邮箱：</td>
                  <td class="even">
                      <s:textfield name="email" id = "email" cssStyle="160px;"  cssClass="text"  size="25" maxlength="60"/>
                  </td>
                </tr>
                <tr>
                  <td class="odd" width="25%">QQ：</td>
                  <td class="even">
                      <s:textfield name="qq" id = "qq" cssStyle="160px;"  cssClass="text"  size="25" maxlength="15"/>
                  </td>
                </tr>
                <tr>
                  <td class="odd" width="25%">&nbsp;
                    <input name="action" id="action" value="newuser" type="hidden"></td>
                  <td class="even">
                      <s:submit method="register" cssClass="submit" name="submit" theme="simple" cssStyle="cursor:pointer;" value=" 提 交 "/>
                  </td>
                </tr>
              </tbody>
            </table>
            <s:hidden name="backUrl"/>
        </s:form>
  </div>
<jsp:include page="/WEB-INF/content/commom/footer.jsp" />
</body>
</html>