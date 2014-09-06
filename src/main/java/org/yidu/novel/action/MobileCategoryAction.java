package org.yidu.novel.action;

import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;

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
