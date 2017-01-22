package com.noboll.core.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noboll.core.exception.BusinessException;

public class JsonUtil {
	
	public static String objToJson(Object object)  {  
        ObjectMapper mapper = new ObjectMapper();  
        try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new BusinessException("无法转换成json");
		}  
    }  
      
    public static <T extends Object>  T jsonToObject(String str,Class<T> cls)  {  
        ObjectMapper mapper = new ObjectMapper();  
        try {
			return mapper.readValue(str, cls);
		} catch (Exception e) {
			throw new BusinessException("该json字符串["+str+"]无法转换成对象");
		} 
    }  
    
}
