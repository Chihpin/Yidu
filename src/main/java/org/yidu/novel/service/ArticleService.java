package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.entity.TArticle;

public interface ArticleService {
    /**
     * 取得小说列表
     * 
     * @param searchBean
     * @return 小说列表
     */

    public List<TArticle> find(final ArticleSearchBean searchBean);

    public Integer getCount(final ArticleSearchBean searchBean);

    /**
     * 通过拼音查找
     * 
     * @param pinyin
     *            拼音
     * @return 小说
     */
    public TArticle findByPinyin(final String pinyin);

    /**
     * 
     * 小说信息取得
     * 
     * @param articleno
     *            小说号
     * @return 文章详细
     */
    public TArticle getByNo(final int articleno);

    public void delteByNo(final int articleno);

    public void save(final TArticle article);

    public void updateVisitStatistic(final int articleno);

    public void updateVoteStatistic(final int articleno);

    public void cleanStatistics();

}
