package com.noboll.context;

import java.util.List;

import javax.annotation.Resource;

import com.noboll.business.user.entity.RoleMenu;
import com.noboll.business.user.entity.User;
import com.noboll.core.cache.service.CacheService;
import com.noboll.core.context.BaseContext;
import com.noboll.core.util.XmlUtil;

public class SystemContext extends BaseContext<User> {

	@Resource
	private CacheService cacheService;
	
	public void init() {
		List<RoleMenu> roleMenus=XmlUtil.xmlToList(RoleMenu.class, "roleMenu.xml", "roleMenu");
		cacheService.put("roleMenu", roleMenus);
	}
	
}
