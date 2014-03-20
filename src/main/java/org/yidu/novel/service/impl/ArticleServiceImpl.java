package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.service.ArticleService;
import org.yidu.novel.utils.Pagination;

public class ArticleServiceImpl extends HibernateSupportServiceImpl implements ArticleService {

    @Override
    public List<TArticle> find(final ArticleSearchBean searchBean) {

        // 初期SQL做成
        StringBuffer hql = new StringBuffer();
        hql.append("From TArticle WHERE 1=1 ");
        List<Object> params = new ArrayList<Object>();

        buildCondtion(searchBean, hql, params);

        Pagination pagination = searchBean.getPagination();
        // 添加排序信息
        if (pagination != null) {
            hql.append(pagination.getSortInfo());
            return this.findByRange(hql.toString(), pagination.getStart(), pagination.getEnd(), params);
        } else {
            hql.append("ORDER BY articleno");
            return this.find(hql.toString(), params);
        }
    }

    private void buildCondtion(ArticleSearchBean searchBean, StringBuffer hql, List<Object> params) {
        // 小说号条件追加
        if (searchBean.getArticleno() != 0) {
            hql.append(" AND articleno = ? ");
            params.add(searchBean.getArticleno());
        }
        // 小说名条件追加
        if (StringUtils.isNotEmpty(searchBean.getArticlename())) {
            hql.append(" AND articlename = ? ");
            params.add(searchBean.getArticlename());
        }
        // 小说作者条件追加
        if (StringUtils.isNotEmpty(searchBean.getAuthor())) {
            hql.append(" AND author = ? ");
            params.add(searchBean.getAuthor());
        }

        // 小说种别追加
        if (searchBean.getCategory() != null && searchBean.getCategory() != 0) {
            hql.append(" AND category = ? ");
            params.add(searchBean.getCategory());
        }

        // 完本标识追加
        if (searchBean.getFullflag() != null && searchBean.getFullflag()) {
            hql.append(" AND fullflag = TRUE ");
        }

        // 小说编号数组追加
        if (StringUtils.isNotEmpty(searchBean.getArticlenos())) {
            hql.append(" AND articleno in ( " + searchBean.getArticlenos() + " )  ");
        }

        hql.append(" AND lastchapterno is not null ");

        // 条件追加
        if (StringUtils.isNotEmpty(searchBean.getKey())) {
            // 文章名
            hql.append(" AND  (LOWER(articlename) like '%"
                    + StringEscapeUtils.escapeSql(searchBean.getKey().toLowerCase()) + "%' OR LOWER(author) like '%"
                    + StringEscapeUtils.escapeSql(searchBean.getKey().toLowerCase()) + "%' )");
        }

        // 小说编号数组追加
        hql.append(" AND lastupdate is not null ");
    }

    @Override
    public TArticle getByNo(final int articleno) {
        return this.get(TArticle.class, articleno);
    }

    @Override
    public void delteByNo(final int articleno) {
        TArticle article = getByNo(articleno);
        this.delete(article);

    }

    @Override
    public void save(TArticle article) {
        this.saveOrUpdate(article);
    }

    @Override
    public Integer getCount(ArticleSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("SELECT count(*) FROM TArticle where 1=1");

        buildCondtion(searchBean, hql, params);
        return this.getIntResult(hql.toString(), params);
    }

    @Override
    public void updateVisitStatistic(int articleno) {
        String sql = "update TArticle set   dayvisit  = dayvisit +1 , weekvisit= weekvisit +1 ,"
                + "monthvisit =monthvisit+1 , allvisit = allvisit +1 where articleno =  ? ";
        this.sqlQuery(sql, articleno);
    }

    @Override
    public void updateVoteStatistic(int articleno) {
        String sql = "update TArticle set  dayvote  = dayvote +1 , weekvote= weekvote +1 ,"
                + "monthvote =monthvote+1 , allvote = allvote +1 where articleno =  ? ";
        this.sqlQuery(sql, articleno);
    }

    @Override
    public void cleanStatistics() {
        System.out.println("cleanStatistics start");
        String sql = "update t_article set dayvote = 0 ,dayvisit = 0";
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        // 每周一清空周统计信息
        if (dayOfWeek == Calendar.MONDAY) {
            sql += ",weekvote = 0,weekvisit=0";
        }
        // 每月1号清空月统计信息
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        if (dayOfMonth == 1) {
            sql += ",monthvote = 0,monthvisit=0";
        }
        System.out.println(sql);
        this.yiduJdbcTemplate.update(sql);
        System.out.println("cleanStatistics end");

    }
}
