package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.entity.TChapter;

public interface ChapterService {
    /**
     * 取得章节列表
     * 
     * @param searchBean
     * @return 章节列表
     */
    public List<TChapter> find(final ChapterSearchBean searchBean);

    public Integer getCount(final ChapterSearchBean searchBean);

    public TChapter getLastChapter(final int articleno);

    public Integer getArticleSize(final int articleno);

    public TChapter getNextChapter(final int articleno, final int chapterno, final boolean isNext);

    public TChapter getByNo(final int chapterno);

    public void save(final TChapter chapter);

    public void delteByNo(final int chapterno);

}
