package org.yidu.novel.action.api.chapter;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.ChapterDTO;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.utils.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.yidu.novel.bean.ResponseBean;
import org.yidu.novel.action.base.JsonBasePublicAction;

/**
 * <p>
 * 小说章节列表页
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "api/chapter/list")
public class ListAction extends JsonBasePublicAction {

    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -694735140661882540L;

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
    private TArticle article;
    /**
     * 章节列表信息
     */
    private List<ChapterDTO> chapterList;

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
     * 获取chapterList
     * 
     * @return chapterList
     */
    public List<ChapterDTO> getChapterList() {
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
    public void setChapterList(List<ChapterDTO> chapterList) {
        this.chapterList = chapterList;
    }

    @Override
    protected ResponseBean<?> loadJsonData()  {
        logger.debug("loadData start.");

        if (articleno != 0) {
            article = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX, articleno);
            if (!Utils.isDefined(article)) {
                article = articleService.getByNo(articleno);
                CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX, articleno, article);
            }
        } else if (Utils.isDefined(pinyin)) {
            article = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX, pinyin);
            if (!Utils.isDefined(article)) {
                ArticleSearchBean searchBean = new ArticleSearchBean();
                searchBean.setPinyin(pinyin);
                List<TArticle> articleList = articleService.find(searchBean);
                if (Utils.isDefined(articleList)) {
                    article = articleList.get(0);
                    CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX, pinyin, article);
                }
            }
        }

        if (article != null) {
            // 获取章节信息
            ChapterSearchBean searchBean = new ChapterSearchBean();
            BeanUtils.copyProperties(this, searchBean);
            searchBean.setArticleno(article.getArticleno());
            chapterList = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_CHAPTER_LIST_PREFIX, searchBean);
            if (!Utils.isDefined(chapterList)) {
                chapterList = chapterService.findWithPinyin(searchBean);
                if (Utils.isDefined(chapterList)) {
                    CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_CHAPTER_LIST_PREFIX, searchBean,
                            chapterList);
                }
            }

        } else {
            addActionError(getText("errors.not.exsits.article"));
            return new ResponseBean<Object>(RETCODE.BOOK_NOTFOUND.intValue, null);
        }

        articleService.updateVisitStatistic(articleno);
        logger.debug("loadData normally end.");
        
        ResponseBean<String> responseBean = new ResponseBean<String>();
        Gson gson = new Gson();
        String obj = gson.toJson(chapterList);
        responseBean.setDataObj(obj);
        return responseBean;
    }
}
