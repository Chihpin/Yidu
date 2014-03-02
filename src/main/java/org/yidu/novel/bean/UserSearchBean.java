package org.yidu.novel.bean;

public class UserSearchBean extends BaseSearchBean {
    /**
     * 用户ID
     */
    private int userno;
    /**
     * 登录ID
     */
    private String loginid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 邮件地址
     */
    private String email;
    /**
     * 性别
     */
    private int sex;
    private Boolean deleteflag;

    public int getUserno() {
        return userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Boolean getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Boolean deleteflag) {
        this.deleteflag = deleteflag;
    }

}
