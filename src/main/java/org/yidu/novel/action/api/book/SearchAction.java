package org.yidu.novel.action.api.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.yidu.novel.bean.ResponseBean;
import org.yidu.novel.action.base.JsonBasePublicAction;
import org.apache.struts2.convention.annotation.Action;

/**
 * <p>
 * 搜索Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "api/book/search")
public class SearchAction extends JsonBasePublicAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -4215796997609788238L;
    /**
     * 检索关键字
     */
    private String key;

    /**
     * 页号
     */
    private int page;

    /**
     * 小说列表
     */
    private List<TArticle> articleList = new ArrayList<TArticle>();

    /**
     * 获取 page
     * 
     * @return page
     */
    public int getPage() {
    	return page ==0 ? 1 : page;
    }

    /**
     * 
     * 设置page
     * 
     * 
     * @param page
     *            page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 获取 articleList
     * 
     * @return articleList
     */
    public List<TArticle> getArticleList() {
        return articleList;
    }

    /**
     * 
     * 设置articleList
     * 
     * 
     * @param articleList
     *            articleList
     */
    public void setArticleList(List<TArticle> articleList) {
        this.articleList = articleList;
    }

    /**
     * 获取 key
     * 
     * @return key
     */
    public String getKey() {
        return StringEscapeUtils.escapeHtml4(key);
    }

    /**
     * 
     * 设置key
     * 
     * @param key
     *            key
     */
    public void setKey(String key) {
        this.key = StringUtils.trim(key);
    }

    
    /**
     * 获取排行榜名字的JSON数据
     * 
     * @return 排行榜名字的JSON数据
     */
    public String getTopNameJsonData() {
        Gson gson = new Gson();
        return gson.toJson(YiDuConstants.TOP_NAME_MAP, Map.class);
    }
    

    @Override
    protected ResponseBean<?> loadJsonData() {
        logger.debug("loadData start.");
        //不指定关键字的时候，默认设置为空，防止模版报错
        if (key == null) {
            key = "";
        }
        ArticleSearchBean searchBean = new ArticleSearchBean();
//        BeanUtils.copyProperties(this, searchBean);
        searchBean.setKey(key);
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
        
        ResponseBean<String> responseBean = new ResponseBean<String>();
        Gson gson = new Gson();
        String obj = gson.toJson(articleList);
        responseBean.setDataObj(obj);
        return responseBean;
    }
}
