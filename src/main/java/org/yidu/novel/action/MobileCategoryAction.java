package org.yidu.novel.action;

import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;

/**
 * 
 * <p>
 * 小说用类别Action
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "mobileCategory")
public class MobileCategoryAction extends AbstractPublicBaseAction {

    private static final long serialVersionUID = -6592333259290864477L;

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_CATEGORY;
    }

    public String getTempName() {
        return "category";
    }

    @Override
    protected void loadData() {
    }

}
