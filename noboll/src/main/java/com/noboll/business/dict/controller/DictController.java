package com.noboll.business.dict.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.dict.entity.Dict;
import com.noboll.business.dict.entity.DictType;
import com.noboll.business.dict.service.DictService;
import com.noboll.business.dict.service.DictTypeService;
import com.noboll.business.user.entity.User;
import com.noboll.context.SystemContext;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.cache.service.CacheService;
import com.noboll.core.util.StringUtil;
import com.noboll.util.InitUtil;

/**
 * 
 * @ClassName: DictController
 * @Description: 数据字典控制器
 * @author kent.wang@noboll.com.cn
 * @date 2015年5月2日 上午8:17:46
 *
 */
@Controller
@RequestMapping("/business/dict")
public class DictController extends BaseController<Dict> {

	@Resource
	private DictService dictService;
	
	@Resource
	private DictTypeService dictTypeService;
	
	@Resource
	private CacheService cacheService;

	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListDict(HttpServletRequest request,Model model,String typeId) {
		model.addAttribute("typeId", typeId);
		return "business/dict/dict_list";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listDict(HttpServletRequest request,Model model,String typeId) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		if(!StringUtil.isEmpty(typeId)) {
			queryParam.addParam("typeId", typeId);
		}
		Page<Dict> page = InitUtil.initPage(request);
		page = dictService.getPageList("com.noboll.business.dict.dao.DictDao.getList", queryParam,
				page);
		return page;
	}

	// 跳转到新增页面
	@RequestMapping("/toAdd")
	public String toAddDict(Model model,String typeId) {
		if(!StringUtil.isEmpty(typeId)) {
			DictType type=dictTypeService.getEntity(typeId);
			int maxSeq=dictService.getMaxSeqByTypeId(typeId);
			model.addAttribute("type", type);
			model.addAttribute("seq", maxSeq+10);
		}
		return "business/dict/dict_add";
	}

	// 异步返回操作信息
	@RequestMapping("/add")
	@ResponseBody
	public Object addDict(Dict dictionary) {
		dictService.saveDict(dictionary);
		return InitUtil.sucessMessage("新增成功");
	}

	// 跳转到修改页面
	@RequestMapping("/toEdit")
	public String toEditDict(Model model, String id) {
		Dict dictionary = dictService.getEntity(id);
		model.addAttribute("dictionary", dictionary);
		return "business/dict/dict_edit";
	}

	// 异步返回操作信息
	@RequestMapping("/edit")
	@ResponseBody
	public Object editDict(Dict dictionary) {
		dictService.updateDict(dictionary);
		return InitUtil.sucessMessage("修改成功");
	}

	// 异步返回操作信息
	@RequestMapping("/remove")
	@ResponseBody
	public Object removeDictType(String id) {
		dictService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}

	// 跳转到列表页面
	@RequestMapping("/toChoose")
	public String toDictChoose(Model model, String parentCode,
			String type) {
		if (StringUtil.isEmpty(type)) {
			type = "0";
		}
		if (!StringUtil.isEmpty(parentCode)) {
			model.addAttribute("parentCode", parentCode);
		}

		model.addAttribute("type", type);
		return "business/dict/dict_choose";
	}

	// 获取选择器数据
	@RequestMapping(value = "/choose", method = RequestMethod.POST)
	@ResponseBody
	public Object choose(HttpServletRequest request,String parentCode) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		if (!StringUtil.isEmpty(parentCode)) {
			queryParam.getMap().put("parentCode", parentCode);
		}

		Page<Dict> page = InitUtil.initPage(request);
		page = dictService.getPageList("com.noboll.business.dict.dao.DictDao.getList",queryParam, page);
		return page;
	}
	
	// 根据字典类型code查询出它的所有数据字典项
	@RequestMapping("/queryAllDict")
	@ResponseBody
	public Object queryAllDict() {
		Map<String,Object> map=new ConcurrentHashMap<String, Object>();
		Map<String, List<Dict>> temp=new HashMap<String, List<Dict>>();
		
		List<Dict> list=dictService.getAllEntity(null);
		for(Dict sd:list) {
			if(!temp.containsKey(sd.getTypeCode())) {//不存在就继续往map放，已经存在了以属性配置文件中的数据字典为准
				temp.put(sd.getTypeCode(), new ArrayList<Dict>());
			}
			temp.get(sd.getTypeCode()).add(sd);
			
		}
		User user=(User) new SystemContext().getLoginUser();
		if(user!=null)
			map.put("allAccessUrl", user.getMenus());
		map.put("allDict", temp);
		return map;
	}
	
	// 根据字典类型code查询出它的所有数据字典项
	@RequestMapping("/queryByTypeCode")
	@ResponseBody
	public Object getSysDictionaryByTypeCode(String typeCode) {
		List<Dict> list = dictService.queryByTypeCode(typeCode);
		return list;
	}
}
