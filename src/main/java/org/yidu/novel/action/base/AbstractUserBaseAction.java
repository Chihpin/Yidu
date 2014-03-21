package org.yidu.novel.action.base;

import org.apache.struts2.interceptor.validation.SkipValidation;

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
        loadReadHistory();
        if (this.hasErrors()) {
            logger.debug("execute abnormally end.");
            setHasError(true);
            return ERROR;
        }
        logger.debug("execute normally end.");
        return FREEMARKER;
    }

    public abstract int getPageType();

    public abstract String getTempName();

}
