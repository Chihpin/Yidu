<#include "base.ftl"/>

<#macro titleContent>  
<title>${chapter.articlename}-${chapter.chaptername}</title>
<meta name="keywords" content="${chapter.chaptername},${chapter.articlename}最新章节,${chapter.articlename}TXT下载,${chapter.articlename}无广告,${getText("label.system.name")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  

<#macro customizetop>
    <div class="top3">
        <ul>
            <li><a class="button blue r3" href="<#if chapter.preChapterno ==0>${encodeURL("/info?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}")}<#else>${encodeURL("/reader?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}&chapterno=${chapter.preChapterno?c}")}</#if>">上一章</a></li>
            <li><a class="button blue r3" href="${encodeURL("/info?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}")}">简介</a></li>
            <li><a class="button blue r3" href="${encodeURL("/bookcase")}">书架</a></li>
            <li><a class="button blue r3" href="javascript:return false;">设置</a></li>
            <li><a class="button blue r3" href="<#if chapter.nextChapterno ==0>${encodeURL("/info?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}")}<#else>${encodeURL("/reader?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}&chapterno=${chapter.nextChapterno?c}")}</#if>">下一章</a></li>
        </ul>
    </div>

</#macro>

<#macro content>
    <div class="mainnav" style="font-size: 22px ;padding: 10px 12px;word-wrap: break-word;word-break: break-word;line-height: 1.562;">
       <strong class="l jieqi_title">${chapter.chaptername}</strong>
        <div class="mainContenr"   id="content">
            <#if chapter.content??>${chapter.content}</#if>
        </div>
    </div>
    <script language="JavaScript" type="text/JavaScript"> 
        window.onload = function() {
            var str = document.getElementById("content").innerHTML;//这里是整个页面代码 ,也可以指定id
            str = str.replace(/\<script[\s\S]+?\<\/script\>/gi, "");
            str = str.replace(/\<styltyp[\s\S]+?\<\/styl\>/gi, "");
            str = str.replace(/\<style[\s\S]+?\<\/style\>/gi, "");
            str = str.replace(/\<a[\s\S].+?\<\/a\>/gi, "");
            str = str.replace(/Www.+?ggyy\.net/gi, "");
            str = str.replace(/Www.+?Com/gi, "");
            str = str.replace(/Www.+?net/gi, "");
            str = str.replace(/Www.+?cc/gi, "");
            str = str.replace(/&lt;br.+?&gt;/gi, "<br />");
            str = str.replace(/&amp;hllp;/gi, "&hellip;").replace(/&amp;ldqo;/gi,
                    "&ldquo;").replace(/ldqo/gi, "ldquo").replace(/&amp;rdqo;/gi,
                    "&rdquo;").replace(/&amp;dash;/gi, "&mdash;");
            document.getElementById("content").innerHTML = str;
        }
    </script>
    
    <@customizetop/>

</#macro>
