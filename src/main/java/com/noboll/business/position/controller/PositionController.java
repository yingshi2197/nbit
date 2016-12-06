package com.noboll.business.position.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.position.entity.Position;
import com.noboll.business.position.service.PositionService;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/business/position")
public class PositionController extends BaseController<Position> {

	@Resource
	private PositionService positionService;
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListPosition(HttpServletRequest request,Model model,String typeId) {
		model.addAttribute("typeId", typeId);
		return "business/position/position_list";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listPosition(HttpServletRequest request,Model model,String typeId) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Position> page = InitUtil.initPage(request);
		page = positionService.getPageList("com.noboll.business.position.dao.PositionDao.getList", queryParam,
				page);
		return page;
	}

	// 跳转到新增页面
	@RequestMapping("/toAdd")
	public String toAddPosition(Model model) {
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
	@RequestMapping("/publish")
	@ResponseBody
	public Object publish(String id) {
		positionService.publish(id);
		return InitUtil.sucessMessage("发布成功");
	}
	
	// 异步返回操作信息
	@RequestMapping("/finish")
	@ResponseBody
	public Object finish(String id) {
		positionService.finish(id);
		return InitUtil.sucessMessage("操作成功");
	}

	// 异步返回操作信息
	@RequestMapping("/remove")
	@ResponseBody
	public Object removePosition(String id) {
		positionService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}

}
