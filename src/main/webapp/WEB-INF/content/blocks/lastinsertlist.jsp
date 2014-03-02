<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:set name="artileList" value="blocks.last_insert_list"/>
<section class="box" id="sorts">
    <h2>最新入库小说：</h2>
    <ol>
        <s:iterator value="#artileList" id="article" status="rowstatus">
        <li class="top3">
            <span class="order"><s:property value="#rowstatus.index" /></span>
            <span class="chapter"><a href="<s:url value="/articleList" escapeAmp="false"><s:param name="category" value="#article.category" /><s:param name="page" value="1" /></s:url>" title="<s:property  value="collections['collectionProperties.article.category'][#article.category]" />小说" target="_blank">[<s:property  value="collections['collectionProperties.article.category'][#article.category]" />]</a></span>
            <a target="_blank" href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>" title="<s:property  value="#article.articlename" />最新章节"><s:property  value="#article.articlename" /></a> 
            <span class="chapter"><a href="<s:url value="/reader" escapeAmp="false"><s:param name="articleno" value="#article.articleno" /><s:param name="chapterno" value="#article.lastchapterno" /></s:url>" title="<s:property value="#article.lastchapter" />" target="_blank"><s:property value="#article.lastchapter" /></a></span>
        </li>
        </s:iterator>
    </ol>
</section>