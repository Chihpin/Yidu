package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.bean.BookcaseSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.BookcaseDTO;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TBookcase;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.LoginManager;

/**
 * 
 * <p>
 * 书架管理Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class BookcaseAction extends AbstractUserBaseAction {

    private static final long serialVersionUID = 366181195078436796L;
    /**
     * 书签号
     */
    private int bookcaseno;
    /**
     * 小说号
     */
    private int articleno;
    /**
     * 章节号
     */
    private int chapterno;

    private List<BookcaseDTO> bookcaseList = new ArrayList<BookcaseDTO>();

    public int getBookcaseno() {
        return bookcaseno;
    }

    public void setBookcaseno(int bookcaseno) {
        this.bookcaseno = bookcaseno;
    }

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

    public List<BookcaseDTO> getBookcaseList() {
        return bookcaseList;
    }

    public void setBookcaseList(List<BookcaseDTO> bookcaseList) {
        this.bookcaseList = bookcaseList;
    }

    public int getMaxBookcaseNum() {
        return YiDuConstants.yiduConf.getInt(YiDuConfig.MAX_BOOKCASE);
    }

    public int getBookcaseNum() {
        return bookcaseList.size();
    }

    @Override
    protected void loadData() {
        BookcaseSearchBean searchBean = new BookcaseSearchBean();
        searchBean.setUserno(LoginManager.getLoginUser().getUserno());
        bookcaseList = this.bookcaseService.findBySql(searchBean);
    }

    @Transactional
    public String add() {
        initCollections(new String[] { "collectionProperties.article.category" });
        // 小说号如果没有的话，终止登录，返回错误画面
        if (articleno == 0) {
            addActionError(getText("errors.not.exsits.article"));
            return ERROR;
        }

        // 检查当前登录的最大件数
        BookcaseSearchBean searchBean = new BookcaseSearchBean();
        searchBean.setUserno(LoginManager.getLoginUser().getUserno());
        int bookcaseCount = this.bookcaseService.getCount(searchBean);
        if (bookcaseCount > YiDuConstants.yiduConf.getInt(YiDuConfig.MAX_BOOKCASE)) {
            addActionError(getText("errors.max.bookcase"));
            return ERROR;
        }

        TBookcase bookcase = this.bookcaseService.getByArticleno(LoginManager.getLoginUser().getUserno(), articleno);
        if (bookcase == null) {
            bookcase = new TBookcase();
        }

        if (chapterno != 0) {
            TChapter chapter = this.chapterService.getByNo(chapterno);
            if (chapter != null && chapter.getChapterno() != 0) {
                BeanUtils.copyProperties(chapter, bookcase);
            } else {
                addActionError(getText("errors.not.exsits.chapter"));
                return ERROR;
            }
        } else if (articleno != 0) {
            TArticle article = this.articleService.getByNo(articleno);
            if (article != null && article.getArticleno() != 0) {
                BeanUtils.copyProperties(article, bookcase);
            } else {
                addActionError(getText("errors.not.exsits.article"));
                return ERROR;
            }
        }
        bookcase.setCreatetime(new Date());
        bookcase.setUserno(LoginManager.getLoginUser().getUserno());

        this.bookcaseService.save(bookcase);
        this.loadData();
        return MESSAGE;
    }

    @Transactional
    public String delete() {
        if (bookcaseno != 0) {
            TBookcase bookcase = this.bookcaseService.getByNo(bookcaseno);
            if (bookcase.getUserno() == LoginManager.getLoginUser().getUserno()) {
                this.bookcaseService.delteByNo(bookcaseno);
            } else {
                addActionError(getText("errors.unauthority.bookcase"));
                return ERROR;
            }
        }
        this.loadData();
        return INPUT;
    }
}
