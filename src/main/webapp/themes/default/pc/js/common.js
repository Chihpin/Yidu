jQuery.cookie = function (key, value, options) {

    // key and at least value given, set cookie...
    if (arguments.length > 1 && String(value) !== "[object Object]") {
        options = jQuery.extend({}, options);

        if (value === null || value === undefined) {
            options.expires = -1;
        }

        if (typeof options.expires === 'number') {
            var days = options.expires, t = options.expires = new Date();
            t.setDate(t.getDate() + days);
        }
        value = String(value);

        return (document.cookie = [
            encodeURIComponent(key), '=',
            options.raw ? value : cookie_encode(value),
            options.expires ? '; expires=' + options.expires.toUTCString() : '', 
            options.path ? '; path=' + options.path : '',
            options.domain ? '; domain=' + options.domain : '',
            options.secure ? '; secure' : ''
        ].join(''));
    }

    // key and possibly options given, get cookie...
    options = value || {};
    var result, decode = options.raw ? function (s) { return s; } : decodeURIComponent;
    return (result = new RegExp('(?:^|; )' + encodeURIComponent(key) + '=([^;]*)').exec(document.cookie)) ? decode(result[1]) : null;
};

function cookie_encode(string){
	var decoded = encodeURIComponent(string);
	var ns = decoded.replace(/(%7B|%7D|%3A|%22|%23|%5B|%5D)/g,function(charater){return decodeURIComponent(charater);});
	return ns;
}

function repales_rell1(num, size) {
	for ( var i = 1; i <= size; i++) {
		if (i == num) {
			$("#cttd" + i).show();
			$("#ask" + i).addClass("select");
		} else {
			$("#cttd" + i).hide();
			$("#ask" + i).removeClass("select");
		}
	}
}
function replaces(num, size) {
	for ( var i = 1; i <= size; i++) {
		if (i == num) {
			$("#content" + i).show();
			$("#for" + i).addClass("select");
		} else {
			$("#content" + i).hide();
			$("#for" + i).removeClass("select");
		}
	}
}

//定义方法
var in_array = function(arr) {
	for ( var i = 0, k = arr.length; i < k; i++) {
		if (this == arr[i].articleno) {
			return i;
		}
	}
	return -1;
};
Number.prototype.in_array = in_array;
String.prototype.in_array = in_array;

/** 评论相关 */
var DFL_SUG_TEXT = '既然来了，就留下几句话吧';
$(function() {
	$('.comment_content').bind('focus', function() {
		if ($(this).val() == DFL_SUG_TEXT) {
			$(this).val('');
		}
	}).bind('blur', function() {
		if ($(this).val() == '') {
			$(this).val(DFL_SUG_TEXT);
		}
	});
});

// 统计已输入字数
function stat_text_word(ob) {
	ob = $(ob);
	cnt = ob.val().replace(/\[em\:\d+?\:\]/g, '');
	ob.parent().parent().find('#comment_text_word').html(cnt.length);
}

function onSubmitClick() {
	$.ajax({
		type : "post",
		url : "/reviewList!addReview",
		data : {
			"review" : $("#review").val(),
			"articleno" : $("#articleno").val(),
			"isFromForm" : $("#isFromForm").val()
		},
		async : false,
		success : function(data) {
			if (data == "success") {
				alert("添加评论成功！感谢您的参与：）");
				location.reload();
			} else {
				alert(data);
			}

		},
		error : function() {
			alert("服务器暂时无法处理您的请求，请稍后再试！");
		},
	});
}

//初始化用户菜单
var enableQQLogin = false;
function initUserMenu(){
    $(document).ready(function(){
        $.post(contextPath+'/checklogin',function(data){
            if(data!=null){
               var html = '你好   <a href="'+contextPath+'/user/useredit" style="color: rgb(240, 240, 240);"> '+ data.loginid +"</a>";
                if(enableQQLogin && data.openid==null){
                    html = html + '&nbsp;&nbsp;&nbsp;<a href=\"'+contextPath+'/gotoQQLogin" \"><img src=\"'+contextPath+'/themes/default/pc/images/qq_bind_small.gif\" alt=\"QQ绑定\"></a>';
                }
                if(data.type==30){
                    html = html + '&nbsp;&nbsp;&nbsp;<a href="'+contextPath+'/admin/index" style="color: rgb(240, 240, 240);">管理后台</a>';
                }else if(data.type==20||data.type==40||data.type==41){
                    html = html + '&nbsp;&nbsp;&nbsp;<a href="'+contextPath+'/user/articleList" style="color: rgb(240, 240, 240);">小说管理</a>';
                }
                html = html + '&nbsp;&nbsp;&nbsp;<a href="'+contextPath+'/user/bookcase" style="color: rgb(240, 240, 240);">我的书架</a>';
                html = html + '&nbsp;&nbsp;&nbsp;<a href="'+contextPath+'/user/message" style="color: rgb(240, 240, 240);">消息管理</a>';
                html = html + '&nbsp;&nbsp;&nbsp;<a href="'+contextPath+'/user/subscribe" style="color: rgb(240, 240, 240);">订阅管理</a>';
                html = html + '&nbsp;&nbsp;&nbsp;<a href="'+contextPath+'/logout" style="color: rgb(240, 240, 240);" class="out">退出</a>&nbsp;&nbsp;';
                $('#checklogin').html(html);
            }
        });
    });
}

$(function() {
	$(".scoreSet").hover(function() {
		$(".scoreSet ul").show();
	}, function() {
		$(".scoreSet ul").hide();
	});
	$(".scoreSet ul li").click(function() {
		$(".scoreSet span").html($(this).html());
		$(".scoreSet ul").hide();
	});
	$(".myhide").hover(function() {
		$(this).children(".hideInfo").show();
		$(this).children(".hides").addClass("select");
	}, function() {
		$(this).children(".hideInfo").hide();
		$(this).children(".hides").removeClass("select");
	});
	$(".hideInfo ul li a.close").click(function() {
		var len = $(this).parents("ul").children("li").length;
		if (len != 1) {
			$(this).parent("li").remove();
		} else {
			$(this).parents(".hideInfo").children("p").remove();
			$(this).parents(".hideInfo").children("span").show();
			$(this).parent("li").remove();
		}
	});
})

//初始化阅读履历

function loadReadHistory(){
    var readhistory = $.cookie("readhistory");
    if(! readhistory ){
         readhistory = new Array();
    }else{
         readhistory = JSON.parse(readhistory);
    }
    var html ="";
    for(var i=0; i < readhistory.length;i++){
         html = html + ' <li class=""><a class="close" href = "javascript:return false" articleno = '+readhistory[i].articleno
         +' ></a><a href="'+readhistory[i].infoUrl+'" class="f14">'+readhistory[i].articlename+'</a><br>';
         html = html + ' 最近浏览:<a href="'+readhistory[i].chapterUrl+'" >'+readhistory[i].chaptername+'</a></li>';
    }
	$('#readhistory').html(html);
	$('.hideInfo ul li').hover(function() {
		$(this).addClass('hover');
	}, function() {
		$(this).removeClass('hover');
	});
	$('.hideInfo .close').click(function() {
		var articleno = $(this).attr('articleno');
		var index = articleno.in_array(readhistory);
		if (index != -1) {
			readhistory.splice(index, 1);
		}
		$.cookie("readhistory", JSON.stringify(readhistory), {	path : "/",expires : 365});
		var len = $(this).parents("ul").children("li").length;
		if (len != 1) {
			$(this).parent("li").remove();
		} else {
			$(this).parents(".hideInfo").children("p").remove();
			$(this).parents(".hideInfo").children("span").show();
			$(this).parent("li").remove();
		}
	});
}

/** 初始化处理 */
$(document).ready(function() {
	
	//初始化登录状态
	initUserMenu();
	
	//初始化阅读履历
	loadReadHistory();
	
	// 给评论按钮绑定点击事件
	$("#reviewSubmitbtn").click(onSubmitClick);
	
	//添加页面广告
	addAd();
	
});

function isDefind( obj ){
	return typeof obj != "undefined" ;
}
var adEffective=false;
function addAd(){
	//广告无效的话，直接退出
	if(!adEffective){
		return ;
	}
	if(isDefind($("#index_ad_01")) ){
		$("#index_ad_01").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
				+'<!-- 站群首页广告1 -->'
				+'<ins class="adsbygoogle"'
				+'     style="display:inline-block;width:970px;height:90px"'
				+'     data-ad-client="ca-pub-0918341902931277"'
				+'     data-ad-slot="9296136348"></ins>'
				+'<script>'
				+'(adsbygoogle = window.adsbygoogle || []).push({});'
				+'</script>');
	}
	
	if(isDefind($("#index_ad_02")) ){
		$("#index_ad_02").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
		+'<!-- 站群首页广告2 -->'
		+'<ins class="adsbygoogle"'
		+'     style="display:inline-block;width:970px;height:90px"'
		+'     data-ad-client="ca-pub-0918341902931277"'
		+'     data-ad-slot="3249602748"></ins>'
		+'<script>'
		+'(adsbygoogle = window.adsbygoogle || []).push({});'
		+'</script>');
	}
	
	if(isDefind($("#index_ad_03")) ){
		$("#index_ad_03").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
		+'<!-- 站群首页广告2 -->'
		+'<ins class="adsbygoogle"'
		+'     style="display:inline-block;width:970px;height:90px"'
		+'     data-ad-client="ca-pub-0918341902931277"'
		+'     data-ad-slot="3249602748"></ins>'
		+'<script>'
		+'(adsbygoogle = window.adsbygoogle || []).push({});'
		+'</script>');
	}
	
	if(isDefind($("#list_ad_01")) ){
		$("#list_ad_01").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
		+'<!-- 站群小说列表页广告1 -->'
		+'<ins class="adsbygoogle"'
		+'     style="display:inline-block;width:970px;height:90px"'
		+'     data-ad-client="ca-pub-0918341902931277"'
		+'     data-ad-slot="7260999940"></ins>'
		+'<script>'
		+'(adsbygoogle = window.adsbygoogle || []).push({});'
		+'</script>');
	}
	
	if(isDefind($("#list_ad_02")) ){
		$("#list_ad_02").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
			+'<!-- 站群小说列表页广告2 -->'
			+'<ins class="adsbygoogle"'
			+'     style="display:inline-block;width:970px;height:90px"'
			+'     data-ad-client="ca-pub-0918341902931277"'
			+'     data-ad-slot="8737733146"></ins>'
			+'<script>'
			+'(adsbygoogle = window.adsbygoogle || []).push({});'
			+'</script>');
	}

	if(isDefind($("#list_ad_03")) ){
		$("#list_ad_03").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
				+'<!-- 站群小说列表页广告2 -->'
				+'<ins class="adsbygoogle"'
				+'     style="display:inline-block;width:970px;height:90px"'
				+'     data-ad-client="ca-pub-0918341902931277"'
				+'     data-ad-slot="8737733146"></ins>'
				+'<script>'
				+'(adsbygoogle = window.adsbygoogle || []).push({});'
				+'</script>');
	}
	
	if(isDefind($("#top_ad_01")) ){
		$("#top_ad_01").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
			+'<!-- 站群小说列表页广告2 -->'
			+'<ins class="adsbygoogle"'
			+'     style="display:inline-block;width:970px;height:90px"'
			+'     data-ad-client="ca-pub-0918341902931277"'
			+'     data-ad-slot="8737733146"></ins>'
			+'<script>'
			+'(adsbygoogle = window.adsbygoogle || []).push({});'
			+'</script>');
	}
	//size 778*90
	if(isDefind($("#top_ad_02")) ){
		$("#top_ad_02").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
			+'<!-- 站群小说列表页广告2 -->'
			+'<ins class="adsbygoogle"'
			+'     style="display:inline-block;width:970px;height:90px"'
			+'     data-ad-client="ca-pub-0918341902931277"'
			+'     data-ad-slot="8737733146"></ins>'
			+'<script>'
			+'(adsbygoogle = window.adsbygoogle || []).push({});'
			+'</script>');
	}	
	
	if(isDefind($("#top_ad_03")) ){
		$("#top_ad_03").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
			+'<!-- 站群小说列表页广告2 -->'
			+'<ins class="adsbygoogle"'
			+'     style="display:inline-block;width:970px;height:90px"'
			+'     data-ad-client="ca-pub-0918341902931277"'
			+'     data-ad-slot="8737733146"></ins>'
			+'<script>'
			+'(adsbygoogle = window.adsbygoogle || []).push({});'
			+'</script>');
	}
	
	if(isDefind($("#search_ad_01")) ){
		$("#search_ad_01").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
			+'<!-- 站群小说列表页广告2 -->'
			+'<ins class="adsbygoogle"'
			+'     style="display:inline-block;width:970px;height:90px"'
			+'     data-ad-client="ca-pub-0918341902931277"'
			+'     data-ad-slot="8737733146"></ins>'
			+'<script>'
			+'(adsbygoogle = window.adsbygoogle || []).push({});'
			+'</script>');
	}
	
	if(isDefind($("#search_ad_02")) ){
		$("#search_ad_02").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
			+'<!-- 站群小说列表页广告2 -->'
			+'<ins class="adsbygoogle"'
			+'     style="display:inline-block;width:970px;height:90px"'
			+'     data-ad-client="ca-pub-0918341902931277"'
			+'     data-ad-slot="8737733146"></ins>'
			+'<script>'
			+'(adsbygoogle = window.adsbygoogle || []).push({});'
			+'</script>');
	}

	if(isDefind($("#info_ad_01")) ){
		$("#info_ad_01").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
				+'<!-- 站群小说简介页广告1 -->'
				+'<ins class="adsbygoogle"'
				+'     style="display:inline-block;width:970px;height:90px"'
				+'     data-ad-client="ca-pub-0918341902931277"'
				+'     data-ad-slot="6203069144"></ins>'
				+'<script>'
				+'(adsbygoogle = window.adsbygoogle || []).push({});'
				+'</script>');
	}
	
	if(isDefind($("#info_ad_02")) ){
		$("#info_ad_02").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
				+'<!-- 站群小说简介页广告2 -->'
				+'<ins class="adsbygoogle"'
				+'     style="display:inline-block;width:336px;height:280px"'
				+'     data-ad-client="ca-pub-0918341902931277"'
				+'     data-ad-slot="1633268747"></ins>'
				+'<script>'
				+'(adsbygoogle = window.adsbygoogle || []).push({});'
				+'</script>');
	}
	
	if(isDefind($("#info_ad_03")) ){
		$("#info_ad_03").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
				+'<!-- 站群小说简介页广告1 -->'
				+'<ins class="adsbygoogle"'
				+'     style="display:inline-block;width:970px;height:90px"'
				+'     data-ad-client="ca-pub-0918341902931277"'
				+'     data-ad-slot="6203069144"></ins>'
				+'<script>'
				+'(adsbygoogle = window.adsbygoogle || []).push({});'
				+'</script>');
	}
	
	if(isDefind($("#info_ad_04")) ){
		$("#info_ad_04").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
				+'<!-- 站群小说简介页广告1 -->'
				+'<ins class="adsbygoogle"'
				+'     style="display:inline-block;width:970px;height:90px"'
				+'     data-ad-client="ca-pub-0918341902931277"'
				+'     data-ad-slot="6203069144"></ins>'
				+'<script>'
				+'(adsbygoogle = window.adsbygoogle || []).push({});'
				+'</script>');
	}
	
	if(isDefind($("#info_ad_05")) ){
		$("#info_ad_05").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
				+'<!-- 站群小说简介页广告1 -->'
				+'<ins class="adsbygoogle"'
				+'     style="display:inline-block;width:970px;height:90px"'
				+'     data-ad-client="ca-pub-0918341902931277"'
				+'     data-ad-slot="6203069144"></ins>'
				+'<script>'
				+'(adsbygoogle = window.adsbygoogle || []).push({});'
				+'</script>');
	}
	
	if(isDefind($("#chapterList_ad_01")) ){
		$("#chapterList_ad_01").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
		+'<!-- 站群小说列表页广告1 -->'
		+'<ins class="adsbygoogle"'
		+'     style="display:inline-block;width:970px;height:90px"'
		+'     data-ad-client="ca-pub-0918341902931277"'
		+'     data-ad-slot="7260999940"></ins>'
		+'<script>'
		+'(adsbygoogle = window.adsbygoogle || []).push({});'
		+'</script>');
	}
	
	if(isDefind($("#chapterList_ad_02")) ){
		$("#chapterList_ad_02").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
		+'<!-- 站群小说列表页广告1 -->'
		+'<ins class="adsbygoogle"'
		+'     style="display:inline-block;width:970px;height:90px"'
		+'     data-ad-client="ca-pub-0918341902931277"'
		+'     data-ad-slot="7260999940"></ins>'
		+'<script>'
		+'(adsbygoogle = window.adsbygoogle || []).push({});'
		+'</script>');
	}
	
	if(isDefind($("#chapterList_ad_03")) ){
		$("#chapterList_ad_03").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
		+'<!-- 站群小说列表页广告1 -->'
		+'<ins class="adsbygoogle"'
		+'     style="display:inline-block;width:970px;height:90px"'
		+'     data-ad-client="ca-pub-0918341902931277"'
		+'     data-ad-slot="7260999940"></ins>'
		+'<script>'
		+'(adsbygoogle = window.adsbygoogle || []).push({});'
		+'</script>');
	}
	

	if(isDefind($("#reader_ad_01")) ){
		$("#reader_ad_01").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
				+'<!-- 站群小说阅读页广告1 -->'
				+'<ins class="adsbygoogle"'
				+'     style="display:inline-block;width:970px;height:90px"'
				+'     data-ad-client="ca-pub-0918341902931277"'
				+'     data-ad-slot="2691199547"></ins>'
				+'<script>'
				+'(adsbygoogle = window.adsbygoogle || []).push({});'
				+'</script>');
	}

	if(isDefind($("#reader_ad_02")) ){
		$("#reader_ad_02").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
				+'<!-- 站群小说阅读页广告1 -->'
				+'<ins class="adsbygoogle"'
				+'     style="display:inline-block;width:970px;height:90px"'
				+'     data-ad-client="ca-pub-0918341902931277"'
				+'     data-ad-slot="2691199547"></ins>'
				+'<script>'
				+'(adsbygoogle = window.adsbygoogle || []).push({});'
				+'</script>');
	}
	
	if(isDefind($("#reader_ad_03")) ){
		$("#reader_ad_03").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
				+'<!-- 站群小说阅读页广告2 -->'
				+'<ins class="adsbygoogle"'
				+'     style="display:inline-block;width:970px;height:90px"'
				+'     data-ad-client="ca-pub-0918341902931277"'
				+'     data-ad-slot="5644665949"></ins>'
				+'<script>'
				+'(adsbygoogle = window.adsbygoogle || []).push({});'
				+'</script>');
	}
	
	if(isDefind($("#reader_ad_04")) ){
		//将来放对联广告
	}
	
	if(isDefind($("#review_ad_01")) ){
		$("#review_ad_01").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
				+'<!-- 站群小说简介页广告1 -->'
				+'<ins class="adsbygoogle"'
				+'     style="display:inline-block;width:970px;height:90px"'
				+'     data-ad-client="ca-pub-0918341902931277"'
				+'     data-ad-slot="6203069144"></ins>'
				+'<script>'
				+'(adsbygoogle = window.adsbygoogle || []).push({});'
				+'</script>');
	}
	
	if(isDefind($("#review_ad_02")) ){
		$("#review_ad_02").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
				+'<!-- 站群小说简介页广告1 -->'
				+'<ins class="adsbygoogle"'
				+'     style="display:inline-block;width:970px;height:90px"'
				+'     data-ad-client="ca-pub-0918341902931277"'
				+'     data-ad-slot="6203069144"></ins>'
				+'<script>'
				+'(adsbygoogle = window.adsbygoogle || []).push({});'
				+'</script>');
	}
	
	if(isDefind($("#review_ad_03")) ){
		$("#review_ad_03").html('<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>'
				+'<!-- 站群小说简介页广告1 -->'
				+'<ins class="adsbygoogle"'
				+'     style="display:inline-block;width:970px;height:90px"'
				+'     data-ad-client="ca-pub-0918341902931277"'
				+'     data-ad-slot="6203069144"></ins>'
				+'<script>'
				+'(adsbygoogle = window.adsbygoogle || []).push({});'
				+'</script>');
	}
}