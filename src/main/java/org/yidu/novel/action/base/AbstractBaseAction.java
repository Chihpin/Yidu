package org.yidu.novel.action.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.service.ArticleService;
import org.yidu.novel.service.BookcaseService;
import org.yidu.novel.service.ChapterService;
import org.yidu.novel.service.MessageService;
import org.yidu.novel.service.SystemBlockService;
import org.yidu.novel.service.SystemConfigService;
import org.yidu.novel.service.UserService;
import org.yidu.novel.template.EncodeURLMethod;
import org.yidu.novel.template.GetTextMethod;
import org.yidu.novel.utils.CookieUtils;

import com.google.gson.Gson;
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
        @Result(name = Action.ERROR, location = "/ftl/error.ftl", type = "freemarker"),
        @Result(name = AbstractBaseAction.MESSAGE, type = "freemarker", location = "/ftl/message.ftl"),
        @Result(name = AbstractBaseAction.FREEMARKER, type = "freemarker", location = "/ftl/${tempName}.ftl"),
        @Result(name = AbstractBaseAction.ADMIN_ERROR, location = "/WEB-INF/adminerror.jsp", type = "dispatcher"),
        @Result(name = AbstractBaseAction.JSON_RESULT, type = "json"),
        @Result(name = AbstractBaseAction.STREAM_RESULT, type = "stream", params = { "inputName", "inputStream",
                "contentType", "application/octet-stream; charset=GBK", "contentLength", "${ contentLength }",
                "contentDisposition", "attachment; filename = ${fileName}" }),
        @Result(name = AbstractBaseAction.GO_TOP, location = org.yidu.novel.action.IndexAction.URL, type = "redirect"),
        @Result(name = AbstractBaseAction.GOTO_LOGIN, location = org.yidu.novel.action.LoginAction.URL, type = "redirect"),
        @Result(name = AbstractBaseAction.REDIRECT, location = "${backUrl}", type = "redirect") })
public abstract class AbstractBaseAction extends ActionSupport implements ValidationWorkflowAware {

    private static final long serialVersionUID = 1L;

    protected static final String JSON_RESULT = "json";

    protected static final String STREAM_RESULT = "stream";

    public static final String GO_TOP = "GOTO_Top";

    public static final String GOTO_LOGIN = "GOTO_Login";

    public static final String REDIRECT = "redirect";

    public static final String MESSAGE = "message";

    public static final String ADMIN_ERROR = "adminerror";

    public static final String FREEMARKER = "freemarker";

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

    public EncodeURLMethod getEncodeURL() {
        return new EncodeURLMethod(ServletActionContext.getResponse());
    }

    public GetTextMethod getGetText() {
        return new GetTextMethod(this);
    }

    /**
     * 阅读履历
     */
    private List<TChapter> historyList = new ArrayList<TChapter>();

    public List<TChapter> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<TChapter> historyList) {
        this.historyList = historyList;
    }

    protected void loadReadHistory() {
        // 获得阅读履历
        String historys = CookieUtils.getHistoryCookie(ServletActionContext.getRequest());
        if (StringUtils.isNotEmpty(historys)) {
            String[] acnos = StringUtils.split(historys, ",");
            List<String> chapternoList = new ArrayList<String>();
            for (String articleAndchapterno : acnos) {
                String[] acnoArr = StringUtils.split(articleAndchapterno, "|");
                if (acnoArr.length == 2) {
                    chapternoList.add(acnoArr[1]);
                }
            }
            if (chapternoList.size() > 0) {
                ChapterSearchBean searchBean = new ChapterSearchBean();
                searchBean.setChapternos(StringUtils.join(chapternoList, ","));
                this.historyList = this.chapterService.find(searchBean);
            }
        }
    }

    private boolean hasError = false;

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public boolean getHasError() {
        return hasError;
    }

    /**
     * 公共页和用户页用类型JSON文字列
     * 
     * @return 类型JSON文字列
     */
    public String getCategoryData() {

        Gson gson = new Gson();
        LinkedMap pulldown = new LinkedMap();
        String value = getText("collectionProperties.article.category");
        String[] items = value.split(",");
        for (String item : items) {
            String[] property = item.split(":");
            if (property.length == 2) {
                pulldown.put(property[0], property[1]);
            }
        }
        return gson.toJson(pulldown);
    }
    
    /**
     * 
     * <p>
     * 获取启用广告标识
     * </p>
     * 
     * @return 启用广告标识
     */
    public boolean getAdEffective() {
        return YiDuConstants.yiduConf.getBoolean(YiDuConfig.AD_EFFECTIVE, true);
    }

}
