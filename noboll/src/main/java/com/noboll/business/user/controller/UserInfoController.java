package com.noboll.business.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.user.entity.UserInfo;
import com.noboll.business.user.service.UserInfoService;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.util.StringUtil;
import com.noboll.util.InitUtil;

/**
 * 
 * @ClassName: UserInfoController
 * @Description: 用户控制器
 * @author weicb
 * @date 2016年12月20日 下午20:18:46
 *
 */
@Controller
@RequestMapping("/business/userInfo")
public class UserInfoController extends BaseController<UserInfo> {

	@Resource
	private UserInfoService userInfoService;
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListUserInfo(HttpServletRequest request,Model model) {
		return "business/user/userInfo_list";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listUserInfo(HttpServletRequest request,Model modeld) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<UserInfo> page = InitUtil.initPage(request);
		page = userInfoService.getPageList("com.noboll.business.user.dao.UserInfoDao.getList", queryParam,
				page);
		return page;
	}

	// 跳转到新增页面
	@RequestMapping("/toAdd")
	public String toAddUserInfo(Model model,String typeId) {
		return "business/user/userInfo_add";
	}

	// 异步返回操作信息
	@RequestMapping("/add")
	@ResponseBody
	public Object addUserInfo(UserInfo userInfo) {
		userInfoService.saveUserInfo(userInfo);
		return InitUtil.sucessMessage("新增成功");
	}

	// 跳转到修改页面
	@RequestMapping("/toEdit")
	public String toEditUserInfo(Model model, String id) {
		UserInfo userInfo = userInfoService.getEntity(id);
		model.addAttribute("userInfo", userInfo);
		return "business/user/userInfo_edit";
	}

	// 异步返回操作信息
	@RequestMapping("/edit")
	@ResponseBody
	public Object editUserInfo(UserInfo userInfo) {
		userInfoService.updateUserInfo(userInfo);
		return InitUtil.sucessMessage("修改成功");
	}

	// 删除
	@RequestMapping("/remove")
	@ResponseBody
	public Object removeUserInfoType(String id) {
		userInfoService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}
	
	// 跳转到列表页面
	@RequestMapping("/toChoose")
	public String toUserInfoChoose(Model model, String parentCode,
			String type) {
		if (StringUtil.isEmpty(type)) {
			type = "0";
		}

		model.addAttribute("type", type);
		return "business/user/userInfo_choose";
	}

	// 获取选择器数据
	@RequestMapping(value = "/choose", method = RequestMethod.POST)
	@ResponseBody
	public Object choose(HttpServletRequest request,String parentCode) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<UserInfo> page = InitUtil.initPage(request);
		page = userInfoService.getPageList("com.noboll.business.user.dao.UserInfoDao.getList",queryParam, page);
		return page;
	}
	
}
