package com.noboll.plugin.file.entity;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.noboll.core.util.StringUtil;

/**
 * Y表示年，YM表示年/月，YMD表示年/月/日
 * @author 00705
 *
 */
public enum PathType {
	Y {
		public String makePath() {
			Map<String,String> map=get();
			return map.get("Y");
		}		
	},
	YM{
		public String makePath() {
			Map<String,String> map=get();
			return map.get("Y")+File.separator+map.get("M");
		}
		
	},
	YMD{
		public String makePath() {
			Map<String,String> map=get();
			return map.get("Y")+File.separator+map.get("M")+File.separator+map.get("D");
		}
	};
	public abstract String makePath();
	
	public Map<String,String> get() {
		Calendar calendar=Calendar.getInstance();  
		Map<String,String> map=new HashMap<String, String>();
		map.put("Y", ""+calendar.get(Calendar.YEAR));
		map.put("M", StringUtil.formateNumber(calendar.get(Calendar.MONTH)+1,2));
		map.put("D", StringUtil.formateNumber(calendar.get(Calendar.DATE),2));
		return map;
	}
}
