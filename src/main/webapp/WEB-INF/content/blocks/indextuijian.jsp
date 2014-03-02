<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:set name="yanqingArtileList" value="blocks.index_yanqing_tuijian"/>
<s:set name="xuanhuanArtileList" value="blocks.index_xuanhuan_tuijian"/>
<s:set name="junshiArtileList" value="blocks.index_junshi_tuijian"/>
<s:set name="kongbuArtileList" value="blocks.index_kongbu_tuijian"/>
<s:set name="wuxiaArtileList" value="blocks.index_wuxia_tuijian"/>

<ul class="seeWell cf" id="cttd1">
    <s:iterator value="#yanqingArtileList" id="article" status="rowstatus">
    <li>
       <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>"  title="<s:property  value="#article.articlename" />" class="l mr10">
           <img src="<s:property value="#article.imgUrl" />" style="width: 120px; height: 150px"/></a>
       <s:if test="#article.fullflag"><img src="/images/only.png" class="topss png_bg"></s:if><s:else><img src="/images/only2.png" class="topss png_bg"></s:else>
       <span class="l">
          <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>"  title="<s:property  value="#article.articlename" />" class="clearfix stitle"><s:property  value="#article.articlename" /></a>
          作者：<a href="<s:url value="/articleList" ><s:param name="author" value="#article.author" /></s:url>"><s:property value="#article.author" /></a>
          <em class="c999 clearfix"><s:property  value="#article.intro" /></em>
          更新：<a href="<s:url value="/reader" escapeAmp="false"><s:param name="articleno" value="#article.articleno" /><s:param name="chapterno" value="#article.lastchapterno" /></s:url>"  title="<s:property value="#article.lastchapter" />"><s:property value="#article.lastchapterOmit" /></a>
          <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>" class="readTo"  title="<s:property  value="#article.articlename" />">马上阅读</a>
       </span>
    </li>
    </s:iterator>
</ul>
<ul class="seeWell cf" id="cttd2" style="display:none;">
    <s:iterator value="#xuanhuanArtileList" id="article" status="rowstatus">
    <li>
       <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>"  title="<s:property  value="#article.articlename" />" class="l mr10">
            <img src="<s:property value="#article.imgUrl" />" style="width: 120px; height: 150px"/></a>
       <s:if test="#article.fullflag"><img src="/images/only.png" class="topss png_bg"></s:if><s:else><img src="/images/only2.png" class="topss png_bg"></s:else>
       <span class="l">
          <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>"  title="<s:property  value="#article.articlename" />" class="clearfix stitle"><s:property  value="#article.articlename" /></a>
          作者：<a href="<s:url value="/articleList" ><s:param name="author" value="#article.author" /></s:url>"><s:property value="#article.author" /></a>
          <em class="c999 clearfix"><s:property  value="#article.intro" /></em>
          更新：<a href="<s:url value="/reader"  escapeAmp="false"><s:param name="articleno" value="#article.articleno" /><s:param name="chapterno" value="#article.lastchapterno" /></s:url>"  title="<s:property value="#article.lastchapter" />"><s:property value="#article.lastchapterOmit" /></a>
          <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>" class="readTo"  title="<s:property  value="#article.articlename" />">马上阅读</a>
       </span>
    </li>
    </s:iterator>
 
</ul>
<ul class="seeWell cf" id="cttd3" style="display:none;">
    <s:iterator value="#junshiArtileList" id="article" status="rowstatus">
    <li>
       <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>"  title="<s:property  value="#article.articlename" />" class="l mr10">
            <img src="<s:property value="#article.imgUrl" />" style="width: 120px; height: 150px"/></a>
       <s:if test="#article.fullflag"><img src="/images/only.png" class="topss png_bg"></s:if><s:else><img src="/images/only2.png" class="topss png_bg"></s:else>
       <span class="l">
          <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>"  title="<s:property  value="#article.articlename" />" class="clearfix stitle"><s:property  value="#article.articlename" /></a>
          作者：<a href="<s:url value="/articleList" ><s:param name="author" value="#article.author" /></s:url>"><s:property value="#article.author" /></a>
          <em class="c999 clearfix"><s:property  value="#article.intro" /></em>
          更新：<a href="<s:url value="/reader"  escapeAmp="false"><s:param name="articleno" value="#article.articleno" /><s:param name="chapterno" value="#article.lastchapterno" /></s:url>"  title="<s:property value="#article.lastchapter" />"><s:property value="#article.lastchapterOmit" /></a>
          <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>" class="readTo"  title="<s:property  value="#article.articlename" />">马上阅读</a>
       </span>
    </li>
    </s:iterator>

</ul> 
<ul class="seeWell cf"  id="cttd4" style="display:none;">
    <s:iterator value="#kongbuArtileList" id="article" status="rowstatus">
    <li>
       <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>"  title="<s:property  value="#article.articlename" />" class="l mr10">
            <img src="<s:property value="#article.imgUrl" />" style="width: 120px; height: 150px"/></a>
       <s:if test="#article.fullflag"><img src="/images/only.png" class="topss png_bg"></s:if><s:else><img src="/images/only2.png" class="topss png_bg"></s:else>
       <span class="l">
          <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>"  title="<s:property  value="#article.articlename" />" class="clearfix stitle"><s:property  value="#article.articlename" /></a>
          作者：<a href="<s:url value="/articleList" ><s:param name="author" value="#article.author" /></s:url>"><s:property value="#article.author" /></a>
          <em class="c999 clearfix"><s:property  value="#article.intro" /></em>
          更新：<a href="<s:url value="/reader" escapeAmp="false"><s:param name="articleno" value="#article.articleno" /><s:param name="chapterno" value="#article.lastchapterno" /></s:url>"  title="<s:property value="#article.lastchapter" />"><s:property value="#article.lastchapterOmit" /></a>
          <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>" class="readTo"  title="<s:property  value="#article.articlename" />">马上阅读</a>
       </span>
    </li>
    </s:iterator>
</ul> 
<ul class="seeWell cf" id="cttd5"  style="display:none;">
    <s:iterator value="#wuxiaArtileList" id="article" status="rowstatus">
       <li>
       <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>"  title="<s:property  value="#article.articlename" />" class="l mr10">
            <img src="<s:property value="#article.imgUrl" />" style="width: 120px; height: 150px"/></a>
       <s:if test="#article.fullflag"><img src="/images/only.png" class="topss png_bg"></s:if><s:else><img src="/images/only2.png" class="topss png_bg"></s:else>
       <span class="l">
          <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>"  title="<s:property  value="#article.articlename" />" class="clearfix stitle"><s:property  value="#article.articlename" /></a>
          作者：<a href="<s:url value="/articleList" ><s:param name="author" value="#article.author" /></s:url>"><s:property value="#article.author" /></a>
          <em class="c999 clearfix"><s:property  value="#article.intro" /></em>
          更新：<a href="<s:url value="/reader"  escapeAmp="false"><s:param name="articleno" value="#article.articleno" /><s:param name="chapterno" value="#article.lastchapterno" /></s:url>"  title="<s:property value="#article.lastchapter" />"><s:property value="#article.lastchapterOmit" /></a>
          <a href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>" class="readTo"  title="<s:property  value="#article.articlename" />">马上阅读</a>
       </span>
    </li>
    </s:iterator>

</ul>