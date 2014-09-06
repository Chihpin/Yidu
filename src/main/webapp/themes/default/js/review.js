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

$(document).ready(function() {
	$("#submitbtn").click(onSubmitClick);
});

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