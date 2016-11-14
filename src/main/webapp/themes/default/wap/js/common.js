/// <reference path="jQuery.Min.js" />

(function () {

    window.Util = {

        Alert: function (msg, callback) {

            var htm = [
                '<div id="_jxalertbox" class="alertbox">',
                '    <div class="alert-div"><p>' + msg + '</p></div>',
                '</div>'
            ];

            $("#_jxalertbox").remove();
            $(htm.join("")).appendTo("body");

            var box = $("#_jxalertbox");
            box.css({ top: ($(window).height() - box.height() - 20 + $(window).scrollTop()) + "px" });
            
            setTimeout(function () {
                $("#_jxalertbox").remove();
                if (typeof callback == "function") {
                    callback();
                }
            }, 2000);

        },

        Loading: function (msg) {
            var htm = [
                '<div id="_jxloadingbox" class="loadingbox">',
                '    <div class="loading-div">',
                '    </div>',
                '</div>'
            ];
            $("#_jxloadingbox").remove();
            $(htm.join("")).appendTo("body");
            var lbox = $("#_jxloadingbox");
            lbox.css({ top: ($(window).height() - lbox.height()) * 0.4 + $(window).scrollTop() + "px" });
        },

        LoadingClear: function () {
            $("#_jxloadingbox").remove();
        },

        _Cookie: function (name, value, options) {

            if (typeof value != 'undefined') {

                options = options || {};
                if (value === null) {
                    value = '';
                    options.expires = -1;
                }

                var expires = '';

                if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
                    var date;
                    if (typeof options.expires == 'number') {
                        date = new Date();
                        date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
                    } else {
                        date = options.expires;
                    }
                    expires = '; expires=' + date.toUTCString();
                }
                var path = options.path ? '; path=' + (options.path) : '';
                var domain = options.domain ? '; domain=' + (options.domain) : '';
                var secure = options.secure ? '; secure' : '';
                document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
            } else {
                var cookieValue = '';
                if (document.cookie && document.cookie != '') {
                    var cookies = document.cookie.split(';');
                    for (var i = 0; i < cookies.length; i++) {
                        cookie = cookies[i].replace(/^\s+|\s+$/g, '');
                        if (cookie.substring(0, name.length + 1) == (name + '=')) {
                            cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                            break;
                        }
                    }
                }
                return cookieValue;
            }
        },

        CookieValue: function (cookieName) {
            return this._Cookie(cookieName);
        },

        CookieWrite: function (name , value , expires) {
            this._Cookie(name, value, { path: '/', expires: expires });
        },

        NumberFormat: function (number) {
            var number = parseInt(number);
            if (number > 10000) return parseInt(number / 10000) + "万";
            return number + "";
        },

        goBack : function () {          
            if (document.referrer == "") {
                location.href = "index";
                return;
            }
            history.go(-1);
        }
    };

    /*移除数组元素 @index:数组索引*/
    Array.prototype.remove = function (index) {
        if (index < 0) return this;
        return this.slice(0, index).concat(this.slice(index + 1, this.length));
    }
})();


(function () {

    window.Site = {
        /*接口地址*/
        Host: "/ajaxService",
        /*公共操作数*/
        options: {
            codeFlag: 0,
            delay: 60,
            timer: null,
            checkTimer: null
        },
        /*设置窗体标题*/
        _setBoxTitle: function (title) {
            $("#_winbox .win-header span").html(title);
        },
        /*重置窗体Top位置*/
        _resetBoxTop: function (top) {
            $("#_winbox").css({ top: top + "px" });
        },
        /*重置位置*/
        _resetPos: function () {
            var box = $("#_winbox");
            var top = ($(window).height() - box.height()) * 0.4;
            if (top < 0) top = 0;
            box.css({ top: top + "px" });
        },
        /*显示错误信息*/
        showMsg: function (id, message, flag) {
            var color = flag ? "#00F" : "#F00";
            $(id).html('<span style="color:' + color + '">' + message + '</span>');
            setTimeout(function () { $(id).html(""); }, 1500);
        },
        /*显示浮层*/
        showWinbox: function (title, html, cover , goHis) {

            var htm = [];
            htm.push('<div id="_winbox" class="win-box">');
            htm.push('    <div class="win-padding">');
            htm.push('        <div class="win-main">');
            htm.push('            <div class="win-header">');
            htm.push('                <span>' + title + '</span>');
            htm.push('                <button class="win-box-close">×</button>');
            htm.push('            </div>');
            htm.push('              <div class="win-line"></div>');
            htm.push('             <div id="_winBody">' + html + '</div>');
            htm.push('        </div>');
            htm.push('    </div>');
            htm.push('</div>');
            $("#_winbox").remove();
            $(htm.join("")).appendTo("body");

            Site._resetPos();
            $("button[class=win-box-close]").click(function () {
                Site.closeWinbox(goHis);
            });

            if (cover) {
                $("#_winCover").remove();
                $('<div id="_winCover" class="win-cover"></div>').appendTo("body");
                $("#_winCover").height($(document).height());
            }
        },
        /*关闭浮层*/
        closeWinbox: function (goHis) {
            $("#_winbox").remove();
            $("#_winCover").remove();
            if (Site.options.timer) clearTimeout(Site.options.timer);
            if (Site.options.checkTimer) clearInterval(Site.options.checkTimer);
            if (typeof goHis != "undefined" && goHis == 1)
                history.go(-1);
        },
        /*登陆框*/
        showLoginbox: function (callback) {

            var htm = [
                '<ul class="group2 m10">',
                '    <li><input id="_txtLoginName" class="input r3" type="text" data-text="账号" value="账号" /></li>',
                '    <li><input id="_txtPassword" class="input r3" type="text" data-text="密码" value="密码" /></li>',
                '    <li style="text-align:right;"><a id="_lnkFindPassword" href="javascript:;">忘记密码?</a></li>',
                '    <li><a id="_lnkLogin" class="button blue r3" href="javascript:;" onclick="login();">登录</a></li>',
                '    <li><a id="_lnkRegister" class="button color2 r3" href="javascript:;">注册账户</a></li>',
                '</ul>'
            ];
            
            var goHis = location.href.toLocaleLowerCase().indexOf("content.aspx") >= 0 ? 1 : 0;
            this.showWinbox('用户登陆', htm.join(""), true, goHis);

            $(".input").click(
                function () {
                    if ($(this).val() == $(this).attr("data-text")) {
                        $(this).val("");
                        $(this).css({ color: "#555" });
                    }
                }).blur(
                    function () {
                        if ($(this).val() == "") {
                            $(this).val($(this).attr("data-text"));
                            $(this).css({ color: "#999" });
                        }
                    });
            $("#_lnkLogin").click(
                function () {
                    var userName = $("#_txtLoginName").val();
                    var userPass = $("#_txtPassword").val();
                    if (userName == $("#_txtLoginName").attr("data-text")
                        || userName == "") {
                        Util.Alert("请输入账户");
                        return;
                    }
                    if (userPass == $("#_txtPassword").attr("data-text")
                        || userPass == "") {
                        Util.Alert("请输入密码");
                        return;
                    }
                    Site._setBoxTitle("登录中....");
                    $.get(Site.Host, $.param({ action: "login", loginid: userName, password: userPass })
                        , function (res) {

                            Site._setBoxTitle("用户登录");

                            if (res.code == 0) {
                                Util.CookieWrite("USER_FLAG", res.result, 999);
                                Util.Alert("登录成功了");
                                setTimeout(function () {
                                    if (typeof callback == "function") callback(res.uid);
                                    Site.closeWinbox();
                                }, 1500);
                                return;
                            }
                            Util.Alert(res.err);
                        });
                });
        },
        
        /*修改密码*/
        showUpdatePasswordbox: function (userid, callback) {

            var htm = [];

            htm.push('<div>');
            htm.push('   <ul class="group2">');
            htm.push('       <li><input id="_txtOldPassword" class="input" type="text" data-text="输入旧密码" value="输入旧密码" /></li>');
            htm.push('       <li><input id="_txtNewPassword" class="input" type="text" data-text="新密码" value="新密码" /></li>');
            htm.push('       <li><a href="javascript:;" id="_lnkUpdate" class="button blue r3">确定修改</a></li>');
            htm.push('   </ul>');
            htm.push('</div>');

            this.showWinbox('密码修改', htm.join(""), true);

            $(".input").click(
                function () {
                    if ($(this).val() == $(this).attr("data-text")) {
                        $(this).val("");
                        $(this).css({ color: "#555" });
                    }
                }).blur(
                    function () {
                        if ($(this).val() == "") {
                            $(this).val($(this).attr("data-text"));
                            $(this).css({ color: "#999" });
                        }
                    });
        },
        
        /*显示确认框*/
        showConfirmbox: function (msg, callback) {
            var htm = [
                '<div id="_confirmBox" class="box" style="padding:10px 0;">',
                '    <div class="box" style="padding-bottom:10px; color:#555; line-height:24px; text-indent:28px; ">',
                msg,
                '    </div>',
                '    <div class="line"></div>',
                '    <ul class="group g2 m10">',
                '        <li><a href="javascript:;" data-value="1" class="button blue r3">确定</a></li>',
                '        <li><a href="javascript:;" data-value="0" class="button color2 r3">取消</a></li>',
                '    </ul>',
                '</div>'
            ];
            this.showWinbox('提示', htm.join(""), true);
            $("#_confirmBox .button").click(function () {
                var value = parseInt($(this).attr("data-value"));
                if (typeof callback == "function")
                    callback(value);
                Site.closeWinbox();
            });
        },
        /*回顶部*/
        initGotoTop: function () {
            $("#_goTop").remove();
            $('<a id="_goTop" href="javascript:;" class="gotop"></a>').appendTo("body");
            $("#_goTop").click(function () {
                $("body").animate({ scrollTop: 0 }, 200);
            });
            setInterval(function () {
                var top = $("body").scrollTop();
                top > 10 ? $("#_goTop").show() : $("#_goTop").hide();
            }, 200);
        }
    };

})();

window.onload = function () {
    Site.initGotoTop();
};

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


function isDefind( obj ){
	return typeof obj != "undefined" ;
}