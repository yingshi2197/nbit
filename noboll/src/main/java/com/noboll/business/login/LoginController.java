package com.noboll.business.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.user.constant.UserInfoConstant;
import com.noboll.business.user.entity.RoleMenu;
import com.noboll.business.user.entity.User;
import com.noboll.business.user.entity.UserInfo;
import com.noboll.business.user.service.UserInfoService;
import com.noboll.context.SystemContext;
import com.noboll.core.cache.service.CacheService;
import com.noboll.core.util.Md5Util;
import com.noboll.core.util.StringUtil;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Resource
	private CacheService cacheService;
	@Resource
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	@ResponseBody
	public Object login(String loginId, String password,HttpSession session,HttpServletRequest request) {
		if (StringUtil.isEmpty(loginId) || StringUtil.isEmpty(password)) {
			return InitUtil.errorMessage("账号和密码不能为空！");
		}
		UserInfo userInfo = userInfoService.getByLoginId(loginId);
		if(null != userInfo && loginId.equals(userInfo.getLoginId()) && Md5Util.MD5(password).equals(userInfo.getPassword())) {
			 try {
				List<RoleMenu> roleMenus=(List<RoleMenu>) cacheService.get("roleMenu");

			    List<String> menus=new ArrayList<String>();
			    String[] temp=userInfo.getRole().split(",");
			    List<String> roles=Arrays.asList(temp);
			    String formal="0";
			    for(RoleMenu rm:roleMenus) {
				   if(roles.contains(rm.getRole())) {
					   formal=rm.getFormal();
					   menus.addAll(rm.getAllMenu());
					   // 存在to的链接，那么就将去掉to的地址作为相同的角色权限
					   for (String am : rm.getAllMenu()) {
						   if (!StringUtil.isEmpty(am) && am.indexOf("/to") > 0) {
								// 截取"/toXxx"之前的链接
								String left = am.substring(0,am.indexOf("/to"));
								// 截取"/toXxx"之后的链接
								String rightBefore = am.substring(am.indexOf("/to"), am.length());
								// 去掉to并把to后的字母第一个字母小写
								String rightNoTo = rightBefore.replaceAll("/to", "/");
								String right="/"+StringUtil.toLowerCaseFirst(rightNoTo.substring(1));
								String urlNoTo = left + right;
								menus.add(urlNoTo);
							}
					   }
				   }
			    }
			    User user = new User(); 
			    user.setMenus(menus);
			    user.setFormal(formal);
			    user.setCustomerId(userInfo.getCustomerId());
			    user.setId(userInfo.getId());
			    user.setName(userInfo.getName());
			    user.setLoginId(userInfo.getId());
			    user.setRole(userInfo.getRole());
			    SystemContext.setLoginUser(user);
				return InitUtil.sucessMessage("登录成功！");
			} catch (Exception e) {
				SystemContext.setLoginUser(null);
				return InitUtil.errorMessage("登录失败！");
			}  
		}
		return InitUtil.errorMessage("账号或密码错误！");
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		SystemContext.setLoginUser(null);
		return "business/login/login";
	}
	
	@RequestMapping(value = "/toLogin")
	public String toLogin() {
		return "business/login/login";
	}
	
	@RequestMapping(value = "/home")
	public String home(Model model) {
		String initMenu = "requirementSearchHref";
		if (UserInfoConstant.ROLE_CUSTOMER.equals(((User)SystemContext.getLoginUser()).getRole()))
			initMenu = "resumeSearchHref";
		model.addAttribute("initMenu", initMenu);
		return "business/login/home";
	}
	
}
