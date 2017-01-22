package com.noboll.business.wx.util;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.noboll.business.wx.entity.WxResponseObject;
import com.noboll.context.SystemContext;
import com.noboll.core.cache.service.CacheService;
import com.noboll.core.constants.SystemConstant;
import com.noboll.core.exception.BusinessException;
import com.noboll.core.util.JsonUtil;

public class WxUtil {
	
	public static final String APP_ID="wxd0a5bbfe747fed85";
	public static final String APP_SECRET="d1242c7a1b88261c3cb137292ebc1ffd";
	
	public static WxResponseObject success(Object obj) {
		WxResponseObject wro=new WxResponseObject();
		wro.setCode(SystemConstant.OPERATE_SUCCESS);
		wro.setMsg("操作成功");
		wro.setObj(obj);
		return wro;
	}
	
	public static WxResponseObject error(String error,Object obj) {
		WxResponseObject wro=new WxResponseObject();
		wro.setCode(SystemConstant.OPERATE_ERROR);
		wro.setMsg(error);
		wro.setObj(obj);
		return wro;
	}
	
	private static String createShareSign(String token,String rand,String timestamp,String url)  {
		  try{
			  String jsapiUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
			  jsapiUrl = jsapiUrl.replace("ACCESS_TOKEN", token);
			  
			  String jsonObject = new HttpRequestor().doGet(jsapiUrl);//调用jsapi接口获取jsapi_ticket
			  Map map=JsonUtil.jsonToObject(jsonObject, HashMap.class);
			  String jsapiTicket = (String) map.get("ticket");
			
			  String string1="";
			   String signature="";
			        //System.out.println("SHA1签名 :"+str);
			   string1 = "jsapi_ticket=" + jsapiTicket +
		                 "&noncestr=" + rand +
		                 "&timestamp=" + timestamp +
		                 "&url=" + url;
		       System.out.println(string1);
	           MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	           crypt.reset();
	           crypt.update(string1.getBytes("UTF-8"));
	           signature = byteToHex(crypt.digest());
		       
		       System.out.println("签名1:"+signature);
		       return signature;
		  }catch (Exception e) {
	          throw new BusinessException("微信签名失败");
	      }
	 }
	
	 private static String byteToHex(final byte[] hash) {
	        Formatter formatter = new Formatter();
	        for (byte b : hash)
	        {
	            formatter.format("%02x", b);
	        }
	        String result = formatter.toString();
	        formatter.close();
	        return result;
    }
	 
	 public static Map<String,Object> getWxSignature() {
		CacheService cacheService=(CacheService) SystemContext.getBean("cacheService");
		Map<String,Object> map=(Map<String, Object>) cacheService.get("token_wx");
		String rand =  UUID.randomUUID().toString();  //随机字符串
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		String url="http://192.168.0.228:8080/wxz";
		if(null==map||map.isEmpty()) {
			setWxToken(rand, timestamp, url);
			map=(Map<String, Object>) cacheService.get("token_wx");
		}else {
			long old=(Long) map.get("time");
			if((System.currentTimeMillis()-old)/1000>6000) {
				setWxToken(rand, timestamp, url);
				map=(Map<String, Object>) cacheService.get("token_wx");
			}
		}
		return map;
	}
	 
	 private static void setWxToken(String rand,String timestamp,String url) {
		CacheService cacheService=(CacheService) SystemContext.getBean("cacheService");
		try {
			Map<String,Object> map=new HashMap<String, Object>();
			String str=new HttpRequestor().doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APP_ID+"&secret="+APP_SECRET);
			Map temp=JsonUtil.jsonToObject(str, HashMap.class);
			String token=(String) temp.get("access_token");
			String signature=createShareSign(token, rand, timestamp, url);
			map=new HashMap<String, Object>();
			map.put("time", System.currentTimeMillis());
			map.put("token", temp.get("access_token"));
			map.put("rand", rand);
			map.put("timestamp", timestamp);
			map.put("signature", signature);
			map.put("appId", WxUtil.APP_ID);
			map.put("url", url);
			cacheService.put("token_wx", map);
		} catch (Exception e) {
			throw new BusinessException("获取微信认证失败！");
		}
	}
}
