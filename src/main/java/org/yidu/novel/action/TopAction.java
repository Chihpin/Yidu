package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.cache.CacheManager;
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

    private static final long serialVersionUID = -4123651229405239412L;

    private static final Map<String, String> topNameMap = new LinkedMap() {
        private static final long serialVersionUID = 2390455878552118167L;
        {
            put("lastupdate", "最近更新");
            put("allvisit", "总排行榜");
            put("allvote", "总推荐榜");
            put("monthvisit", "月排行榜");
            put("monthvote", "月推荐榜");
            put("weekvisit", "周排行榜");
            put("weekvote", "周推荐榜");
            put("dayvisit", "日排行榜");
            put("dayvote", "日推荐榜");
            put("postdate", "最新入库");
            put("size", "字数排行");
        }
    };

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
        return gson.toJson(topNameMap, Map.class);
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

    public String getTempName() {
        return "top";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");

        if (!topNameMap.containsKey(sortColumn)) {
            // 默认最近更新
            sortColumn = "lastupdate";
        }

        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        searchBean.setPageType(ArticleSearchBean.PageType.publicPage);

        pagination.setPageNumber(page == 0 ? 1 : page);
        pagination.setSortColumn(sortColumn);
        pagination.setSortOrder(StringUtils.equalsIgnoreCase("ASC", sortOrder) ? sortOrder : "DESC");

        Integer count = CacheManager.getObject(CACHE_KEY_ARTICEL_TOP_LIST_COUNT_PREFIX);
        if (count == null || count == 0) {
            count = articleService.getCount(searchBean);
        }

        // 总件数设置
        pagination.setPreperties(count);
        searchBean.setPagination(pagination);

        articleList = CacheManager.getObject(CACHE_KEY_ARTICEL_TOP_LIST_PREFIX + searchBean.toString());
        if (articleList == null || articleList.size() == 0) {
            articleList = articleService.find(searchBean);
            CacheManager.putObject(CACHE_KEY_ARTICEL_TOP_LIST_PREFIX + searchBean.toString(), articleList);
        }
        logger.debug("normally end.");
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_TOP;
    }
}
