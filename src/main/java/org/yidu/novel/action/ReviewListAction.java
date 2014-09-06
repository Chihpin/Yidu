package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.bean.ReviewSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TReview;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

/**
 * <p>
 * 评价页
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "reviewList")
public class ReviewListAction extends AbstractPublicListBaseAction {

    private static final long serialVersionUID = 7851872585464786041L;

    /**
     * 功能名称。
     */
    public static final String NAME = "reviewList";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    /**
     * 小说编号
     */
    private int articleno;

    /**
     * 页号
     */
    private int page;
    /**
     * 评论信息
     */
    private String review;

    /**
     * 是否是来自Form
     */
    private boolean isFromForm;

    /**
     * 评论列表
     */
    private List<TReview> reviewList = new ArrayList<TReview>();

    private TArticle article;

    private TUser user;

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = StringUtils.trim(review);
    }

    public boolean getIsFromForm() {
        return isFromForm;
    }

    public void setIsFromForm(boolean isFromForm) {
        this.isFromForm = isFromForm;
    }

    public List<TReview> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<TReview> reviewList) {
        this.reviewList = reviewList;
    }

    public TArticle getArticle() {
        return article;
    }

    public void setArticle(TArticle article) {
        this.article = article;
    }

    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_REVIEW;
    }

    public String getTempName() {
        return "reviewList";
    }

    public String getBackUrl() {
        return ServletActionContext.getResponse().encodeURL(
                "/reviewList?subdir=" + articleno / 1000 + "&articleno=" + articleno);
    }

    public String getData() {
        if (hasActionErrors()) {
            return StringUtils.join(getActionErrors(), ",");
        } else {
            return "success";
        }
    }

    @Override
    protected void loadData() {

        logger.debug("loadData start.");

        if (articleno != 0) {
            // 获取小说信息
            article = articleService.getByNo(articleno);
        }

        ReviewSearchBean searchBean = new ReviewSearchBean();
        BeanUtils.copyProperties(this, searchBean);

        pagination.setPageNumber(page == 0 ? 1 : page);
        pagination.setSortColumn(TReview.PROP_POSTDATE);
        pagination.setSortOrder("DESC");

        // 总件数设置
        pagination.setPreperties(reviewService.getCount(searchBean));
        searchBean.setPagination(pagination);
        // 获取评论信息
        reviewList = reviewService.find(searchBean);

        logger.debug("normally end.");

    }

    @Transactional
    public String addReview() {

        boolean addWithoutLogin = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ADD_REVIEW_WITHOUT_LOGIN, false);
        // 不允许非登录状态评论的话，检查登录状态
        if (!addWithoutLogin && !LoginManager.isLoginFlag()) {
            addActionError(getText("errors.notLogin"));
        }

        // 章节编号没添的话，报未知错误
        if (articleno == 0) {
            addActionError(getText("errors.unknown"));
        }

        // 输入检查
        if (StringUtils.length(review) < 5 || StringUtils.length(review) > 500) {
            addActionError(getText("errors.lengthrange", new String[] { "5", "500", "评论" }));
        }

        if (hasActionErrors()) {
            if (!isFromForm) {
                return JSON_RESULT;
            }
            // 重新读取评论信息
            this.loadData();
            return FREEMARKER;
        }

        // 开始登录评论信息
        TReview review = new TReview();
        TArticle article = articleService.getByNo(articleno);
        TUser user = LoginManager.getLoginUser();

        BeanUtils.copyProperties(this, review);
        BeanUtils.copyProperties(article, review);
        BeanUtils.copyProperties(user, review);

        review.setPostdate(new Date());
        review.setDeleteflag(false);
        review.setModifyuserno(user.getUserno());
        review.setModifytime(new Date());
        this.reviewService.save(review);

        if (!isFromForm) {
            return JSON_RESULT;
        }

        return REDIRECT;
    }

}
