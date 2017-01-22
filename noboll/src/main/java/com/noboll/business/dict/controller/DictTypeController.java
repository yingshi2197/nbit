package com.noboll.business.dict.controller;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.dict.entity.DictType;
import com.noboll.business.dict.service.DictTypeService;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.util.InitUtil;


@Controller
@RequestMapping("/business/dictType")
public class DictTypeController extends BaseController<DictType> {

	@Resource
	private DictTypeService dictTypeService;
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListDictType() {
		return "business/dict/dictType_list";
	}
	
	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listDictType(HttpServletRequest request) {
		QueryParam queryParam=InitUtil.initQueryParam(request);
    	Page<DictType> page=InitUtil.initPage(request);
    	page=dictTypeService.getPageList("com.noboll.business.dict.dao.DictTypeDao.getList", queryParam, page);
        return page;
	}
	
	// 跳转到新增页面
	@RequestMapping("/toAdd")
	public String toAddDictType() {
		return "business/dict/dictType_add";
	}
	
	// 异步返回操作信息
	@RequestMapping("/add")
	@ResponseBody
	public Object addDictType(DictType dictType) {
		dictTypeService.saveDictType(dictType);
		return InitUtil.sucessMessage("新增成功");
	}
	
	// 跳转到修改页面
	@RequestMapping("/toEdit")
	public String toEditDictType(Model model,String id) {
		DictType dictType=dictTypeService.getEntity(id);
		model.addAttribute("dictType", dictType);
		return "business/dict/dictType_edit";
	}
	
	
	// 异步返回操作信息
	@RequestMapping("/edit")
	@ResponseBody
	public Object editDictType(DictType dictType) {
		dictTypeService.updateDictType(dictType);
		return InitUtil.sucessMessage("修改成功");
	}
	
	// 异步返回操作信息
	@RequestMapping("/remove")
	@ResponseBody
	public Object removeDictType(String id) {
		dictTypeService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}
	
	
	// 跳转到列表页面
		@RequestMapping("/toChoose")
		public String toChooseDictType() {
			return "business/dict/dictType_choose";
		}
		
		// 异步返回json数据
		@RequestMapping("/choose")
		@ResponseBody
		public Object chooseDictType(HttpServletRequest request) {
			QueryParam queryParam=InitUtil.initQueryParam(request);
	    	Page<DictType> page=InitUtil.initPage(request);
	    	page=dictTypeService.getPageList("com.noboll.business.dict.dao.DictTypeDao.getList", queryParam, page);
	        return page;
		}
}
