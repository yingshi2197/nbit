package com.noboll.core.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	public static boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}
	
	/**
	 * 格式化数字，补零
	 * @param number
	 * @param length
	 * @return
	 */
	public static String formateNumber(int number,int length) {
		String str=""+number;
		StringBuffer sb=new StringBuffer("");
		for(int i=0;i<length-str.length();i++) {
			sb.append("0");
		}
		sb.append(str);
		return sb.toString();
	}
	
	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public static String toLowerCaseFirst(String str){
		return  str.substring(0, 1).toLowerCase()+str.substring(1);
	}
	
}
