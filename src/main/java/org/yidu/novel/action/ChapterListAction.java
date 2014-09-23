package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.Utils;

/**
 * <p>
 * 小说章节列表页
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "chapterList")
public class ChapterListAction extends AbstractPublicBaseAction {

    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -694735140661882540L;

    /**
     * 功能名称。
     */
    public static final String NAME = "chapterList";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    /**
     * 小说编号
     */
    private int articleno;
    /**
     * 拼音
     */
    private String pinyin;
    /**
     * 小说信息
     */
    private TArticle article = new TArticle();
    /**
     * 章节列表信息
     */
    private List<TChapter> chapterList = new ArrayList<TChapter>();

    /**
     * 推荐章节列表
     */
    private List<TArticle> recommendArticleList = new ArrayList<TArticle>();

    /**
     * 随机推荐章节列表
     */
    private List<TArticle> randomRecommendArticleList = new ArrayList<TArticle>();

    /**
     * 获取 articleno
     * 
     * @return articleno
     */
    public int getArticleno() {
        return articleno;
    }

    /**
     * 
     * 设置articleno
     * 
     * 
     * @param articleno
     *            articleno
     */
    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    /**
     * 获取 pinyin
     * 
     * @return pinyin
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * 
     * 设置pinyin
     * 
     * 
     * @param pinyin
     *            pinyin
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /**
     * 获取 article
     * 
     * @return article
     */
    public TArticle getArticle() {
        return article;
    }

    /**
     * 
     * 设置article
     * 
     * 
     * @param article
     *            article
     */
    public void setArticle(TArticle article) {
        this.article = article;
    }

    /**
     * 获取 chapterList
     * 
     * @return chapterList
     */
    public List<TChapter> getChapterList() {
        return chapterList;
    }

    /**
     * 
     * 设置chapterList
     * 
     * 
     * @param chapterList
     *            chapterList
     */
    public void setChapterList(List<TChapter> chapterList) {
        this.chapterList = chapterList;
    }

    /**
     * 获取recommendArticleList
     * 
     * @return recommendArticleList
     */
    public List<TArticle> getRecommendArticleList() {
        return recommendArticleList;
    }

    /**
     * 
     * 设置recommendArticleList
     * 
     * 
     * @param recommendArticleList
     *            recommendArticleList
     */
    public void setRecommendArticleList(List<TArticle> recommendArticleList) {
        this.recommendArticleList = recommendArticleList;
    }

    /**
     * 获取randomRecommendArticleList
     * 
     * @return randomRecommendArticleList
     */
    public List<TArticle> getRandomRecommendArticleList() {
        return randomRecommendArticleList;
    }

    /**
     * 
     * 设置randomRecommendArticleList
     * 
     * 
     * @param randomRecommendArticleList
     *            randomRecommendArticleList
     */
    public void setRandomRecommendArticleList(List<TArticle> randomRecommendArticleList) {
        this.randomRecommendArticleList = randomRecommendArticleList;
    }

    @Override
    public String getTempName() {
        return "chapterList";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        article = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX, articleno);
        if (article == null || article.getArticleno() == 0) {
            if (articleno != 0) {
                article = articleService.getByNo(articleno);
                CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX, articleno, article);
            } else if (StringUtils.isNotEmpty(pinyin)) {
                article = articleService.findByPinyin(pinyin);
                CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX, pinyin, article);
            }
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

            recommendArticleList = CacheManager.getObject(
                    CacheManager.CacheKeyPrefix.CACHE_KEY_RECOMMEND_ARTICEL_LIST_PREFIX, NAME + articleno);

            if (!Utils.isDefined(recommendArticleList)) {
                // 如果没有缓存，就去查询数据库
                recommendArticleList = articleService.findRecommendArticleList(article.getCategory(),
                        article.getArticleno(), 6);
                CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_RECOMMEND_ARTICEL_LIST_PREFIX, NAME
                        + articleno, recommendArticleList);
            }

            randomRecommendArticleList = CacheManager.getObject(
                    CacheManager.CacheKeyPrefix.CACHE_KEY_RANDOM_RECOMMEND_ARTICEL_LIST_PREFIX, NAME + articleno);

            if (!Utils.isDefined(randomRecommendArticleList)) {
                // 如果没有缓存，就去查询数据库
                randomRecommendArticleList = articleService.findRandomRecommendArticleList(6);
                CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_RANDOM_RECOMMEND_ARTICEL_LIST_PREFIX, NAME
                        + articleno, randomRecommendArticleList);
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
        return YiDuConstants.Pagetype.PAGE_CHAPTER_LIST;
    }

}
