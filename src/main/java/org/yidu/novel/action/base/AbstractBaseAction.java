package org.yidu.novel.action.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.yidu.novel.service.ArticleService;
import org.yidu.novel.service.BookcaseService;
import org.yidu.novel.service.ChapterService;
import org.yidu.novel.service.MessageService;
import org.yidu.novel.service.SystemBlockService;
import org.yidu.novel.service.SystemConfigService;
import org.yidu.novel.service.UserService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ValidationWorkflowAware;

/**
 * 
 * <p>
 * 易读系统画面基类<br>
 * 定义各种服务
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Results({
        @Result(name = Action.ERROR, location = "/WEB-INF/error.jsp", type = "dispatcher"),
        @Result(name = AbstractBaseAction.ADMIN_ERROR, location = "/WEB-INF/adminerror.jsp", type = "dispatcher"),
        @Result(name = AbstractBaseAction.ERROR_404, params = { "status", "404" }, type = "httpheader"),
        @Result(name = AbstractBaseAction.JSON_RESULT, type = "json"),
        @Result(name = AbstractBaseAction.STREAM_RESULT, type = "stream", params = { "inputName", "inputStream",
                "contentType", "application/octet-stream; charset=GBK", "contentLength", "${ contentLength }",
                "contentDisposition", "attachment; filename = ${fileName}" }),
        @Result(name = AbstractBaseAction.GO_TOP, location = org.yidu.novel.action.IndexAction.URL, type = "redirect"),
        @Result(name = AbstractBaseAction.GOTO_LOGIN, location = org.yidu.novel.action.LoginAction.URL, type = "redirect"),
        @Result(name = AbstractBaseAction.REDIRECT, location = "${backUrl}", type = "redirect"),
        @Result(name = AbstractBaseAction.MESSAGE, location = "/WEB-INF/message.jsp", type = "dispatcher") })
public abstract class AbstractBaseAction extends ActionSupport implements ValidationWorkflowAware {

    private static final long serialVersionUID = 1L;

    protected static final String JSON_RESULT = "json";

    protected static final String STREAM_RESULT = "stream";

    protected static final String ERROR_404 = "error404";

    public static final String GO_TOP = "GOTO_Top";

    public static final String GOTO_LOGIN = "GOTO_Login";

    public static final String REDIRECT = "redirect";

    public static final String MESSAGE = "message";

    public static final String ADMIN_ERROR = "adminerror";

    /**
     * 输出log
     */
    protected Log logger = LogFactory.getLog(this.getClass());

    /**
     * 用户关联操作服务
     */
    protected UserService userService;

    /**
     * 小说关联操作服务
     */
    protected ArticleService articleService;
    /**
     * 章节关联操作服务
     */
    protected ChapterService chapterService;

    protected BookcaseService bookcaseService;

    protected SystemBlockService systemBlockService;

    protected SystemConfigService systemConfigService;

    protected MessageService messageService;

    @Autowired
    public void setSystemBlockService(SystemBlockService systemBlockService) {
        this.systemBlockService = systemBlockService;
    }

    @Autowired
    public void setSystemConfigService(SystemConfigService systemConfigService) {
        this.systemConfigService = systemConfigService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @Autowired
    public void setBookcaseService(BookcaseService bookcaseService) {
        this.bookcaseService = bookcaseService;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public String getInputResultName() {
        return Action.INPUT;
    }

    @Override
    @SkipValidation
    public String execute() {
        logger.debug("execute start.");
        initCollections(new String[] { "collectionProperties.article.category" });
        loadData();
        if (this.hasErrors()) {
            logger.debug("execute abnormally end.");
            return ERROR;
        }
        logger.debug("execute normally end.");
        return Action.INPUT;
    }

    protected abstract void loadData();

    /**
     * 获得系统相对路径
     * 
     * @return 系统相对路径
     */
    public String getContextPath() {
        return ServletActionContext.getServletContext().getContextPath();
    }

    /**
     * 获得系统相对路径
     * 
     * @return 系统相对路径
     */
    public String getRequestUrl() {
        System.out.println(ServletActionContext.getRequest().getRequestURL().toString());

        return ServletActionContext.getRequest().getRequestURL().toString();
    }

    @SkipValidation
    public String back() throws Exception {
        return REDIRECT;
    }

    protected Map<String, LinkedMap> collections = new HashMap<String, LinkedMap>();

    public Map<String, LinkedMap> getCollections() {

        return collections;
    }

    public void initCollections(String[] keys) {
        for (String key : keys) {
            LinkedMap pulldown = new LinkedMap();
            String value = getText(key);
            String[] items = value.split(",");
            for (String item : items) {
                String[] property = item.split(":");
                if (property.length == 2) {
                    pulldown.put(property[0], property[1]);
                }
            }
            collections.put(key, pulldown);
        }
    }

    private String backUrl;

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

}
