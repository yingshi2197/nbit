package com.noboll.core.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import com.noboll.core.context.BaseContext;
import com.noboll.core.exception.BusinessException;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

public class FreeMarkTemplateUtil {
	/**
	 * 模板文件后缀
	 */
	private static final String SUFFIX = ".ftl";
	/**
	 * 模板引擎配置
	 */
	private static Configuration configuration=null;

	/**
	 * 
	 * @param templateName  模板名，例如message
	 * @param parameters  传入参数
	 * @return
	 */
	public static String getText(String templateName,Map<String, Object> parameters) {
		if(configuration==null) {
			configuration = new Configuration(Configuration.VERSION_2_3_22);
			try {
				String templatePath=PropertiesUtil.getSettingValue("sys.message.template.file.path");
				// 如果路径为相对路径
				if(templatePath.startsWith("|")) {
					templatePath=BaseContext.getWebRootPath()+File.separator+templatePath.substring(1);
				}
				configuration.setTemplateLoader(new FileTemplateLoader(new File(templatePath)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			configuration.setEncoding(Locale.getDefault(), "UTF-8");
			configuration.setDateFormat("yyyy-MM-dd HH:mm:ss");
			configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
			configuration.setTimeFormat("yyyy-MM-dd HH:mm:ss");
		}
		
		String templateFile = templateName + SUFFIX;
		try {
			Template template = configuration.getTemplate(templateFile);
			StringWriter stringWriter = new StringWriter();
			template.process(parameters, stringWriter);
			return stringWriter.toString();
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException("读取模板配置出错：模板"+templateFile+"不存在！");
		} catch(Exception e){
			e.printStackTrace();
			throw new BusinessException("读取模板配置出错！");
		}
	}
}
