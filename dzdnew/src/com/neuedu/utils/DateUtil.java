package com.neuedu.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class DateUtil {
	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr(java.sql.Date dateDate) {
		
		String dateString = dateDate.toString();
		return dateString;
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		
		Date strtodate = Date.valueOf(strDate);
		return strtodate;
	}
}
