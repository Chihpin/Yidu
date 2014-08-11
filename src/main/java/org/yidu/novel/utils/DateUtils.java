package org.yidu.novel.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	private static final String DEFAULT_PATTERN = "yyyy-MM-dd";
	
	public static String format(Timestamp time){
		return format(time, DEFAULT_PATTERN);
	}
	
	public static String format(Date date){
		return format(date, DEFAULT_PATTERN);
	}

	public static String format(Date date, String pattern){
		return new SimpleDateFormat(pattern).format(date);
	}

}
