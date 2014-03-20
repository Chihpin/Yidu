package org.yidu.novel.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

/**
 * <p>
 * 用户编辑Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "useredit")
public class UserEditAction extends AbstractUserBaseAction {

    private static final long serialVersionUID = 8182483310788301445L;

    /**
     * 功能名称。
     */
    public static final String NAME = "useredit";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    private String loginid;
    private String password;
    private String username;
    private String email;
    private Short sex;
    private String qq;

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_USER_USEREDIT;
    }

    @Override
    public String getTempName() {
        return "useredit";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        int userno = LoginManager.getLoginUser().getUserno();
        // 初始化类别下拉列表选项
        initCollections(new String[] { "collectionProperties.user.sex", "collectionProperties.user.type" });
        // 编辑
        TUser user = userService.getByNo(userno);
        BeanUtils.copyProperties(user, this);

        logger.debug("loadData normally end.");
    }

    /**
     * <p>
     * 保存画面的内容
     * </p>
     * 
     * @return
     * @throws Exception
     */
    public String save() {
        logger.debug("save start.");
        TUser user = new TUser();
        int userno = LoginManager.getLoginUser().getUserno();
        user = userService.getByNo(userno);
        BeanUtils.copyProperties(this, user, new String[] { "regdate", "lastlogin" });
        userService.save(user);
        logger.debug("save normally end.");
        return REDIRECT;
    }
}
