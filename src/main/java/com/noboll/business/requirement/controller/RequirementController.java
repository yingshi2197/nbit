package com.noboll.business.requirement.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.requirement.constant.RequirementConstant;
import com.noboll.business.requirement.entity.Requirement;
import com.noboll.business.requirement.service.RequirementService;
import com.noboll.context.SystemContext;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/business/requirement")
public class RequirementController extends BaseController<Requirement> {

	@Resource
	private RequirementService requirementService;
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListRequirement(HttpServletRequest request,Model model) {
		return "business/requirement/requirement_list";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listRequirement(HttpServletRequest request,Model model) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Requirement> page = InitUtil.initPage(request);
		page = requirementService.getPageList("com.noboll.business.requirement.dao.RequirementDao.getList", queryParam,
				page);
		return page;
	}

	// 跳转到新增页面
	@RequestMapping("/toAdd")
	public String toAddRequirement(Model model) {
		return "business/requirement/requirement_add";
	}

	// 异步返回操作信息
	@RequestMapping("/add")
	@ResponseBody
	public Object addRequirement(Requirement requirement) {
		requirementService.saveRequirement(requirement);
		return InitUtil.sucessMessage("新增成功");
	}

	// 跳转到修改页面
	@RequestMapping("/toEdit")
	public String toEditRequirement(Model model, String id) {
		Requirement requirement = requirementService.getEntity(id);
		model.addAttribute("requirement", requirement);
		return "business/requirement/requirement_edit";
	}

	// 异步返回操作信息
	@RequestMapping("/edit")
	@ResponseBody
	public Object editRequirement(Requirement requirement) {
		requirementService.updateRequirement(requirement);
		return InitUtil.sucessMessage("修改成功");
	}
	
	// 异步返回操作信息
	@RequestMapping("/remove")
	@ResponseBody
	public Object removeRequirement(String id) {
		requirementService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}
	
	// 跳转到需求搜索页面
	@RequestMapping("/toSearchList")
	public String toSearchListRequirement(HttpServletRequest request,Model model) {
		return "business/requirement/requirement_search_list";
	}

	// 异步返回json数据
	@RequestMapping("/searchList")
	@ResponseBody
	public Object searchListRequirement(HttpServletRequest request,Model model) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Requirement> page = InitUtil.initPage(request);
		queryParam.addParam("status", RequirementConstant.REQUIREMENT_STATUS_FB);
		queryParam.addParam("userId", SystemContext.getLoginUser().getId());
		page = requirementService.getPageList("com.noboll.business.requirement.dao.RequirementDao.getSearchList", queryParam,
				page);
		return page;
	}
	
	// 异步返回操作信息
	@RequestMapping("/publish")
	@ResponseBody
	public Object publish(String id) {
		requirementService.publish(id);
		return InitUtil.sucessMessage("操作成功");
	}
	
	// 异步返回操作信息
	@RequestMapping("/finish")
	@ResponseBody
	public Object finish(String id) {
		requirementService.finish(id);
		return InitUtil.sucessMessage("操作成功");
	}
}
