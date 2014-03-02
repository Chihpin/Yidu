package org.yidu.novel.bean;

public class ChapterSearchBean extends BaseSearchBean {

    /**
     * 小说号
     */
    private int articleno;

    /**
     * 章节号
     */
    private int chapterno;

    /**
     * 章节号
     */
    private String chapternos;

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getChapterno() {
        return chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    public String getChapternos() {
        return chapternos;
    }

    public void setChapternos(String chapternos) {
        this.chapternos = chapternos;
    }
}
