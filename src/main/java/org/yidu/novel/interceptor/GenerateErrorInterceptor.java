package org.yidu.novel.interceptor;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yidu.novel.action.base.AbstractAdminBaseAction;
import org.yidu.novel.action.base.AbstractBaseAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * <p>
 * 生成静态页面拦截器
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.5
 * @author shinpa.you
 */
public class GenerateErrorInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -4852225907669227478L;

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public String intercept(final ActionInvocation invocation) throws Exception {
        String rtn = invocation.invoke();
        return rtn;
    }
}
