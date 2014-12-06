package org.yidu.novel.dto;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.ChapterListAction;
import org.yidu.novel.action.ReaderAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TChapter;

/**
 * 
 * <p>
 * 章节DTO
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class ChapterDTO extends TChapter {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -9171385880720383954L;
    /**
     * 下一章章节编号
     */
    private int nextChapterno;
    /**
     * 上一章章节编号
     */
    private int preChapterno;
    /**
     * 章节内容
     */
    private String content;

    /**
     * 获取 nextChapterno
     * 
     * @return nextChapterno
     */
    public int getNextChapterno() {
        return nextChapterno;
    }

    /**
     * 
     * 设置nextChapterno
     * 
     * 
     * @param nextChapterno
     *            nextChapterno
     */
    public void setNextChapterno(int nextChapterno) {
        this.nextChapterno = nextChapterno;
    }

    /**
     * 获取 preChapterno
     * 
     * @return preChapterno
     */
    public int getPreChapterno() {
        return preChapterno;
    }

    /**
     * 
     * 设置preChapterno
     * 
     * 
     * @param preChapterno
     *            preChapterno
     */
    public void setPreChapterno(int preChapterno) {
        this.preChapterno = preChapterno;
    }

    /**
     * 获取 content
     * 
     * @return content
     */
    public String getContent() {
        String keywords = YiDuConstants.yiduConf.getString(YiDuConfig.FILTER_KEYWORD);
        String[] keywordArr = StringUtils.split(keywords, ",");
        for (String string : keywordArr) {
            content = content.replaceAll(string, "");
        }
        return content;
    }

    /**
     * 
     * 设置content
     * 
     * 
     * @param content
     *            content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取章节列表URL
     * 
     * @return 分类列表URL
     */
    public String getChapterListUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ChapterListAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno());
    }

    /**
     * 获取下一章章节URL
     * 
     * @return 下一章章节URL
     */

    public String getNextChapterUrl() {
        if (getNextChapterno() != 0) {
            HttpServletResponse response = ServletActionContext.getResponse();
            return response.encodeURL(ReaderAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno()
                    + "&chapterno=" + getNextChapterno());
        } else {
            if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CHAPTER_INDEX_PAGE, false)) {
                return getChapterListUrl();
            } else {
                return getInfoUrl();
            }
        }
    }

    /**
     * 获取下一章章节URL
     * 
     * @return 上一章章节URL
     */
    public String getPreChapterUrl() {
        if (getPreChapterno() != 0) {
            HttpServletResponse response = ServletActionContext.getResponse();
            return response.encodeURL(ReaderAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno()
                    + "&chapterno=" + getPreChapterno());
        } else {
            if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CHAPTER_INDEX_PAGE, false)) {
                return getChapterListUrl();
            } else {
                return getInfoUrl();
            }

        }
    }

    /**
     * 获取下一章章节URL
     * 
     * @return 下一章章节URL
     */

    public String getNextChapterThumbnailUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ReaderAction.URL + "?chapterno=" + getNextChapterno());
    }

    /**
     * 获取下一章章节URL
     * 
     * @return 上一章章节URL
     */
    public String getPreChapterThumbnailUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ReaderAction.URL + "?chapterno=" + getPreChapterno());
    }

}
