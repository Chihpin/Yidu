package org.yidu.novel.action;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractUserBaseAction;

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
     * 小说编号
     */
    private int articleno;

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    @SkipValidation
    public String execute() {
        logger.debug("execute start.");
        initCollections(new String[] { "collectionProperties.article.category" });
        if (articleno != 0) {
            articleService.updateVoteStatistic(articleno);
        } else {
            addActionError(getText("errors.not.exsits.article"));
            return ERROR;
        }
        logger.debug("execute normally start.");
        return MESSAGE;
    }

    @Override
    protected void loadData() {
    }
}
