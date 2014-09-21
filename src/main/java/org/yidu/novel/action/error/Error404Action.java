package org.yidu.novel.action.error;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;

/**
 * 
 * <p>
 * 404错误画面，默认处理Action。
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "error404")
public class Error404Action extends AbstractPublicBaseAction {

    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -7303163605597104098L;

    /**
     * 功能名称。
     */
    public static final String NAME = "error404";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    @SkipValidation
    @Override
    public String execute() {

        return FREEMARKER;
    }

    @Override
    protected void loadData() {
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

    @Override
    public String getTempName() {
        return "error/error404";
    }
}
