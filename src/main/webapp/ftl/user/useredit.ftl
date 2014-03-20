<#include "usercommon.ftl"/>

<#macro titleContent>  
<title>我的消息|${getText("label.system.title")}</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro content>
<div class="layout grid-s160m0e190 channel-netnovel">
    <div class="col-main"><div class="main-wrap channel-netnovel-main">
    <section>
        <header class="netnovel-header">
            <h3>我的消息 &nbsp;&nbsp;- 您共有 ${messageList.size()}条消息。</h3>
        </header>
        <form action="" method="post" name="checkform" id="checkform" onSubmit="return check_confirm();">
            <table class="mygrid" width="100%" align="center" style="font-size:12px">
              <tbody>
              <tr align="center">
                    <th width="5%">
                        <input type="checkbox" id="checkall" name="checkall" value="checkall" onclick="javascript: for (var i=0;i<this.form.elements.length;i++){ if (this.form.elements[i].name != 'checkkall') this.form.elements[i].checked = form.checkall.checked; }"></th>
                    <th width="21%">来自用户</th>
                    <th width="30%">标题</th>
                    <th width="30%">内容概要</th>
                    <th width="7%">操作</th>
              </tr>
              <#list messageList as message>
              <tr>
                    <td class="odd" align="center">
                    <input type="checkbox" id="checkid[]" name="checkid[]" value="${message.messageno?c}">&nbsp;<span class="hottext"><#if message.isread?? && message.isread>未读</#if></span> </td>
                    <td class="even"><a href="${encodeURL("/message?messageno=${message.messageno?c}")}" target="_blank">${message.loginid}</a></td>
                    <td class="odd">${message.title}</td>
                    <td class="even">${message.title}</td>
                    <td class="even" align="center"><a href="javascript:if(confirm('确实要删除这条消息么？')) document.location='${encodeURL("/user/message!delete?messageno${message.messageno?c}")}';">移除</a></td>
             </tr>
             </#list>
            </tbody>
          </table>
        </form>
    </section>
    </div>
</div>
<#if menuContent?exists>  
    <@menuContent/>  
</#if>
</#macro>


<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
    <s:form namespace="/admin" action="userEdit" method="post" validate="true">
        <jsp:include page="/WEB-INF/content/commom/common_form.jsp" />
        <s:hidden name="userno" />
        <br>
        <table class="yidu-table" align="center">
            <colgroup>
                <col width="120px">
                <col width="442px">
            </colgroup>
            <tbody>
                <tr>
                    <th colspan="2"><s:text name="label.admin.user.edit.title" /></th>
                </tr>
               <s:if test="userno!=0">
                <tr>
                    <td><s:text name="label.admin.user.edit.userno" /></td>
                    <td>
                       <s:property value="userno" />
                    </td>
                </tr>
                </s:if>
                <tr>
                    <td><s:text name="label.admin.user.edit.loginid" /></td>
                    <td>
                        <s:textfield id="loginid" name="loginid" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.password" /></td>
                    <td>
                        <s:password name="password" value="password" fieldValue="true"/>
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.username" /></td>
                    <td>
                        <s:textfield id="username" name="username" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.email" /></td>
                    <td>
                        <s:textfield id="email" name="email" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.sex" /></td>
                    <td>
                         <s:radio
                            id="sex"
                            name="sex"
                            list="collections['collectionProperties.user.sex']"
                            />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.type" /></td>
                    <td>
                         <s:select
                            id="type"
                            name="type"
                            list="collections['collectionProperties.user.type']"
                            />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.qq" /></td>
                    <td>
                        <s:textfield id="qq" name="qq" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.lineno" /></td>
                    <td>
                        <s:textfield id="lineno" name="lineno" cssClass="tb-rounded" /> 
                    </td>
                </tr>
                <s:if test="userno!=0">
                <tr>
                    <td><s:text name="label.admin.user.edit.regdate" /></td>
                    <td>
                        <s:date name="regdate" format="yyyy-MM-dd HH:mm" />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.admin.user.edit.lastlogin" /></td>
                    <td>
                        <s:date name="lastlogin" format="yyyy-MM-dd HH:mm" />
                    </td>
                </tr>
                </s:if>
                <tr>
                    <td colspan="2" style="text-align: center; padding: 2px">
                        <s:submit name="submit" value="%{getText('label.admin.edit.back')}" method="back" cssClass="submitButton" theme = "simple" onclick="goback()"/>
                        <s:if test="userno==0">
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


