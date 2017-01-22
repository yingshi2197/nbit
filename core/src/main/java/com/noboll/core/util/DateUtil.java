package com.noboll.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.noboll.core.constants.SystemConstant;
import com.noboll.core.exception.BusinessException;
/**
 * 日期工具类
 * @author 00705
 *
 */
public class DateUtil {
	
	/**
	 * 根据字符串和日期格式转化日期
	 * @param date
	 * @param format  可参考com.noboll.core.constants.SystemConstant.DATE_FORMAT和DATETIME_FORMAT
	 * @return
	 */
	public static String dateToString(Date date,String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		if (null==date) {
			return "-";
		}  
		return f.format(date);
	}
	
	/**
	 * 根据字符串和日期格式转化日期
	 * @param date
	 * @param format  可参考com.noboll.core.constants.SystemConstant.DATE_FORMAT和DATETIME_FORMAT
	 * @return
	 */
	public static Date stringToDate(String date,String format) {
		Date date1 = null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			date1 = df.parse(date);
		} catch (ParseException e) {
			throw new BusinessException("日期"+date+"转换失败："+format);
		}
		return date1;
	}
	
	/**
	 * 
	* @Title: addDay 
	* @Description: 日期加减，如果要减，num就为负数
	* @param @param date
	* @param @param num
	* @param @return    设定文件 
	* @return Date    返回类型 
	* @throws
	 */
	public static Date addDay(Date date,int num) {
		 	Calendar calendar=Calendar.getInstance();  
		   calendar.setTime(date);
		   calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+num);//让日期加1 
		   return calendar.getTime();
	}
	
	/**
	 * 
	* @Title: addDay 
	* @Description: 日期加减，如果要减，num就为负数
	* @param @param date
	* @param @param num
	* @param @return    设定文件 
	* @return string    返回类型 
	* @throws
	 */
	public static String addDay(String date,int num) {
	 	Calendar calendar=Calendar.getInstance();  
	 	String patten="";
	 	if(date.length()==SystemConstant.DATETIME_FORMAT.length()) {
	 		patten=SystemConstant.DATETIME_FORMAT;
	 	}else {
	 		patten=SystemConstant.DATE_FORMAT;
	 	}
	    calendar.setTime(stringToDate(date,patten));
	    calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+num);//让日期加1 
	    SimpleDateFormat df2 = new SimpleDateFormat(patten);
	    return df2.format(calendar.getTime());
	}

}
