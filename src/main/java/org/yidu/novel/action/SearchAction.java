package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;

/**
 * <p>
 * 搜索Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class SearchAction extends AbstractPublicListBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -4215796997609788238L;

    private String key;

    /**
     * 页号
     */
    private int page;

    /**
     * 小说列表
     */
    List<TArticle> articleList = new ArrayList<TArticle>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = StringUtils.trim(key);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<TArticle> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<TArticle> articleList) {
        this.articleList = articleList;
    }

    public String getTempName() {
        return "search";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");

        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        searchBean.setPageType(ArticleSearchBean.PageType.publicPage);
        pagination.setPageNumber(page == 0 ? 1 : page);
        pagination.setSortColumn("lastupdate");
        pagination.setSortOrder("ASC");

        Object countInfo = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_COUNT_PREFIX,
                searchBean);
        int count = 0;
        if (countInfo == null) {
            count = articleService.getCount(searchBean);
            CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_COUNT_PREFIX, searchBean, count);
        } else {
            count = Integer.parseInt(countInfo.toString());
        }
        // 总件数设置
        pagination.setPreperties(count);
        searchBean.setPagination(pagination);

        articleList = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_PREFIX, searchBean);
        if (articleList == null || articleList.size() == 0) {
            articleList = articleService.find(searchBean);
            CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_PREFIX, searchBean, articleList);
        }
        logger.debug("normally end.");
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_SEARCH;
    }

}
