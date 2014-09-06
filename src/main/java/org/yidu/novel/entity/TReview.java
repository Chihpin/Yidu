package org.yidu.novel.entity;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.InfoAction;
import org.yidu.novel.action.UserInfoAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.base.BaseTReview;

public class TReview extends BaseTReview {
    private static final long serialVersionUID = 1L;

    /* [CONSTRUCTOR MARKER BEGIN] */
    public TReview() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public TReview(int reviewno) {
        super(reviewno);
    }

    /* [CONSTRUCTOR MARKER END] */

    public Boolean getDeleteflag() {
        return isDeleteflag();
    }

    public Integer getSubdir() {
        return getArticleno() / YiDuConstants.SUB_DIR_ARTICLES;
    }

    /**
     * 获取用户信息URL
     * 
     * @return 用户信息URL
     */
    public String getUserInfoUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(UserInfoAction.URL + "?userno=" + getUserno());
    }

    /**
     * 获取小说介绍页URL
     * 
     * @return 小说介绍页URL
     */
    public String getInfoUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(InfoAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno());
    }

}