package org.yidu.novel.action.api.bookcase;

import com.google.gson.Gson;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.JsonBaseUserAction;
import org.yidu.novel.bean.BookcaseSearchBean;
import org.yidu.novel.bean.ResponseBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.BookcaseDTO;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TBookcase;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * <p>
 * 书架管理Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "api/bookcase/add")
public class AddAction extends JsonBaseUserAction {

    /**
     * 书签号
     */
    private int bookcaseno;
    /**
     * 小说号
     */
    private int articleno;


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
    protected ResponseBean<?> loadJsonData() {

        // 小说号如果没有的话，终止登录，返回错误画面
        if (articleno == 0) {
            return new ResponseBean<Object>(RETCODE.PARAM_ERR.intValue, null);
        }

        // 检查当前登录的最大件数
        BookcaseSearchBean searchBean = new BookcaseSearchBean();
        searchBean.setUserno(LoginManager.getLoginUser().getUserno());
        int bookcaseCount = this.bookcaseService.getCount(searchBean);
        if (bookcaseCount > YiDuConstants.yiduConf.getInt(YiDuConfig.MAX_BOOKCASE)) {
            return new ResponseBean<Object>(RETCODE.BOOKCASE_MAX.intValue, null);
        }

        TBookcase bookcase = this.bookcaseService.getByArticlenoAndUserno(LoginManager.getLoginUser().getUserno(),
                articleno);
        if (bookcase == null) {
            bookcase = new TBookcase();
        }

        TArticle article = this.articleService.getByNo(articleno);
        if (Utils.isDefined(article) && article.getArticleno() != 0) {
            BeanUtils.copyProperties(article, bookcase);
        } else {
            return new ResponseBean<Object>(RETCODE.BOOK_NOTFOUND.intValue, null);
        }

        bookcase.setCreatetime(new Date());
        bookcase.setUserno(LoginManager.getLoginUser().getUserno());
        this.bookcaseService.save(bookcase);

        bookcase = this.bookcaseService.getByArticlenoAndUserno(LoginManager.getLoginUser().getUserno(),
                articleno);
        ResponseBean<String> responseBean = new ResponseBean<String>();
        Gson gson = new Gson();
        String obj = gson.toJson(bookcase);
        responseBean.setDataObj(obj);
        return responseBean;
    }
}
