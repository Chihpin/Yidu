package org.yidu.novel.bean;

import java.util.Date;

public class ArticleSearchBean extends BaseSearchBean {

    private int articleno;
    private Date lastupdate;
    private String articlename;
    private String keywords;
    private Integer authorid;
    private String author;
    private Integer category;
    private Boolean fullflag;

    private String key;

    private String articlenos;

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getFullflag() {
        return fullflag;
    }

    public void setFullflag(Boolean fullflag) {
        this.fullflag = fullflag;
    }

    public String getArticlenos() {
        return articlenos;
    }

    public void setArticlenos(String articlenos) {
        this.articlenos = articlenos;
    }

    @Override
    public String toString() {
        return "ArticleSearchBean [articleno=" + articleno + ", lastupdate=" + lastupdate + ", articlename="
                + articlename + ", keywords=" + keywords + ", authorid=" + authorid + ", author=" + author
                + ", category=" + category + ", searchKey=" + key + ", fullflag=" + fullflag + ", articlenos="
                + articlenos + getPagination() + "]";
    }

}
