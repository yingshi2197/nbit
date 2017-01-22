package com.noboll.core.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.noboll.core.exception.BusinessException;

public class ReflectUtil {
    
      
    /** 
     * 通过BeanUtils工具包获取反射获取字段值,注意此值是以字符串形式存在的,它支持属性连缀操作:如,.对象.属性 
     * @param propertyName 属性名 
     * @param object       实例对象 
     * @return          字段值 
     */  
    public static Object getValue(String propertyName, Object object) {  
        try {             
            return BeanUtils.getProperty(object, propertyName);  
        } catch (Exception ex) {  
        	ex.printStackTrace();
            throw new RuntimeException();  
        }  
    }  
      
    /** 
     * 通过BeanUtils工具包获取反射获取字段值,注意此值是以字符串形式存在的 
     * @param object       实例对象 
     * @param propertyName 属性名 
     * @param value        字段值 
     * @return           
     */  
    public static void setValue(Object obj,String fieldName,Object fieldValue) {  
        try {             
        	List<Field> list=getClassFields(obj.getClass(), true);
        	Field field=null;
        	for(Field f:list) {
        		if(f.getName().equals(fieldName)) {
        			field=f;
        			break;
        		}
        	}
        	if(field==null) {
        		throw new BusinessException("这个属性不存在");
        	}
            field.setAccessible(true);  
            field.set(obj, fieldValue);  
        } catch (Exception ex) {  
            throw new RuntimeException();  
        }  
    }  
      
	/**
	* 获取类实例的属性值
	* @param clazz
	*            类名
	* @param includeParentClass
	*            是否包括父类的属性值
	* @return 类名.属性名=属性类型
	*/
	public static List<Field> getClassFields ( Class clazz, boolean includeParentClass )	{
		List<Field> list=new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields ( );
		for ( Field field : fields )		{
			list.add(field);
		}
		if ( includeParentClass )
			getParentClassFields ( list, clazz.getSuperclass ( ) );
		return list;
	}
	
	/**
	* 获取类实例的父类的属性值
	* @param map
	*            类实例的属性值Map
	* @param clazz
	*            类名
	* @return 类名.属性名=属性类型
	*/
	private static  List<Field> getParentClassFields ( List<Field> list, Class clazz )	{
		Field[] fields = clazz.getDeclaredFields ( );
		for ( Field field : fields ) {
			list.add(field);
		}
		if ( clazz.getSuperclass ( ) == null)	{
			return list;
		}
		getParentClassFields ( list, clazz.getSuperclass ( ) );
		return list;
	}
	
	 /**
     * 
    * @Title: getFields 
    * @Description: 获取该类的所有属性，不包括父类
    * @param @param cls
    * @param @return    设定文件 
    * @return List<String>    返回类型 
    * @throws
     */
    public static List<String> getFields(Class cls) {
    	List<String> list=new ArrayList<String>();
        List<Field> fieldlist = getClassFields(cls, true);
        for (int i = 0; i < fieldlist.size(); i++) {
            Field fld = fieldlist.get(i);
            list.add(fld.getName());
        }
        return list;
    }
}
