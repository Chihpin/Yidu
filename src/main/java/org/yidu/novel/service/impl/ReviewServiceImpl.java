package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.yidu.novel.bean.ReviewSearchBean;
import org.yidu.novel.entity.TReview;
import org.yidu.novel.service.ReviewService;
import org.yidu.novel.utils.Pagination;
import org.yidu.novel.utils.Utils;

public class ReviewServiceImpl extends HibernateSupportServiceImpl implements ReviewService {

    @Override
    public TReview getByNo(int reviewno) {
        return this.get(TReview.class, reviewno);
    }

    @Override
    public void deleteByNo(int reviewno) {
        TReview review = getByNo(reviewno);
        this.delete(review);

    }

    @Override
    public void save(TReview review) {
        this.saveOrUpdate(review);

    }

    @Override
    public int getCount(ReviewSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("SELECT count(*) FROM TReview where  deleteflag=false ");
        buildCondtion(searchBean, hql, params);
        return this.getIntResult(hql.toString(), params);
    }

    /**
     * 创建检索条件
     * 
     * @param searchBean
     * @param hql
     * @param params
     */
    private void buildCondtion(ReviewSearchBean searchBean, StringBuffer hql, List<Object> params) {
        if (Utils.isDefined(searchBean.getArticleno())) {
            hql.append(" AND articleno = ? ");
            params.add(searchBean.getArticleno());
        }

        if (Utils.isDefined(searchBean.getArticlename())) {
            hql.append(" AND articlename like ? ");
            params.add("%" + StringEscapeUtils.escapeSql(searchBean.getArticlename()) + "%");
        }

        if (Utils.isDefined(searchBean.getLoginid())) {
            hql.append(" AND loginid like  ?");
            params.add("%" + StringEscapeUtils.escapeSql(searchBean.getLoginid()) + "%");
        }

        if (Utils.isDefined(searchBean.getChaptername())) {
            hql.append(" AND chaptername like  ?  ");
            params.add("%" + StringEscapeUtils.escapeSql(searchBean.getChaptername()) + "%");
        }
    }

    @Override
    public List<TReview> find(ReviewSearchBean searchBean) {
        // 初期SQL做成
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("From TReview WHERE  deleteflag=false  ");
        buildCondtion(searchBean, hql, params);
        Pagination pagination = searchBean.getPagination();
        // 添加排序信息
        if (pagination != null) {
            hql.append(pagination.getSortInfo());
            return this.findByRange(hql.toString(), pagination.getStart(), pagination.getEnd(), params);
        } else {
            hql.append(" ORDER BY reviewno");
            return this.find(hql.toString(), params);
        }
    }

}
