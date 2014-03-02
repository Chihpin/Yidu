package org.yidu.novel.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;

/**
 * 
 * <p>
 * 登录状态管理
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class LoginManager {

    private static final HttpSession getSession(final boolean create) {
        return ServletActionContext.getRequest().getSession(create);
    }

    public static final TUser getLoginUser() {
        HttpSession session = getSession(false);
        if (session != null) {
            return (TUser) session.getAttribute(YiDuConstants.LoginUser);
        } else {
            return null;
        }
    }

    /**
     * 登录判断
     * 
     * @return true - 是否登录
     */
    public static final boolean isLoginFlag() {
        return (getLoginUser() != null);
    }

    /**
     * 登录处理
     * 
     * @param user
     *            用户信息
     */
    public static final void doLogin(final TUser user) {
        HttpSession session = getSession(true);
        session.invalidate();
        session = getSession(true);
        session.setAttribute(YiDuConstants.LoginUser, user);
    }

    /**
     * 退出处理
     */
    public static final void doLogout() {
        HttpSession session = getSession(true);
        session.setAttribute(YiDuConstants.LoginUser, null);
    }
}
