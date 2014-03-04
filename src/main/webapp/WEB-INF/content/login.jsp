<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <jsp:include page="/WEB-INF/content/commom/header.jsp" />
    <div id="container">
        <s:form action="login" method="post" validate="true">
            <center>
                <span id="ErrorList"><s:fielderror /> <s:actionerror /></span>
            </center>
            <table class="grid" align="center" width="500">
              <caption>
                登陆<s:text name="label.system.name" />
              </caption>
              <tbody>
                <tr>
                  <td sytle="text-align: right; padding-left: 20px;" class="odd userN" width="25%">用&nbsp;&nbsp;户&nbsp;&nbsp;名:</td>
                  <td class="even">
                        <s:textfield name="loginid" id = "loginid" cssStyle="width:150px; height:20px;" maxlength="32"/>
                  </td>
                </tr>
                <tr>
                  <td class="odd psd" width="25%">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
                  <td class="even">
                    <s:password name="password" id="password" cssStyle="width:150px; height:20px;" maxlength="32"></s:password>
                  </td>
                </tr>
                <tr>
                  <td width="25%"></td>
                  <td class="even">
                    <s:checkbox name="useCookie" fieldValue="true" theme="simple"  value="true" />下次自动登录
                  </td>
                </tr>
                <tr>
                  <td class="odd" width="25%">&nbsp;
                  </td>
                  <td class="even">
                      <s:submit method="login" cssClass="submit" name="submit" theme="simple" cssStyle="cursor:pointer;" value=" 提 交 "/>
                      <span class="pl10">没有账号？<a href="<s:property value="contextPath" />/register">注册一个吧!</a></span>
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