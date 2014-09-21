package org.yidu.novel.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.service.ArticleService;

/**
 * 
 * <p>
 * 小说件数管理器
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class ArticleCountManager {

    /**
     * 输出log
     */
    private static Log logger = LogFactory.getLog(ArticleCountManager.class);
    /**
     * 小说件数MAP
     */
    private static Map<String, Integer> articleCountMap;

    /**
     * 获得小说数量
     * 
     * @param key
     *            键值
     * @return 小说数量
     */
    public static int getArticleCount(String key) {

        if (articleCountMap.get(key) != null) {
            return articleCountMap.get(key);
        }
        return 0;
    }

    /**
     * 初始化小说件数管理器
     */
    public static void initArticleCountManager() {

        logger.info("going to init ArticleCountManager.");

        articleCountMap = new HashMap<String, Integer>();

        new Thread(new Runnable() {

            @Override
            public void run() {

                // TODO 新现成启动初始化，会导致刚启动时小说数不正确的问题，但是只是启动时，马上就恢复了，将来启动先加载
                logger.info("start ArticleCount Manager daemon process.");

                ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
                        new String[] { "spring/springWithoutBatch.xml" });

                ArticleService articleService = (ArticleService) context.getBean("articleService");

                while (true) {
                    try {
                        logger.info("ArticleCount Manager daemon process going to load count info.");
                        logger.debug("going to init category count.");
                        // 初始化分类小说件数
                        PropertiesConfiguration languageConf = new PropertiesConfiguration(Thread.currentThread()
                                .getContextClassLoader().getResource("language/package.properties"));
                        String value = languageConf.getString("collectionProperties.article.category");
                        String[] items = StringUtils.split(value, ",");
                        for (int j = 0; j < items.length; j++) {
                            String[] property = StringUtils.split(items[j], ":");
                            if (property.length == 2) {
                                ArticleSearchBean searchBean = new ArticleSearchBean();
                                searchBean.setCategory(Integer.parseInt(property[0]));
                                Integer count = articleService.getCountByJDBC(searchBean);
                                if (count != null) {
                                    articleCountMap.put(property[0], count);
                                } else {
                                    articleCountMap.put(property[0], 0);
                                }
                            }
                        }

                        logger.debug("going to init top count.");
                        // 初始化排行榜小说件数
                        int allcount = 0;
                        Integer count = articleService.getCountByJDBC(new ArticleSearchBean());
                        if (count != null) {
                            allcount = count;
                        }
                        for (Iterator<String> it = YiDuConstants.TOP_NAME_MAP.keySet().iterator(); it.hasNext();) {
                            articleCountMap.put(it.next(), allcount);
                        }

                        logger.debug("going to init all count.");
                        articleCountMap.put("all", allcount);

                        logger.debug("going to init author count.");
                        // TODO 默认设为10个，估计超过10个的很少！
                        articleCountMap.put("author", 10);

                        logger.debug("going to init fullflg count.");
                        ArticleSearchBean searchBean = new ArticleSearchBean();
                        searchBean.setFullflag(true);
                        int fullcount = articleService.getCountByJDBC(searchBean);
                        articleCountMap.put("fullflag", fullcount);

                        logger.info("ArticleCount Manager daemon process going to sleep.");

                        Thread.sleep(YiDuConstants.yiduConf.getInt(YiDuConfig.RELOAD_ARTICLE_COUNT_INTERVAL, 120) * 60 * 1000);

                    } catch (Exception e) {
                        logger.error("init initArticleCountManager failed.", e);
                        break;
                    }
                }
                context.close();
            }
        }).start();

        logger.info("init ArticleCountManager normally end.");
    }
}
