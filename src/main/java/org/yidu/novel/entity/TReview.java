package org.yidu.novel.entity;

// Generated 2013/12/26 20:57:47 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * TReview generated by hbm2java
 */
public class TReview implements java.io.Serializable {

    private static final long serialVersionUID = -1850384133155930329L;
    private int reviweno;
    private Integer articleno;
    private String review;
    private String username;
    private String email;
    private Date createtime;

    public TReview() {
    }

    public TReview(int reviweno) {
        this.reviweno = reviweno;
    }

    public TReview(int reviweno, Integer articleno, String review, String username, String email, Date createtime) {
        this.reviweno = reviweno;
        this.articleno = articleno;
        this.review = review;
        this.username = username;
        this.email = email;
        this.createtime = createtime;
    }

    public int getReviweno() {
        return this.reviweno;
    }

    public void setReviweno(int reviweno) {
        this.reviweno = reviweno;
    }

    public Integer getArticleno() {
        return this.articleno;
    }

    public void setArticleno(Integer articleno) {
        this.articleno = articleno;
    }

    public String getReview() {
        return this.review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

}
