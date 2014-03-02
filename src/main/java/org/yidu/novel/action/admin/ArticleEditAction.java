package org.yidu.novel.action.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.entity.TArticle;

/**
 * <p>
 * 小说编辑Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "articleEdit")
@Result(name = AbstractBaseAction.REDIRECT, type = AbstractBaseAction.REDIRECT, location = ArticleListAction.URL)
public class ArticleEditAction extends AbstractAdminEditBaseAction {

    private static final long serialVersionUID = 822196809678036074L;

    private int articleno;
    private String articlename;
    private String keywords;
    private Integer authorid;
    private String author;
    private Integer category;
    private String intro;
    private Boolean fullflag;
    private Date postdate;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Boolean getFullflag() {
        return fullflag;
    }

    public void setFullflag(Boolean fullflag) {
        this.fullflag = fullflag;
    }

    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    public String getPostdateStr() {
        return sdf.format(postdate);
    }

    public void setPostdateStr(String postdateStr) {
        try {
            this.postdate = sdf.parse(postdateStr);
        } catch (ParseException e) {
            this.addFieldError(postdateStr, "sdfsdf");
        }
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        // 初始化类别下拉列表选项
        initCollections(new String[] { "collectionProperties.article.category" });
        // 编辑
        if (articleno != 0) {
            TArticle article = articleService.getByNo(articleno);
            BeanUtils.copyProperties(article, this);
        }
        logger.debug("loadData normally end.");
    }

    /**
     * <p>
     * 保存画面的内容
     * </p>
     * 
     * @return 结果，画面
     */
    public String save() {
        logger.debug("save start.");
        TArticle article = new TArticle();
        if (articleno != 0) {
            article = articleService.getByNo(articleno);
        }
        BeanUtils.copyProperties(this, article);
        articleService.save(article);
        logger.debug("save normally end.");
        return REDIRECT;
    }

}
