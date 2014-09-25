package org.yidu.novel.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.error.Error404Action;

/**
 * 
 * <p>
 * 当页面找不到的时候，默认处理Action。
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "notFound")
public class NotFoundAction extends Error404Action {

    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -7303163605597104098L;

    /**
     * 功能名称。
     */
    public static final String NAME = "notFound";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    @SkipValidation
    @Override
    public String execute() {
        // 取得数据
        ServletActionContext.getResponse().setStatus(HttpServletResponse.SC_NOT_FOUND);
        return FREEMARKER;
    }

}
