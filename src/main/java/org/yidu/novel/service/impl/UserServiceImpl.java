package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.service.UserService;

public class UserServiceImpl extends HibernateSupportServiceImpl implements UserService {

    @Override
    public TUser findByLoginInfo(final String loginid, final String password) {
        String sql = "FROM TUser where loginid = ? AND password = ? AND deleteflag=false";
        List<Object> params = new ArrayList<Object>();
        params.add(loginid);
        params.add(password);
        List<TUser> userinfoList = this.find(sql, params);
        if (userinfoList.size() > 0) {
            return userinfoList.get(0);
        }
        return null;
    }

    @Override
    public TUser findByLoginInfoByJDBC(final String loginid, final String password) {
        String sql = "select * from t_user where loginid = ? AND password = ? AND deleteflag=false";
        List<Object> params = new ArrayList<Object>();
        params.add(loginid);
        params.add(password);
        List<TUser> userinfoList = this.yiduJdbcTemplate.query(sql, params.toArray(), new BeanPropertyRowMapper<TUser>(
                TUser.class));
        if (userinfoList.size() > 0) {
            return userinfoList.get(0);
        }
        return null;
    }

    @Override
    public List<TUser> find(final UserSearchBean searchBean) {
        StringBuilder hql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        hql.append("FROM TUser WHERE 1=1 ");
        buildCondition(searchBean, hql, params);
        hql.append(" order by userno ");
        List<TUser> userinfoList = this.find(hql.toString(), params);
        return userinfoList;
    }

    @Override
    public void delteByNo(int userno) {
        TUser user = this.get(TUser.class, userno);
        this.delete(user);
    }

    @Override
    public void save(TUser user) {
        this.saveOrUpdate(user);
    }

    @Override
    public TUser getByNo(int userno) {
        return this.get(TUser.class, userno);
    }

    @Override
    public int getCount(UserSearchBean searchBean) {
        StringBuilder hql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        hql.append("SELECT count(*) FROM TUser WHERE 1=1 ");
        buildCondition(searchBean, hql, params);
        return this.getIntResult(hql.toString(), params);
    }

    private void buildCondition(UserSearchBean searchBean, StringBuilder hql, List<Object> params) {
        if (searchBean.getUserno() != 0) {
            hql.append(" AND userno = ? ");
            params.add(searchBean.getUserno());
        }

        if (StringUtils.isNotBlank(searchBean.getLoginid())) {
            hql.append(" AND loginid = ? ");
            params.add(searchBean.getLoginid());
        }

        if (StringUtils.isNotBlank(searchBean.getUsername())) {
            hql.append(" AND username = ? ");
            params.add(searchBean.getUsername());
        }

        if (searchBean.getDeleteflag() != null) {
            hql.append(" AND deleteflag = ? ");
            params.add(searchBean.getDeleteflag());
        }

    }

}
