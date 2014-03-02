<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <jsp:include page="/WEB-INF/content/commom/header.jsp" />
    <div id="container">
        <div class="layout grid-s160m0e190 channel-netnovel">
        <div class="col-main"><div class="main-wrap channel-netnovel-main">
        <section>
            <header class="netnovel-header">
                <h3>我的消息</h3>
            </header>
            <form action="" method="post" name="checkform" id="checkform" onSubmit="return check_confirm();">
                <table class="mygrid" width="100%" align="center">
                  <tbody>
                  <tr align="center">
                        <th width="5%">
                            <input type="checkbox" id="checkall" name="checkall" value="checkall" onclick="javascript: for (var i=0;i<this.form.elements.length;i++){ if (this.form.elements[i].name != 'checkkall') this.form.elements[i].checked = form.checkall.checked; }"></th>
                        <th width="21%">小说名</th>
                        <th width="30%">最新章节</th>
                        <th width="30%">书签</th>
                        <th width="7%">操作</th>
                  </tr>
                  <s:iterator value="messageList" id="message" status="rowstatus">
                  <tr>
                        <td class="odd" align="center">
                        <input type="checkbox" id="checkid[]" name="checkid[]" value="20843">&nbsp;<span class="hottext"><s:if test="#bookcase.chapterno!=#bookcase.lastchapterno">新</s:if></span> </td>
                        <td class="even"><a href="<s:url value="/info" ><s:param name="articleno" value="#message.messageno" /></s:url>" target="_blank"><s:property value="#message.title" /></a></td>
                        <td class="odd"><a href="<s:url value="/reader" escapeAmp="false"><s:param name="articleno" value="#bookcase.articleno" /><s:param name="chapterno" value="#bookcase.lastchapterno" /></s:url>" target="_blank"><s:property value="#bookcase.lastchapter" /></a>
                        </td>
                        <td class="even"><a href="<s:url value="/reader" escapeAmp="false"><s:param name="articleno" value="#bookcase.articleno" /><s:param name="chapterno" value="#bookcase.chapterno" /></s:url>" target="_blank"><s:property value="#bookcase.chaptername" /></a></td>
                        <td class="even" align="center"><a href="javascript:if(confirm('确实要将本书移出书架么？')) document.location='<s:url value="/bookcase!delete" escapeAmp="false"><s:param name="bookcaseno" value="#bookcase.bookcaseno" /></s:url>';">移除</a></td>
                 </tr>
                 </s:iterator>
                </tbody>
              </table>
            </form>
        </section>
        </div>
        </div>
        <div class="col-sub">
            <nav class="top-rank">
                <h3>用户面板</h3>
                <div class="top-rank-list">
					<ul>
					   <li class="current"><a href="<s:url value="/bookcase" ></s:url>">我的书架</a></li>
					</ul>
					<ul>
					   <li><a href="#">查看短信</a></li>
					</ul>
					<ul class="last">
					   <li><a href="<s:url value="/logout" ></s:url>">退出登录</a></li>
					</ul>
                </div>
            </nav>
        </div>
    </div>
    </div>
  <jsp:include page="/WEB-INF/content/commom/footer.jsp" />
  </body>
</html>
