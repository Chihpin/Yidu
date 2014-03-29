package org.yidu.novel.action.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminListBaseAction;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;

/**
 * <p>
 * 章节列表Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "chapterList")
public class ChapterListAction extends AbstractAdminListBaseAction {

    private static final long serialVersionUID = 4594082209171084359L;

    /**
     * 功能名称。
     */
    public static final String NAME = "chapterList";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    /**
     * 小说号
     */
    private int articleno;

    /**
     * 章节号
     */
    private int chapterno;

    private TArticle article = new TArticle();

    private List<TChapter> chapterList = new ArrayList<TChapter>();

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getChapterno() {
        return chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    public TArticle getArticle() {
        return article;
    }

    public void setArticle(TArticle article) {
        this.article = article;
    }

    public List<TChapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<TChapter> chapterList) {
        this.chapterList = chapterList;
    }

    @Override
    protected void loadData() {

        article = articleService.getByNo(articleno);
        ChapterSearchBean searchBean = new ChapterSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        chapterList = chapterService.find(searchBean);

    }

    public String delete() throws Exception {

        TChapter chapter = chapterService.getByNo(chapterno);
        chapter.setDeleteflag(true);
        chapterService.save(chapter);

        loadData();
        return INPUT;

    }

}
