package org.yidu.novel.action;

import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;

public class IndexAction extends AbstractPublicBaseAction {

    private static final long serialVersionUID = -5991997032217966675L;

    /**
     * 功能名称。
     */
    public static final String NAME = "index";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;
    
    public String getTempName() {
        return "index";
    }

    @Override
    public void loadData() {
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_INDEX;
    }
}
