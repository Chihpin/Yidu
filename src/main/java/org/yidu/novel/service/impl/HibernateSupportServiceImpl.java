package org.yidu.novel.service.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.entity.Joinable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * <p>
 * Hibernate操作用基类
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@SuppressWarnings("unchecked")
public class HibernateSupportServiceImpl extends BaseServiceImpl {

    protected SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected final <T> T get(final Class<T> entityClass, final Serializable id) {
        return (T) sessionFactory.getCurrentSession().get(entityClass, id);
    }

    protected final <T> T load(final Class<T> entityClass, final Serializable id) {
        return (T) sessionFactory.getCurrentSession().load(entityClass, id);
    }

    protected final void delete(final Object entity) {
        this.sessionFactory.getCurrentSession().delete(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    protected final void save(final Object entity) {
        this.sessionFactory.getCurrentSession().save(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    protected final void update(final Object entity) {
        this.sessionFactory.getCurrentSession().update(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    protected final void saveOrUpdate(final Object entity) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    protected final Query getQuery(final String hql, final List<?> params) {
        return this.getQuery(hql, params.toArray());
    }

    protected final Query getQuery(final String hql, final Object... params) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if (params != null && params.length != 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        return query;
    }

    protected final Integer getIntResult(final String hql, final List<?> params) {
        return getIntResult(hql, params.toArray());
    }

    protected final Integer getIntResult(final String hql, final Object... params) {
        Query query = this.getQuery(hql, params);
        return new Integer(((Number) query.uniqueResult()).intValue());
    }

    protected final <T> List<T> find(final String hql, final List<?> params) {
        return this.find(hql, params.toArray());
    }

    protected final <T> List<T> find(final String hql, final Object... params) {
        Query query = this.getQuery(hql, params);
        return query.list();
    }

    protected final <T> List<T> findByRange(final String hql, final int firstResult, final int maxResults,
            final List<?> params) {
        return this.findByRange(hql, firstResult, maxResults, params.toArray());
    }

    protected final <T> List<T> findByRange(final String hql, final int firstResult, final int maxResults,
            final Object... params) {
        Query query = this.getQuery(hql, params);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        return query.list();
    }

    protected final void execute(final String hql, final List<?> params) {
        this.execute(hql, params.toArray());
    }

    protected final void execute(final String hql, final Object... params) {
        Query query = this.getQuery(hql, params);
        query.executeUpdate();
        this.sessionFactory.getCurrentSession().flush();
    }

    protected final void sqlQuery(final String sql, final List<?> params) {
        this.sqlQuery(sql, params.toArray());
    }

    protected final void sqlQuery(final String sql, final Object... params) {
        Query query = getQuery(sql, params);
        query.executeUpdate();
        this.sessionFactory.getCurrentSession().clear();
    }

    protected final List<?> getList(final String sql, final List<?> params) {
        return this.getList(sql, params.toArray());
    }

    protected final List<?> getList(final String sql, final Object... params) {
        Query query = getQuery(sql, params);
        List<?> list = query.list();
        return list;
    }

    protected final List<BigInteger> getIntList(final String sql, final Object... params) {
        Query query = this.getQuery(sql, params);
        List<BigInteger> list = query.list();
        return list;
    }

    protected final void delete(final String sql, final int... params) {
        Query query = getQuery(sql, params);
        query.executeUpdate();
    }

    protected final List<String> getColumnNames(final Class<?> entityClass) {
        List<String> result = new ArrayList<String>();
        AbstractEntityPersister aep = ((AbstractEntityPersister) this.sessionFactory.getCurrentSession()
                .getSessionFactory().getClassMetadata(entityClass));
        result.addAll(Arrays.asList(aep.getIdentifierColumnNames()));
        for (String propertyName : aep.getPropertyNames()) {
            if (!aep.getPropertyType(propertyName).isCollectionType()) {
                for (String column : aep.getPropertyColumnNames(propertyName)) {
                    result.add(column);
                }
            }
        }
        return result;
    }

    protected final String getColumnStr(final Class<?> entityClass, String str) {
        return StringUtils.join(getColumnNames(entityClass), str);
    }

    protected final void rollback() {
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    protected final String getTableNameByEntity(final Class<?> entityClass) {
        // sessionFactory.getCollectionMetadata("").
        ClassMetadata meta = sessionFactory.getClassMetadata(entityClass);
        return Joinable.class.cast(meta).getTableName();
    }

    protected final String getTableNameByEntity(final String entityName) {
        ClassMetadata meta = sessionFactory.getClassMetadata(entityName);
        return Joinable.class.cast(meta).getTableName();
    }

    protected final void clearSession() {
        this.sessionFactory.getCurrentSession().clear();
    }
}
