package com.wzd.wolf_open_resource.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CommonDateUtil {

	protected static SimpleDateFormat longFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	protected static SimpleDateFormat longHourFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	protected static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	protected static SimpleDateFormat dateFormat2 = new SimpleDateFormat(
			"yy/MM/dd");
	protected static SimpleDateFormat timeFormat = new SimpleDateFormat(
			"HH:mm:ss");
	protected static SimpleDateFormat yearMonthFormat = new SimpleDateFormat(
			"yyyy-MM");
	protected static SimpleDateFormat monthDayFormat = new SimpleDateFormat(
			"MM-dd");
	protected static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	protected static SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
	protected static SimpleDateFormat monthDateFormat = new SimpleDateFormat(
			"MM-dd HH:mm:ss");
	protected static SimpleDateFormat minuteSecondFormat = new SimpleDateFormat(
			"mm分ss秒");

	// ************************** 获取指定格式的时间日期 **********************
	/**
	 * 以指定的格式来格式化日期
	 * 
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @return String
	 */
	public static String formatDateByFormat(java.util.Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 常用的格式化日期
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDate2(java.util.Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 常用的格式化日期
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDate(java.util.Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 */
	public static String getMonthBegin(String strdate) {
		java.util.Date date = StringToDate(strdate);
		return formatDateByFormat(date, "yyyy-MM") + "-01";
	}

	/**
	 * date型转化为String 格式为yyyy-MM-dd
	 * 
	 * @param date
	 * @param strDefault
	 * @return
	 */
	public static String DateToString(Date date, String strDefault) {
		String strTemp = strDefault;
		if (date != null) {
			strTemp = dateFormat.format(date);
		}
		return strTemp;
	}

	/**
	 * date型转化为String 格式为yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String DateToString(java.util.Date date) {
		String strTemp = "";
		if (date != null) {
			strTemp = dateFormat.format(date);
		}
		return strTemp;
	}

	/**
	 * date型转化为String 格式为yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateToString(long time) {
		java.util.Date date = new java.util.Date(time);
		String strTemp = "";
		if (date != null) {
			strTemp = dateFormat.format(date);
		}
		return strTemp;
	}

	/**
	 * date型转化为String 格式为yy/MM/dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateToString2(long time) {
		java.util.Date date = new java.util.Date(time);
		String strTemp = "";
		if (date != null) {
			strTemp = dateFormat2.format(date);
		}
		return strTemp;
	}

	/**
	 * long型转化为String 格式为MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonthDayToString(long time) {
		java.util.Date date = new java.util.Date(time);
		String strTemp = "";
		if (date != null) {
			strTemp = monthDayFormat.format(date);
		}
		return strTemp;
	}

	/**
	 * date型转化为String 格式为yyyy-MM-dd HH:mm
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateToStringHour(long time) {
		java.util.Date date = new java.util.Date(time);
		String strTemp = "";
		if (date != null) {
			strTemp = longHourFormat.format(date);
		}
		return strTemp;
	}

	/**
	 * date型转化为String 格式为MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String DateToStringDay(java.util.Date date) {
		String strTemp = "";
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
			strTemp = formatter.format(date);
		}
		return strTemp;
	}

	/**
	 * date型转化为String 格式为hh:mm
	 * 
	 * @param date
	 * @param strDefault
	 * @return
	 */
	public static String DateToString2(java.util.Date date) {
		String strTemp = "";
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
			strTemp = formatter.format(date);
		}
		return strTemp;
	}

	/**
	 * date型转化为String 格式为hh:mm:ss
	 * 
	 * @param date
	 * @param strDefault
	 * @return
	 */
	public static String DateToString3(java.util.Date date) {
		String strTemp = "";
		if (date != null) {
			strTemp = timeFormat.format(date);
		}
		return strTemp;
	}

	/**
	 * date型转化为String 格式为yyyy-MM
	 * 
	 * @param date
	 * @param strDefault
	 * @return
	 */
	public static String DateToString3(Date date, String strDefault) {
		String strTemp = strDefault;
		if (date != null) {
			strTemp = yearMonthFormat.format(date);
		}
		return strTemp;
	}

	public static String DateToString2(Date date, String strDefault) {
		String strTemp = strDefault;
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			strTemp = formatter.format(date);
		}
		return strTemp;
	}

	public static String DateToString(Date date) {
		return DateToString(date, null);
	}

	/**
	 * 取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 */
	public static String getMonthEnd(String strdate) {
		java.util.Date date = StringToDate(getMonthBegin(strdate));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}

	// ************************* 日期转换为对应类型 ********************

	/**
	 * 返回时间(格式：yyyy-MM-dd hh:mm:ss)
	 */
	public static String getLongTime(long time) {
		return longFormat.format(time);
	}

	/**
	 * 返回时间(格式：yyyy-MM-dd hh:mm)
	 */
	public static String getLongHourTime(long time) {
		return longHourFormat.format(time);
	}

	/**
	 * String转化为Timestamp类型
	 * 
	 * @param strDefault
	 * @param date
	 * @return
	 */
	public static Timestamp StringToTimestamp(String strDate) {
		if (strDate != null && !strDate.equals("")) {
			try {
				java.util.Date d = dateFormat.parse(strDate);
				Timestamp numTime = new Timestamp(d.getTime());
				return numTime;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * String转化为java.sql.date类型，
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date StringToDate(String strDate) {
		if (strDate != null && !strDate.equals("")) {
			try {
				java.util.Date d = dateFormat.parse(strDate);
				Date numTime = new Date(d.getTime());
				return numTime;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * String转化为java.sql.date类型，
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date StringToDateLong(String strDate) {
		if (strDate != null && !strDate.equals("")) {
			try {
				java.util.Date d = longFormat.parse(strDate);
				Date numTime = new Date(d.getTime());
				return numTime;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * String转化为java.sql.date类型，
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date StringToDateLongHour(String strDate) {
		if (strDate != null && !strDate.equals("")) {
			try {
				java.util.Date d = longHourFormat.parse(strDate);
				Date numTime = new Date(d.getTime());
				return numTime;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 从Timestamp类型转化为yyyy-mm-dd类型的字符串
	 * 
	 * @param date
	 * @param strDefault
	 * @return
	 */
	public static String TimestampToString(Timestamp date, String strDefault) {
		String strTemp = strDefault;
		if (date != null) {
			strTemp = longFormat.format(date);
		}
		return strTemp;
	}

	/**
	 * 从Timestamp类型转化为yyyy/mm/dd类型的字符串,如果为null,侧放回""
	 * 
	 * @param date
	 * @return
	 */
	public static String TimestampToString(Timestamp date) {
		return TimestampToString(date, null);
	}

	/**
	 * 取得指定月份的总天数
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 */
	public static int getMonthDaynum(String strdate) {
		String enddate = getMonthEnd(strdate);
		return Integer.parseInt(enddate.substring(enddate.length() - 2,
				enddate.length()));
	}

	// *************************** 当前时间 ***********************************

	/**
	 * 取得系统当前时间,类型为Timestamp
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getNowTimestamp() {
		java.util.Date d = new java.util.Date();
		Timestamp numTime = new Timestamp(d.getTime());
		return numTime;
	}

	/**
	 * 取得系统当前时间,类型为String
	 * 
	 * @return String
	 */
	public static String getNowTimeString() {
		return TimestampToString(getNowTimestamp());
	}

	/**
	 * 取得系统的当前时间,类型为String
	 * 
	 * @return String
	 */
	public static String getNowMonth() {
		return getNowTimeString().substring(0, 7);
	}

	/**
	 * 取得系统的当前时间,类型为java.sql.Date
	 * 
	 * @return java.sql.Date
	 */
	public static Date getNowDate() {
		java.util.Date d = new java.util.Date();
		return new Date(d.getTime());
	}

	/**
	 * 得到当前日期的字符串
	 * 
	 * @return
	 */
	public static String getNowDateString() {
		return DateToString(getNowDate());
	}

	/**
	 * 得到当前日期的字符串
	 * 
	 * @return
	 */
	public static String getNowDateBeginString() {
		return getNowDateString() + " 00:00:00";
	}

	public static String getNowDateEndString() {
		return getNowDateString() + " 23:59:59";
	}

	/**
	 * 取得系统的当前年份,类型为String
	 * 
	 * @return String
	 */
	public static String getYearNow() {
		java.util.Date now = new java.util.Date();
		return yearFormat.format(now);
	}

	/**
	 * 取得系统的当前月份,类型为String "MM"
	 * 
	 * @return String
	 */
	public static String getMonthNow() {
		java.util.Date now = new java.util.Date();
		return monthFormat.format(now);
	}

	/**
	 * 返回当前时间(格式：yyyy-MM-dd hh:mm:ss)
	 */
	public static String getLongNow() {
		Calendar calendar = Calendar.getInstance();
		return longFormat.format(calendar.getTime());
	}

	/**
	 * 返回当前时间(格式：yyyy-MM-dd hh:mm)
	 */
	public static String getLongHourNow() {
		Calendar calendar = Calendar.getInstance();
		return longHourFormat.format(calendar.getTime());
	}

	/**
	 * 返回当前时间(格式：yyyy-MM-dd)
	 */
	public static String getDateNow() {
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 返回当前年月（格式：yyyy-MM）
	 * 
	 * @return
	 */
	public static String getYearMonthNow() {
		Calendar calendar = Calendar.getInstance();
		return yearMonthFormat.format(calendar.getTime());
	}

	/**
	 * 返回当前时间（格式：HH:mm:ss）
	 * 
	 * @return
	 */
	public static String getTimeNow() {
		Calendar calendar = Calendar.getInstance();
		return timeFormat.format(calendar.getTime());
	}

	/**
	 * 得到当前日期年月日字符串 result[0]:year result[1]:month result[2]:day
	 */
	public static String[] getYearMonthDay() {
		String[] result = new String[3];
		Calendar c = Calendar.getInstance();
		c.setTime(new java.util.Date());
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		if (month < 10) {
			result[1] = String.valueOf("0" + month);
		} else {
			result[1] = String.valueOf(month);
		}
		if (day < 10) {
			result[2] = String.valueOf("0" + day);
		} else {
			result[2] = String.valueOf(day);
		}
		result[0] = String.valueOf(c.get(Calendar.YEAR));
		return result;
	}

	/**
	 * 返回10位的int型当前时间
	 * 
	 * @return
	 */
	public static int getCurrTime() {
		long nowTime = System.currentTimeMillis();
		String nowTimeStr = String.valueOf(nowTime).substring(0, 10);
		return Integer.parseInt(nowTimeStr);
	}

	// *************************** 时间对比 ***********************************

	/**
	 * 获得年
	 */
	public static String getYear(Date date) {
		String strdate = DateToString(date);
		String datearr[] = strdate.split("-");
		return datearr[0];
	}

	/**
	 * 获得月
	 */
	public static String getMonth(Date date) {
		String strdate = DateToString(date);
		String datearr[] = strdate.split("-");
		return datearr[1];
	}

	/**
	 * 获得天
	 */
	public static String getDay(Date date) {
		String strdate = DateToString(date);
		String datearr[] = strdate.split("-");
		return datearr[2];
	}

	/**
	 * 是否是今天
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isToday(long time) {
		Calendar current = Calendar.getInstance();
		current.set(Calendar.HOUR, 0);
		current.set(Calendar.MINUTE, 0);
		current.set(Calendar.SECOND, 0);
		current.set(Calendar.MILLISECOND, 0);
		Calendar old = Calendar.getInstance();
		old.setTime(new java.util.Date(time));
		old.set(Calendar.HOUR, 0);
		old.set(Calendar.MINUTE, 0);
		old.set(Calendar.SECOND, 0);
		old.set(Calendar.MILLISECOND, 0);
		return current.getTime().getTime() == old.getTime().getTime();
	}

	/**
	 * 是否是今年
	 */
	public static boolean isCurrentYear(long time) {
		String currentYear = getYearNow();
		if (longFormat.format(time).substring(0, 4).equals(currentYear)) {
			return true;
		}
		return false;
	}

	/**
	 * 是否在同一分钟
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isSameMinute(java.util.Date d1, java.util.Date d2) {
		boolean result;
		if (d1.getTime() - d2.getTime() >= 60 * 1000
				|| d1.getTime() - d2.getTime() < -60 * 1000) {
			result = false;
		} else {
			result = d1.getMinutes() == d2.getMinutes();
		}
		return result;
	}

	/**
	 * 是否在同一天
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isTheSameDay(long milliseconds1, long milliseconds2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTimeInMillis(milliseconds1);
		c2.setTimeInMillis(milliseconds2);
		return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
				&& (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
				&& (c1.get(Calendar.DAY_OF_MONTH) == c2
						.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 是否在同一年
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isTheSameYear(long milliseconds1, long milliseconds2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTimeInMillis(milliseconds1);
		c2.setTimeInMillis(milliseconds2);
		return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR));
	}

}
