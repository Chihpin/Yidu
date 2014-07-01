package org.yidu.novel.action;

import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
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
public class BookcaseAction extends AbstractPublicBaseAction {

    private static final long serialVersionUID = 366181195078436796L;

    public String getTempName() {
        return "bookcase";
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_BOOKCASE;
    }

    public TUser getLoginUser() {
        return LoginManager.getLoginUser();
    }

    @Override
    protected void loadData() {
    }
}
