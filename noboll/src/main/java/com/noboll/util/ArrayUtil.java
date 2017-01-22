package com.noboll.util;

import java.util.LinkedList;
import java.util.List;

public class ArrayUtil {

	/**
	 * 去除数组中重复的记录  
	 */
	public static String[] arrayUnique(String[] a) {  
	    // array_unique  
	    List<String> list = new LinkedList<String>();  
	    for(int i = 0; i < a.length; i++) {  
	        if(!list.contains(a[i])) {  
	            list.add(a[i]);  
	        }  
	    }  
	    return (String[])list.toArray(new String[list.size()]);  
	}
}
