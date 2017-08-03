package org.yidu.novel.action.api.user;

import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.yidu.novel.bean.ResponseBean;
import org.yidu.novel.action.base.JsonBaseUserAction;
import org.apache.struts2.convention.annotation.Action;
/**
 * <p>
 * 用户信息展示
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "api/user/info")
public class InfoAction extends JsonBaseUserAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 8182483310788301445L;


    /**
     * 用户编号
     */
    private int userno;
    /**
     * 用户信息
     */
    private TUser user;

    /**
     * 获取 userno
     * 
     * @return userno
     */
    public int getUserno() {
        return userno;
    }

    /**
     * 
     * 设置userno
     * 
     * 
     * @param userno
     *            userno
     */
    public void setUserno(int userno) {
        this.userno = userno;
    }

    /**
     * 获取 user
     * 
     * @return user
     */
    public TUser getUser() {
        return user;
    }

    /**
     * 
     * 设置user
     * 
     * 
     * @param user
     *            user
     */
    public void setUser(TUser user) {
        this.user = user;
    }


    @Override
    protected ResponseBean<?> loadJsonData() {
        logger.debug("loadData start.");

        // 编辑
        user = userService.getByNo(userno);
        logger.debug("loadData normally end.");


        ResponseBean<String> responseBean = new ResponseBean<String>();
        Gson gson = new Gson();
        String obj = gson.toJson(user);
        responseBean.setDataObj(obj);
        return responseBean;
    }
}
