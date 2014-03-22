package org.yidu.novel.action.base;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.Action;

/**
 * <p>
 * 管理画面的基类
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public abstract class AbstractAdminBaseAction extends AbstractBaseAction {

    private static final long serialVersionUID = 4900892616460135567L;
    /**
     * 命名空间。
     */
    public static final String NAMESPACE = "/admin";

    protected abstract void loadData();

    @Override
    @SkipValidation
    public String execute() {
        logger.debug("execute start.");
        initCollections(new String[] { "collectionProperties.article.category" });
        loadData();
        if (this.hasErrors()) {
            logger.debug("execute abnormally end.");
            return ADMIN_ERROR;
        }
        logger.debug("execute normally end.");
        return Action.INPUT;
    }

}
