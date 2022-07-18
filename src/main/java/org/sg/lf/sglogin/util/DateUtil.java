package org.sg.lf.sglogin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static Date getAddDate(Date date,int ss) {
		String newDate=df.format(date);
		Date returnDate=null;
		try {
			returnDate = df.parse(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		returnDate.setTime(returnDate.getTime()+ss*1000);
		return returnDate;
	}
	
	public static String getDateShow(Date date) {
		if(date==null) return null;
		return df.format(date);
	}
	
	public static String getDateShow(String date) {
		if(date==null) return null;
		if("mysql".equals(DataBaseConfig.getDataName().toLowerCase())) {
			return df.format(date);
		}else {
			return df.format(new Date(Long.parseLong(date)));
		}
	}
	
	public static String getNowDate() {
		if("mysql".equals(DataBaseConfig.getDataName().toLowerCase())) {
			return df.format(new Date());
		}else {
			try {
				return String.valueOf(df.parse(df.format(new Date())).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		    return null;
		}
	}
	
	// unix时间戳转人类能看懂的格式
	public static String dateToString(String date) {
		if(date==null) return null;
		if("mysql".equals(DataBaseConfig.getDataName().toLowerCase())) {
			return date;
		}else {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(date)));
		}
	}
	
	// 向数据库保存的日期格式
	public static String dateToSaveDate(Date date) {
		if(date==null) return null;
		if("mysql".equals(DataBaseConfig.getDataName().toLowerCase())) {
			return df.format(date);
		}else {
			try {
				return String.valueOf(df.parse(df.format(date)).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	// String转为java.util.Date格式
	public static Date dateToSaveDate(String date) {
		if(date==null) return null;
		if("mysql".equals(DataBaseConfig.getDataName().toLowerCase())) {
			try {
				return df.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}else {
			return new Date(Integer.parseInt(date));
		}
	}
	
	//取两个日期间的天时分
	public static String getTwoDateTimes(Date startTime, Date endTime) {
		if(startTime==null || endTime==null) {
			return null;
		}
		if(startTime.compareTo(endTime)>0) {
				return "0天0小时0分钟0秒";
		}
		// 按照传入的格式生成一个simpledateformate对象
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		diff = endTime.getTime() - startTime.getTime();
		day = diff / nd;// 计算差多少天
		hour = diff % nd / nh + day * 24;// 计算差多少小时
		min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
		sec = diff % nd % nh % nm / ns;// 计算差多少秒
		// 输出结果
		return day + "天" + (hour - day * 24) + "小时"+ (min - day * 24 * 60) + "分钟" + sec + "秒";
	}
	
	//取两个日期间的天时分
	public static int getTwoDateSecond(Date startTime, Date endTime) {
		if(startTime==null || endTime==null) {
			return 0;
		}
		// 按照传入的格式生成一个simpledateformate对象
		long nm = 1000 * 60;// 一分钟的毫秒数
		long diff;
		// 获得两个时间的毫秒时间差异
		diff = Math.abs(endTime.getTime() - startTime.getTime());
		// 输出结果
		return (int) (diff/nm);
	}
}
