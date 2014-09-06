package org.yidu.novel.dto;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.ReaderAction;
import org.yidu.novel.entity.TChapter;

public class ChapterDTO extends TChapter {

    private static final long serialVersionUID = -9171385880720383954L;
    private int nextChapterno;
    private int preChapterno;
    private String content;

    public int getNextChapterno() {
        return nextChapterno;
    }

    public void setNextChapterno(int nextChapterno) {
        this.nextChapterno = nextChapterno;
    }

    public int getPreChapterno() {
        return preChapterno;
    }

    public void setPreChapterno(int preChapterno) {
        this.preChapterno = preChapterno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取下一章章节URL
     * 
     * @return 下一章章节URL
     */

    public String getNextChapterUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ReaderAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno()
                + "&chapterno=" + getNextChapterno());
    }

    /**
     * 获取下一章章节URL
     * 
     * @return 上一章章节URL
     */
    public String getPreChapterUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ReaderAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno()
                + "&chapterno=" + getPreChapterno());
    }

}
