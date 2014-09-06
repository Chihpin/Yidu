package org.yidu.novel.action.user;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConstants;

/**
 * <p>
 * 小说推荐Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class VoteAction extends AbstractUserBaseAction {

    private static final long serialVersionUID = -2984522801349519469L;

    /**
     * 功能名称。
     */
    public static final String NAME = "vote";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    /**
     * 小说编号
     */
    private int articleno;

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

    @SkipValidation
    public String execute() {
        logger.debug("execute start.");
        if (articleno != 0) {
            articleService.updateVoteStatistic(articleno);
        } else {
            addActionError(getText("errors.not.exsits.article"));
            return FREEMARKER_ERROR;
        }
        logger.debug("execute normally start.");
        return FREEMARKER;
    }

    @Override
    protected void loadData() {
    }

    @Override
    public String getTempName() {
        return "message";
    }
}
