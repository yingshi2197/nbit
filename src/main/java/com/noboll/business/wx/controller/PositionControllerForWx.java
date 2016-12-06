package com.noboll.business.wx.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.dict.entity.Dict;
import com.noboll.business.dict.service.DictService;
import com.noboll.business.position.entity.Position;
import com.noboll.business.position.service.PositionService;
import com.noboll.business.wx.util.WxUtil;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.util.StringUtil;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/wx/position")
public class PositionControllerForWx extends BaseController<Position> {

	@Resource
	private PositionService positionService;
	
	@Resource
	private DictService dictService;
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListPosition(HttpServletRequest request,Model model,String typeId) {
		List<Dict> address=dictService.queryByTypeCode("address");
		List<Dict> type=dictService.queryByTypeCode("position_type");
		model.addAttribute("address", address);
		model.addAttribute("type", type);
		return "business/wx/index";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listPosition(HttpServletRequest request,Model model,String typeId) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		queryParam.addParam("status", 1);
		Page<Position> page = InitUtil.initPage(request);
		page = positionService.getPageList("com.noboll.business.position.dao.PositionDao.getList", queryParam,
				page);
		return WxUtil.success(page.getRows());
	}

	// 异步返回json数据
	@RequestMapping("/checkPosition")
	@ResponseBody
	public Object checkPosition(String ids) {
		if(StringUtil.isEmpty(ids)) {
			return InitUtil.errorMessage("请选择一个职位！");
		}
		String[] idarr=ids.split(",");
		StringBuffer sb=new StringBuffer("");
		for(int i=0;i<idarr.length;i++) {
			String id=idarr[i];
			Position p=positionService.getEntity(id);
			if(p==null||!"1".equals(p.getStatus())) {
				sb.append("职位{"+i+"}不存在或者已经结束！</br>");
			}
		}
		if(!StringUtil.isEmpty(sb.toString()))
			return InitUtil.errorMessage(sb.toString()); 
		else 
			return InitUtil.sucessMessage(""); 
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
	@RequestMapping("/remove")
	@ResponseBody
	public Object removePosition(String id) {
		positionService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}

}
