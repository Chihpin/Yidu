package org.yidu.novel.action;

import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.constant.YiDuConstants;

/**
 * <p>
 * 手机搜索Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "mobileSearch")
public class MobileSearchAction extends AbstractPublicListBaseAction {

    private static final long serialVersionUID = -4215796997609788238L;

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTempName() {
        return "search";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        logger.debug("normally end.");
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_SEARCH;
    }

}
