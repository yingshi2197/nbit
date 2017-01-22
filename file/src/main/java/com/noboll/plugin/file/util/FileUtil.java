package com.noboll.plugin.file.util;

import java.io.File;
import java.util.UUID;

import com.noboll.core.context.BaseContext;
import com.noboll.core.util.PropertiesUtil;
import com.noboll.core.util.StringUtil;

/**
 * 文件上传工具类
 * @author 00705
 *
 */
public class FileUtil {
	// 获取文件后缀名
	public static String getFileSuffix(String fileName) {
		if(StringUtil.isEmpty(fileName)) {
			return fileName;
		}
		return  fileName.substring(fileName.lastIndexOf(".")+1);
	}
	
	// 获取文件后缀名
	public static String makeFileName(String fileName,String module) {
		String rename=PropertiesUtil.getSettingValue("sys.file.upload."+module+".rename");
		if(StringUtil.isEmpty(rename)) {
			rename=PropertiesUtil.getSettingValue("sys.file.upload.base.rename");
		}
		if("1".endsWith(rename)) {
			return UUID.randomUUID().toString()+"."+getFileSuffix(fileName);
		}
		return fileName;
	}
	
	public static String getAbsolutePath(String path) {
		String absolute=PropertiesUtil.getSettingValue("sys.file.upload.path.absolute");
		if(StringUtil.isEmpty(absolute)) {
			return BaseContext.getWebPath()+path;
		}else
			return absolute+path;
	}
}
