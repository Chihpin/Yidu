package org.yidu.novel.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

@Action(value = "checklogin")
public class CheckLoginAction extends AbstractPublicBaseAction {

    private static final long serialVersionUID = -5991997032217966675L;

    /**
     * 功能名称。
     */
    public static final String NAME = "checklogin";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    @SkipValidation
    @Override
    public String execute() {
        logger.debug("execute normally end.");
        return AbstractBaseAction.JSON_RESULT;
    }

    public TUser getData() {
        if (LoginManager.isLoginFlag()) {
            return LoginManager.getLoginUser();
        }
        return null;
    }

    @Override
    public void loadData() {
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }
}