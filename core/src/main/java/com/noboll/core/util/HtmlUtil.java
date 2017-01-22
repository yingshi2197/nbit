package com.noboll.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {
	
	/**
	 * 过滤html中的script，style，html标签
	 * @param htmlStr
	 * @return
	 */
	public static String filterHtml(String htmlStr) {
		if(StringUtil.isEmpty(htmlStr))
			return "";
		
		String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式
        
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        htmlStr=m_script.replaceAll(""); //过滤script标签
        
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        htmlStr=m_style.replaceAll(""); //过滤style标签

        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        htmlStr=m_html.replaceAll(""); //过滤html标签

        htmlStr=htmlStr.replaceAll("&nbsp;", ""); // 去除空格
        
        return htmlStr.trim(); //返回文本字符串 
	}
	
}
