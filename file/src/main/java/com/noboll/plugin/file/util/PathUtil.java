package com.noboll.plugin.file.util;

import java.io.File;

import com.noboll.core.exception.BusinessException;
import com.noboll.core.util.PropertiesUtil;
import com.noboll.core.util.StringUtil;
import com.noboll.plugin.file.entity.PathType;

/**
 * 路径工具类
 * @author 00705
 *
 */
public class PathUtil {
	
	/**
	 * 根据模块名和类型生成文件
	 * @param module
	 * @param type
	 * @return
	 */
	public static String makePath(String module,PathType type) {
		String setting=PropertiesUtil.getSettingValue("sys.file.upload.path");
		if(StringUtil.isEmpty(setting)) {
			throw new BusinessException("请设置文件上传路径");
		}
		return setting+File.separator+module+File.separator+type.makePath();
	}
	
	public static void main(String[] args) {
		System.out.println(UploadUtil.uploadFile(new File("D:\\转正审批流设计.docx"), "test", PathType.YMD));
	}
}
