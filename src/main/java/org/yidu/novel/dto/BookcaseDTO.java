package org.yidu.novel.dto;

import java.util.Date;

import org.yidu.novel.entity.TBookcase;

public class BookcaseDTO extends TBookcase {

    private static final long serialVersionUID = 2193825089517868949L;
    private Integer lastchapterno;
    private String lastchapter;
    private Integer chapters;
    private Integer size;
    private Boolean fullflag;
    private Date lastupdate;

    public Integer getLastchapterno() {
        return lastchapterno;
    }

    public void setLastchapterno(Integer lastchapterno) {
        this.lastchapterno = lastchapterno;
    }

    public String getLastchapter() {
        return lastchapter;
    }

    public void setLastchapter(String lastchapter) {
        this.lastchapter = lastchapter;
    }

    public Integer getChapters() {
        return chapters;
    }

    public void setChapters(Integer chapters) {
        this.chapters = chapters;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getFullflag() {
        return fullflag;
    }

    public void setFullflag(Boolean fullflag) {
        this.fullflag = fullflag;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

}
