package com.noboll.business.wx.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.wx.util.WxUtil;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.BaseEntity;
import com.noboll.core.cache.service.CacheService;

@Controller
@RequestMapping("/wx")
public class WxController extends BaseController<BaseEntity> {
	
	@Resource
	private CacheService cacheService;
	
	@RequestMapping("/getToken")
	@ResponseBody
	public Object getToken() {
		return WxUtil.getWxSignature();
	}
	
	
	
}
