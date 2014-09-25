package org.yidu.novel.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yidu.novel.cache.ArticleCountManager;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.cache.SingleBookManager;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

/**
 * 
 * <p>
 * 程序初始化和结束的代码
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class InitializerListener implements ServletContextListener {
    /**
     * 串行化版本统一标识符
     */
    protected Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            // 设定文件初期读入
            PropertiesConfiguration yiduConf = new PropertiesConfiguration("yidu.properties");
            // 设定文件自动更新
            FileChangedReloadingStrategy reloadStrategy = new FileChangedReloadingStrategy();
            yiduConf.setReloadingStrategy(reloadStrategy);
            YiDuConstants.yiduConf = yiduConf;

            // 加载伪原创设置
            YiDuConstants.pseudoConf = new PropertiesConfiguration("pseudo.properties");
            YiDuConstants.pseudoConf.setReloadingStrategy(reloadStrategy);

            // 初始化缓存
            CacheManager.initCacheManager();

            if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CACHE_ARTICLE_COUNT, false)) {
                // 初始化小说件数MAP
                ArticleCountManager.initArticleCountManager();
            }

            if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_SINGLE_BOOK, false)) {
                // 初始化小说拼音和编号映射件数MAP
                SingleBookManager.initSingleBookManager();
            }

            logger.info("Initialize successfully.");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            System.exit(0);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        CacheManager.dispose();
    }
}
