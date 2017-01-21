package com.noboll.business.evaluate.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.deliver.entity.Deliver;
import com.noboll.business.deliver.service.DeliverService;
import com.noboll.business.evaluate.entity.Evaluate;
import com.noboll.business.evaluate.service.EvaluateService;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.util.StringUtil;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/business/evaluate")
public class EvaluateController extends BaseController<Evaluate> {

	@Resource
	private EvaluateService evaluateService;
	@Resource
	private DeliverService deliverService;
	
	// 跳转到评价客户新增页面,入口在“我的投递”页面
	@RequestMapping("/toCEvalute")
	public String toCEvalute(Model model,String id) {
		Deliver deliver = deliverService.getEntity(id);
		model.addAttribute("deliver", deliver);
		Evaluate evaluate = evaluateService.getByResumeAndCustomerId(deliver.getResumeId(), deliver.getCustomerId());
		if (null != evaluate && !StringUtil.isEmpty(evaluate.getCEvaluate()) && !StringUtil.isEmpty(evaluate.getCEvaluateStar())) {
			model.addAttribute("evaluate", evaluate);
			return "business/evaluate/cevaluate_view";
		}else if(null == evaluate)
			evaluate = new Evaluate();
		evaluate.setResumeId(deliver.getResumeId());
		evaluate.setCustomerId(deliver.getCustomerId());
		model.addAttribute("evaluate", evaluate);
		return "business/evaluate/cevaluate_add";
	}

	// 评价客户
	@RequestMapping("/cEvalute")
	@ResponseBody
	public Object cEvalute(Evaluate evaluate) {
		evaluateService.saveEvaluate4Customer(evaluate);
		return InitUtil.sucessMessage("评价成功");
	}

	// 跳转到评价简历页面,入口在“投递管理-客户”页面
	@RequestMapping("/toREvalute")
	public String toREvalute(Model model, String id) {
		Deliver deliver = deliverService.getEntity(id);
		model.addAttribute("deliver", deliver);
		Evaluate evaluate = evaluateService.getByResumeAndCustomerId(deliver.getResumeId(), deliver.getCustomerId());
		if (null != evaluate && !StringUtil.isEmpty(evaluate.getREvaluate()) && !StringUtil.isEmpty(evaluate.getREvaluateStar())) {
			model.addAttribute("evaluate", evaluate);
			return "business/evaluate/revaluate_view";
		}else if(null == evaluate)
			evaluate = new Evaluate();
		evaluate.setResumeId(deliver.getResumeId());
		evaluate.setCustomerId(deliver.getCustomerId());
		model.addAttribute("evaluate", evaluate);
		return "business/evaluate/revaluate_add";
	}

	// 评价简历
	@RequestMapping("/rEvalute")
	@ResponseBody
	public Object rEvalute(Evaluate evaluate) {
		evaluateService.saveEvaluate4Resume(evaluate);
		return InitUtil.sucessMessage("评价成功");
	}
	
	// 异步返回操作信息
	@RequestMapping("/remove")
	@ResponseBody
	public Object removeEvaluate(String id) {
		evaluateService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}
	
	// 评价管理-管理员列表
	@RequestMapping("/toManageList")
	public String toManageList(HttpServletRequest request,Model model) {
		return "business/evaluate/evaluate_manage_list";
	}

	// 评价管理-管理员列表数据
	@RequestMapping("/manageList")
	@ResponseBody
	public Object manageList(HttpServletRequest request,Model model) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Evaluate> page = InitUtil.initPage(request);
		page = evaluateService.getPageList("com.noboll.business.evaluate.dao.EvaluateDao.getList", queryParam,
				page);
		return page;
	}
	
	// 跳转到详情页面
	@RequestMapping("/toView")
	public String toView(Model model,String id) {
		// 查找评价记录
		Evaluate evaluate = evaluateService.getEntity(id);
		model.addAttribute("evaluate", evaluate);
		return "business/evaluate/evaluate_view";
	}
	
}
