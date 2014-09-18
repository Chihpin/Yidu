package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.yidu.novel.bean.SubscribeSearchBean;
import org.yidu.novel.dto.SubscribeDTO;
import org.yidu.novel.entity.TSubscribe;
import org.yidu.novel.service.SubscribeService;
import org.yidu.novel.utils.Utils;

/**
 * <p>
 * 提供订阅信息操作的服务实装类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class SubscribeServiceImpl extends HibernateSupportServiceImpl implements SubscribeService {

    @Override
    public TSubscribe getByNo(int subscribeno) {
        return this.get(TSubscribe.class, subscribeno);
    }

    @Override
    public void deleteByNo(int subscribeno) {
        TSubscribe subscribe = getByNo(subscribeno);
        this.delete(subscribe);
    }

    @Override
    public void save(TSubscribe subscribe) {
        this.saveOrUpdate(subscribe);
    }

    @Override
    public List<TSubscribe> find(SubscribeSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("SELECT count(*) FROM TSubscribe where 1=1 ");
        buildCondtion(searchBean, hql, params);
        return this.find(hql.toString(), params);
    }

    private void buildCondtion(SubscribeSearchBean searchBean, StringBuffer sql, List<Object> params) {
        // 小说号条件追加
        if (Utils.isDefined(searchBean.getArticleno())) {
            sql.append(" AND articleno = ? ");
            params.add(searchBean.getArticleno());
        }
        // 小说名条件追加
        if (Utils.isDefined(searchBean.getUserno())) {
            sql.append(" AND userno = ? ");
            params.add(searchBean.getUserno());
        }
    }

    @Override
    public int getCount(SubscribeSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("SELECT count(*) FROM TSubscribe where 1=1 ");
        buildCondtion(searchBean, hql, params);
        return this.getIntResult(hql.toString(), params);
    }

    @Override
    public List<SubscribeDTO> findByUserno(int userno) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ts.subscribeno, ta.* FROM t_subscribe ts join t_article ta on ts.articleno = ta.articleno"
                + " where userno =  " + userno);
        List<Object> params = new ArrayList<Object>();
        List<SubscribeDTO> subscribeList = this.yiduJdbcTemplate.query(sql.toString(), params.toArray(),
                new BeanPropertyRowMapper<SubscribeDTO>(SubscribeDTO.class));
        return subscribeList;
    }

}
