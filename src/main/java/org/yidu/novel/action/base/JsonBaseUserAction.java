package org.yidu.novel.action.base;

import org.apache.struts2.ServletActionContext;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.CookieUtils;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.bean.ResponseBean;

/**
 * <p>
 * JSON处理的基类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public abstract class JsonBaseUserAction extends JsonBaseAction {


    private String loginid;

    @Override
    public String execute() {
        // 如果没有登录的话，读取Cookie信息尝试登录
        if (!LoginManager.isLoginFlag()) {
            CookieUtils.getUserCookieAndLogoin(ServletActionContext.getRequest(), userService);
        }

        TUser u = LoginManager.getLoginUser();
        if (!LoginManager.isLoginFlag() || !loginid.equals(u.getLoginid())) {
            ResponseBean<String> responseBean = new ResponseBean<String>();
            responseBean.setStatus(RETCODE.UNLOGIN.intValue);
            return JSON_RESULT;
        }

        res = loadJsonData();
        return JSON_RESULT;
    }
}
         