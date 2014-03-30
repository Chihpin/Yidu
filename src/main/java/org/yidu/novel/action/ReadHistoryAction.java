package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.CookieUtils;

/**
 * 
 * <p>
 * 取得阅览履历Action
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "readhistory")
public class ReadHistoryAction extends AbstractPublicBaseAction {

    private static final long serialVersionUID = -1839809309350704624L;

    /**
     * 阅读履历
     */
    private List<TChapter> historyList = new ArrayList<TChapter>();

    @SkipValidation
    @Override
    public String execute() {
        logger.debug("execute start.");
        loadData();
        logger.debug("execute normally end.");
        return AbstractBaseAction.JSON_RESULT;
    }

    public List<TChapter> getData() {
        return historyList;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
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
                historyList = this.chapterService.find(searchBean);
            }
        }
        logger.debug("loadData normally end.");
    }

}
