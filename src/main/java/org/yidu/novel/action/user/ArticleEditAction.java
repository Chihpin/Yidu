package org.yidu.novel.action.user;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

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
public class ArticleEditAction extends AbstractUserBaseAction {

    private static final long serialVersionUID = 822196809678036074L;

    private int articleno;
    private String articlename;
    private String keywords;
    private Integer authorid;
    private String author;
    private Integer category;
    private String intro;
    private Boolean fullflag;

    private Integer dayvisit;
    private Integer weekvisit;
    private Integer monthvisit;
    private Integer allvisit;
    private Integer dayvote;
    private Integer weekvote;
    private Integer monthvote;
    private Integer allvote;

    private File articlespic;
    private String articlespicContentType;
    private String articlespicFileName;

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

    public Integer getDayvisit() {
        return dayvisit;
    }

    public void setDayvisit(Integer dayvisit) {
        this.dayvisit = dayvisit;
    }

    public Integer getWeekvisit() {
        return weekvisit;
    }

    public void setWeekvisit(Integer weekvisit) {
        this.weekvisit = weekvisit;
    }

    public Integer getMonthvisit() {
        return monthvisit;
    }

    public void setMonthvisit(Integer monthvisit) {
        this.monthvisit = monthvisit;
    }

    public Integer getAllvisit() {
        return allvisit;
    }

    public void setAllvisit(Integer allvisit) {
        this.allvisit = allvisit;
    }

    public Integer getDayvote() {
        return dayvote;
    }

    public void setDayvote(Integer dayvote) {
        this.dayvote = dayvote;
    }

    public Integer getWeekvote() {
        return weekvote;
    }

    public void setWeekvote(Integer weekvote) {
        this.weekvote = weekvote;
    }

    public Integer getMonthvote() {
        return monthvote;
    }

    public void setMonthvote(Integer monthvote) {
        this.monthvote = monthvote;
    }

    public Integer getAllvote() {
        return allvote;
    }

    public void setAllvote(Integer allvote) {
        this.allvote = allvote;
    }

    public File getArticlespic() {
        return articlespic;
    }

    public void setArticlespic(File articlespic) {
        this.articlespic = articlespic;
    }

    public String getArticlespicContentType() {
        return articlespicContentType;
    }

    public void setArticlespicContentType(String articlespicContentType) {
        this.articlespicContentType = articlespicContentType;
    }

    public String getArticlespicFileName() {
        return articlespicFileName;
    }

    public void setArticlespicFileName(String articlespicFileName) {
        this.articlespicFileName = articlespicFileName;
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        // 初始化类别下拉列表选项
        initCollections(new String[] { "collectionProperties.article.category", "collectionProperties.article.fullflag" });

        // 编辑
        if (articleno != 0) {
            TArticle article = articleService.getByNo(articleno);

            if (!checkRight(article)) {
                addActionError(getText("errors.right"));
                return;
            }

            BeanUtils.copyProperties(article, this);

        }
        logger.debug("loadData normally end.");
    }

    private boolean checkRight(TArticle article) {
        boolean hasRihgtFlag = false;
        TUser user = LoginManager.getLoginUser();
        // 作者
        if (user.getType() == YiDuConstants.UserType.AUTHER && article.getAuthorid() == user.getUserno()) {
            hasRihgtFlag = true;
        }
        // TODO 编辑
        if (user.getType() == YiDuConstants.UserType.EDITOR && article.getCategory() == user.getUserno()) {
            hasRihgtFlag = true;
        }
        return hasRihgtFlag;
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

        // 初始化类别下拉列表选项
        initCollections(new String[] { "collectionProperties.article.category", "collectionProperties.article.fullflag" });

        TArticle article = new TArticle();
        if (articleno != 0) {
            article = articleService.getByNo(articleno);
            if (!checkRight(article)) {
                addActionError(getText("errors.right"));
                return FREEMARKER_ERROR;
            }
        }

        BeanUtils.copyProperties(this, article, new String[] { "articleno", "dayvisit", "weekvisit", "monthvisit",
                "allvisit", "dayvote", "weekvote", "monthvote", "allvote" });

        // 保存图片文件
        if (articlespic != null) {
            if (ArrayUtils.contains(YiDuConstants.allowSampleTypes, getArticlespicContentType())) {
                try {
                    saveArticlespic();
                } catch (Exception e) {
                    addActionError(getText("errors.file.save"));
                    return FREEMARKER;
                }
            } else {
                addActionError(getText("errors.file.type"));
                return FREEMARKER;
            }

            if (StringUtils.equals(getArticlespicContentType(), "image/jpg")) {
                article.setImgflag(YiDuConstants.ImageType.JPG);
            } else if (StringUtils.equals(getArticlespicContentType(), "image/gif")) {
                article.setImgflag(YiDuConstants.ImageType.GIF);
            } else if (StringUtils.equals(getArticlespicContentType(), "image/png")) {
                article.setImgflag(YiDuConstants.ImageType.PNG);
            }
        }

        articleService.save(article);
        logger.debug("save normally end.");
        return REDIRECT;
    }

    private void saveArticlespic() throws Exception {
        String path = YiDuConstants.yiduConf.getString(YiDuConfig.RELATIVE_IAMGE_PATH);
        path = ServletActionContext.getServletContext().getRealPath("/") + "/" + path + "/" + articleno / 1000 + "/"
                + articleno + "/" + articleno + "s." + StringUtils.substringAfterLast(getArticlespicFileName(), ".");

        File savefile = new File(path);
        if (!savefile.getParentFile().exists()) {
            savefile.getParentFile().mkdirs();
        }
        FileUtils.copyFile(articlespic, savefile);
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_AUTHER_ARTICLE_EDIT;
    }

    @Override
    public String getTempName() {
        return "user/articleEdit";
    }
}
