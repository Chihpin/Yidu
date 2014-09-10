package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.bean.ReviewSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.entity.TReview;
import org.yidu.novel.utils.Pagination;

/**
 * <p>
 * 小说简介页
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class InfoAction extends AbstractPublicBaseAction {

    /**
     * 
     */
    private static final long serialVersionUID = -4215796997609788238L;

    /**
     * 功能名称。
     */
    public static final String NAME = "info";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    /**
     * 小说编号
     */
    private int articleno;

    private String pinyin;

    private TArticle article = new TArticle();

    private List<TChapter> chapterList = new ArrayList<TChapter>();

    private int reviewCount;

    private List<TReview> reviewList = new ArrayList<TReview>();

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public int getSubDir() {
        return articleno / YiDuConstants.SUB_DIR_ARTICLES;
    }

    public TArticle getArticle() {
        return article;
    }

    public List<TChapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<TChapter> chapterList) {
        this.chapterList = chapterList;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<TReview> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<TReview> reviewList) {
        this.reviewList = reviewList;
    }

    public String getTempName() {
        return "info";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        article = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX, articleno);
        if (article == null || article.getArticleno() == 0) {
            article = articleService.getByNo(articleno);
            CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX, articleno, article);
        }

        if (article != null) {
            // 获取章节信息
            ChapterSearchBean searchBean = new ChapterSearchBean();
            BeanUtils.copyProperties(this, searchBean);
            chapterList = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_CHAPTER_LIST_PREFIX, searchBean);
            if (chapterList == null || chapterList.size() == 0) {
                chapterList = chapterService.find(searchBean);
                if (chapterList != null && chapterList.size() != 0) {
                    CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_CHAPTER_LIST_PREFIX, searchBean,
                            chapterList);
                }
            }

            // 获取评论信息
            ReviewSearchBean reviewSearchBean = new ReviewSearchBean();
            reviewSearchBean.setArticleno(articleno);
            // 获取评论件数
            reviewCount = reviewService.getCount(reviewSearchBean);
            // 获取评论
            Pagination pagination = new Pagination(YiDuConstants.yiduConf.getInt(YiDuConfig.REVIEW_NUM, 5), 1);
            pagination.setSortColumn(TChapter.PROP_POSTDATE);
            pagination.setSortOrder("DESC");
            reviewSearchBean.setPagination(pagination);
            this.reviewList = reviewService.find(reviewSearchBean);

        } else {
            addActionError(getText("errors.not.exsits.article"));
        }
        if (articleno != 0) {
            articleService.updateVisitStatistic(articleno);
        }
        logger.debug("loadData normally end.");
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_ARTICLE_INFO;
    }

}
