package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;

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
        this.key = key;
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

        pagination.setPageNumber(page == 0 ? 1 : page);
        pagination.setSortColumn("lastupdate");
        pagination.setSortOrder("ASC");

        // 总件数设置
        pagination.setPreperties(articleService.getCount(searchBean));
        searchBean.setPagination(pagination);

        articleList = CacheManager.getObject(CACHE_KEY_ARTICEL_LIST_PREFIX + searchBean.toString());
        if (articleList == null || articleList.size() == 0) {
            articleList = articleService.find(searchBean);
            CacheManager.putObject(CACHE_KEY_ARTICEL_LIST_PREFIX + searchBean.toString(), articleList);
        }
        logger.debug("normally end.");
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_SEARCH;
    }

}
