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
import org.yidu.novel.service.ReviewService;
import org.yidu.novel.service.SystemBlockService;
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
        @Result(name = AbstractBaseAction.FREEMARKER_ERROR, location = "/themes/${themeName}/error.ftl", type = "freemarker"),
        @Result(name = AbstractBaseAction.FREEMARKER_MESSAGE, type = "freemarker", location = "/themes/${themeName}/message.ftl"),
        @Result(name = AbstractBaseAction.FREEMARKER, type = "freemarker", location = "/themes/${themeName}/${tempName}.ftl"),
        @Result(name = AbstractBaseAction.MOBILE_FREEMARKER_ERROR, location = "/themes/${themeName}/mobile/error.ftl", type = "freemarker"),
        @Result(name = AbstractBaseAction.MOBILE_FREEMARKER_MESSAGE, type = "freemarker", location = "/themes/${themeName}/mobile/message.ftl"),
        @Result(name = AbstractBaseAction.MOBILE_FREEMARKER, type = "freemarker", location = "/themes/${themeName}/mobile/${tempName}.ftl"),
        @Result(name = AbstractBaseAction.ADMIN_ERROR, location = "/WEB-INF/error.jsp", type = "dispatcher"),
        @Result(name = AbstractBaseAction.JSON_RESULT, type = "json"),
        @Result(name = AbstractBaseAction.GO_TOP, location = org.yidu.novel.action.IndexAction.URL, type = "redirect"),
        @Result(name = AbstractBaseAction.GOTO_LOGIN, location = org.yidu.novel.action.LoginAction.URL, type = "redirect"),
        @Result(name = AbstractBaseAction.REDIRECT, location = "${backUrl}", type = "redirect") })
public abstract class AbstractBaseAction extends ActionSupport implements ValidationWorkflowAware {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 1L;

    protected static final String JSON_RESULT = "json";

    public static final String GO_TOP = "GOTO_Top";

    public static final String GOTO_LOGIN = "GOTO_Login";

    public static final String REDIRECT = "redirect";

    public static final String MESSAGE = "message";

    public static final String ADMIN_ERROR = "adminerror";

    public static final String FREEMARKER = "freemarker";
    public static final String FREEMARKER_ERROR = "freemarker_error";
    public static final String FREEMARKER_MESSAGE = "freemarker_message";

    public static final String MOBILE_FREEMARKER = "mobile_freemarker";
    public static final String MOBILE_FREEMARKER_ERROR = "mobile_freemarker_error";
    public static final String MOBILE_FREEMARKER_MESSAGE = "mobile_freemarker_message";

    /**
     * 输出log
     */
    protected Log logger = LogFactory.getLog(this.getClass());

    /**
     * 用户关联操作服务
     */
    @Autowired
    protected UserService userService;

    /**
     * 小说关联操作服务
     */
    @Autowired
    protected ArticleService articleService;
    /**
     * 章节关联操作服务
     */
    @Autowired
    protected ChapterService chapterService;
    /**
     * 书签关联操作服务
     */
    @Autowired
    protected BookcaseService bookcaseService;
    /**
     * 区块关联操作服务
     */
    @Autowired
    protected SystemBlockService systemBlockService;
    /**
     * 消息关联操作服务
     */
    @Autowired
    protected MessageService messageService;
    /**
     * 评论关联操作服务
     */
    @Autowired
    protected ReviewService reviewService;

    @Override
    public String getInputResultName() {
        return Action.INPUT;
    }

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
        return ServletActionContext.getRequest().getRequestURL().toString();
    }

    /**
     * 回退URL
     */
    private String backUrl;

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    @SkipValidation
    public String back() throws Exception {
        return REDIRECT;
    }

    protected Map<String, LinkedMap> collections = new HashMap<String, LinkedMap>();

    public Map<String, LinkedMap> getCollections() {

        return collections;
    }

    /**
     * 从property中读取内容转成Map
     * 
     * @param keys
     *            property里的键
     */
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

}
