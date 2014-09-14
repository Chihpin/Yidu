package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.cache.ArticleCountManager;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConfig;
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
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -4215796997609788238L;

    /**
     * 功能名称。
     */
    public static final String NAME = "articleList";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

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
        searchBean.setPageType(ArticleSearchBean.PageType.publicPage);

        pagination.setPageNumber(page == 0 ? 1 : page);
        pagination.setSortColumn(TArticle.PROP_LASTUPDATE);
        pagination.setSortOrder("DESC");

        int count = 0;
        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CACHE_ARTICLE_COUNT, false)) {
            // 开启缓存件数的话
            if (category != null && category != 0) {
                // 分类
                count = ArticleCountManager.getArticleCount(String.valueOf(category));
            } else if (StringUtils.isNotEmpty(author)) {
                count = ArticleCountManager.getArticleCount("author");
            } else if (fullflag != null && fullflag) {
                count = ArticleCountManager.getArticleCount("fullflag");
            } else {
                count = ArticleCountManager.getArticleCount("all");
            }
        } else {
            Object countInfo = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_COUNT_PREFIX,
                    searchBean);
            if (countInfo == null) {
                count = articleService.getCount(searchBean);
                CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_COUNT_PREFIX, searchBean,
                        count);
            } else {
                count = Integer.parseInt(countInfo.toString());
            }
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
        return YiDuConstants.Pagetype.PAGE_ARTICLE_LIST;
    }
}
