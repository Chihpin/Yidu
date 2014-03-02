<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="pagelink" id="pagelink">
	<em id="pagestats"><s:property value="pagination.pageNumber"/>/<s:property value="pagination.totalPages"/></em>
	<a href="<s:url value="/articleList" >
                        <s:param name="category" value="#article.category" />
                        <s:param name="page" value="1" />
                        </s:url>" class="first">1</a>
                        
      <a href="<s:url value="/articleList" >
                        <s:param name="category" value="#category" />
                        <s:param name="page" value="#page" />
                        </s:url>"  class="pgroup">&lt;&lt;</a>
     <strong>1</strong>
	 <s:iterator value="pagination.pageNumberList" id="page">
	 <a href="http://www.kaixinwx.com/list/1/2.html">2</a>
	 </s:iterator>
	

	<a href="http://www.kaixinwx.com/list/1/2.html" class="next">&gt;</a>
	<a href="http://www.kaixinwx.com/list/1/16.html" class="ngroup">&gt;&gt;</a>
	<a href="http://www.kaixinwx.com/list/1/579.html" class="last">579</a>
	<kbd>
		<input name="page" type="text" size="4" maxlength="6"
			onkeydown="if(event.keyCode==13){window.location='http://www.kaixinwx.com/list/1/&lt;{$page}&gt;.html'.replace('&lt;{$page|subdirectory}&gt;', '/' + Math.floor(this.value / 1000)).replace('&lt;{$page}&gt;', this.value); return false;}">
	</kbd>
</div>
