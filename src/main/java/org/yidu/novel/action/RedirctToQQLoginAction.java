package org.yidu.novel.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;

import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;

@Action(value = "gotoQQLogin")
@Result(name = AbstractBaseAction.REDIRECT, type = AbstractBaseAction.REDIRECT, location = "${url}")
public class RedirctToQQLoginAction extends AbstractPublicBaseAction {

    private static final long serialVersionUID = -4123651229405239412L;

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

    public String getUrl() {
        String url = NotFoundAction.URL;
        try {
            url = new Oauth().getAuthorizeURL(ServletActionContext.getRequest());
        } catch (QQConnectException e) {
            logger.warn(e.getMessage());
        }
        return url;
    }

    @Override
    public String execute() {
        String backUrl = getBackUrl();
        if (StringUtils.isEmpty(backUrl)) {
            backUrl = "/";
        }
        ServletActionContext.getRequest().getSession(true).setAttribute(BACK_URL, backUrl);
        return REDIRECT;
    };

    @Override
    protected void loadData() {
        // TODO Auto-generated method stub

    }

    public String getTempName() {
        return "test";
    }
}