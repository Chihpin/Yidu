package org.yidu.novel.entity;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.ArticleListAction;
import org.yidu.novel.action.DownloadAction;
import org.yidu.novel.action.InfoAction;
import org.yidu.novel.action.ReaderAction;
import org.yidu.novel.action.ReviewListAction;
import org.yidu.novel.action.user.BookcaseAction;
import org.yidu.novel.action.user.VoteAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.base.BaseTArticle;

public class TArticle extends BaseTArticle {
    private static final long serialVersionUID = 1L;

    /* [CONSTRUCTOR MARKER BEGIN] */
    public TArticle() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public TArticle(int articleno) {
        super(articleno);
    }

    /* [CONSTRUCTOR MARKER END] */

    public Boolean getFullflag() {
        return isFullflag();
    }

    public Boolean getFirstflag() {
        return isFirstflag();
    }

    public Boolean getAuthorflag() {
        return isAuthorflag();
    }

    public Boolean getDeleteflag() {
        return isDeleteflag();
    }

    public String getIntroForHtml() {
        if (getIntro() != null) {
            // 替换换行和空格
            String html = StringEscapeUtils.escapeHtml4(getIntro());
            return html.replaceAll("\r\n", "<br/>").replaceAll("\n", "<br/>").replaceAll("\\s", "&nbsp;");
        }
        return null;
    }

    public String getIntroOmit() {
        if (getIntro() != null && getIntro().length() > 60) {
            return getIntro().substring(0, 60) + "...";
        }
        return getIntro();
    }

    public Integer getSubdir() {
        return getArticleno() / YiDuConstants.SUB_DIR_ARTICLES;
    }

    public String getLastchapterOmit() {
        if (getLastchapter() != null && getLastchapter().length() > 10) {
            return getLastchapter().substring(0, 10);
        }
        return getLastchapter();
    }

    public String getLastupdateMin() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        return sdf1.format(getLastupdate());
    }

    public String getCategoryStr() {
        String[] categoryArr = new String[] { "玄幻魔法", "武侠修真", "都市言情", "历史军事", "侦探推理", "网游动漫", "科幻小说", "恐怖灵异", "散文诗词",
                "其他类型" };
        return categoryArr[getCategory() - 1];
    }

    public String getImgUrl() {
        String fileName = "";
        if (getImgflag() == null) {
            fileName = "nocover.jpg";
        } else {
            switch (getImgflag()) {
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

    /**
     * 获取小说介绍页URL
     * 
     * @return 小说介绍页URL
     */
    public String getUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(InfoAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno());
    }

    /**
     * 获取作者列表URL
     * 
     * @return 作者列表URL
     */
    public String getAuthorUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ArticleListAction.URL + "?author=" + getAuthor());
    }

    /**
     * 获取分类列表URL
     * 
     * @return 分类列表URL
     */
    public String getCategoryListUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ArticleListAction.URL + "?category=" + getCategory());
    }

    /**
     * 获取最新章节URL
     * 
     * @return 最新章节URL
     */
    public String getLastChapterUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ReaderAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno()
                + "&chapterno=" + getLastchapterno());
    }

    /**
     * 获取加入书签URL
     * 
     * @return 加入书签URL
     */
    public String getAddBookcaseUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(BookcaseAction.URL + "!add?articleno=" + getArticleno());
    }

    /**
     * 获取推荐小说URL
     * 
     * @return 推荐小说URL
     */
    public String getAddVoteUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(VoteAction.URL + "?articleno=" + getArticleno());
    }

    /**
     * 获取下载小说URL
     * 
     * @return 下载小说URL
     */
    public String getDownloadUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(DownloadAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno());
    }

    /**
     * 获取小说评价URL
     * 
     * @return 小说评价URL
     */
    public String getReviewUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ReviewListAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno());
    }

    public final static class CatetoryType {

        /**
         * 1|玄幻魔法
         */
        public final static int XUANHUAN = 1;
        /**
         * 2|武侠修真
         */
        public final static int WUXIA = 2;
        /**
         * 3|都市言情
         */
        public final static int YANXIN = 3;
        /**
         * 4|历史军事
         */
        public final static int JUSHI = 4;
        /**
         * 5|侦探推理
         */
        public final static int ZHENTAN = 5;
        /**
         * 6|网游动漫
         */
        public final static int WANGYOU = 6;
        /**
         * 7|科幻灵异
         */
        public final static int KEHUAN = 7;
        /**
         * 8|恐怖灵异
         */
        public final static int KONGBU = 8;
        /**
         * 9|剧本其他
         */
        public final static int JUBEN = 9;
        /**
         * 10|其他类型
         */
        public final static int OTHER = 10;
    }
}