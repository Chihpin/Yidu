package org.yidu.novel.service;

/**
 * 
 * <p>
 * 提供升级操作的服务
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public interface UpgradeService {
    /**
     * 执行升级sql
     * 
     * @param sql
     */
    void excuteUpgradeSql(final String sql);
}
