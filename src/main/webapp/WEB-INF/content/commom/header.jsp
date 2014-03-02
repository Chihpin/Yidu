<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:if test="hasActionErrors()">
<title>错误画面|<s:text name="label.system.title" /></title>
<meta name="keywords" content="<s:text name="label.system.siteKeywords" />" />
<meta name="description" content="<s:text name="label.system.siteDescription" />" />
</s:if>
<s:elseif test="pageType==1">
<title><s:text name="label.system.title" /></title>
<meta name="keywords" content="<s:text name="label.system.siteKeywords" />" />
<meta name="description" content="<s:text name="label.system.siteDescription" />" />
</s:elseif>
<s:elseif test="pageType==2">
<s:if test="category!=null">
<title><s:property  value="collections['collectionProperties.article.category'][category]" />|<s:text name="label.system.title" /></title>
<meta name="keywords" content="<s:property  value="collections['collectionProperties.article.category'][category]" />,<s:property  value="collections['collectionProperties.article.category'][category]" />小说,<s:text name="label.system.siteKeywords" />" />
</s:if>
<s:elseif test="!author.equals('')">
<title><s:property  value="author" />的小说|<s:text name="label.system.title" /></title>
<meta name="keywords" content="<s:property  value="author" />的小说,<s:text name="label.system.siteKeywords" />" />
</s:elseif>
<s:else>
<title>完本小说|<s:text name="label.system.title" /></title>
<meta name="keywords" content="完本小说,<s:text name="label.system.siteKeywords" />" />
</s:else>
<meta name="description" content="<s:text name="label.system.siteDescription" />" />
</s:elseif>
<s:elseif test="pageType==3">
<title><s:property  value="article.articlename" />|<s:property  value="article.articlename" />最新章节|<s:property  value="article.articlename" />TXT下载</title>
<meta name="keywords" content="<s:property  value="article.articlename" />,<s:property  value="article.articlename" />最新章节,<s:property  value="article.articlename" />TXT下载,<s:property  value="article.articlename" />无广告,<s:text name="label.system.name" />" />
<meta name="description" content="《<s:property  value="article.articlename" />》情节跌宕起伏、扣人心弦，是一本情节与文笔俱佳的<s:property  value="collections['collectionProperties.article.category'][article.category]" />小说，<s:text name="label.system.name" />免费提供<s:property  value="article.articlename" />最新的清爽干净的文字章节在线阅读!" />
</s:elseif>
<s:elseif test="pageType==4">
<title><s:property  value="chapter.articlename" />-<s:property  value="chapter.chaptername" /></title>
<meta name="keywords" content="<s:property  value="chapter.chaptername" />,<s:property  value="chapter.articlename" />最新章节,<s:property  value="chapter.articlename" />TXT下载,<s:property  value="chapter.articlename" />无广告,<s:text name="label.system.name" />" />
<meta name="description" content="<s:text name="label.system.siteDescription" />" />
</s:elseif>
<s:else>
<title><s:text name="label.system.title" /></title>
<meta name="keywords" content="<s:text name="label.system.siteKeywords" />" />
<meta name="description" content="<s:text name="label.system.siteDescription" />" />
</s:else>
<meta name="author" content="www.51yd.org"/> 
<!--[if lt IE 9]>
<script src="<s:property value="contextPath" />/js/lib/html5.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<s:property value="contextPath" />/css/board.css">
<link rel="stylesheet" type="text/css" href="<s:property value="contextPath" />/css/common.css">
<script type="text/javascript" src="<s:property value="contextPath" />/js/lib/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<s:property value="contextPath" />/js/index.js"></script>
<script type="text/javascript" src="<s:property value="contextPath" />/js/common.js"></script>
<!--[if IE 6]>
<script src="<s:property value="contextPath" />/js/DD_belatedPNG_0.0.8a-min.js"></script>
<script>
  DD_belatedPNG.fix('.png_bg');
</script>
<![endif]-->
</head>
<body>
<div id="wrapper">
  <div class="shadow" <s:if test="pageType==4">style="height:1px;"</s:if> ></div>
  <header id="global-head" <s:if test="pageType==4">style="height:45px;"</s:if>>
    <div>
      <nav class="site-navigation cf">
      <a class="home" href="/">首页</a>
      <a href="<s:url value="/siteMap" escapeAmp="false"></s:url>">网站地图</a>
      <a href="<s:url value="/articleList" escapeAmp="false"><s:param name="fullflag" value="true" /></s:url>">完本小说</a>
      </nav>
      <div class="hd-follow" style="width:197px" >
        <div class="myhide"><a href="#" class="hides">浏览记录</a>
          <div class="hideInfo">
            <ul>
            <s:iterator value="historyList" id="chapter" status="rowstatus">
			    <li class=""><a href="<s:url value="/info" ><s:param name="articleno" value="#chapter.articleno" /></s:url>" class="f14"><s:property  value="#chapter.articlename" /></a><br>
                  最近浏览:<a href="<s:url value="/reader" escapeAmp="false"><s:param name="articleno" value="#chapter.articleno" /><s:param name="chapterno" value="#chapter.chapterno" /></s:url>"><s:property  value="#chapter.chaptername" /></a></li>
		    </s:iterator>
            </ul>
            <p>*提示：浏览记录仅放置最近浏览的10本书籍</p>
            <span>浏览记录是空的</span>
          </div>
        </div>
          <span id="checklogin"  align="right">
          <script>
            $(document).ready(function(){
                $.post('/checklogin',function(data){
                    if(data==null)
                    {
                        $(".hd-follow").width(197);
                    }
                    else
                    {
                    	var html = "你好," + data.loginid ;
                    	if(data.type==30)
                    	{
                    		html = html + '&nbsp;&nbsp;&nbsp;<a href="<s:url value="/admin/index" escapeAmp="false" />" style="color: rgb(240, 240, 240);">管理后台</a>'
                    	}
                        $('#checklogin').html(html + '&nbsp;&nbsp;&nbsp;<a href="<s:url value="/bookcase" escapeAmp="false" />" style="color: rgb(240, 240, 240);">我的书架</a>&nbsp;&nbsp;&nbsp;<a href="<s:url value="/logout" escapeAmp="false" />" style="color: rgb(240, 240, 240);" class="out">退出</a>&nbsp;&nbsp;');
                        $(".hd-follow").width(320);
                    }
                })
            })
        </script>
        <script>
              thisUrl = window.location.href;
              if(thisUrl.indexOf('?backUrl') != -1)
              {
              thisUrl = thisUrl.substring(0,thisUrl.indexOf('?backUrl'));
              }
              logintag =" <a href=\"/login?backUrl="+thisUrl+" \" style=\"color:#F0F0F0\">访客登录</a>&nbsp;&nbsp;";
              registertag =" <a href=\"/register?backUrl="+thisUrl+" \" style=\"color:#F0F0F0\">免费注册</a>&nbsp;&nbsp;";
              document.writeln(logintag);
              document.writeln(registertag);
           </script>
          </span>
        </div>
    </div>
    <s:if test="pageType!=4 || hasActionErrors()">
    <p class="site-logo">
        <a href="/" title="<s:text name="label.system.title" />">
        <img src="<s:property value="contextPath" />/images/logo-medium.png" alt="<s:text name="label.system.title" /> - logo"></a>
    </p>
    <div class="site-search">
       <form action="search" method="get" >
       <input name="key" type="text" id="key" onFocus="this.classname='over';if (value =='这是一个神奇的搜索，请输入小说名或作者名'){value =''}" onBlur="this.classname='input'"  value="这是一个神奇的搜索，请输入小说名或作者名" />
       <input type="submit" id="searchbuttom" value="" style="background:url('<s:property value="contextPath" />/images/search.jpg');border:0px solid;cursor:pointer;" />
      </form>
    </div>
    </s:if>
</header>
<link rel="stylesheet" type="text/css" href="<s:property value="contextPath" />/css/channel-net.css">
  <div id="channel-header" class="clearfix">
    <div class="channel-header-wrapper">
      <nav class="channel-nav">
        <ul class="channel-nav-list">
            <li <s:if test="pageType==1">class="current"</s:if> ><a href="/" title="<s:text name="label.system.name" />"><s:text name="label.system.name" /></a></li>
            <s:iterator value="collections['collectionProperties.article.category']" id="categoryinfo">
                <li <s:if test="key==category">class="current"</s:if> ><a href="<s:url value="/articleList" escapeAmp="false"><s:param name="category" value="key" /></s:url>"><s:property value="value"/></a></li>
            </s:iterator>
        </ul>
      </nav>
    </div>
  </div>
