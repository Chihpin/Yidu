<#include "../common.ftl"/>
<#macro menuContent>
<div class="col-sub">
    <nav class="top-rank">
        <h3>用户面板</h3>
        <div class="top-rank-list">
            <ul>
               <li <#if pageType==21>class="current"</#if>><a href="${encodeURL("/user/bookcase")}">我的书架</a></li>
            </ul>
            <ul>
               <li <#if pageType==22>class="current"</#if>><a href="${encodeURL("/user/message")}">查看短信</a></li>
            </ul>
            <ul>
               <li <#if pageType==23>class="current"</#if>><a href="${encodeURL("/user/useredit")}">编辑资料</a></li>
            </ul>
            <ul>
               <li <#if pageType==24>class="current"</#if>><a href="${encodeURL("/user/useredit")}">申请作者</a></li>
            </ul>
            <ul class="last">
               <li><a href="${encodeURL("/logout")}">退出登录</a></li>
            </ul>
        </div>
    </nav>
</div>
</#macro>