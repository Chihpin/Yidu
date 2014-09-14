package org.yidu.novel.constant;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 
 * <p>
 * 易读静态定义
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class YiDuConstants {

    public static PropertiesConfiguration yiduConf;

    public static PropertiesConfiguration pseudoConf;

    public static ThreadLocal<String> requestUri = new ThreadLocal<String>();

    public static final Map<String, String> topNameMap = new LinkedHashMap<String, String>() {
        private static final long serialVersionUID = -2355068040470822368L;
        {
            put("lastupdate", "最近更新");
            put("allvisit", "总排行榜");
            put("allvote", "总推荐榜");
            put("monthvisit", "月排行榜");
            put("monthvote", "月推荐榜");
            put("weekvisit", "周排行榜");
            put("weekvote", "周推荐榜");
            put("dayvisit", "日排行榜");
            put("dayvote", "日推荐榜");
            put("postdate", "最新入库");
            put("size", "字数排行");
        }
    };

    /**
     * UTF-8字符串
     */
    public static final String ENCODING_UTF_8 = "UTF-8";

    public static final String ENCODING_GBK = "GBK";

    public static final int SUB_DIR_ARTICLES = 1000;

    public static final class UserGroup {

        /**
         * 游客
         */
        public static final int GUEST = 1;
        /**
         * 系统管理员
         */
        public static final int ADMIN = 2;
        /**
         * 普通会员
         */
        public static final int NORMARL = 3;
        /**
         * 高级会员
         */
        public static final int AD = 4;
        /**
         * VIP会员
         */
        public static final int VIP = 5;
        /**
         * 专栏作家
         */
        public static final int AUTHOR = 6;
        /**
         * 驻站作家
         */
        public static final int AUTHOR2 = 7;
        /**
         * 初级版主
         */
        public static final int KONGBU = 8;
        /**
         * 中级版主
         */
        public static final int JUBEN = 9;
        /**
         * 高级版主
         */
        public static final int OTHER = 10;
    }

    /**
     * 
     * <p>
     * ResponseStatus
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.0.0
     * @author shinpa.you
     */
    public static final class ResponseStatus {

        /**
         * 成功
         */
        public static final int SUCCESS = 200;
        /**
         * 失败
         */
        public static final int FAILED = 400;

    }

    /**
     * 
     * <p>
     * 区块标识
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.0.0
     * @author shinpa.you
     */
    public static final class BlockTarget {
        public final static short ARTICLE_LIST = 1;
        public final static short ARTICLE_DETAIL = 2;
        public final static short CHAPTER_LIST = 3;
        public final static short READER_PAGE = 4;
        public final static short USER_DETAIL = 5;
        public final static short INDEX = 6;
    }

    public static final class ImageType {
        public final static short JPG = 1;
        public final static short GIF = 2;
        public final static short PNG = 3;
    }

    /**
     * 
     * <p>
     * 区块类型
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.0.0
     * @author shinpa.you
     */
    public static final class BlockType {
        public final static short ARTICLE_LIST = 10;
        public final static short CUSTONIZE_ARTICLE_LIST = 20;
        public final static short HTML = 30;
    }

    /**
     * 
     * <p>
     * 页面类型
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.0.0
     * @author shinpa.you
     */
    public static final class Pagetype {

        /**
         * 1:主页
         * */
        public static final int PAGE_INDEX = 1;
        /**
         * 2：小说列表
         */
        public static final int PAGE_ARTICLE_LIST = 2;
        /**
         * 3：小说介绍页
         */
        public static final int PAGE_ARTICLE_INFO = 3;
        /**
         * 4：小说阅读页
         */
        public static final int PAGE_READER = 4;
        /**
         * 5：搜索页
         */
        public static final int PAGE_SEARCH = 5;

        /**
         * 手机分类
         */
        public static final int PAGE_CATEGORY = 6;
        /**
         * 手机排行
         */
        public static final int PAGE_TOP = 7;

        /**
         * 8：评论页
         */
        public static final int PAGE_REVIEW = 8;

        /**
         * 9：用户信息页
         */
        public static final int PAGE_USER_INFO = 9;

        /**
         * 11：登录页
         */
        public static final int PAGE_LOGIN = 11;
        /**
         * 11：注册页
         */
        public static final int PAGE_REGEDIT = 12;
        /**
         * 书架
         */
        public static final int PAGE_USER_BOOKCASE = 21;
        /**
         * 消息管理
         */
        public static final int PAGE_USER_MESSAGE = 22;
        /**
         * 资料编辑
         */
        public static final int PAGE_USER_USEREDIT = 23;
        /**
         * 申请作者
         */
        public static final int PAGE_REGI_AUTHOR = 24;
        /**
         * 用户中心
         */
        public static final int PAGE_USER_CENTER = 25;
        /**
         * 手机书架
         */
        public static final int PAGE_BOOKCASE = 26;
        /**
         * 小说列表
         */
        public static final int PAGE_AUTHER_ARTICLE_LIST = 30;
        /**
         * 小说编辑
         */
        public static final int PAGE_AUTHER_ARTICLE_EDIT = 31;
        /**
         * 章节列表
         */
        public static final int PAGE_AUTHER_CHAPTER_LIST = 32;
        /**
         * 章节编辑
         */
        public static final int PAGE_AUTHER_CHAPTER_EDIT = 33;
        /**
         * 章节编辑
         */
        public static final int PAGE_AUTHER_BILL_DETAIL = 40;
        /**
         * * 99：其他页
         */
        public static final int PAGE_OTHERS = 99;

    }

    /**
     * 
     * <p>
     * 用户类型定义
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.0.0
     * @author shinpa.you
     */
    public static final class UserType {

        /**
         * 普通用户
         */
        public static final short NORMAL_USER = 10;
        /**
         * 作家
         */
        public static final int AUTHER = 20;
        /**
         * VIP
         */
        public static final int VIP = 25;
        /**
         * 管理员
         */
        public static final int ADMINISTRATOR = 30;
        /**
         * 编辑
         */
        public static final int EDITOR = 40;

    }

    /**
     * 前缀
     */
    private static final String PREFIX = "yidu.Novel.";

    /**
     * 用户名
     */
    public static final String LOGINUSER = PREFIX + "LoginUser";
    /**
     * 当前页的LOCALE
     */
    public static final String CURRENTPAGELOCALE = "CURRENT_PAGE_LOCALE";

    /**
     * 正则表达式
     */
    public static final class Regex {
        /**
         * 日期（ 「YYYY/MM/DD」）
         */
        public static final String DATE = "^(?:((?!0000)[0-9]{4}/(?:(?:0[1-9]|1[0-2])/(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])/(?:29|30)|(?:0[13578]|1[02])/31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)/02/29)?)$";
        /**
         * 半角英数字
         */
        public static final String ALPHANUMERIC = "^[A-Za-z0-9]*$";
        /**
         * 半角数字
         */
        public static final String NUMBER = "^\\d*$";
        /**
         * 半角数字
         */
        public static final String EMAIL = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
    }

    /**
     * 
     * <p>
     * 图片Mate类型定义
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.0.0
     * @author shinpa.you
     */
    public final static class ImgageMateType {
        public static final String JPG = "image/jpeg";
        public static final String PNG = "image/png";
        public static final String GIF = "image/gif";

    }

    public static final String[] allowPicTypes = new String[] { ImgageMateType.JPG, ImgageMateType.PNG,
            ImgageMateType.GIF };

    public static final String[] allowSampleTypes = new String[] { "text/plain", "application/kswps" };

    public enum SiteMapType {
        HTML("html"), XML("xml");

        private String name;

        private SiteMapType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}