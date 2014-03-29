package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.yidu.novel.bean.BookcaseSearchBean;
import org.yidu.novel.dto.BookcaseDTO;
import org.yidu.novel.entity.TBookcase;
import org.yidu.novel.service.BookcaseService;

public class BookcaseServiceImpl extends HibernateSupportServiceImpl implements BookcaseService {

    @Override
    public List<TBookcase> find(BookcaseSearchBean searchBean) {
        // 初期SQL做成
        StringBuffer hql = new StringBuffer();
        hql.append("From TBookcase WHERE  deleteflag=false  ");
        List<Object> params = new ArrayList<Object>();
        buildCondtion(searchBean, hql, params);
        hql.append("ORDER BY createtime DESC");
        return this.find(hql.toString(), params);

    }

    private void buildCondtion(BookcaseSearchBean searchBean, StringBuffer hql, List<Object> params) {
        if (searchBean.getUserno() != null && searchBean.getUserno() != 0) {
            hql.append("  AND userno=?");
            params.add(searchBean.getUserno());
        }

        if (searchBean.getArticleno() != null && searchBean.getArticleno() != 0) {
            hql.append("  AND articleno=?");
            params.add(searchBean.getArticleno());
        }
    }

    @Override
    public Integer getCount(BookcaseSearchBean searchBean) {
        // 初期SQL做成
        StringBuffer hql = new StringBuffer();
        hql.append("SELECT count(*)  From TBookcase WHERE  deleteflag=false  ");
        List<Object> params = new ArrayList<Object>();
        buildCondtion(searchBean, hql, params);
        return this.getIntResult(hql.toString(), params);
    }

    @Override
    public TBookcase getByNo(int bookcaseno) {
        return this.get(TBookcase.class, bookcaseno);
    }

    @Override
    public void delteByNo(int bookcaseno) {
        TBookcase bookcase = getByNo(bookcaseno);
        this.delete(bookcase);
    }

    @Override
    public void save(TBookcase bookcase) {
        this.saveOrUpdate(bookcase);
    }

    @Override
    public List<BookcaseDTO> findBySql(BookcaseSearchBean searchBean) {
        // 初期SQL做成
        StringBuffer sql = new StringBuffer();
        sql.append("Select tb.*,ta.lastchapterno,ta.lastchapter,ta.chapters,ta.size,ta.fullflag,ta.lastupdate   "
                + "      FROM t_bookcase tb                                                                     "
                + "      LEFT JOIN t_article ta ON tb.articleno = ta.articleno                                  "
                + "WHERE tb.userno= ");
        sql.append(searchBean.getUserno());
        sql.append("ORDER BY ta.lastupdate DESC");
        return yiduJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<BookcaseDTO>(BookcaseDTO.class));
    }

    @Override
    public TBookcase getByArticleno(int userno, int articleno) {
        BookcaseSearchBean searchBean = new BookcaseSearchBean();
        searchBean.setArticleno(articleno);
        searchBean.setUserno(userno);
        List<TBookcase> list = this.find(searchBean);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
