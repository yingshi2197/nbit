package com.noboll.business.label.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.label.constant.LabelConstant;
import com.noboll.business.label.entity.Label;
import com.noboll.business.label.service.LabelService;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/business/label")
public class LabelController extends BaseController<Label> {

	@Resource
	private LabelService labelService;
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListLabel(HttpServletRequest request,Model model) {
		return "business/label/label_list";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listLabel(HttpServletRequest request,Model model) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Label> page = InitUtil.initPage(request);
		page = labelService.getPageList("com.noboll.business.label.dao.LabelDao.getList", queryParam,
				page);
		return page;
	}

	// 跳转到新增页面
	@RequestMapping("/toAdd")
	public String toAddLabel(Model model,String type) {
		return "business/label/label_add";
	}

	// 异步返回操作信息
	@RequestMapping("/add")
	@ResponseBody
	public Object addLabel(Label label) {
		labelService.saveEntity(label);
		return InitUtil.sucessMessage("新增成功");
	}

	// 跳转到修改页面
	@RequestMapping("/toEdit")
	public String toEditLabel(Model model, String id) {
		Label label = labelService.getEntity(id);
		model.addAttribute("label", label);
		return "business/label/label_edit";
	}

	// 异步返回操作信息
	@RequestMapping("/edit")
	@ResponseBody
	public Object editLabel(Label label) {
		labelService.updateEntity(label);
		return InitUtil.sucessMessage("修改成功");
	}
	
	// 异步返回操作信息
	@RequestMapping("/remove")
	@ResponseBody
	public Object removeLabel(String id) {
		labelService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}
	
	// 获取客户评价标签选择器数据
	@RequestMapping(value = "/chooseCustomer", method = RequestMethod.POST)
	@ResponseBody
	public Object chooseCustomer(HttpServletRequest request,int limit) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		queryParam.addParam("limit", limit);
		queryParam.addParam("type", LabelConstant.TYPE_CUSTOMER);
		List<Label> list = labelService.getChooseList(queryParam.getMap());
		return list;
	}
	
	// 获取简历评价标签选择器数据
	@RequestMapping(value = "/chooseResume", method = RequestMethod.POST)
	@ResponseBody
	public Object chooseResume(HttpServletRequest request,int limit) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		queryParam.addParam("limit", limit);
		queryParam.addParam("type", LabelConstant.TYPE_APPLICANT);
		List<Label> list = labelService.getChooseList(queryParam.getMap());
		return list;
	}

}
