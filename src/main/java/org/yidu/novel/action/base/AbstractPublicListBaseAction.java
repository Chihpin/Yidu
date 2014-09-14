package org.yidu.novel.action.base;

import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.Pagination;

/**
 * <p>
 * 公共页面的列表类处理的基类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
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
