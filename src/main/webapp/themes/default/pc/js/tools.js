// JavaScript Document
//获得鼠标坐标值
function mouseCoords(e) {
	var e = window.event || e;
	if (e.pageX || e.pageY) {
		return {
			x : e.pageX,
			y : e.pageY
		};
	}
	return {
		x : e.clientX + document.body.scrollLeft - document.body.clientLeft,
		y : e.clientY + document.body.scrollTop - document.body.clientTop
	};
	return false;
}

function ForDight(Dight, How) {
	Dight = Math.round(Dight * Math.pow(10, How)) / Math.pow(10, How);
	return Dight;
}
var timer;
function StopScroll() {
	if (timer != '') {
		clearInterval(timer);
	}
	if ($("#mousebox").css('display') != 'none') {
		$("#mousebox").css('display', 'none');
	}
}
function BeginScroll() {
	if ($.cookie("axyx_speed") != null) {
		timer = setInterval("Scrolling()", $.cookie("axyx_speed"));
	} else {
		timer = setInterval("Scrolling()", 50);
	}
}
function setSpeed(o) {
	$.cookie("axyx_speed", o, {
		expires : 7,
		path : '/'
	});
}
function Scrolling() {
	currentpos = document.documentElement.scrollTop;
	window.scroll(0, ++currentpos);
	if (currentpos != document.documentElement.scrollTop) {
		clearInterval(timer);
	}
}

function setBG(o) {
	$("#content").css('backgroundColor', o);
	$.cookie("axyx_background", o, {
		expires : 7,
		path : '/'
	});
}

function setFontColor(o) {
	$("#content").css("color", o);
	$.cookie("axyx_fontColor", o, {
		expires : 7,
		path : '/'
	});
}

function setFontCookie(fs, fb) {
	$.cookie("axyx_fontSize", fs, {
		expires : 7,
		path : '/'
	});
}

function setStyle(o) {
	if (o == '0') {
		$("#wrap").width(950);
	} else if (o == '1') {
		$("#wrap").width(document.body.clientWidth - 20);
	}
	;
	$("#style" + o).val(o);
	$.cookie("axyx_style", o, {
		expires : 7,
		path : '/'
	});
}
$(function() {
	document.onclick = StopScroll;
	document.ondblclick = BeginScroll;
	if ($.cookie("axyx_background")) {
		setBG($.cookie("axyx_background"));
	}
	if ($.cookie("axyx_fontColor")) {
		setFontColor($.cookie("axyx_fontColor"));
		$("#txtcolor").val($.cookie("axyx_fontColor"));
	}
	if ($.cookie("axyx_fontSize")) {
		var fontSize = $.cookie("axyx_fontSize");
		$("#fontsize").val(fontSize);
		$("#content").css({
			fontSize : fontSize + "px"
		});
	} else {
		$("#content").css({
			fontSize : "14px"
		});
	}
	if ($.cookie("axyx_style")) {
		setStyle($.cookie("axyx_style"));
	}
	if ($.cookie("axyx_speed")) {
		$("#sudu a").removeClass("this");
		$("#sudu" + $.cookie("axyx_speed")).addClass("this");
	}
	$("#sudu a").click(function() {
		$("#sudu a").removeClass("this");
		$(this).addClass("this");
	});
	$(":range").rangeinput({
		progress : false,
		change : function(e, i) {
			setFontCookie(i);
			$("#content").css("fontSize", i);
			$(".handle").text(i);
		},
		speed : 2
	});
});

window.onload = function() {
	var str = document.getElementById("content").innerHTML;
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
};