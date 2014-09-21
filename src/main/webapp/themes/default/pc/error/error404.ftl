<#include "../common.ftl"/>

<#macro titleContent>  
<title>没有找到页面</title>
</#macro>

<#macro content>
<div class="wrapper">
<div class="main">
<h1 style="height: 35px;font-size: 24px;font-family: '微软雅黑';line-height: 26px;"><strong style = "color: #f60;">404 错误</strong> 找不到您访问的页面，郁闷。</h1>
<h2 style="height: 30px;font-size: 18px;font-family: '微软雅黑';line-height: 26px;"><strong>请CTRL+F5重新刷新页面,或者点击下面链接：</strong></h2>
<h2 style="height: 30px;font-size: 18px;font-family: '微软雅黑';line-height: 26px;"><strong><script type="text/JavaScript">document.write('<a href="'+window.location.href+'">'+window.location.href+'</a>');</script></strong></h2>
<h2 style="height: 30px;font-size: 18px;font-family: '微软雅黑';line-height: 26px;">如果还是无法打开，请看下边帮助。</h2>
<dl>
<dt>可能是这样儿，检查看看：</dt>
<dd>网址输入错错了吧？</dd>
<dd>可能本书色情描写含有部分成人内容，不符合<a href="${contextPath}/">${getText("label.system.name")}</a>健康阅读的宗旨，已被管理员删除。</dd>
<dd>由于网络延迟，读取错误……请刷新或者稍后再试。</dd>
<dd>也有可能是我们的网站正在升级维护中……</dd>
</dl>
    <script type="text/javascript" src="http://www.qq.com/404/search_children.js?edition=small" charset="utf-8"></script>
</div>
</div>
</#macro>