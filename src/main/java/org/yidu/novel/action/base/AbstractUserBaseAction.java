package org.yidu.novel.action.base;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

/**
 * <p>
 * 用户画面的基类
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public abstract class AbstractUserBaseAction extends AbstractPublicAndUserBaseAction {

    /**
     * 
     */
    private static final long serialVersionUID = 4900892616460135567L;
    /**
     * 命名空间。
     */
    public static final String NAMESPACE = "/user";

    @Override
    @SkipValidation
    public String execute() {
        logger.debug("execute start.");
        loadData();
        if (this.hasErrors()) {
            logger.debug("execute abnormally end.");
            setHasError(true);
            return FREEMARKER_ERROR;
        }
        logger.debug("execute normally end.");
        return FREEMARKER;
    }

    public abstract int getPageType();

    public abstract String getTempName();

    public TUser getUser() {
        return LoginManager.getLoginUser();
    }

    protected boolean checkRight(TArticle article) {
        boolean hasRihgtFlag = false;
        TUser user = LoginManager.getLoginUser();
        // 作者
        if (user.getType() == YiDuConstants.UserType.AUTHER && article.getAuthorid() == user.getUserno()) {
            hasRihgtFlag = true;
        }
        // TODO 编辑
        if (user.getType() == YiDuConstants.UserType.EDITOR && article.getCategory() == user.getUserno()) {
            hasRihgtFlag = true;
        }
        return hasRihgtFlag;
    }

}
