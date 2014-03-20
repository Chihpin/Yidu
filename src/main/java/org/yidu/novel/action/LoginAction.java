package org.yidu.novel.action;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.CookieUtils;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

public class LoginAction extends AbstractPublicBaseAction {

    private static final long serialVersionUID = 1L;

    /**
     * 功能名称。
     */
    public static final String NAME = "login";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    private String loginid;
    private String password;
    private boolean useCookie;

    public String getLoginid() {
        return loginid;
    }

    @Validations(
    // 必須
    requiredStrings = { @RequiredStringValidator(message = "${getText(\"errors.required.input\", "
            + "{getText(\"label.user.loginid\")})}") }, stringLengthFields = { @StringLengthFieldValidator(maxLength = "32", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.loginid\")})}") })
    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return password;
    }

    @Validations(
    // 必須
    requiredStrings = { @RequiredStringValidator(message = "${getText(\"errors.required.input\","
            + " {getText(\"label.user.password\")})}") }, stringLengthFields = { @StringLengthFieldValidator(maxLength = "32", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.password\")})}") })
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUseCookie() {
        return useCookie;
    }

    public void setUseCookie(boolean useCookie) {
        this.useCookie = useCookie;
    }

    public String getTempName() {
        return "login";
    }

    @SkipValidation
    public String execute() {
        logger.info("LoginAction execute has been excuted.");
        initCollections(new String[] { "collectionProperties.article.category" });
        if (LoginManager.isLoginFlag()) {
            return GO_TOP;
        } else {
            return FREEMARKER;
        }
    }

    @Transactional
    public String login() {
        logger.info("LoginAction login has been excuted.");
        TUser user = userService.findByLoginInfo(loginid, Utils.convert2MD5(password));
        if (user != null && user.getDeleteflag() != null && !user.getDeleteflag()) {
            logger.info("user info is " + user.toString());
            // 正常登录
            LoginManager.doLogin(user);
            if (true) {
                Cookie cookie = CookieUtils.addUserCookie(user);
                // 添加cookie到response中
                ServletActionContext.getResponse().addCookie(cookie);
            }
            logger.debug("LoginAction login user is exist. normally end.");
            return REDIRECT;
        } else {
            addActionError(getText("errors.login.failed"));
            logger.debug("LoginAction login user is not exist. abnormally end.");
        }
        return FREEMARKER;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_LOGIN;
    }

    @Override
    protected void loadData() {
    }
}
