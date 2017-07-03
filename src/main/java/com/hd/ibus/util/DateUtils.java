package com.hd.ibus.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * 日期格式化 工具类
 */
public class DateUtils {

	/**
	 * 输入date 和 pattern 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getDate(Date date, String pattern) {
		if (date == null)
			return "";
		SimpleDateFormat s = new SimpleDateFormat(pattern);
		return s.format(date);
	}

	/**
	 * 把util日期 转换为 sql日期
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date getSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 指定 模式 返回date
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String date, String pattern) throws ParseException {
		SimpleDateFormat s = new SimpleDateFormat(pattern);
		return s.parse(date);
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static final long SECOND = 1000;
	public static final long MINUTE = SECOND * 60;
	public static final long HOUR = MINUTE * 60;
	public static final long DAY = HOUR * 24;
	public static final long WEEK = DAY * 7;
	public static final long MONTH = DAY * 30;
	public static final long YEAR = DAY * 365;

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static SimpleDateFormat sdf = new SimpleDateFormat();

	//////////////////////////////////////////////////////////// 时间转换///////////////////////////////////////////////////////////////////
	/**
	 * 时间转换为字符，默认使用yyyy-MM-dd格式
	 * 
	 * @param date
	 *            被格式化日期
	 * @return 格式化后的日期字符串
	 */
	public static String formatDateToString(Date date) {
		return formatDateToString(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 根据给定的格式把时间转换为字符
	 * 
	 * @param date
	 *            被格式化日期
	 * @param format
	 *            转换格式
	 * @return 格式化后的日期字符串
	 */
	public static String formatDateToString(Date date, String format) {
		if (date == null || format == null || format.length() == 0) {
			return "";
		} else {
			sdf.applyPattern(format);
			return sdf.format(date);
		}
	}

	/**
	 * 字符转换为时间，默认使用yyyy-MM-dd格式
	 * 
	 * @param date
	 *            字符串形式的日期
	 * @return 格式化后的日期
	 */
	public static Date formatStringToDate(String date) {
		return formatStringToDate(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 根据给定的格式把字符转换为时间
	 * 
	 * @param date
	 *            字符串形式的日期
	 * @param format
	 *            转换格式
	 * @return 格式化后的日期
	 */
	public static Date formatStringToDate(String date, String format) {
		try {
			if (date == null || format == null || format.length() == 0) {
				return null;
			} else {
				sdf.applyPattern(format);
				return sdf.parse(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 用默认格式yyyy-MM-dd获取系统时间
	 * 
	 * @return 格式化后的字符型日期
	 */
	public static String getSystemDate() {
		return getSystemDate(DEFAULT_DATE_FORMAT);
	}

	/**
	 * 根据给定的格式获取系统时间
	 * 
	 * @param format
	 *            转换格式
	 * @return 格式化后的字符型日期
	 */
	public static String getSystemDate(String format) {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	////////////////////////////////////////////////////// 时间相加//////////////////////////////////////////////////////////////
	/**
	 * 给指定的日期加年
	 */
	public static Date addYear(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, offset);
		return calendar.getTime();
	}

	/**
	 * 给指定的日期加月
	 */
	
	public static String addMonth(Date date, int offset, String pattern) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, offset);
		return getDate(calendar.getTime(), pattern);
	}

	/**
	 * 给指定的日期加天
	 */
	public static Date addDay(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, offset);
		return calendar.getTime();
	}

	/**
	 * 给指定的日期加小时
	 */
	public static Date addHour(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, offset);
		return calendar.getTime();
	}

	/**
	 * 给指定的日期加分
	 */
	public static Date addMinute(int offset, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, offset);
		return calendar.getTime();
	}

	/**
	 * 给指定的日期加秒
	 */
	public static Date addSecond(int offset, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, offset);
		return calendar.getTime();
	}

	//////////////////////////////////////////////// 时间相减,开始时间必须大于结束时间///////////////////////////////////////////////////////////////

	/**
	 * 两个时间相差的年数
	 */
	public static double minusYear(Date startDate, Date endDate) {
		if (startDate.getTime() >= endDate.getTime()) {
			return 0;
		}

		double startDay = (double) (startDate.getTime() + 4 * DAY) / (double) YEAR;
		double endDay = (double) (endDate.getTime() + 4 * DAY) / (double) YEAR;
		double result = endDay - startDay;
		return new Double(result).doubleValue();
	}

	/**
	 * 两个时间相差的月数
	 */
	public static double minusMonth(Date startDate, Date endDate) {
		if (startDate.getTime() >= endDate.getTime()) {
			return 0;
		}

		double startDay = (double) (startDate.getTime() + 4 * DAY) / (double) MONTH;
		double endDay = (double) (endDate.getTime() + 4 * DAY) / (double) MONTH;
		double result = endDay - startDay;
		return new Double(result).doubleValue();
	}

	/**
	 * 两个时间相差的天数
	 */
	public static double minusDay(Date startDate, Date endDate) {
		if (startDate.getTime() >= endDate.getTime()) {
			return 0;
		}

		double startDay = (double) (startDate.getTime() + 4 * DAY) / (double) DAY;
		double endDay = (double) (endDate.getTime() + 4 * DAY) / (double) DAY;
		double result = endDay - startDay;
		return new Double(result).doubleValue();
	}

	/**
	 * 两个时间相差的小时数
	 */
	public static double minusHour(Date startDate, Date endDate) {
		if (startDate.getTime() >= endDate.getTime()) {
			return 0;
		}

		double startDay = (double) (startDate.getTime() + 4 * DAY) / (double) HOUR;
		double endDay = (double) (endDate.getTime() + 4 * DAY) / (double) HOUR;
		double result = endDay - startDay;
		return new Double(result).doubleValue();
	}

	/**
	 * 两个时间相差的分钟数
	 */
	public static double minusMinute(Date startDate, Date endDate) {
		if (startDate.getTime() >= endDate.getTime()) {
			return 0;
		}

		double startDay = (double) (startDate.getTime() + 4 * DAY) / (double) MINUTE;
		double endDay = (double) (endDate.getTime() + 4 * DAY) / (double) MINUTE;
		double result = endDay - startDay;
		return new Double(result).doubleValue();
	}

	/**
	 * 两个时间相差的秒数
	 */
	public static double minusSecond(Date startDate, Date endDate) {
		if (startDate.getTime() >= endDate.getTime()) {
			return 0;
		}

		double startDay = (double) (startDate.getTime() + 4 * DAY) / (double) SECOND;
		double endDay = (double) (endDate.getTime() + 4 * DAY) / (double) SECOND;
		double result = endDay - startDay;
		return new Double(result).doubleValue();
	}

	///////////////////////////////////////// 时间比较////////////////////////////////////////
	public static boolean compare(Date date1, Date date2) {
		if (date1 == null && date2 == null) {
			return true;
		}
		if (date1 == null || date2 == null) {
			return false;
		} else {
			return date1.getTime() == date2.getTime();
		}
	}

	/**
	 * 用默认格式yyyy-MM-dd HH:mm:ss获取系统时间
	 * 
	 * @return 格式化后的字符型日期
	 */
	public static String GetTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
		return format.format(date);
	}

	/**
	 * 取得月份
	 */
	public static Integer getMonth(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar.get(Calendar.MONTH)+1;
	}
	
	/**
	 * 取得月份
	 */
	public static Integer getYear(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * 当月第一天
	 * 
	 * @return
	 */
	public static String getFirstDayOfMonth(String str, String pattern1, String pattern2) throws Exception {

		Date date = new SimpleDateFormat(pattern1).parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		return getDate(calendar.getTime(), pattern2);
	}

	/**
	 * 当月最后一天 最后时间
	 * 
	 * @return
	 */
	public static String getLastDayOfMonth(String str, String pattern1, String pattern2) throws Exception {

		Date date = new SimpleDateFormat(pattern1).parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.SECOND, -1);

		return getDate(calendar.getTime(), pattern2);

	}

	public static boolean validateDate(String str){
		try {
			//String str ="2016年9月1w8日16:34:16";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
			Date date1 = sdf.parse(str);
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static String formartUploadDate(String str){
		try {
			//String str ="2016年9月1w8日16:34:16";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf2.format(sdf.parse(str));
			
		} catch (Exception e) {
			return "";
		}
	}
	
	
	@Test
	public void main() throws Exception {

		/*System.out.println(getFirstDayOfMonth("2011-11", "yyyy-M", "yyyy年M月d号"));
		System.out.println(getLastDayOfMonth("2011-11", "yyyy-M", "yyyy年M月d号"));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		// System.out.println(getDate(calendar.getTime(), "yyyy-MM-d"));
		System.out.println(getFirstDayOfMonth(getDate(calendar.getTime(), "yyyy-M-d"), "yyyy-M", "yyyy-M-d HH:mm:ss"));
		System.out.println(getLastDayOfMonth(getDate(calendar.getTime(), "yyyy-M-d"), "yyyy-M", "yyyy-M-d HH:mm:ss"));*/
		
		//System.out.println(getMonth(getDate("2015年12月", "yyyy年MM月")));
		//System.out.println(getYear(getDate("2015年12月", "yyyy年M月")));
		//System.out.println(addDay(new Date(),-1));
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
		//System.out.println(formartUploadDate("2016年9月8日16:34:16"));
		
		System.out.println("12345".substring(0,"12345".length()-1));
	}

}
