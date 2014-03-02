package org.yidu.novel.entity;

// Generated 2013/12/26 20:57:47 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * TChapter generated by hbm2java
 */
public class TChapter implements java.io.Serializable {

    private static final long serialVersionUID = -1179430868094869232L;
    private int chapterno;
    private Integer articleno;
    private String articlename;
    private Short chaptertype;
    private String chaptername;
    private Integer size;
    private Boolean isvip;
    private Date postdate;

    public TChapter() {
    }

    public TChapter(int chapterno) {
        this.chapterno = chapterno;
    }

    public TChapter(int chapterno, Integer articleno, String articlename, Short chaptertype, String chaptername,
            Integer size, Boolean isvip, Date postdate) {
        this.chapterno = chapterno;
        this.articleno = articleno;
        this.articlename = articlename;
        this.chaptertype = chaptertype;
        this.chaptername = chaptername;
        this.size = size;
        this.isvip = isvip;
        this.postdate = postdate;
    }

    public int getChapterno() {
        return this.chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    public Integer getArticleno() {
        return this.articleno;
    }

    public void setArticleno(Integer articleno) {
        this.articleno = articleno;
    }

    public String getArticlename() {
        return this.articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public Short getChaptertype() {
        return chaptertype;
    }

    public void setChaptertype(Short chaptertype) {
        this.chaptertype = chaptertype;
    }

    public String getChaptername() {
        return this.chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getIsvip() {
        return this.isvip;
    }

    public void setIsvip(Boolean isvip) {
        this.isvip = isvip;
    }

    public Date getPostdate() {
        return this.postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

}
