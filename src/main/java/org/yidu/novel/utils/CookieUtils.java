package org.yidu.novel.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.service.UserService;

/**
 * cookie的增加、删除、查询
 */
public class CookieUtils {
    protected static Log logger = LogFactory.getLog(CookieUtils.class);
    public static final String USER_COOKIE = "user.cookie";
    public static final String READ_HISTORY_COOKIE = "read.history.cookie";

    // 添加一个cookie
    public static Cookie addUserCookie(TUser user) {
        try {
            Cookie cookie = new Cookie(USER_COOKIE, URLEncoder.encode(user.getLoginid(), YiDuConstants.ENCODING_UTF_8)
                    + "," + user.getPassword());
            cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周
            return cookie;
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * 使用cookie信息登录
     * 
     * @param request
     * @param userService
     */
    public static void getUserCookieAndLogoin(HttpServletRequest request, UserService userService) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            // 遍历cookie查找用户信息
            for (Cookie cookie : cookies) {
                if (CookieUtils.USER_COOKIE.equals(cookie.getName())) {
                    // 使用cookie内的用户信息登录
                    String value = cookie.getValue();
                    if (StringUtils.isNotBlank(value)) {
                        String[] split = value.split(",");
                        if (split.length == 2) {
                            String loginid = split[0];
                            String password = split[1];
                            TUser user = userService.findByLoginInfoByJDBC(loginid, password);
                            if (user != null) {
                                LoginManager.doLogin(user);
                                // 更新用户最后登录时间
                                userService.updateLastLoginDate(user.getUserno(),new Date());
                            }
                        }
                    }
                }
            }
        }
    }

    // 删除cookie
    public static Cookie delUserCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (USER_COOKIE.equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    return cookie;
                }
            }
        }
        return null;
    }

    // 添加一个cookie
    public static Cookie addHistoryCookie(String articlenos) {
        Cookie cookie = new Cookie(READ_HISTORY_COOKIE, articlenos);
        // cookie保存1年
        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie.setPath("/");
        return cookie;
    }

    // 得到cookie
    public static String getHistoryCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CookieUtils.READ_HISTORY_COOKIE.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
