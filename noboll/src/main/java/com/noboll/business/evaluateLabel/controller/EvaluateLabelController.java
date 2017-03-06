package com.noboll.business.evaluateLabel.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.evaluateLabel.constant.EvaluateLabelConstant;
import com.noboll.business.evaluateLabel.entity.EvaluateLabel;
import com.noboll.business.evaluateLabel.service.EvaluateLabelService;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/business/evaluateLabel")
public class EvaluateLabelController extends BaseController<EvaluateLabel> {

	@Resource
	private EvaluateLabelService evaluateLabelService;
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListEvaluateLabel(HttpServletRequest request,Model model) {
		return "business/evaluateLabel/evaluateLabel_list";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listEvaluateLabel(HttpServletRequest request,Model model) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<EvaluateLabel> page = InitUtil.initPage(request);
		page = evaluateLabelService.getPageList("com.noboll.business.evaluateLabel.dao.EvaluateLabelDao.getList", queryParam,
				page);
		return page;
	}

	// 跳转到新增页面
	@RequestMapping("/toAdd")
	public String toAddEvaluateLabel(Model model,String type) {
		return "business/evaluateLabel/evaluateLabel_add";
	}

	// 异步返回操作信息
	@RequestMapping("/add")
	@ResponseBody
	public Object addEvaluateLabel(EvaluateLabel evaluateLabel) {
		evaluateLabelService.saveEntity(evaluateLabel);
		return InitUtil.sucessMessage("新增成功");
	}

	// 跳转到修改页面
	@RequestMapping("/toEdit")
	public String toEditEvaluateLabel(Model model, String id) {
		EvaluateLabel evaluateLabel = evaluateLabelService.getEntity(id);
		model.addAttribute("evaluateLabel", evaluateLabel);
		return "business/evaluateLabel/evaluateLabel_edit";
	}

	// 异步返回操作信息
	@RequestMapping("/edit")
	@ResponseBody
	public Object editEvaluateLabel(EvaluateLabel evaluateLabel) {
		evaluateLabelService.updateEntity(evaluateLabel);
		return InitUtil.sucessMessage("修改成功");
	}
	
	// 异步返回操作信息
	@RequestMapping("/remove")
	@ResponseBody
	public Object removeEvaluateLabel(String id) {
		evaluateLabelService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}
	
	// 获取客户评价标签选择器数据
	@RequestMapping(value = "/chooseCustomer", method = RequestMethod.POST)
	@ResponseBody
	public Object chooseCustomer(HttpServletRequest request,int limit) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		queryParam.addParam("limit", limit);
		queryParam.addParam("type", EvaluateLabelConstant.TYPE_CUSTOMER);
		List<EvaluateLabel> list = evaluateLabelService.getChooseList(queryParam.getMap());
		return list;
	}
	
	// 获取简历评价标签选择器数据
	@RequestMapping(value = "/chooseResume", method = RequestMethod.POST)
	@ResponseBody
	public Object chooseResume(HttpServletRequest request,int limit) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		queryParam.addParam("limit", limit);
		queryParam.addParam("type", EvaluateLabelConstant.TYPE_APPLICANT);
		List<EvaluateLabel> list = evaluateLabelService.getChooseList(queryParam.getMap());
		return list;
	}

}
