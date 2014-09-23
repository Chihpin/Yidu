package org.yidu.novel.action.base;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.template.EncodeURLMethod;
import org.yidu.novel.template.GetTextMethod;
import org.yidu.novel.template.UpperNumMethod;

import com.google.gson.Gson;

/**
 * 
 * <p>
 * 公共页和用户页共通父类
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public abstract class AbstractPublicAndUserBaseAction extends AbstractBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 6698799932081679448L;

    /**
     * 获取数据
     */
    protected abstract void loadData();

    /**
     * 是否存在错误
     */
    private boolean hasError = false;

    /**
     * 设置是否存在错误标识
     * 
     * @param hasError
     *            是否存在错误标识
     */
    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    /**
     * 获得是否存在错误标识
     * 
     * @return 是否存在错误标识
     */
    public boolean getHasError() {
        return hasError;
    }

    /**
     * 公共页和用户页用类型JSON文字列
     * 
     * @return 类型JSON文字列
     */
    public String getCategoryData() {

        Gson gson = new Gson();
        LinkedMap pulldown = new LinkedMap();
        String value = getText("collectionProperties.article.category");
        String[] items = value.split(",");
        for (String item : items) {
            String[] property = item.split(":");
            if (property.length == 2) {
                pulldown.put(property[0], property[1]);
            }
        }
        return gson.toJson(pulldown);
    }

    /**
     * 
     * <p>
     * 获取启用广告标识
     * </p>
     * 
     * @return 启用广告标识
     */
    public boolean getAdEffective() {
        return YiDuConstants.yiduConf.getBoolean(YiDuConfig.AD_EFFECTIVE, true);
    }

    /**
     * 取得自定义的freemarker方法 <br>
     * 实现encodeURL
     * 
     * @return EncodeURL
     */
    public EncodeURLMethod getEncodeURL() {
        return new EncodeURLMethod(ServletActionContext.getResponse());
    }

    /**
     * 取得自定义的freemarker方法 <br>
     * 实现struts的gettxt
     * 
     * @return GetText
     */
    public GetTextMethod getGetText() {
        return new GetTextMethod(this);
    }

    /**
     * 取得自定义的freemarker方法 <br>
     * 实现struts的gettxt
     * 
     * @return GetText
     */
    public UpperNumMethod getUpperNum() {
        return new UpperNumMethod();
    }

    /**
     * 取得模版名
     * 
     * @return 模版名
     */
    public String getThemeName() {

        return YiDuConstants.yiduConf.getString("themeName", "default");
    }

    /**
     * <p>
     * 是否开启QQ登录
     * </p>
     * 
     * @return 是否开启QQ登录
     */
    public boolean getEnableQQLogin() {
        return YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_QQLOGIN, false);
    }

    public String getSiteTitle() {
        return getText("label.system.title");
    }

    public String getSiteKeyword() {
        return getText("label.system.siteKeywords");
    }

    public String getSiteDescription() {
        return getText("label.system.siteDescription");
    }

    public String getSiteName() {
        return getText("label.system.name");
    }

    public String getSiteUrl() {
        return getText("label.system.url");
    }

    public String getSiteCopyright() {
        return getText("label.system.copyright");
    }

    public String getSiteSupport() {
        return getText("label.system.support");
    }

    public String getBeianNo() {
        return getText("label.system.beianNo");
    }

    public String getAnalyticscode() {
        return getText("label.system.analyticscode");
    }

    public String getDomain() {
        return getText("label.system.domain");
    }

}
