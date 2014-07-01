package org.yidu.novel.action;

import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;

public class TopAction extends AbstractPublicBaseAction {

    private static final long serialVersionUID = -4123651229405239412L;

    public String getTempName() {
        return "top";
    }

    @Override
    protected void loadData() {
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_TOP;
    }
}
