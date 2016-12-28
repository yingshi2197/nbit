package com.noboll.business.position.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.dict.entity.Dict;
import com.noboll.business.dict.service.DictService;
import com.noboll.business.position.entity.Position;
import com.noboll.business.position.service.PositionService;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.util.StringUtil;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/business/position")
public class PositionController extends BaseController<Position> {

	@Resource
	private PositionService positionService;
	@Resource
	private DictService dictService;
	
	// 跳转到列表页面
	@RequestMapping("/toTypeList")
	public String toTypeListPosition(HttpServletRequest request,Model model) {
		return "business/position/position_type_list";
	}

	// 异步返回json数据
	@RequestMapping("/typeList")
	@ResponseBody
	public Object typeListPosition(HttpServletRequest request,Model model) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Dict> page = InitUtil.initPage(request);
		queryParam.addParam("typeCode", "position_type");
		page = dictService.getPageList("com.noboll.business.dict.dao.DictDao.queryByTypeCode", queryParam,
				page);
		return page;
	}
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListPosition(HttpServletRequest request,Model model,String type) {
		model.addAttribute("type", type);
		return "business/position/position_list";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listPosition(HttpServletRequest request,Model model,String type) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Position> page = InitUtil.initPage(request);
		queryParam.addParam("type", type);
		page = positionService.getPageList("com.noboll.business.position.dao.PositionDao.getList", queryParam,
				page);
		return page;
	}

	// 跳转到新增页面
	@RequestMapping("/toAdd")
	public String toAddPosition(Model model,String type) {
		Dict typeDict = dictService.getEntity(type);
		model.addAttribute("typeDict", typeDict);
		return "business/position/position_add";
	}

	// 异步返回操作信息
	@RequestMapping("/add")
	@ResponseBody
	public Object addPosition(Position position) {
		positionService.saveEntity(position);
		return InitUtil.sucessMessage("新增成功");
	}

	// 跳转到修改页面
	@RequestMapping("/toEdit")
	public String toEditPosition(Model model, String id) {
		Position position = positionService.getEntity(id);
		model.addAttribute("position", position);
		return "business/position/position_edit";
	}

	// 异步返回操作信息
	@RequestMapping("/edit")
	@ResponseBody
	public Object editPosition(Position position) {
		positionService.updateEntity(position);
		return InitUtil.sucessMessage("修改成功");
	}
	
	// 异步返回操作信息
	@RequestMapping("/remove")
	@ResponseBody
	public Object removePosition(String id) {
		positionService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}
	
	// 跳转到列表页面
	@RequestMapping("/toChoose")
	public String toDictChoose(Model model, String type) {
		if (StringUtil.isEmpty(type)) 
			type = "0";
		model.addAttribute("type", type);
		return "business/position/position_choose";
	}

	// 获取选择器数据
	@RequestMapping(value = "/choose", method = RequestMethod.POST)
	@ResponseBody
	public Object choose(HttpServletRequest request,String parentCode) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Position> page = InitUtil.initPage(request);
		page = positionService.getPageList("com.noboll.business.position.dao.PositionDao.getList",queryParam, page);
		return page;
	}

}
