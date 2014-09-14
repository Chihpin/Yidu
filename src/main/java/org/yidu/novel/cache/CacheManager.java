package org.yidu.novel.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

import com.google.gson.Gson;

/**
 * 
 * <p>
 * 缓存管理器
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class CacheManager {

    /**
     * 输出log
     */
    private static Log logger = LogFactory.getLog(CacheManager.class);
    /**
     * 默认领域名
     */
    private static final String CACHE_REGION_NAME = "default";
    /**
     * 缓存对象
     */
    private static JCS cache = null;

    /**
     * GSON对象
     */
    private static Gson gson = new Gson();

    /**
     * 缓存管理器
     */
    private CacheManager() {
        // Exists only to defeat instantiation.
    }

    /**
     * 初始化缓存管理器
     * 
     * @throws CacheException
     *             缓存异常
     */
    public static void initCacheManager() throws CacheException {
        CacheManager.cache = JCS.getInstance(CACHE_REGION_NAME);
    }

    /**
     * 从缓存中获取缓存对象
     * 
     * @param prefix
     *            键前缀
     * @param key
     *            键值
     * @return 缓存的数据
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObject(String prefix, Object key) {
        return (T) cache.get(getCacheKey(prefix, key));
    }

    /**
     * 获得缓存的键 <br>
     * 默认形式 prefix-value的json字符串
     * 
     * @param prefix
     *            键前缀
     * @param key
     *            键值
     * @return 缓存键
     */
    private static String getCacheKey(String prefix, Object key) {
        String cacheKey = prefix;
        if (key != null) {
            cacheKey += "-" + gson.toJson(key);
        }
        logger.debug("cache key is : " + cacheKey);
        return cacheKey;

    }

    /**
     * 将数据添加到缓存管理器中
     * 
     * @param prefix
     *            键前缀
     * @param key
     *            键值
     * @param value
     *            数据
     */
    public static void putObject(String prefix, Object key, Object value) {
        try {
            if (value != null && YiDuConstants.yiduConf.getBoolean(YiDuConfig.CACHE_EFFECTIVE, true)) {
                cache.put(getCacheKey(prefix, key), value);
            }
        } catch (CacheException e) {
            e.printStackTrace();
        }
    }

    /**
     * 销毁缓存
     */
    public static void dispose() {
        // Dispose of the cache region
        cache.dispose();
    }

    /**
     * 
     * <p>
     * 缓存的键前缀定义
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.0.0
     * @author shinpa.you
     */
    public class CacheKeyPrefix {
        /**
         * 主页区块的键
         */
        public static final String CACHE_KEY_INDEX_BLOCK = "CacheKey_indexBlock";
        /**
         * 小说件数的键
         */
        public static final String CACHE_KEY_ARTICEL_LIST_COUNT_PREFIX = "CacheKey_ARTICLE_LIST_COUNT";
        /**
         * 小说列表的键
         */
        public static final String CACHE_KEY_ARTICEL_LIST_PREFIX = "CacheKey_ARTICLE_LIST";
        /**
         * 小说的键
         */
        public static final String CACHE_KEY_ARTICEL_PREFIX = "CacheKey_ARTICLE";
        /**
         * 章节列表的键
         */
        public static final String CACHE_KEY_CHAPTER_LIST_PREFIX = "CacheKey_CHAPTER_LIST";
        /**
         * 章节的键
         */
        public static final String CACHE_KEY_CHAPTER_PREFIX = "CacheKey_CHAPTER";
        /**
         * 历史的键
         */
        public static final String CACHE_KEY_HISTORY_PREFIX = "CacheKey_HISTORY";
        /**
         * 排行榜的小说列表的键
         */
        public static final String CACHE_KEY_ARTICEL_TOP_LIST_PREFIX = "CacheKey_ARTICLE_TOP_LIST";
        /**
         * 排行榜的小说件数的键
         */
        public static final String CACHE_KEY_ARTICEL_TOP_LIST_COUNT_PREFIX = "CacheKey_ARTICLE_TOP_COUNT";
    }

}
