<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6"><![endif]-->
<!--[if IE 7 ]><html class="ie ie7"><![endif]-->
<!--[if IE 8 ]><html class="ie ie8"><![endif]-->
<!--[if IE 9 ]><html class="ie ie9"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html>
<!--<![endif]-->
    <jsp:include page="/WEB-INF/content/commom/header.jsp" />
    <div id="container">
    <script type="text/javascript" src="<s:property value="contextPath" />/ad/index1.js"></script>
    <section class="section board-list board-list-collapse">
      <p class="b-all-switch normal">好看的:
        <a href="javascript:;" class="select" id="ask1" onMouseOver="repales_rell1(1,5)">都市言情</a>
        <a href="javascript:;" id="ask2" onMouseOver="repales_rell1(2,5)">玄幻魔法</a>
        <a href="javascript:;" id="ask3" onMouseOver="repales_rell1(3,5)">历史军事</a>
        <a href="javascript:;" id="ask4" onMouseOver="repales_rell1(4,5)">恐怖灵异</a>
        <a href="javascript:;" id="ask5" onMouseOver="repales_rell1(5,5)">武侠修真</a>
      </p>
      <jsp:include page="/WEB-INF/content/blocks/indextuijian.jsp" />
      <script type="text/javascript" src="<s:property value="contextPath" />/ad/index2.js"></script>
      <div id="J_random_board" class="random-board cf board">
        <jsp:include page="/WEB-INF/content/blocks/lastupdatelist.jsp" />
        <jsp:include page="/WEB-INF/content/blocks/lastinsertlist.jsp" />
      </div> 
      <div class="clear"></div>
      <p class="b-all-switch normal">友情链接:</p>
      <div class="mainLink">
        <s:property value="blocks.friend_link" escape="false"/>
      </div>
      <jsp:include page="/WEB-INF/content/commom/footer.jsp" />
    </section>
  </div>
</div>
</body>
</html>




