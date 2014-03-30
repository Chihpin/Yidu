<#include "base.ftl"/>
<#macro headerContent>
  <div class="shadow" <#if pageType?? && pageType==4> style="height:1px;" </#if>></div>
  <header id="global-head" <#if pageType?? && pageType==4> style="height:45px;" </#if>>
    <div>
      <nav class="site-navigation cf">
      <a class="home" href="${contextPath}/">首页</a>
      <a href="${encodeURL("/siteMap")}">网站地图</a>
      <a href="${encodeURL("/articleList?fullflag=true")}">完本小说</a>
      <a href="${encodeURL("/saveShortcut")}">创建桌面快捷</a>
      </nav>
      <div class="hd-follow" style="width:500px;text-align:right;" >
        <div class="myhide"><a href="#" class="hides">浏览记录</a>
          <div class="hideInfo">
            <ul id="readhistory">
                <script>
                $(document).ready(function(){
                    $.post('${contextPath}/readhistory',function(data){
                        if(data != null){
                            var html ="";
                           for(i=0; i < data.length;i++){
                                html = html + ' <li class=""><a href="${contextPath}/info/'+Math.floor(data[i].articleno/1000)+'/'+data[i].articleno +'.html" class="f14">'+data[i].articlename+'</a><br>'
                                html = html + ' 最近浏览:<a href="${contextPath}/reader/'+Math.floor(data[i].articleno/1000)+'/'+data[i].articleno+'/'+data[i].chapterno+'.html" >'+data[i].chaptername+'</a></li>'
                           }
                           $('#readhistory').html(html);
                        }
                    })
                })
               </script>
            </ul>
            <p>*提示：浏览记录仅放置最近浏览的10本书籍</p>
            <span>浏览记录是空的</span>
          </div>
        </div>
          <span id="checklogin">
          <script>
            $(document).ready(function(){
                $.post('${contextPath}/checklogin',function(data){
                    if(data!=null){
                       var html = '你好   <a href="${encodeURL("/user/useredit")}" style="color: rgb(240, 240, 240);"> '+ data.loginid +"</a>";
                        if(data.type==30){
                            html = html + '&nbsp;&nbsp;&nbsp;<a href="${encodeURL("/admin/index")}" style="color: rgb(240, 240, 240);">管理后台</a>';
                        }else if(data.type==20||data.type==40||data.type==41){
                            html = html + '&nbsp;&nbsp;&nbsp;<a href="${encodeURL("/user/articleList")}" style="color: rgb(240, 240, 240);">小说管理</a>';
                        }
                        html = html + '&nbsp;&nbsp;&nbsp;<a href="${encodeURL("/user/message")}" style="color: rgb(240, 240, 240);">消息管理</a>';
                        html = html + '&nbsp;&nbsp;&nbsp;<a href="${encodeURL("/user/bookcase")}" style="color: rgb(240, 240, 240);">我的书架</a>';
                        html = html + '&nbsp;&nbsp;&nbsp;<a href="${encodeURL("/logout")}" style="color: rgb(240, 240, 240);" class="out">退出</a>&nbsp;&nbsp;';
                        $('#checklogin').html(html);
                    }
                })
            })
           </script>
           <script>
            thisUrl = window.location.href;
            if(thisUrl.indexOf('?backUrl') != -1){
                thisUrl = thisUrl.substring(0,thisUrl.indexOf('?backUrl'));
            }
            document.writeln("<a href=\"${contextPath}/login?backUrl="+thisUrl+" \" style=\"color:#F0F0F0\">访客登录</a>&nbsp;&nbsp;");
            document.writeln("<a href=\"${contextPath}/register?backUrl="+thisUrl+" \" style=\"color:#F0F0F0\">免费注册</a>&nbsp;&nbsp;");
          </script>
          </span>
        </div>
    </div>
    <!--TODO haserror check-->
    <#if hasError || ( pageType?? && pageType!=4) >
    <p class="site-logo">
        <a href="${contextPath}/" title="${getText("label.system.title")}">
        <img src="${contextPath}/themes/${themeName}/images/logo-medium.png" alt="${getText("label.system.title")} - logo"></a>
    </p>
    <div class="site-search">
       <form action="${contextPath}/search" method="get" >
       <input name="key" type="text" id="key" onFocus="this.classname='over';if (value =='这是一个神奇的搜索，请输入小说名或作者名'){value =''}" onBlur="this.classname='input'"  value=<#if key??>${key?html}<#else>"这是一个神奇的搜索，请输入小说名或作者名"</#if> />
       <input type="submit" id="searchbuttom" value="" style="background:url('${contextPath}/themes/${themeName}/images/search.jpg');border:0px solid;cursor:pointer;" />
       </form>
    </div>
    </#if>
  </header>
</#macro>  

<#macro naviContent>
<link rel="stylesheet" type="text/css" href="${contextPath}/themes/${themeName}/css/channel-net.css">
  <div id="channel-header" class="clearfix">
    <div class="channel-header-wrapper">
      <nav class="channel-nav">
        <ul class="channel-nav-list">
            <li <#if pageType ?? && pageType==1>class="current"</#if> ><a href="${contextPath}/" title="${getText("label.system.name")}">${getText("label.system.name")}</a></li>
            <#list categorymap?keys as categorykey>
            <li <#if category??><#if categorykey==category?c>class="current"</#if></#if>><a href="${encodeURL("/articleList?category=${categorykey}")}">${categorymap[categorykey]}</a></li>
            </#list>
        </ul>
      </nav>
    </div>
  </div>
</#macro>

<#macro footer>
<div class="clear"></div>
<footer id="global-foot">
    <p>
    本小说站所有小说、评论均为网友更新！仅代表发布者个人行为，与本小说站(${getText("label.system.url")})立场无关！<br/>
    本站所有小说的版权为原作者所有！如无意中侵犯到您的权益，或是含有非法内容，请及时与我们联系，我们将在第一时间做出回应！谢谢！<br/>
        ${getText("label.system.copyright")} ${getText("label.system.beianNo")} ${getText("label.system.analyticscode")}
        <#if pageType?? && pageType==1>
            <br>
            ${getText("label.system.support")}
        </#if>
    </p>
</footer>
<#if customizefooter?exists>  
    <@customizefooter/>  
</#if>
</#macro>