package com.neuedu.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class DateUtil {
	/**
	 * ����ʱ���ʽʱ��ת��Ϊ�ַ��� yyyy-MM-dd
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
	 * ����ʱ���ʽ�ַ���ת��Ϊʱ�� yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		
		Date strtodate = Date.valueOf(strDate);
		return strtodate;
	}
}
