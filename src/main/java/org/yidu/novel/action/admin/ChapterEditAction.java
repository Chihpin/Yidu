package org.yidu.novel.action.admin;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.Utils;

/**
 * <p>
 * 章节编辑Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "chapterEdit")
@Result(name = AbstractBaseAction.REDIRECT, type = AbstractBaseAction.REDIRECT, location = ChapterListAction.URL)
public class ChapterEditAction extends AbstractAdminEditBaseAction {

    private static final long serialVersionUID = -6064353669030314155L;

    private int chapterno;
    private Integer articleno;
    private String articlename;
    private String volumeid;
    private String chaptername;
    private String content;
    private Integer size;
    private Boolean isvip;
    private Date postdate;

    public int getChapterno() {
        return chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    public Integer getArticleno() {
        return articleno;
    }

    public void setArticleno(Integer articleno) {
        this.articleno = articleno;
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getVolumeid() {
        return volumeid;
    }

    public void setVolumeid(String volumeid) {
        this.volumeid = volumeid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChaptername() {
        return chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getIsvip() {
        return isvip;
    }

    public void setIsvip(Boolean isvip) {
        this.isvip = isvip;
    }

    public int getVip() {
        return isvip ? 2 : 1;
    }

    public void setVip(int vip) {
        this.isvip = vip == 2;
    }

    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        // 初始化种别下拉列表选项
        initCollections(new String[] { "collectionProperties.chapter.isvip" });
        // 编辑
        if (chapterno != 0) {
            TChapter chapter = chapterService.getByNo(chapterno);
            BeanUtils.copyProperties(chapter, this);
        }

        this.content = Utils.getContext(articleno, chapterno, false);
        logger.debug("loadData normally end.");
    }

    public String save() {
        logger.debug("save start.");
        TChapter chapter = new TChapter();
        if (chapterno != 0) {
            chapter = chapterService.getByNo(chapterno);
        }
        BeanUtils.copyProperties(this, chapter);
        chapterService.save(chapter);
        Utils.saveContext(articleno, chapterno, content);
        logger.debug("save normally end.");
        return REDIRECT;
    }

}
