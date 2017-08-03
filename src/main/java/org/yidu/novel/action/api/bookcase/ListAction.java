package org.yidu.novel.action.api.bookcase;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.action.base.JsonBaseUserAction;
import org.yidu.novel.bean.BookcaseSearchBean;
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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.yidu.novel.bean.ResponseBean;
import org.yidu.novel.action.base.JsonBasePublicAction;

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
@Action(value = "api/bookcase/list")
public class ListAction extends JsonBaseUserAction {

    /**
     * 书签号
     */
    private int bookcaseno;
    /**
     * 小说号
     */

    @Override
    protected ResponseBean<?> loadJsonData() {

        BookcaseSearchBean searchBean = new BookcaseSearchBean();
        searchBean.setUserno(LoginManager.getLoginUser().getUserno());
        List<BookcaseDTO> bookcaseList = this.bookcaseService.findWithArticleInfo(searchBean);

        ResponseBean<String> responseBean = new ResponseBean<String>();
        Gson gson = new Gson();
        String obj = gson.toJson(bookcaseList);
        responseBean.setDataObj(obj);
        return responseBean;
    }
}
