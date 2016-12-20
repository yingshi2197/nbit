package com.noboll.business.user.service;


import com.noboll.business.user.entity.UserInfo;
import com.noboll.core.base.service.BaseService;


public interface UserInfoService extends BaseService<UserInfo> {

	/**
	 * 新增用户
	 */
	public void saveUserInfo(UserInfo userInfo);

	/**
	 * 修改用户
	 */
	public void updateUserInfo(UserInfo userInfo);
	
}
