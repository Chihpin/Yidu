<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
    <jsp:include page="/WEB-INF/content/admin/commom/header.jsp" />
    <div id="container">
        <section class="section board-list board-list-collapse">
            <p class="b-all-switch normal">升级成功</p>
            <div class="msgwin mgl_61" style="text-align:center;">
                <div class="blockcontent">
                    <div style="padding:10px"><br />当前版本<s:text name="uv" />，请重启tomcat！<br /><br /></div>
                </div>
                
            </div>
          <jsp:include page="/WEB-INF/content/admin/commom/footer.jsp" />
        </section>
      </div>
    </div>
</body>
</html>


