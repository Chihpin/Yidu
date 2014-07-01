package org.yidu.novel.dto;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TBookcase;

public class BookcaseDTO extends TBookcase {

    private static final long serialVersionUID = 2193825089517868949L;
    private Integer lastchapterno;
    private String lastchapter;
    private Integer chapters;
    private Integer size;
    private Boolean fullflag;
    private Date lastupdate;
    private Integer imgflag;

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

    public Integer getImgflag() {
        return imgflag;
    }

    public void setImgflag(Integer imgflag) {
        this.imgflag = imgflag;
    }

    public String getImgUrl() {
        String fileName = "";
        if (imgflag == null) {
            fileName = "nocover.jpg";
        } else {
            switch (imgflag) {
            case 1:
                fileName = getArticleno() + "s.jpg";
                break;
            case 2:
                fileName = getArticleno() + "s.gif";
                break;
            case 3:
                fileName = getArticleno() + "s.png";
                break;
            case 10:
                fileName = getArticleno() + "l.jpg";
                break;
            default:
                fileName = "nocover.jpg";
                break;
            }
        }
        String imgUrl = YiDuConstants.yiduConf.getString(YiDuConfig.RELATIVE_IAMGE_PATH) + "/";
        if (StringUtils.equals("nocover.jpg", fileName)) {
            imgUrl = imgUrl + fileName;
        } else {
            imgUrl = imgUrl + getArticleno() / YiDuConstants.SUB_DIR_ARTICLES + "/" + getArticleno() + "/" + fileName;
        }
        return imgUrl;
    }

}
