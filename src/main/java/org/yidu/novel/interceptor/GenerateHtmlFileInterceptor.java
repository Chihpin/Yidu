package org.yidu.novel.interceptor;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.ReaderAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.StaticUtils;

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
public class GenerateHtmlFileInterceptor extends AbstractInterceptor {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -4852225907669227478L;
    /**
     * logger
     */
    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public String intercept(final ActionInvocation invocation) throws Exception {
        String rtn = invocation.invoke();

        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_GENERATE_HTML_FILE, false)) {
            // 如果是阅读页的话，同时生成静态页面
            if (invocation.getAction() instanceof ReaderAction) {
                ReaderAction action = (ReaderAction) invocation.getAction();
                logger.info("going to Generate Html file.");

                String templatePath = "themes/" + YiDuConstants.yiduConf.getString(YiDuConfig.THEME_NAME) + "/pc/"
                        + action.getTempName() + ".ftl";

                StaticUtils.crateHTML(ServletActionContext.getServletContext(), action, templatePath,
                        YiDuConstants.requestUri.get());
            }
        }
        return rtn;
    }

    /**
     * 
     * <p>
     * 生成HTML用线程
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.0.0
     * @author shinpa.you
     */
    class GenerateHtmlFileThread extends Thread {
        /**
         * ServletContext
         */
        ServletContext context;
        /**
         * 生成html用的数据
         */
        Object data;
        /**
         * 模版路径
         */
        String templatePath;
        /**
         * 静态html路径
         */
        String htmlPath;

        /**
         * 
         * 构造
         * 
         * @param context
         *            ServletContext
         * @param data
         *            生成html用的数据
         * @param templatePath
         *            模版路径
         * @param htmlPath
         *            静态html路径
         */
        public GenerateHtmlFileThread(ServletContext context, ReaderAction data, String templatePath, String htmlPath) {
            this.context = context;
            this.data = data;
            this.templatePath = templatePath;
            this.htmlPath = htmlPath;
        }

        /**
         * 启动
         */
        public void run() {
            StaticUtils.crateHTML(context, data, templatePath, htmlPath);
        }
    }
}
