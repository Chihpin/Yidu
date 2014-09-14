package org.yidu.novel.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.LoginManager;

import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;

/**
 * 
 * <p>
 * 跳转到QQ登录画面
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "gotoQQLogin")
@Result(name = AbstractBaseAction.REDIRECT, type = AbstractBaseAction.REDIRECT, location = "${url}")
public class RedirctToQQLoginAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -4123651229405239412L;

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

    /**
     * 获取qq登录页的地址
     * 
     * @return qq登录页的地址
     */
    public String getUrl() {
        String url = NotFoundAction.URL;
        try {
            url = new Oauth().getAuthorizeURL(ServletActionContext.getRequest());
        } catch (QQConnectException e) {
            logger.warn(e.getMessage());
        }
        return url;
    }

    @Override
    public String execute() {
        // 记录访问地址
        LoginManager.setReferer();
        return REDIRECT;
    };

    @Override
    protected void loadData() {
        // TODO Auto-generated method stub

    }
}