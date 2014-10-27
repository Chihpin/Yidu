<!DOCTYPE HTML>
<!--[if lt IE 7 ]><html class="ie ie6"><![endif]-->
<!--[if IE 7 ]><html class="ie ie7"><![endif]-->
<!--[if IE 8 ]><html class="ie ie8"><![endif]-->
<!--[if IE 9 ]><html class="ie ie9"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><html><![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--[if lt IE 9]>
<script src="${contextPath}/themes/${themeName}/pc/js/lib/html5.js"></script>
<![endif]-->
<!--[if IE 6]>
<script src="${contextPath}/themes/${themeName}/pc/js/DD_belatedPNG_0.0.8a-min.js"></script>
<script>
  DD_belatedPNG.fix('.png_bg');
</script>
<![endif]-->
<#assign categorymap = categoryData?eval>
<#if assignContent?exists>  
      <@assignContent/>
</#if>
<#if titleContent?exists>  
      <@titleContent/>
</#if>
<meta name="author" content="www.51yd.org"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/themes/${themeName}/pc/css/common.css">
<#if customizeimport?exists>  
      <@customizeimport/>
</#if>
</head>
<body>
<div id="wrapper">
    <#if headerContent?exists>  
        <@headerContent/>
    </#if>
    <#if naviContent?exists>  
        <@naviContent/>
    </#if>
    <div id="container">
    <#if content?exists>  
        <@content/>
    </#if>
    <#if footer?exists>  
        <@footer/>
    </#if>
    </div>
 </div>
<script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/lib/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/lib/layer.min.js"></script>
<script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/common.js" charset="UTF-8"></script>
<!--[if lt IE 8]>
<script type="text/javascript" src="${contextPath}/themes/${themeName}/pc/js/lib/json.js"></script>
<![endif]-->
<script type="text/javascript">
        enableQQLogin = <#if enableQQLogin>true<#else>false</#if>;
        adEffective = <#if adEffective?? && adEffective>true<#else>false</#if>;
        var contextPath = "${contextPath}";
</script>
<#if customizeJs?exists>  
        <@customizeJs/>
</#if>
</body>  
</html>