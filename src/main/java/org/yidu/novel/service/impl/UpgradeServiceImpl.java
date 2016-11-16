package org.yidu.novel.service.impl;

import org.yidu.novel.service.UpgradeService;

/**
 * 
 * <p>
 * 提供小说信息操作的服务实装类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class UpgradeServiceImpl extends HibernateSupportServiceImpl implements UpgradeService {

    @Override
    public void excuteUpgradeSql(final String sql) {
        this.yiduJdbcTemplate.execute(sql);
    }
}
