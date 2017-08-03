package org.yidu.novel.action.api.category;

import com.google.gson.Gson;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.JsonBaseUserAction;
import org.yidu.novel.bean.ResponseBean;
import org.yidu.novel.cache.CategoryCacheManager;

/**
 * <p>
 * 用户信息展示
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "api/category/list")
public class ListAction extends JsonBaseUserAction {

    @Override
    protected ResponseBean<?> loadJsonData() {
        logger.debug("loadData start.");

        ResponseBean<String> responseBean = new ResponseBean<String>();
        Gson gson = new Gson();
        String obj = gson.toJson(CategoryCacheManager.getCategoryInfo());
        responseBean.setDataObj(obj);
        return responseBean;
    }
}
