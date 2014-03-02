package org.yidu.novel.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yidu.novel.action.base.AbstractAdminBaseAction;
import org.yidu.novel.action.base.AbstractBaseAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * <p>
 * 错误处理拦截器
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class ErrorInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 9085740143630189023L;

    private static final String UNKNOWN_ERROR_KEY = "errors.unknown";

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public String intercept(final ActionInvocation invocation) throws Exception {
        try {
            String rtn = invocation.invoke();
            return rtn;
        } catch (Throwable th) {
            AbstractBaseAction action = (AbstractBaseAction) invocation.getAction();
            logger.error(action, th);
            String errorMsg = action.getText(UNKNOWN_ERROR_KEY);
            action.addActionError(errorMsg);
        }
        if (invocation.getAction() instanceof AbstractAdminBaseAction) {
            return AbstractBaseAction.ADMIN_ERROR;
        } else {
            return AbstractBaseAction.ERROR;
        }

    }
}
