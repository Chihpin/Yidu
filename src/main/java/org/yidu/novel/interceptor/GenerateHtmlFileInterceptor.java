package org.yidu.novel.interceptor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    private static final long serialVersionUID = -4852225907669227478L;

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public String intercept(final ActionInvocation invocation) throws Exception {
        String rtn = invocation.invoke();

        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_GENERATE_HTML_FILE, false)) {
            // 如果是阅读页的话，同时生成静态页面
            if (invocation.getAction() instanceof ReaderAction) {
                ReaderAction action = (ReaderAction) invocation.getAction();
                logger.info("going to Generate Html file.");

                String templatePath = "themes/" + YiDuConstants.yiduConf.getString(YiDuConfig.THEME_NAME) + "/"
                        + action.getTempName() + ".ftl";

                StaticUtils.crateHTML(ServletActionContext.getServletContext(), action, templatePath,
                        YiDuConstants.requestUri.get());

                // GenerateHtmlFileThread thread = new
                // GenerateHtmlFileThread(ServletActionContext.getServletContext(),
                // action, templatePath, YiDuConstants.requestUri.get());
                // thread.start();
            }
        }

        return rtn;
    }

    public class ConcurrencyLockExample implements Runnable {

        private Lock lock;

        public ConcurrencyLockExample() {
            this.lock = new ReentrantLock();
        }

        @Override
        public void run() {
            try {
                if (lock.tryLock(10, TimeUnit.SECONDS)) {
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // release lock
                lock.unlock();
            }
        }
    }

    class GenerateHtmlFileThread extends Thread {

        ServletContext context;
        Object data;
        String templatePath;
        String htmlPath;

        public GenerateHtmlFileThread(ServletContext context, ReaderAction data, String templatePath, String htmlPath) {
            this.context = context;
            this.data = data;
            this.templatePath = templatePath;
            this.htmlPath = htmlPath;
        }

        public void run() {
            StaticUtils.crateHTML(context, data, templatePath, htmlPath);
        }
    }
}
