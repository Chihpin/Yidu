package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.entity.TArticle;

/**
 * 
 * <p>
 * 网站地图Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "siteMap")
public class SiteMapAction extends AbstractPublicListBaseAction {

    private static final long serialVersionUID = -3069730816500421029L;

    /**
     * 功能名称。
     */
    public static final String NAME = "siteMap";
    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    List<TArticle> articleList = new ArrayList<TArticle>();

    public List<TArticle> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<TArticle> articleList) {
        this.articleList = articleList;
    }

    @Override
    public int getPageType() {
        return 0;
    }

    public String getTempName() {
        return "siteMap";
    }

    @Override
    protected void loadData() {
    	articleList = articleService.find(new ArticleSearchBean());
    }
	
}
