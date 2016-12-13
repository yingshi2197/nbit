package com.noboll.business.deliver.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.deliver.entity.Deliver;
import com.noboll.business.deliver.service.DeliverService;
import com.noboll.business.requirement.entity.Requirement;
import com.noboll.business.requirement.service.RequirementService;
import com.noboll.context.SystemContext;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/business/deliver")
public class DeliverController extends BaseController<Deliver> {

	@Resource
	private DeliverService deliverService;
	@Resource
	private RequirementService requirementService;
	
	// 投递管理-我的投递列表
	@RequestMapping("/toMyList")
	public String toListDeliver(HttpServletRequest request,Model model) {
		return "business/deliver/deliver_my_list";
	}

	// 投递管理-我的投递列表数据
	@RequestMapping("/myList")
	@ResponseBody
	public Object listDeliver(HttpServletRequest request,Model model) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Deliver> page = InitUtil.initPage(request);
		queryParam.addParam("userId", SystemContext.getLoginUser().getId());
		page = deliverService.getPageList("com.noboll.business.deliver.dao.DeliverDao.getList", queryParam,
				page);
		return page;
	}

	// 跳转到新增页面
	@RequestMapping("/toAdd")
	public String toAddDeliver(Model model,String id) {
		Requirement requirement = requirementService.getEntity(id);
		model.addAttribute("requirement", requirement);
		return "business/deliver/deliver_add";
	}

	// 异步返回操作信息
	@RequestMapping("/add")
	@ResponseBody
	public Object addDeliver(Deliver deliver) {
		deliverService.saveDeliver(deliver);
		return InitUtil.sucessMessage("新增成功");
	}

	// 跳转到修改页面
	@RequestMapping("/toEdit")
	public String toEditDeliver(Model model, String id) {
		Deliver deliver = deliverService.getEntity(id);
		model.addAttribute("deliver", deliver);
		return "business/deliver/deliver_edit";
	}

	// 异步返回操作信息
	@RequestMapping("/edit")
	@ResponseBody
	public Object editDeliver(Deliver deliver) {
		deliverService.updateEntity(deliver);
		return InitUtil.sucessMessage("修改成功");
	}
	
	// 异步返回操作信息
	@RequestMapping("/remove")
	@ResponseBody
	public Object removeDeliver(String id) {
		deliverService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}
	
}
