package org.yidu.novel.action.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.template.EncodeURLMethod;
import org.yidu.novel.template.GetTextMethod;
import org.yidu.novel.utils.CookieUtils;

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

    private static final long serialVersionUID = 6698799932081679448L;

    protected abstract void loadData();

    /**
     * 是否存在错误
     */
    private boolean hasError = false;

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

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
     * 阅读履历
     */
    private List<TChapter> historyList = new ArrayList<TChapter>();

    public List<TChapter> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<TChapter> historyList) {
        this.historyList = historyList;
    }

    protected void loadReadHistory() {
        // 获得阅读履历
        String historys = CookieUtils.getHistoryCookie(ServletActionContext.getRequest());
        if (StringUtils.isNotEmpty(historys)) {
            String[] acnos = StringUtils.split(historys, ",");
            List<String> chapternoList = new ArrayList<String>();
            for (String articleAndchapterno : acnos) {
                String[] acnoArr = StringUtils.split(articleAndchapterno, "|");
                if (acnoArr.length == 2) {
                    chapternoList.add(acnoArr[1]);
                }
            }
            if (chapternoList.size() > 0) {
                ChapterSearchBean searchBean = new ChapterSearchBean();
                searchBean.setChapternos(StringUtils.join(chapternoList, ","));
                this.historyList = this.chapterService.find(searchBean);
            }
        }
    }

    /**
     * 回退URL
     */
    private String backUrl;

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
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
     * 取得模版名
     * 
     * @return 模版名
     */
    public String getThemeName() {

        return YiDuConstants.yiduConf.getString("themeName", "default");
    }
}
