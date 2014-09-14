package org.yidu.novel.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

/**
 * 
 * <p>
 * 用户注册Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class RegisterAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 1L;

    private String loginid;
    private String password;
    private String repassword;
    private String email;
    private String qq;

    public String getLoginid() {
        return loginid;
    }

    @RequiredStringValidator(message = "${getText(\"errors.required.input\"," + " {getText(\"label.user.loginid\")})}")
    @StringLengthFieldValidator(minLength = "5", maxLength = "32", message = "${getText(\"errors.lengthrange\", "
            + "{ {minLength}, {maxLength},getText(\"label.user.loginid\")})}")
    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return password;
    }

    // 必須
    @RequiredStringValidator(message = "${getText(\"errors.required.input\"," + " {getText(\"label.user.password\")})}")
    @StringLengthFieldValidator(minLength = "6", maxLength = "32", message = "${getText(\"errors.lengthrange\", "
            + "{ {minLength},{maxLength},getText(\"label.user.password\")})}")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getEmail() {
        return email;
    }

    // 必須
    @RequiredStringValidator(message = "${getText(\"errors.required.input\"," + " {getText(\"label.user.email\")})}")
    // 长度
    @StringLengthFieldValidator(maxLength = "60", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.email\")})}")
    @RegexFieldValidator(regexExpression = YiDuConstants.Regex.EMAIL, message = "${getText(\"errors.format.email\", {getText('label.user.email')})}")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    // 长度检查
    @StringLengthFieldValidator(maxLength = "15", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.qq\")})}")
    // 数字检查
    @RegexFieldValidator(regexExpression = YiDuConstants.Regex.NUMBER, message = "${getText(\"errors.format.number\", {getText('label.user.qq')})}")
    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTempName() {
        return "register";
    }

    @SkipValidation
    public String execute() {
        logger.info("RegisterAction execute has been excuted.");
        if (LoginManager.isLoginFlag()) {
            return REDIRECT;
        } else {
            // 记录访问地址
            LoginManager.setReferer();
            return FREEMARKER;
        }
    }

    @Transactional
    public String register() {
        logger.info("RegisterAction register started.");
        // 密码检查
        if (!StringUtils.equals(password, repassword)) {
            addActionError(getText("errors.repassword"));
            return FREEMARKER;
        }
        // 重复检查
        UserSearchBean searchBean = new UserSearchBean();
        searchBean.setLoginid(loginid);
        searchBean.setDeleteflag(false);
        List<TUser> userList = this.userService.find(searchBean);
        if (userList != null && userList.size() > 0) {
            addActionError(this.getText("errors.duplicated", new String[] { this.getText("label.user.loginid") }));
            return FREEMARKER;
        }

        TUser user = new TUser();
        BeanUtils.copyProperties(this, user);
        user.setDeleteflag(false);
        user.setRegdate(new Date());
        user.setPassword(Utils.convert2MD5(password));
        user.setType(YiDuConstants.UserType.NORMAL_USER);
        // 注册用户登录
        this.userService.save(user);
        // 登录处理
        LoginManager.doLogin(user);
        logger.debug("RegisterAction register normally end.");
        return REDIRECT;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_REGEDIT;
    }

    @Override
    protected void loadData() {
    }
}