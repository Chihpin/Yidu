package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.cache.ArticleCountManager;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;

import com.google.gson.Gson;

/**
 * 
 * <p>
 * `排行榜Action
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class TopAction extends AbstractPublicListBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -4123651229405239412L;

    /**
     * 页号
     */
    private int page;
    /**
     * 排序字段
     */
    private String sortColumn;

    /**
     * 排列顺序
     */
    private String sortOrder;

    public String getTopNameJsonData() {
        Gson gson = new Gson();
        return gson.toJson(YiDuConstants.topNameMap, Map.class);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = StringUtils.lowerCase(sortColumn);
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 小说列表
     */
    List<TArticle> articleList = new ArrayList<TArticle>();

    public List<TArticle> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<TArticle> articleList) {
        this.articleList = articleList;
    }

    @Override
    public String getTempName() {
        return "top";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");

        if (!YiDuConstants.topNameMap.containsKey(sortColumn)) {
            // 默认最近更新
            sortColumn = "lastupdate";
        }

        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        searchBean.setPageType(ArticleSearchBean.PageType.publicPage);

        pagination.setPageNumber(page == 0 ? 1 : page);
        pagination.setSortColumn(sortColumn);
        pagination.setSortOrder(StringUtils.equalsIgnoreCase("ASC", sortOrder) ? sortOrder : "DESC");

        int count = 0;
        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CACHE_ARTICLE_COUNT, false)) {
            // 开启缓存件数的话
            count = ArticleCountManager.getArticleCount(sortColumn);
        } else {
            Integer countCache = CacheManager.getObject(
                    CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_TOP_LIST_COUNT_PREFIX, null);
            if (countCache == null || countCache == 0) {
                count = articleService.getCount(searchBean);
            } else {
                count = countCache;
            }
        }
        // 总件数设置
        pagination.setPreperties(count);
        searchBean.setPagination(pagination);

        articleList = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_TOP_LIST_PREFIX, searchBean);
        if (articleList == null || articleList.size() == 0) {
            articleList = articleService.find(searchBean);
            CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_TOP_LIST_PREFIX, searchBean,
                    articleList);
        }
        logger.debug("normally end.");
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_TOP;
    }
}
