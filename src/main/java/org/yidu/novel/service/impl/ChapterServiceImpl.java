package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.service.ChapterService;

public class ChapterServiceImpl extends HibernateSupportServiceImpl implements ChapterService {

    @Override
    public List<TChapter> find(ChapterSearchBean searchBean) {

        StringBuffer hql = new StringBuffer();
        hql.append("FROM TChapter WHERE  deleteflag=false  ");
        List<Object> params = new ArrayList<Object>();
        // 追加小说ID条件
        if (searchBean.getArticleno() != 0) {
            hql.append(" AND articleno = ? ");
            params.add(searchBean.getArticleno());
        }
        if (StringUtils.isNotEmpty(searchBean.getChapternos())) {
            hql.append(" AND chapterno in ( " + searchBean.getChapternos() + ") ");
        }
        hql.append("ORDER BY chapterno ASC ");
        List<TChapter> chapterList = this.find(hql.toString(), params);
        return chapterList;
    }

    @Override
    public TChapter getByNo(int chapterno) {
        return this.get(TChapter.class, chapterno);
    }

    @Override
    public TChapter getNextChapter(int articleno, int chapterno, boolean isNext) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("FROM TChapter WHERE articleno = ? ");
        // 追加小说号条件
        params.add(articleno);
        if (isNext) {
            hql.append(" AND chapterno > ?  AND chaptertype = 0 ORDER BY  chapterno ASC ");
        } else {
            hql.append(" AND chapterno < ?  AND chaptertype = 0 ORDER BY  chapterno DESC ");
        }
        // 追加章节号条件
        params.add(chapterno);
        // 只取一条记录
        List<TChapter> chapterList = this.findByRange(hql.toString(), 0, 1, params);
        if (chapterList != null && chapterList.size() > 0) {
            return chapterList.get(0);
        }
        return null;
    }

    @Override
    public void save(TChapter chapter) {
        this.saveOrUpdate(chapter);
    }

    @Override
    public void delteByNo(int chapterno) {
        TChapter chapter = getByNo(chapterno);
        this.delete(chapter);
    }

    @Override
    public Integer getCount(ChapterSearchBean searchBean) {
        StringBuffer sql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        sql.append("SELECT count(*) FROM TChapter where  deleteflag=false ");

        // 小说号条件追加
        if (searchBean.getArticleno() != 0) {
            sql.append(" AND articleno = ? ");
            params.add(searchBean.getArticleno());
        }

        return this.getIntResult(sql.toString(), params);
    }
}
