package com.noboll.plugin.file.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.noboll.core.context.BaseContext;
import com.noboll.core.exception.BusinessException;
import com.noboll.core.util.NumberUtil;
import com.noboll.core.util.PropertiesUtil;
import com.noboll.core.util.StringUtil;
import com.noboll.plugin.file.entity.PathType;

public class UploadUtil {
	
	/**
	 * 上传文件
	 * @param file  文件
	 * @param module  模块名
	 * @param type  类型，PathType.Y,PathType.YM,PathType.YMD
	 * @return
	 */
	public static String uploadFile(MultipartFile file,String module,PathType type) {
		String path=PathUtil.makePath(module, type);
		String returnPath=path;
		String absolute=PropertiesUtil.getSettingValue("sys.file.upload.path.absolute");
		String allow = PropertiesUtil.getSettingValue("sys.file.upload."+module+".type");
		if(StringUtil.isEmpty(allow)) {
			allow=PropertiesUtil.getSettingValue("sys.file.upload.base.type");
		}
		List<String> allowList=new ArrayList<String>();
		if(!StringUtil.isEmpty(allow)) {
			allowList=Arrays.asList(allow.toLowerCase().split(",")); 
		}
		String sub=FileUtil.getFileSuffix(file.getOriginalFilename());
		if(allowList.size()>0&&!allowList.contains(sub.toLowerCase())) {
			throw new BusinessException("文件上传类型必须为："+allow);
		}
		String fileSize=PropertiesUtil.getSettingValue("sys.file.upload."+module+".size");
		if(StringUtil.isEmpty(fileSize)) {
			fileSize=PropertiesUtil.getSettingValue("sys.file.upload.base.size");
		}
		if(NumberUtil.isNumber(fileSize)) {
			if(file.getSize()>Double.parseDouble(fileSize)*1024*1024) {
				throw new BusinessException("文件大小不能超过："+fileSize+"M");
			}
		}
		try {
			if(StringUtil.isEmpty(absolute)) {
				path=BaseContext.getWebPath()+path;
			}else {
				path=absolute+path;
			}
			File outFile=new File(path);
			outFile.mkdirs();
			String name=FileUtil.makeFileName(file.getOriginalFilename(), module);
			File target=new File(outFile+File.separator+name);
			file.transferTo(target);
			returnPath=returnPath+File.separator+name;
            return returnPath;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("文件上传失败！");
		} 
	}
	
	
	public static String uploadFile(File file,String module,PathType type) {
		String path=PathUtil.makePath(module, type);
		String returnPath=path;
		String allow = PropertiesUtil.getSettingValue("sys.file.upload."+module+".type");
		String absolute=PropertiesUtil.getSettingValue("sys.file.upload.path.absolute");
		if(StringUtil.isEmpty(allow)) {
			allow=PropertiesUtil.getSettingValue("sys.file.upload.base.type");
		}
		List<String> allowList=new ArrayList<String>();
		if(!StringUtil.isEmpty(allow)) {
			allowList=Arrays.asList(allow.toLowerCase().split(",")); 
		}
		String sub=FileUtil.getFileSuffix(file.getName());
		if(allowList.size()>0&&!allowList.contains(sub.toLowerCase())) {
			throw new BusinessException("文件上传类型必须为："+allow);
		}
		String fileSize=PropertiesUtil.getSettingValue("sys.file.upload."+module+".size");
		if(StringUtil.isEmpty(fileSize)) {
			fileSize=PropertiesUtil.getSettingValue("sys.file.upload.base.size");
		}
		if(NumberUtil.isNumber(fileSize)) {
			if(file.length()>Double.parseDouble(fileSize)*1024*1024) {
				throw new BusinessException("文件大小不能超过："+fileSize+"M");
			}
		}
		
		try {
			// 相对路径
			if(StringUtil.isEmpty(absolute)) {
				path=BaseContext.getWebPath()+path;
			}else {
				path=absolute+path;
			}
			File outFile=new File(path);
			outFile.mkdirs();
			
			InputStream reader = null;
			OutputStream fout = null;
			byte[] tempbytes = new byte[1024];
            int byteread = 0;
            reader = new FileInputStream(file);
            String name=FileUtil.makeFileName(file.getName(), module);
            fout=new FileOutputStream(outFile+File.separator+name);
            returnPath=returnPath+File.separator+name;
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = reader.read(tempbytes)) != -1) {
               fout.write(tempbytes,0,byteread);
            }
            fout.flush();
			fout.close();
			reader.close();
			return returnPath;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("文件上传失败！");
		} 
	}
	
	
}
