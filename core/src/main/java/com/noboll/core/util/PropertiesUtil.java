package com.noboll.core.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
/**
 * 读取配置文件
 * @author 00705
 *
 */
public class PropertiesUtil {
	
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
	
	private static Properties settingProperties=null;
	
	private static Properties getSettingProperties() {
		if(settingProperties==null) {
			settingProperties=loadProperties("setting.properties");
		}
		return settingProperties;
	}
	
	/**
	 * 通过文件获取properties对象
	 * @param location
	 * @return
	 */
	public static Properties loadProperties(String location) {
		InputStream is = null;
		Properties props = new Properties();
		try {
			Resource resource = resourceLoader.getResource(location);
			is = resource.getInputStream();
			props.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
		}
		return props;
	}
	
	
	// 通过key获取配置文件中的值
	public static String getSettingValue(String key) {
		return getSettingProperties().getProperty(key);
	}
	
   /**
    * 
   * @Title: writeData 
   * @Description: 通过键修改值
   * @param @param key
   * @param @param value
   * @param @param fileName
   * @param @throws Exception    设定文件 
   * @return void    返回类型 
   * @throws
    */
   public static void writeData(String location,String key, String value) throws Exception {
	   OutputStream fos = null;  
	   try {
	       Properties prop = loadProperties(location);  	      
		   Resource resource = resourceLoader.getResource(location);
	       fos = new FileOutputStream(resource.getFile());  
	       prop.setProperty(key, value);  
	       prop.store(fos, "Update '" + key + "' value");  
	       fos.close();
	   }catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(fos);
		}
   }   
   
}
