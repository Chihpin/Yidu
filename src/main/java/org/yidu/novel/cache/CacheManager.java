package org.yidu.novel.cache;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

public class CacheManager {

    private static final String CACHE_REGION_NAME = "default";

    private static JCS cache = null;

    private CacheManager() {
        // Exists only to defeat instantiation.
    }

    public static void initCacheManager() throws CacheException {
        CacheManager.cache = JCS.getInstance(CACHE_REGION_NAME);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getObject(String key) {
        return (T) cache.get(key);
    }

    public static void putObject(String key, Object value) {
        try {
            if (value != null && YiDuConstants.yiduConf.getBoolean(YiDuConfig.CACHE_EFFECTIVE, true)) {
                cache.put(key, value);
            }
        } catch (CacheException e) {
            e.printStackTrace();
        }
    }

    public static void dispose() {
        // Dispose of the cache region
        cache.dispose();
    }
}
