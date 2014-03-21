package org.yidu.novel.action.admin;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
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
    private Boolean firstflag;
    private Integer permission;
    private Boolean authorflag;
    private String agent;

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

    public Boolean getFirstflag() {
        return firstflag;
    }

    public void setFirstflag(Boolean firstflag) {
        this.firstflag = firstflag;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public Boolean getAuthorflag() {
        return authorflag;
    }

    public void setAuthorflag(Boolean authorflag) {
        this.authorflag = authorflag;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
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

    public void setPostdateStr(String postdateStr) {
        try {
            this.postdate = sdf.parse(postdateStr);
        } catch (ParseException e) {
            // TODO
            this.addFieldError(postdateStr, "sdfsdf");
        }
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
        initCollections(new String[] { "collectionProperties.article.category",
                "collectionProperties.article.fullflag", "collectionProperties.article.authorflag",
                "collectionProperties.article.permission", "collectionProperties.article.firstflag" });
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

        // 初始化类别下拉列表选项
        initCollections(new String[] { "collectionProperties.article.category",
                "collectionProperties.article.fullflag", "collectionProperties.article.authorflag",
                "collectionProperties.article.permission", "collectionProperties.article.firstflag" });

        TArticle article = new TArticle();
        if (articleno != 0) {
            article = articleService.getByNo(articleno);
        }

        BeanUtils.copyProperties(this, article);

        // 保存图片文件
        if (articlespic != null) {
            if (ArrayUtils.contains(YiDuConstants.allowTypes, getArticlespicContentType())) {
                try {
                    saveArticlespic();
                } catch (Exception e) {
                    addActionError(getText("errors.file.save"));
                    return INPUT;
                }
            } else {
                addActionError(getText("errors.file.type"));
                return INPUT;
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
}
