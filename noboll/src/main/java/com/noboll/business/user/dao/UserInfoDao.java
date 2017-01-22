package com.noboll.business.user.dao;

import com.noboll.business.user.entity.UserInfo;
import com.noboll.core.base.dao.BaseDao;

public interface UserInfoDao extends BaseDao<UserInfo>{

	/**
	 * 通过登录账号查找用户
	 */
	public UserInfo getByLoginId(String loginId);

	
}
