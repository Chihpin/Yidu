package org.yidu.novel.service.impl;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 
 * <p>
 * 服务基类
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class BaseServiceImpl {

    protected Log logger = LogFactory.getLog(this.getClass());
    /**
     * 定义JdbcTemplate
     */
    protected JdbcTemplate yiduJdbcTemplate;

    @Autowired
    public void setYiduDataSource(final DataSource yiduDataSource) {
        this.yiduJdbcTemplate = new JdbcTemplate(yiduDataSource);
    }
}
