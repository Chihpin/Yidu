package org.yidu.novel.action.admin;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.Utils;

/**
 * <p>
 * 用户编辑Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "userEdit")
@Result(name = AbstractBaseAction.REDIRECT, type = AbstractBaseAction.REDIRECT, location = UserListAction.URL)
public class UserEditAction extends AbstractAdminEditBaseAction {

    private static final long serialVersionUID = 8182483310788301445L;

    /**
     * 命名空间。
     */
    public static final String NAMESPACE = "/admin/user";

    /**
     * 功能名称。
     */
    public static final String NAME = "edit";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    private int userno;
    private String loginid;
    private String password;
    private String username;
    private String email;
    private Date regdate;
    private Short sex;
    private String qq;
    private Short type;
    private Date lastlogin;

    public int getUserno() {
        return userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }

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

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
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

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        // 初始化类别下拉列表选项
        initCollections(new String[] { "collectionProperties.user.sex", "collectionProperties.user.type" });
        // 编辑
        if (userno != 0) {
            TUser user = userService.getByNo(userno);
            BeanUtils.copyProperties(user, this);
        }
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
        if (userno != 0) {
            user = userService.getByNo(userno);
        } else {
            user.setRegdate(new Date());
        }
        BeanUtils.copyProperties(this, user, new String[] { "regdate", "lastlogin", "password" });
        user.setPassword(Utils.convert2MD5(password));

        userService.save(user);
        logger.debug("save normally end.");
        return REDIRECT;
    }

}
