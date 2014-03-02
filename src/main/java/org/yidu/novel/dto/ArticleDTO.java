package org.yidu.novel.dto;

import org.yidu.novel.entity.TArticle;

public class ArticleDTO extends TArticle {

    private static final long serialVersionUID = 1386921210616354787L;

    public String getIntroOmit() {
        if (getIntro() != null && getIntro().length() > 60) {
            return getIntro().substring(0, 60) + "...";
        }
        return getIntro();
    }

    public String getLastchapterOmit() {
        if (getLastchapter() != null && getLastchapter().length() > 10) {
            return getLastchapter().substring(0, 10);
        }
        return getLastchapter();
    }

    public final static class ArticleType {

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
