package com.noboll.business.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.customer.constant.CustomerConstant;
import com.noboll.business.customer.entity.Customer;
import com.noboll.business.customer.service.CustomerService;
import com.noboll.business.user.constant.UserInfoConstant;
import com.noboll.business.user.dao.UserInfoDao;
import com.noboll.business.user.entity.UserInfo;
import com.noboll.business.user.service.UserInfoService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.core.exception.BusinessException;
import com.noboll.core.util.Md5Util;
import com.noboll.core.util.StringUtil;

@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo>
		implements UserInfoService {

	@Resource
	private UserInfoDao userInfoDao;
	@Resource
	private CustomerService customerService;
	
	@Override
	public BaseDao<UserInfo> getBaseDao() {
		return userInfoDao;
	}

	@Override
	public void saveUserInfo(UserInfo userInfo) {
		// 登录账号是否已经存在
		UserInfo loginUser = userInfoDao.getByLoginId(userInfo.getLoginId());
		if (null != loginUser) 
			throw new BusinessException("该登录账号已经存在，换个账号试试！");
		// 密码MD5加密处理
		String password = userInfo.getPassword();
		userInfo.setPassword(Md5Util.MD5(password));
		// 如果角色是客户，那么要验证是否选择了客户，如果角色是求职者，需要把客户id清空
		String role = userInfo.getRole();
		if (StringUtil.isEmpty(role)) 
			throw new BusinessException("请选择用户角色！");
		if (UserInfoConstant.ROLE_CUSTOMER.equals(role)) {
			String customerId = userInfo.getCustomerId();
			Customer customer = customerService.getEntity(customerId);
			if (null == customer || CustomerConstant.STATUS_DISABLED.equals(customer.getStatus())) 
				throw new BusinessException("客户不存在或已经禁用！");
		}else if(UserInfoConstant.ROLE_APPLICANT.equals(role) || UserInfoConstant.ROLE_ADMIN.equals(role)){
			userInfo.setCustomerId(null);
		}else{
			throw new BusinessException("角色不合法！");
		}
		this.saveEntity(userInfo);
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		// 登录账号是否已经存在
		UserInfo loginUser = userInfoDao.getByLoginId(userInfo.getLoginId());
		if (null != loginUser && !userInfo.getId().equals(loginUser.getId())) 
			throw new BusinessException("该登录账号已经存在，换个账号试试！");
		// 如果角色是客户，那么要验证是否选择了客户，如果角色是求职者，需要把客户id清空
		String role = userInfo.getRole();
		if (StringUtil.isEmpty(role)) 
			throw new BusinessException("请选择用户角色！");
		if (UserInfoConstant.ROLE_CUSTOMER.equals(role)) {
			String customerId = userInfo.getCustomerId();
			Customer customer = customerService.getEntity(customerId);
			if (null == customer || CustomerConstant.STATUS_DISABLED.equals(customer.getStatus())) 
				throw new BusinessException("客户不存在或已经禁用！");
		}else if(UserInfoConstant.ROLE_APPLICANT.equals(role) || UserInfoConstant.ROLE_ADMIN.equals(role)){
			userInfo.setCustomerId(null);
		}else{
			throw new BusinessException("角色不合法！");
		}
		this.updateEntity(userInfo);
	}

	/**
	 * 通过登录账号查找用户
	 */
	@Override
	public UserInfo getByLoginId(String loginId){
		return userInfoDao.getByLoginId(loginId);
	}

}
