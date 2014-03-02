<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:set name="articleList" value="blocks.last_update_list" />
<section class="box" id="news">
    <h2>刚刚更新的：</h2>
    <ol>
        <s:iterator value="#articleList" id="article">
            <li>
                <em class="r"><s:date name="#article.lastupdate" format="MM-dd HH:mm" /></em>
                <a href="<s:url value="/articleList" escapeAmp="false" ><s:param name="category" value="#article.category" /><s:param name="page" value="1" /></s:url>"
                    title="<s:property  value="collections['collectionProperties.article.category'][#article.category]" />">[&nbsp;<s:property
                            value="collections['collectionProperties.article.category'][#article.category]" />&nbsp;]</a>
                <a class="poptext" href="<s:url value="/info" ><s:param name="articleno" value="#article.articleno" /></s:url>"
                    title="<s:property  value="#article.articlename" />最新章节" class="f14"><s:property value="#article.articlename" /></a>
                <a class="poptext" href="<s:url value="/reader"  escapeAmp="false"><s:param name="articleno" value="#article.articleno" /><s:param name="chapterno" value="#article.lastchapterno" /></s:url>" ><s:property value="#article.lastchapter" /></a>
                作者：<a class="poptext" href="<s:url value="/articleList" ><s:param name="author" value="#article.author" /></s:url>" title="<s:property  value="author" />的小说" class="f14"><s:property value="#article.author" /></a>
            </li>
        </s:iterator>
    </ol>
</section>
