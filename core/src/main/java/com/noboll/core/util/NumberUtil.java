package com.noboll.core.util;

import org.apache.commons.lang.math.NumberUtils;

public class NumberUtil {
	
	/**
	 * 判断字符串是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		return NumberUtils.isNumber(str);
	}
	
	/**
	 * 将字符串转成Integer类型，如果是非数字，就返回null
	 * @param str
	 * @return
	 */
	public static Integer parseInt(String str) {
		if(isNumber(str)) {
			return Integer.parseInt(str);
		}
		return null;
	}
	
	/**
	 * 将字符串转成Double类型，如果是非数字，就返回null
	 * @param str
	 * @return
	 */
	public static Double parseDouble(String str) {
		if(isNumber(str)) {
			return Double.parseDouble(str);
		}
		return null;
	}
}
