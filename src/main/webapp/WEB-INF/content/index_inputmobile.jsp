<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><s:text name="label.system.title" /></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="keywords" content="<s:text name="label.system.siteKeywords" />" />
    <meta name="description" content="<s:text name="label.system.siteDescription" />" />	
	<link href="<s:property value="contextPath" />/css/mobile/yidumobile.css" rel="stylesheet" type="text/css">
	
	<script src="<s:property value="contextPath" />/js/mobile/common.js" type="text/javascript"></script>
	<script src="<s:property value="contextPath" />/js/mobile/Utility.js" type="text/javascript"></script>
	
	<script src="<s:property value="contextPath" />/js/lib/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="<s:property value="contextPath" />/js/lib/jquery-ui.js" type="text/javascript"></script>

    <style type="text/css">
	    .swipe {overflow: hidden;visibility: hidden;position: relative;}
	    .swipe-wrap {overflow: hidden;position: relative;}
	    .swipe-wrap > div {float:left;width:100%;position: relative;}
	    .swipe-wrap div img{ width: 100%;}
    </style>
    <script src="<s:property value="contextPath" />/js/mobile/a.js" type="text/javascript"></script>
</head>
<body style="font-family: monospace; width: 320px; border: 2px solid black;">
      <form action="<s:property value="contextPath" />/search" method="get" >
    
<header class="topheadbox">
    <div class="tophead">
        <div class="left">
            <a class="logo" href="<s:property value="contextPath" />/"><s:text name="label.system.title" /></a>
        </div>
        <div class="right" id="loginbox">
            <a href="javascript:MLogin.Login();" style="margin-right: 20px;">登录</a>
            <a href="javascript:MLogin.Register();">注册</a></div>
    </div>
    <nav class="topnav">
        <a href="javascript:Common.toggleCategory(1);" class="open">分类<em></em></a> 
        <a href="http://m.qidian.com/bookstore.aspx">书库</a> 
        <a href="http://m.qidian.com/top.aspx">排行</a> 
        <a href="<s:url value="/user/bookcase" />">书架</a>
        <a href="#">充值</a>
        
        <div class="catalogbox" id="catetop" style="display: none;">
            <div class="catalog">
                <h3>
                    男生小说</h3>
                <a href="http://m.qidian.com/categorytotal.aspx?key=21">玄幻</a> <a href="http://m.qidian.com/categorytotal.aspx?key=1">奇幻</a>
                <a href="http://m.qidian.com/categorytotal.aspx?key=2">武侠</a> <a href="http://m.qidian.com/categorytotal.aspx?key=22">仙侠</a>
                <a href="http://m.qidian.com/categorytotal.aspx?key=4">都市</a>
                <!--<a href="/categorytotal.aspx?key=15">青春</a>-->
                <a href="http://m.qidian.com/categorytotal.aspx?key=5">历史</a> <a href="http://m.qidian.com/categorytotal.aspx?key=6">军事</a>
                <a href="http://m.qidian.com/categorytotal.aspx?key=7">游戏</a> <a href="http://m.qidian.com/categorytotal.aspx?key=8">竞技</a>
                <a href="http://m.qidian.com/categorytotal.aspx?key=9">科幻</a> <a href="http://m.qidian.com/categorytotal.aspx?key=10">灵异</a>
            </div>
            <div class="catalogmm">
                <h3>
                    女生小说</h3>
                <a href="http://m.qidian.com/categorytotal.aspx?key=71">古代言情</a> <a href="http://m.qidian.com/categorytotal.aspx?key=72">现代言情</a>
                <a href="http://m.qidian.com/categorytotal.aspx?key=73">浪漫青春</a> <a href="http://m.qidian.com/categorytotal.aspx?key=74">玄幻仙侠</a>
                <a href="http://m.qidian.com/categorytotal.aspx?key=75">异界奇幻</a> <a href="http://m.qidian.com/categorytotal.aspx?key=76">星际科幻</a>
                <a href="http://m.qidian.com/categorytotal.aspx?key=77">游戏竞技</a> <a href="http://m.qidian.com/categorytotal.aspx?key=78">灵异推理</a>
                <a href="http://m.qidian.com/categorytotal.aspx?key=79">同人美文</a>
            </div>
        </div>
    </nav>
</header>

<script type="text/javascript">
    $(document).ready(function() {
        var userdeclare = '';
        var isEnableWAPOALogin = 1;
        if (typeof userdeclare == undefined || userdeclare != 'Yeah') {
            isEnableWAPOALogin = 1;
        } else {
            isEnableWAPOALogin = 0;
        }
        MLogin.CheckLogin(isEnableWAPOALogin);
    });
</script>

<div class="search">
    <div class="search-left">
        <input type="text" value="" class="search-text">
    </div>
    <div class="search-btn">
        <input type="button" value="快速搜书" class="search-top-btn" onclick="Common.toSearch(this);">
    </div>
</div>

<script type="text/javascript">
    $(".search .search-left .search-text").each(function(i, o) {
        $(o).unbind("keydown").bind("keydown", function(e) {
            if (e && e.keyCode == 13) {
                e.preventDefault();
                Common.toSearch($(o).parent().next(".search-btn").find(".search-top-btn"));
            }
        });
    });
</script>


        <script type="text/javascript">
			(function($){
				$(window).on('load',function(){
				    $("#dvtoptab li").click(function() {
				        var index = $("#dvtoptab li").index(this);
				        $("#dvtoptab .list_cont").css("display","none");
				        $("#dvtoptab .list_cont").eq(index).css("display","block");
				        $("#dvtoptab li").removeClass('hover');
				        $(this).addClass("hover")
				    });
					
				    
				    
				    
                    $("#dvcatebooks li").click(function() {
                    	var len = $("#dvcatebooks .sliderbarc ul li").length;
                        $("#dvcatebooks .nav_title ul li").css("width",
                                $(window).width() / 3);
                        $("#dvcatebooks .nav_title").css(
                                "width",
                                ($(window).width() / 3)
                                        * $("#dvcatebooks .nav_title ul li").length);
                        $("#dvcatebooks .navb").css("width", $(window).width());
                        $("#dvcatebooks .nav_title").css(
                                {
                                    left : (Home.GetRealIndex(pageIndex) - 1)
                                            * ($(window).width() / 3) * -1
                                });
                        $("#dvcatebooks .nav_title ul li").eq(Home.GetRealIndex(pageIndex))
                                .addClass("hover");
                    	
                        var index = $("#dvcatebooks li").index(this);
                        $("#dvcatebooks .list_cont").css("display","none");
                        $("#dvcatebooks .list_cont").eq(index).css("display","block");
                        $("#dvcatebooks li").removeClass('hover');
                        $(this).addClass("hover")
                    });
					
					
                    $("#dvrecbooks li").click(function() {
                        var index = $("#dvrecbooks li").index(this);
                        $("#dvrecbooks .list_cont").css("display","none");
                        $("#dvrecbooks .list_cont").eq(index).css("display","block");
                        $("#dvrecbooks li").removeClass('hover');
                        $(this).addClass("hover")
                    });
				});
				})(jQuery);
		</script>

    <div class="listbox"  id="dvrecbooks">
        <div class="nav sliderbar" id="adtabs">
            <ul>
                <li class="hover"><a href= "javascript:void(0);">热点</a></li>
                <li><a href= "javascript:void(0);">猜你喜欢</a></li>
                <li><a href= "javascript:void(0);">潜力推荐</a></li>
            </ul>
        </div>
        <div class="list_cont slider">
            <div class="ft_book">
            <s:set name="hotList" value="blocks.index_hot_list_mobile"/>
            <s:iterator value="#hotList" id="article">
                <div class="box">
                    <div class="left">
	                    <div class="pic"><a href="<s:url value="/info" escapeAmp="false" ><s:param name="subdir" value="#article.subdir" /><s:param name="articleno" value="#article.articleno" /></s:url>">
	                                <img src="<s:property value="contextPath" /><s:property value="#article.imgUrl" />" width="80" height="100" alt="<s:property  value="#article.articlename" />"></a></div>
                    </div>
                    <div class="righta">
                        <div class="name">
                            <a href="<s:url value="/info" escapeAmp="false" ><s:param name="subdir" value="#article.subdir" /><s:param name="articleno" value="#article.articleno" /></s:url>"><s:property  value="#article.articlename" /></a>
                                <span class="aut"><a href="<s:url value="/articleList" ><s:param name="author" value="#article.author" /></s:url>"><s:property value="#article.author" /></a>著</span>
                        </div>
                        <div class="txt">
                            <s:property  value="#article.introOmit" />
                        </div>
                        <div class="bookLink">
                            <a href="<s:url value="/reader" escapeAmp="false"><s:param name="subdir" value="#article.subdir" /><s:param name="articleno" value="#article.articleno" /><s:param name="chapterno" value="#article.lastchapterno" /></s:url>">【立即阅读】</a>
                            <a id="btn_2496893" href="<s:url value="/user/bookcase!add" escapeAmp="false"><s:param name="articleno" value="#article.articleno" /></s:url>" sdolog="fav" bookid="2496893">【加入书架】</a>
                        </div>
                    </div>
                </div>
             </s:iterator>
            </div>
        </div>
        <!----猜你喜欢内容-->
        <div class="list_cont slider" style="display: none;">
            <div class="ft_book">
                
            </div>
        </div>
        <!----潜力推荐-->
        <div class="list_cont slider" id="adnewsbox" style="display: none;">
            <div class="ft_book">
            <s:set name="hotList" value="blocks.index_potential_list_mobile"/>
            <s:iterator value="#hotList" id="article">
                <div class="box">
                    <div class="left">
                        <div class="pic"><a href="<s:url value="/info" escapeAmp="false" ><s:param name="subdir" value="#article.subdir" /><s:param name="articleno" value="#article.articleno" /></s:url>">
                                    <img src="<s:property value="contextPath" /><s:property value="#article.imgUrl" />" width="80" height="100" alt="<s:property  value="#article.articlename" />"></a></div>
                    </div>
                    <div class="righta">
                        <div class="name">
                            <a href="<s:url value="/info" escapeAmp="false" ><s:param name="subdir" value="#article.subdir" /><s:param name="articleno" value="#article.articleno" /></s:url>"><s:property  value="#article.articlename" /></a>
                                <span class="aut"><a href="<s:url value="/articleList" ><s:param name="author" value="#article.author" /></s:url>"><s:property value="#article.author" /></a>著</span>
                        </div>
                        <div class="txt">
                            <s:property  value="#article.introOmit" />
                        </div>
                        <div class="bookLink">
                            <a href="<s:url value="/reader" escapeAmp="false"><s:param name="subdir" value="#article.subdir" /><s:param name="articleno" value="#article.articleno" /><s:param name="chapterno" value="#article.lastchapterno" /></s:url>">【立即阅读】</a>
                            <a id="btn_2496893" href="<s:url value="/user/bookcase!add" escapeAmp="false"><s:param name="articleno" value="#article.articleno" /></s:url>" sdolog="fav" bookid="2496893">【加入书架】</a>
                        </div>
                    </div>
                </div>
             </s:iterator>
            </div>
        </div>
    </div>
    
    <div id="homewrap">
        <div class="listbox" id="dvcatebooks">
            <div class="navb sliderbarc" style="width: 1351px;">
                <span class="pre"><a href="javascript:void(0);"></a></span>
                <div class="nav_title" style="width: 5404px; left: -2251.6666666666665px;">
                    <ul>
                        <li style="width: 450.3333333333333px;"><a href="javascript:void(0);">玄幻奇幻</a></li>
                        <li style="width: 450.3333333333333px;"><a href="javascript:void(0);">武侠仙侠</a></li>
                        <li style="width: 450.3333333333333px;"><a href="javascript:void(0);">都市言情</a></li>
                        <li style="width: 450.3333333333333px;"><a href="javascript:void(0);">历史军事</a></li>
                        <li style="width: 450.3333333333333px;"><a href="javascript:void(0);">游戏竞技</a></li>
                        <li style="width: 450.3333333333333px;"><a href="javascript:void(0);">科幻灵异</a></li>
                        <li class="hover" style="width: 450.3333333333333px;"><a href="javascript:void(0);">玄幻奇幻</a></li>
                        <li style="width: 450.3333333333333px;"><a href="javascript:void(0);">武侠仙侠</a></li>
                        <li style="width: 450.3333333333333px;"><a href="javascript:void(0);">都市言情</a></li>
                        <li style="width: 450.3333333333333px;"><a href="javascript:void(0);">历史军事</a></li>
                        <li style="width: 450.3333333333333px;"><a href="javascript:void(0);">游戏竞技</a></li>
                        <li style="width: 450.3333333333333px;"><a href="javascript:void(0);">科幻灵异</a></li>
                    </ul>
                </div>
                <span class="next"><a href="javascript:void(0);"></a></span>
            </div>
            <div class="list_cont slider" style="">
	            <div class="ft_book">
		            <div class="box">
			            <div class="left">
				            <div class="pic">
				            <a href="http://m.qidian.com/book/showbook.aspx?bookid=3013862"><img src="./小说阅读-起点小说中文网手机版_files/3013862.jpg" onerror="this.src=&#39;http://image.cmfu.com/Books/1.jpg&#39;;" width="80" height="100" alt=""></a>
				            </div>
			            </div>
			            <div class="righta">
				            <div class="name">
				            <a href="http://m.qidian.com/book/showbook.aspx?bookid=3013862">天择</a>
				            <span class="aut">
				            <a href="http://m.qidian.com/authorindex.aspx?id=1083493">卷土</a> 著</span>
				            </div>
			                <div class="txt">当逢乱世，百家争鸣，看少年领千军万众，混淆天机，主宰苍生沉浮！</div>
			            </div>
		            </div>
	            </div>
	            <div class="txt_tj_book">
	                <div class="list">
		                <ul>
				            <li onclick="location.href=&#39;/book/showbook.aspx?bookid=3017651&#39;;">
				            <span class="type">[异界大陆]</span>
				            <span style="color: #004d00;">带着量子计算机，成为法神！</span>
				            </li>
				            <li onclick="location.href=&#39;/book/showbook.aspx?bookid=3011049&#39;;">
				            <span class="type">[东方玄幻]</span>
				            <span style="color: #004d00;">《最强剑神系统》剑神之技尽重现</span>
				            </li>
				            <li onclick="location.href=&#39;/book/showbook.aspx?bookid=3058009&#39;;">
				            <span class="type">[异界大陆]</span>
				            <span style="color: #004d00;">一不小心《带着火影系统到异界》</span>
				            </li>
				            <li onclick="location.href=&#39;/book/showbook.aspx?bookid=3057601&#39;;">
				            <span class="type">[东方玄幻]</span>
				            <span style="color: #004d00;">重生始皇，带领大秦铁骑横扫八荒</span>
				            </li>
		                </ul>
		            </div>
	            </div>
            </div>
            <div class="list_cont slider" style="display: none;">
            </div>
            <div class="list_cont slider" style="display: none;">
            </div>
            <div class="list_cont slider" style="display: none;">
            </div>
            <div class="list_cont slider" style="display: none;">
            </div>
            <div class="list_cont slider" style="display: none;">
            </div>
        </div>
 </div>
        <div class="listbox" id="dvtoptab">
            <div class="nav sliderbar">
                <ul> 
                    <li class="hover"><a href="javascript:void(0);">更新榜</a></li>
                    <li><a href="javascript:void(0);">点击榜</a></li>
                    <li><a href="javascript:void(0);">推荐榜</a></li>
                </ul>
            </div>
            <div class="slider list_cont">
                <div class="txt_tj_book">
                    <div class="list">
                        <ul>
                        <s:set name="lastupdateList" value="blocks.index_lastupdate_list_mobile"/>
                            <s:iterator value="#lastupdateList" id="article" status="rowstatus">
                            <li onclick="location.href=&#39;<s:url value="/info" escapeAmp="false" ><s:param name="subdir" value="#article.subdir" /><s:param name="articleno" value="#article.articleno" /></s:url>&#39;;">
                            <s:if test = "#rowstatus.index < 3"><span class="num num<s:property value="#rowstatus.index+1" />" href="javascript:void(0);"></s:if>
                            <s:else><span class="num" href="javascript:void(0);"></s:else><s:property value="#rowstatus.index+1" /></span>
                                <a href="<s:url value="/info" escapeAmp="false" ><s:param name="subdir" value="#article.subdir" /><s:param name="articleno" value="#article.articleno" /></s:url>" class="bookname"><s:property  value="#article.articlename" /></a>
                                <span class="sub_txt"><a href="<s:url value="/reader" escapeAmp="false"><s:param name="subdir" value="#article.subdir" /><s:param name="articleno" value="#article.articleno" /><s:param name="chapterno" value="#article.lastchapterno" /></s:url>" title="<s:property value="#article.lastchapter" />" ><s:property value="#article.lastchapter" /></a></span></li>
                            </s:iterator>
                            <li onclick="location.href=&#39;/topdetail.aspx?toptype=-105&amp;returnurl=http%3a%2f%2fm.qidian.com%2fDefault.aspx&#39;;" style="text-align: right;"><a href="javascript:void(0);">点击查看更多</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="slider list_cont" style="display: none;">
                <div class="txt_tj_book">
                    <div class="list">
	                    <ul>
	                        <s:set name="clickList" value="blocks.index_click_list_mobile"/>
                            <s:iterator value="#clickList" id="article" status="rowstatus">
                            <li onclick="location.href=&#39;<s:url value="/info" escapeAmp="false" ><s:param name="subdir" value="#article.subdir" /><s:param name="articleno" value="#article.articleno" /></s:url>&#39;;">
                            <s:if test = "#rowstatus.index < 3"><span class="num num<s:property value="#rowstatus.index+1" />" href="javascript:void(0);"></s:if>
                            <s:else><span class="num" href="javascript:void(0);"></s:else><s:property value="#rowstatus.index+1" /></span>
                                <a href="<s:url value="/info" escapeAmp="false" ><s:param name="subdir" value="#article.subdir" /><s:param name="articleno" value="#article.articleno" /></s:url>" class="bookname"><s:property  value="#article.articlename" /></a>
                                <span class="sub_txt">点击数：<s:property value="#article.dayvisit" /></span></li>
                            </s:iterator>
                            <li onclick="location.href=&#39;/topdetail.aspx?toptype=-105&amp;returnurl=http%3a%2f%2fm.qidian.com%2fDefault.aspx&#39;;" style="text-align: right;"><a href="javascript:void(0);">点击查看更多</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="slider list_cont" style="display: none;">
                <div class="txt_tj_book">
                    <div class="list">
                        <ul>
                            <s:set name="voteList" value="blocks.index_vote_list_mobile"/>
                            <s:iterator value="#voteList" id="article" status="rowstatus">
                            <li onclick="location.href=&#39;<s:url value="/info" escapeAmp="false" ><s:param name="subdir" value="#article.subdir" /><s:param name="articleno" value="#article.articleno" /></s:url>&#39;;">
                            <s:if test = "#rowstatus.index < 3"><span class="num num<s:property value="#rowstatus.index+1" />" href="javascript:void(0);"></s:if>
                            <s:else><span class="num" href="javascript:void(0);"></s:else><s:property value="#rowstatus.index+1" /></span>
                                <a href="<s:url value="/info" escapeAmp="false" ><s:param name="subdir" value="#article.subdir" /><s:param name="articleno" value="#article.articleno" /></s:url>" class="bookname"><s:property  value="#article.articlename" /></a>
                                <span class="sub_txt">推荐数：<s:property value="#article.weekvote" /></span></li>
                            </s:iterator>
                            <li onclick="location.href=&#39;/topdetail.aspx?toptype=-105&amp;returnurl=http%3a%2f%2fm.qidian.com%2fDefault.aspx&#39;;" style="text-align: right;"><a href="javascript:void(0);">点击查看更多</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    
<div class="search">
    <div class="search-left">
        <input type="text" value="" class="search-text">
    </div>
    <div class="search-btn">
        <input type="button" value="快速搜书" class="search-top-btn" onclick="Common.toSearch(this);">
    </div>
</div>

<script type="text/javascript">
    $(".search .search-left .search-text").each(function(i, o) {
        $(o).unbind("keydown").bind("keydown", function(e) {
            if (e && e.keyCode == 13) {
                e.preventDefault();
                Common.toSearch($(o).parent().next(".search-btn").find(".search-top-btn"));
            }
        });
    });
</script>
    
<nav class="btmnav">
    <a href="javascript:Common.toggleCategory(0);" class="close">分类<em></em></a> 
    <a href="http://m.qidian.com/bookstore.aspx">书库</a> 
    <a href="http://m.qidian.com/top.aspx">排行</a> 
    <a href="http://m.qidian.com/bookShelf/bookcase.aspx">书架</a>
    <a href="http://m.qidian.com/Recharge/RechargeMode.aspx">充值</a>
    <div class="catalogbox" id="catebtm" style="display: none;">
        <div class="catalog">
            <h3>男生小说</h3>
            <a href="http://m.qidian.com/categorytotal.aspx?key=21">玄幻</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=1">奇幻</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=2">武侠</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=22">仙侠</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=4">都市</a> 
            <!--<a href="/categorytotal.aspx?key=15">青春</a>-->
            <a href="http://m.qidian.com/categorytotal.aspx?key=5">历史</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=6">军事</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=7">游戏</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=8">竞技</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=9">科幻</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=10">灵异</a>
        </div>
        <div class="catalogmm">
            <h3>女生小说</h3>
            <a href="http://m.qidian.com/categorytotal.aspx?key=71">古代言情</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=72">现代言情</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=73">浪漫青春</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=74">玄幻仙侠</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=75">异界奇幻</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=76">星际科幻</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=77">游戏竞技</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=78">灵异推理</a> 
            <a href="http://m.qidian.com/categorytotal.aspx?key=79">同人美文</a>
        </div>
    </div>
</nav>
    
<div id="mFooterAd" class="float-ad" style="display: none;">
    <a class="close" href="javascript:void(0);"><img src="./小说阅读-起点小说中文网手机版_files/close121203.png" alt="关闭按钮" width="20" height="20"></a>
    <div width="360" height="60" style="text-align:center">  </div>
</div>

<script type="text/javascript">
(function(){
    var duration = 0;
    var isShowAd = 0;
    var storage = window.sessionStorage;
    var storage_key = "_mobile_ad";
    var footerAd = $("#mFooterAd");
    if(isShowAd === 0 || (storage && (storage.getItem(storage_key) === "1"))){
        footerAd.hide();
        return;
    }
    footerAd.find("div img:first").attr("onload", function(){
        if(duration > 0){
            setTimeout(function(){
                footerAd.hide();
            }, duration * 1000);
        }
    });
    if(footerAd.is(":visible")){
        var close = footerAd.find(".close");
        close.click(function(){
            footerAd.hide();
            storage && (storage.setItem(storage_key, "1"));
        });
        var top = footerAd.height() - close.innerHeight();
        close.css("top", top / 2);
        if(location.href.toLocaleLowerCase().indexOf("bookstore.aspx") > -1){
            footerAd.css("zIndex", 10000);
        }
    }
})();
</script>
    

    <!--SDO数据分析统计代码 begin -->

    <script type="text/javascript" src="<s:property value="contextPath" />/js/mobile/official_install_beacon.js"></script><script src="<s:property value="contextPath" />/js/mobile/sdo_beacon.js" type="text/javascript"></script>

    <!--SDO数据分析统计代码 end -->
    <!--起点统计代码 begin -->

    <script type="text/javascript" src="http://uedas.qidian.com/javascript/statlib.js"></script>

    <script type="text/javascript">        CmfuTracker();</script>

    <!--起点统计代码 end -->
    
<div class="footer" id="footer">
    <div class="box">
        <a href="./小说阅读-起点小说中文网手机版_files/小说阅读-起点小说中文网手机版.htm">m.qidian.com</a> <a href="http://m.qidian.com/help.aspx">帮助</a> <a href="http://m.qidian.com/FeedBack.aspx">反馈</a><br>
        <a id="footerPc" href="http://www.qidian.com/mtransfer.aspx?t=m&returnurl=http%3A%2F%2Fwww.qidian.com" rel="nofollow">电脑版</a> <a href="./小说阅读-起点小说中文网手机版_files/小说阅读-起点小说中文网手机版.htm">炫彩版</a> <a href="http://m.qidian.com/download.html">
            客户端</a> <a>[03-07 22:43]</a>
    </div>
    
    <div>

        <script type="text/javascript">            _SNYU_.Alliance.init('202012', { 'autosize': true });</script>

    </div>
    
</div>

<script type="text/javascript">
    (function() {
        // 根据路径配置，显示header和footer
        for (var i = 0, count = PathJson.length; i < count; i++) {
            var o = PathJson[i];
            if (location.pathname.toLowerCase() === o.path.toLowerCase()) {
                if (!o.f) $("#footer").css("display", "none");
            }
        }
        try {
            $.ajax({
                type: "post",
                dataType: "json",
                url: "/Ajax/Index.ashx",
                data: { ajaxMethod: "mp" },
                error: function(data) {
                },
                success: function(data) {
                    if (data && data.IsSuccess) {
                        var list = data.ReturnObject;
                        if (list && list.length > 0) {
                            for (var i = 0; i < list.length; i++) {
                                $(document.body).append('<img id="img_mp_' + i + '" src="" alt="" style="display:none" width="0" height="0"/>');
                                $("#img_mp_" + i).attr("src", list[i]);
                            }
                        }
                    }
                }
            });
        }
        catch (e) {
        }
    })();
</script>
    </form>
<div>
<object id="ClCache" click="sendMsg" host="" width="0" height="0"></object>
</div>
<div id="back2top" class="back2top" onclick="window.scrollTo(0, 0);" style="display: none;"></div>
</body>
</html>