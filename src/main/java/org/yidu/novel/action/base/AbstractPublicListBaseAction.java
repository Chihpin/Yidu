package org.yidu.novel.action.base;

import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.Pagination;

public abstract class AbstractPublicListBaseAction extends AbstractPublicBaseAction {

    private static final long serialVersionUID = 3736781138346525060L;

    // Initializing Pagination with page size countPerPage, and current page 1
    protected Pagination pagination = new Pagination(YiDuConstants.yiduConf.getInt(YiDuConfig.COUNT_PER_PAGE, 20), 1);

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
