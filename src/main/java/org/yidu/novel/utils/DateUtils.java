package org.yidu.novel.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * <p>
 * 日期工具类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class DateUtils {

    /**
     * 默认格式 yyyy-MM-dd
     */
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd";

    /**
     * 格式化时间
     * 
     * @param time
     *            时间戳
     * @return 时间字符串
     */
    public static String format(Timestamp time) {
        return format(time, DEFAULT_PATTERN);
    }

    /**
     * 格式化时间
     * 
     * @param date
     *            日期
     * @return 时间字符串
     */
    public static String format(Date date) {
        return format(date, DEFAULT_PATTERN);
    }

    /**
     * 格式化时间到指定格式
     * 
     * @param date
     *            日期
     * @param pattern
     *            格式
     * @return 时间字符串
     */
    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

}
