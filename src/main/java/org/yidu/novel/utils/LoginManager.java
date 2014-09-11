package org.yidu.novel.utils;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;

/**
 * 
 * <p>
 * 登录状态管理器
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class LoginManager {

    /**
     * 输出log
     */
    private static Log logger = LogFactory.getLog(LoginManager.class);

    private static final String REFERER_KEY = "referer";

    /**
     * 获得Session对象
     * 
     * @param create
     *            是否新建
     * @return Session对象
     */
    private static final HttpSession getSession(final boolean create) {
        return ServletActionContext.getRequest().getSession(create);
    }

    public static final void setReferer() {
        String refererPath = (String) ServletActionContext.getRequest().getHeader(REFERER_KEY);
        // 如果URL里没有本站域名的话，设置本站域名为默认值
        if (!(StringUtils.contains(refererPath, YiDuConstants.yiduConf.getString(YiDuConfig.URI)) && StringUtils
                .contains(refererPath, YiDuConstants.yiduConf.getString(YiDuConfig.URI)))) {
            refererPath = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
        }
        HttpSession session = getSession(true);
        logger.debug("REFERER_KEY : " + refererPath);
        session.setAttribute(REFERER_KEY, refererPath);
    }

    public static final String getAndCleanReferer() {
        HttpSession session = getSession(false);
        if (session == null || StringUtils.isEmpty(session.getAttribute(REFERER_KEY).toString())) {
            return YiDuConstants.yiduConf.getString(YiDuConfig.URI);
        }
        String refererPath = session.getAttribute(REFERER_KEY).toString();
        // 清空REFERER
        session.removeAttribute(REFERER_KEY);
        logger.debug("REFERER_KEY : " + refererPath);
        return refererPath;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
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
