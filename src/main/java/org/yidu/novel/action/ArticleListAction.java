package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;

/**
 * 
 * <p>
 * 小说列表Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "articleList")
public class ArticleListAction extends AbstractPublicListBaseAction {

    private static final long serialVersionUID = -4215796997609788238L;

    /**
     * 小说种类
     */
    private Integer category;

    /**
     * 页号
     */
    private int page;

    /**
     * 作者
     */
    private String author;

    /**
     * 完本标志
     */
    private Boolean fullflag;

    /**
     * 小说列表
     */
    List<TArticle> articleList = new ArrayList<TArticle>();

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getFullflag() {
        return fullflag;
    }

    public void setFullflag(Boolean fullflag) {
        this.fullflag = fullflag;
    }

    public List<TArticle> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<TArticle> articleList) {
        this.articleList = articleList;
    }

    public String getTempName() {
        return "articleList";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");

        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean);

        pagination.setPageNumber(page == 0 ? 1 : page);
        pagination.setSortColumn("lastupdate");
        pagination.setSortOrder("DESC");

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
        return YiDuConstants.Pagetype.PAGE_ARTICLE_LIST;
    }
}
