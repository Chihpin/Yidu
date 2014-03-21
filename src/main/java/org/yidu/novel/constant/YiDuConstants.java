package org.yidu.novel.constant;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.configuration.PropertiesConfiguration;

public class YiDuConstants {

    public static PropertiesConfiguration yiduConf;

    public static LinkedMap categoryMap;

    /**
     * UTF-8字符串
     */
    public static final String ENCODING_UTF_8 = "UTF-8";

    public static final String ENCODING_GBK = "GBK";

    public final static class UserGroup {

        /**
         * 游客
         */
        public final static int GUEST = 1;
        /**
         * 系统管理员
         */
        public final static int ADMIN = 2;
        /**
         * 普通会员
         */
        public final static int NORMARL = 3;
        /**
         * 高级会员
         */
        public final static int AD = 4;
        /**
         * VIP会员
         */
        public final static int VIP = 5;
        /**
         * 专栏作家
         */
        public final static int AUTHOR = 6;
        /**
         * 驻站作家
         */
        public final static int AUTHOR2 = 7;
        /**
         * 初级版主
         */
        public final static int KONGBU = 8;
        /**
         * 中级版主
         */
        public final static int JUBEN = 9;
        /**
         * 高级版主
         */
        public final static int OTHER = 10;
    }

    public final static class ResponseStatus {

        /**
         * 成功
         */
        public final static int SUCCESS = 200;
        /**
         * 失败
         */
        public final static int FAILED = 400;

    }

    public final static class BlockTarget {
        public final static short ARTICLE_LIST = 1;
        public final static short ARTICLE_DETAIL = 2;
        public final static short CHAPTER_LIST = 3;
        public final static short READER_PAGE = 4;
        public final static short USER_DETAIL = 5;
        public final static short INDEX = 6;
    }
    
    public final static class ImageType {
        public final static int JPG = 1;
        public final static int GIF = 2;
        public final static int PNG = 3;
    }

    public final static class BlockType {
        public final static short ARTICLE_LIST = 10;
        public final static short CUSTONIZE_ARTICLE_LIST = 20;
        public final static short HTML = 30;
    }

    public final static class Pagetype {

        // * 1:主页
        public final static int PAGE_INDEX = 1;
        // * 2：小说列表
        public final static int PAGE_ARTICLE_LIST = 2;
        // * 3：小说介绍页
        public final static int PAGE_ARTICLE_INFO = 3;
        // * 4：小说阅读页
        public final static int PAGE_READER = 4;
        // * 5：搜索页
        public final static int PAGE_SEARCH = 5;
        // * 11：登录页
        public final static int PAGE_LOGIN = 11;

        public final static int PAGE_USER_BOOKCASE = 21;
        public final static int PAGE_USER_MESSAGE = 22;
        public final static int PAGE_USER_USEREDIT = 23;
        // * 99：其他页
        public final static int PAGE_OTHERS = 99;

    }

    public final static class UserType {

        // * 普通用户
        public final static short NORMAL_USER = 10;
        // *作家
        public final static int AUTHER = 20;
        // * 管理员
        public final static int ADMINISTRATOR = 30;

    }

    /**
     * 前缀
     */
    private static final String Prefix = "yidu.Novel.";

    /**
     * 用户名
     */
    public static final String LoginUser = Prefix + "LoginUser";

    public static final String currentPageLocale = "CURRENT_PAGE_LOCALE";

    /**
     * 正则表达式
     */
    public final static class Regex {
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

    public static final String[] allowTypes = new String[] { "image/jpg", "image/png", "image/gif" };

}