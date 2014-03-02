$("ol li").ready(function() {
	var obj = $('#sorts ol li');
	obj.each(function(i) {
		this.id = "tab1_div_" + i;
	})
	$('#sorts ol li .order').text("");
	var leng = $('#sorts ol li').length;
	for ( var i = 0; i < leng; i++) {
		if (i > 2) {
			$("#tab1_div_" + i).removeClass("top3");
		}
		var j = i + 1;
		$("#tab1_div_" + i + " .order").append(j + ".");
	}
})
$(function() {
	$(".scoreSet").hover(function() {
		$(".scoreSet ul").show();
	}, function() {
		$(".scoreSet ul").hide();
	})
	$(".scoreSet ul li").click(function() {
		$(".scoreSet span").html($(this).html());
		$(".scoreSet ul").hide();
	})
	$(".myhide").hover(function() {
		$(this).children(".hideInfo").show();
		$(this).children(".hides").addClass("select");
	}, function() {
		$(this).children(".hideInfo").hide();
		$(this).children(".hides").removeClass("select");
	})
	$(".hideInfo ul li a.close").click(function() {
		var len = $(this).parents("ul").children("li").length;
		if (len != 1) {
			$(this).parent("li").remove();
		} else {
			$(this).parents(".hideInfo").children("p").remove();
			$(this).parents(".hideInfo").children("span").show();
			$(this).parent("li").remove();
		}
	})
})
function reg(id) {
	var wid = $('#dialog' + id);
	var left = $(document).width() / 2 - wid.width() / 2 - 25;
	var top = $(window).height() / 2 - wid.height() / 2 - 25
			+ $(document).scrollTop();
	wid.css('left', left);
	wid.css('top', top);
	wid.show();
	$("#mask").show();
}
function closeDialog() {
	var wid = $('#dialog');
	wid.hide();
	var wid2 = $('#mask');
	wid2.hide();
	alert($('#mask').text());
}
function repales_rell1(num, size) {

	for ( var i = 1; i <= size; i++) {
		if (i == num) {
			document.getElementById("cttd" + i).style.display = "block";
			document.getElementById("ask" + i).className = "select";
		} else {
			document.getElementById("cttd" + i).style.display = "none";
			document.getElementById("ask" + i).className = "";
		}
	}
}
function replaces(num, size) {

	for ( var i = 1; i <= size; i++) {
		if (i == num) {
			document.getElementById("content" + i).style.display = "block";
			document.getElementById("for" + i).className = "select";
		} else {
			document.getElementById("content" + i).style.display = "none";
			document.getElementById("for" + i).className = "";
		}
	}
}

