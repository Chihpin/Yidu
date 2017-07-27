package org.yidu.novel.action.base;

import org.yidu.novel.utils.Pagination;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.bean.ResponseBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.yidu.novel.bean.ResponseBean;
/**
 * <p>
 * JSON处理的基类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public abstract class JsonBaseAction extends AbstractBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 1L;

    /**
     * 返回数据
     */
    protected ResponseBean<?> res;


    /**
     * 初始化Pagination，默认每页显示1件
     */
    protected Pagination pagination = new Pagination(YiDuConstants.yiduConf.getInt(YiDuConfig.COUNT_PER_PAGE, 20), 1);

    /**
     * 获取 pagination
     * 
     * @return pagination
     */
    public Pagination getPagination() {
        return pagination;
    }

    /**
     * 
     * 设置pagination
     * 
     * 
     * @param pagination
     *            pagination
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    
    @Override
    public String execute() {
        res = loadJsonData();
        return JSON_RESULT;
    }

    /**
     * 加载Json数据
     * 
     * @return Json数据
     */
    protected abstract ResponseBean<?> loadJsonData();

    /**
     * 取得返回数据
     * 
     * @return 返回数据
     */
    public ResponseBean<?> getData() {
        return res;
    }

}
