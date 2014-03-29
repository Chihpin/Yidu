package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.ArticleDTO;
import org.yidu.novel.entity.TChapter;

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
     * 小说编号
     */
    private int articleno;

    private ArticleDTO article = new ArticleDTO();

    private List<TChapter> chapterList = new ArrayList<TChapter>();

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getSubDir() {
        return articleno / YiDuConstants.SUB_DIR_ARTICLES;
    }

    public void setSubDir(int subDir) {
        // do nothing
    }

    public ArticleDTO getArticle() {
        return article;
    }

    public void setArticle(ArticleDTO article) {
        this.article = article;
    }

    public List<TChapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<TChapter> chapterList) {
        this.chapterList = chapterList;
    }

    public String getTempName() {
        return "info";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        article = CacheManager.getObject(CACHE_KEY_ARTICEL_PREFIX + articleno);
        if (article == null || article.getArticleno() == 0) {
            article = getArticleInfoByNo(articleno);
            if (article != null && article.getArticleno() != 0) {
                CacheManager.putObject(CACHE_KEY_ARTICEL_PREFIX + articleno, article);
            }
        }

        if (article != null) {
            // 取章节信息
            ChapterSearchBean searchBean = new ChapterSearchBean();
            BeanUtils.copyProperties(this, searchBean);
            chapterList = CacheManager.getObject(CACHE_KEY_CHAPTER_LIST_PREFIX + searchBean.toString());
            if (chapterList == null || chapterList.size() == 0) {
                chapterList = chapterService.find(searchBean);
                if (chapterList != null && chapterList.size() != 0) {
                    CacheManager.putObject(CACHE_KEY_CHAPTER_LIST_PREFIX + searchBean.toString(), chapterList);
                }
            }
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
        // TODO Auto-generated method stub
        return YiDuConstants.Pagetype.PAGE_ARTICLE_INFO;
    }

}
