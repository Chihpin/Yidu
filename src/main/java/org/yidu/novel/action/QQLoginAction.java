package org.yidu.novel.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;

@Action(value = "QQLogin")
public class QQLoginAction extends AbstractPublicBaseAction {

    private static final long serialVersionUID = -4123651229405239412L;

    /**
     * 功能名称。
     */
    public static final String NAME = "QQLogin";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

    public String getUrl() {
        return ServletActionContext.getRequest().getSession().getAttribute(BACK_URL).toString();
    }

    @Override
    public String execute() {

        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
            String accessToken = null;
            String openID = null;
            if (accessTokenObj.getAccessToken().equals("")) {
                logger.warn("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                OpenID openIDObj = new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                com.qq.connect.api.qzone.UserInfo qzoneUserInfo = new com.qq.connect.api.qzone.UserInfo(accessToken,
                        openID);
                com.qq.connect.javabeans.qzone.UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                if (userInfoBean.getRet() == 0) {
                    TUser user = null;
                    if (LoginManager.isLoginFlag()) {
                        // 登录状态绑定
                        user = LoginManager.getLoginUser();
                        user.setOpenid(openID);
                        user.setModifytime(new Date());
                        userService.save(user);
                    } else {
                        // 未登录状态添加用户

                        // 根据openid查询用户信息
                        user = userService.findByOpenid(openID);

                        if (user == null) {
                            // 用户不存在的话，新建用户
                            user = new TUser();
                            user.setLoginid(userInfoBean.getNickname());
                            // 设置默认随机密码
                            user.setPassword(Utils.convert2MD5(userInfoBean.getNickname() + Utils.getRandomString(32)));
                            user.setSex(StringUtils.equals(userInfoBean.getGender(), "男") ? (short) 1 : (short) 2);
                            user.setRegdate(new Date());
                            user.setDeleteflag(false);
                            user.setActivedflag(true);
                            user.setOpenid(openID);
                            user.setModifytime(new Date());
                            userService.save(user);
                        }
                    }

                    LoginManager.doLogin(user);
                } else {
                    logger.warn("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
                }
            }

        } catch (QQConnectException e) {
            logger.warn("连接失败：" + e.getMessage());
        }

        return REDIRECT;
    };

    @Override
    protected void loadData() {
    }

}