package com.noboll.business.deliver.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.deliver.entity.Deliver;
import com.noboll.business.deliver.service.DeliverService;
import com.noboll.business.interview.entity.Interview;
import com.noboll.business.interview.service.InterviewService;
import com.noboll.business.requirement.entity.Requirement;
import com.noboll.business.requirement.service.RequirementService;
import com.noboll.business.resume.entity.Resume;
import com.noboll.business.resume.service.ResumeService;
import com.noboll.business.user.entity.User;
import com.noboll.context.SystemContext;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.exception.BusinessException;
import com.noboll.core.util.StringUtil;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/business/deliver")
public class DeliverController extends BaseController<Deliver> {

	@Resource
	private DeliverService deliverService;
	@Resource
	private RequirementService requirementService;
	@Resource
	private InterviewService interviewService;
	@Resource
	private ResumeService resumeService;
	
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
		return InitUtil.sucessMessage("简历投递成功");
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
	
	// 投递管理-管理员列表
	@RequestMapping("/toManageList")
	public String toManageList(HttpServletRequest request,Model model) {
		return "business/deliver/deliver_manage_list";
	}

	// 投递管理-管理员列表数据
	@RequestMapping("/manageList")
	@ResponseBody
	public Object manageList(HttpServletRequest request,Model model) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Deliver> page = InitUtil.initPage(request);
		page = deliverService.getPageList("com.noboll.business.deliver.dao.DeliverDao.getList", queryParam,
				page);
		return page;
	}
	
	// 投递管理-客户列表
	@RequestMapping("/toCustomerList")
	public String toCustomerList(HttpServletRequest request,Model model) {
		return "business/deliver/deliver_customer_list";
	}

	// 投递管理-客户列表数据
	@RequestMapping("/customerList")
	@ResponseBody
	public Object customerList(HttpServletRequest request,Model model) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Deliver> page = InitUtil.initPage(request);
		User user = (User)SystemContext.getLoginUser();
		queryParam.addParam("customerId", user.getCustomerId());
		page = deliverService.getPageList("com.noboll.business.deliver.dao.DeliverDao.getList", queryParam,
				page);
		return page;
	}
	
	// 跳转到详情页面
	@RequestMapping("/toView")
	public String toView(Model model,String id) {
		// 查找投递记录
		Deliver deliver = deliverService.getEntity(id);
		if (null == deliver || StringUtil.isEmpty(deliver.getResumeId())) 
			throw new BusinessException("无投递记录！");
		model.addAttribute("deliver", deliver);
		// 查找电话面试记录
		Interview interviewDh = interviewService.getDhByDeliverId(id);
		model.addAttribute("interviewDh", interviewDh);
		// 查找现场面试记录
		Interview interviewXc = interviewService.getXcByDeliverId(id);
		model.addAttribute("interviewXc", interviewXc);
		// 查找简历信息
		Resume resume = resumeService.getEntity(deliver.getResumeId());
		model.addAttribute("resume", resume);
		// 需求信息
		Requirement requirement = requirementService.getEntity(deliver.getRequirementId());
		model.addAttribute("requirement", requirement);
		// TODO 入职信息
		return "business/deliver/deliver_view";
	}
	
}
