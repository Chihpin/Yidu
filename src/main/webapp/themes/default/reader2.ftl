<#include "common.ftl"/>
<#macro titleContent>  
<title>${chapter.articlename}-${chapter.chaptername}</title>
<meta name="keywords" content="${chapter.chaptername},${chapter.articlename}最新章节,${chapter.articlename}TXT下载,${chapter.articlename}无广告,${getText("label.system.name")}" />
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>  
<#macro customizeimport>  
<link href="${contextPath}/themes/${themeName}/css/readtools.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${contextPath}/themes/${themeName}/js/tools.js"></script>
<script type="text/javascript" src="${contextPath}/themes/${themeName}/js/style5.js"></script>
<script type="text/javascript" src="${contextPath}/themes/${themeName}/js/lib/jquery.tools.min1.2.5.js"></script>
<script type="text/javascript">
    <!--
    document.oncontextmenu=function(e){return false;} 
    document.ondragstart=function(e){return false;}
    document.onselectstart=function(e){return false;}
    document.onselect=function(e){return false;} 
    document.oncopy=function(e){document.selection.empty();}
    document.onbeforecopy=function(e){return false;}
    $(document).ready(function(){
       var readhistory = $.cookie("readhistory");
       if(! readhistory ){
            readhistory = new Array();
       }else{
            readhistory = JSON.parse(readhistory);
       }
       var readObject = new Object();
       readObject.chapterno = ${chapter.chapterno?c};
       readObject.articleno = ${chapter.articleno?c};
       readObject.chaptername = "${chapter.chaptername}";
       readObject.articlename = "${chapter.articlename}";
       var index = readObject.articleno.in_array(readhistory);
       if(index != -1){
            readhistory.splice(index,1);
       }
       readhistory.splice(0,0,readObject);
       if(readhistory.length > 10 ){
            readhistory.splice(9,readhistory.length - 10);
       }
       $.cookie("readhistory",JSON.stringify(readhistory),{expires: 365});
    })
    -->
</script>
</#macro>

<#macro content>
<div class="mainnav">
        <div class="main-index" id="direct">
            <span class="r mr10">
                <form action="${contextPath}/search" method="get" >
                    搜小说：<input type="text" name="key" value="" />
                    <button type="submit" >搜</button>
                </form>
            </span>
    位置：<a href="${contextPath}/" class="c009900">${getText("label.system.name")}</a> > 

    <a href="${encodeURL("/info?subdir=${chapter.subdir?c}&articleno=${chapter.articleno?c}")}" class="article_title">${chapter.articlename}</a>  > 
    ${chapter.chaptername}</div>
    <section class="main b-detail" id="directs">
        <div class="bookInfo">
                <div>
					<ul>
					<#list fullReadChapterList as c >
						<li style="float:left;width:28%;padding:5px 20px;font-size:14px;"><a href="#${c.chapterno?c}">${c.chaptername}</a></li>
					</#list>
					</ul>
				</div>
                <div class="clear"></div>
            <div class="toolbar">
            <ul>
                <li>
                    <span class="fl">背景：</span>
                    <div class="fl">
                        <input id="bg1" onclick="setBG('#dcecf5')" type="button" class="setBG" />
                        <input id="bg2" onclick="setBG('#e1ffe6')" type="button" class="setBG" />
                        <input id="bg3" onclick="setBG('#edf6d0')" type="button" class="setBG" />
                        <input id="bg4" onclick="setBG('#eae8f7')" type="button" class="setBG" />
                        <input id="bg5" onclick="setBG('#f5f1e7')" type="button" class="setBG" />
                        <input id="bg6" onclick="setBG('#ebf4ef')" type="button" class="setBG" />
                        <input id="bg7" onclick="setBG('#FFFFFF')" type="button" class="setBG" />
                    </div>
                </li>
                <li>
                    <span class="fl">字体大小：</span>
                    <input type='range' name='fontsize' id='fontsize' value='14' style='display:none' readonly min='12' max='30' />
                </li>
                <li>
                    <span class="fl">字体颜色：</span>
                    <div class="fl">
                        <select onchange="setFontColor(this.value)" id="txtcolor" name="txtcolor">
                            <option value="#000000">黑色</option>
                            <option value="#ff0000">红色</option>
                            <option value="#006600">绿色</option>
                            <option value="#0000ff">蓝色</option>
                            <option value="#660000">棕色</option>
                        </select>
                    </div>
                </li>
                <li id="sudu">
                    <span class="fl">滚动速度：</span>
                    <a id="sudu50" href="javascript:setSpeed(50);">快</a>
                    <a id="sudu100" href="javascript:setSpeed(100);" class="this">中</a>
                    <a id="sudu150" href="javascript:setSpeed(150);">慢</a>
                </li>
            </ul>
			<div style="clear:both;"></div>
        </div>
        <#if adEffective?? && adEffective>
        <script type="text/javascript" src="${contextPath}/ad/reader1.js"></script>
        </#if>
        <div class="mainContenr"   id="content" style="overflow:hidden">
			<#list fullReadChapterList as c >
                <a name="${c.chapterno?c}">${c.chaptername}</a><br/>
				<#if c.content??>${c.content}<br/></#if>
				<hr/><br/>				
            </#list>
        </div>
        <#if adEffective?? && adEffective>
        <script type="text/javascript" src="${contextPath}/ad/reader2.js"></script>
        </#if>
        <#if adEffective?? && adEffective>
        <script type="text/javascript" src="${contextPath}/ad/reader3.js"></script>
        </#if>
        </div>
       </section>
    </div>
</div>
</#macro>

<#macro customizefooter> 
    <div id="full2" style="width:37px; height:22px; position:fixed; left:50%; top:425px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
    <a class="get_top" alt="返回顶部"></a>
    </div>
    
    <div id="full" style="width:37px; height:22px; position:fixed; left:50%; top:562px; margin-left:493px;  z-index:100; text-align:center; cursor:pointer;">
    <a class="get_bottom" alt="跳至页尾"></a>
    </div>
    <script src="${contextPath}/themes/${themeName}/js/news_top.js" type="text/javascript"></script>
</#macro>
