package org.yidu.novel.action.base;

import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.Pagination;

public abstract class AbstractAdminListBaseAction extends AbstractAdminBaseAction {

    private static final long serialVersionUID = 5250455993870220163L;

    // Initializing Pagination with page size countPerPage, and current page 1
    protected Pagination pagination = new Pagination(YiDuConstants.yiduConf.getInt(YiDuConfig.NUMBER_PER_PAGE), 1);

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
