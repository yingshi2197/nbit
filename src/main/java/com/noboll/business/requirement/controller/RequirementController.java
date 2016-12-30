package com.noboll.business.requirement.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.deliver.entity.Deliver;
import com.noboll.business.dict.constant.DictConstant;
import com.noboll.business.dict.entity.Dict;
import com.noboll.business.dict.entity.QueryBean;
import com.noboll.business.dict.service.DictService;
import com.noboll.business.interview.entity.Interview;
import com.noboll.business.position.entity.Position;
import com.noboll.business.position.service.PositionService;
import com.noboll.business.requirement.constant.RequirementConstant;
import com.noboll.business.requirement.entity.Requirement;
import com.noboll.business.requirement.service.RequirementService;
import com.noboll.business.resume.entity.Resume;
import com.noboll.context.SystemContext;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.exception.BusinessException;
import com.noboll.core.util.StringUtil;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/business/requirement")
public class RequirementController extends BaseController<Requirement> {

	@Resource
	private RequirementService requirementService;
	@Resource
	private DictService dictService;
	@Resource
	private PositionService positionService;
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListRequirement(HttpServletRequest request,Model model) {
		return "business/requirement/requirement_list";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listRequirement(HttpServletRequest request,Model model) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Requirement> page = InitUtil.initPage(request);
		page = requirementService.getPageList("com.noboll.business.requirement.dao.RequirementDao.getList", queryParam,
				page);
		return page;
	}

	// 跳转到新增页面
	@RequestMapping("/toAdd")
	public String toAddRequirement(Model model) {
		return "business/requirement/requirement_add";
	}

	// 异步返回操作信息
	@RequestMapping("/add")
	@ResponseBody
	public Object addRequirement(Requirement requirement) {
		requirementService.saveRequirement(requirement);
		return InitUtil.sucessMessage("新增成功");
	}

	// 跳转到修改页面
	@RequestMapping("/toEdit")
	public String toEditRequirement(Model model, String id) {
		Requirement requirement = requirementService.getEntity(id);
		model.addAttribute("requirement", requirement);
		return "business/requirement/requirement_edit";
	}

	// 异步返回操作信息
	@RequestMapping("/edit")
	@ResponseBody
	public Object editRequirement(Requirement requirement) {
		requirementService.updateRequirement(requirement);
		return InitUtil.sucessMessage("修改成功");
	}
	
	// 异步返回操作信息
	@RequestMapping("/remove")
	@ResponseBody
	public Object removeRequirement(String id) {
		requirementService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}
	
	// 跳转到需求搜索页面
	@RequestMapping("/toSearchList")
	public String toSearchListRequirement(HttpServletRequest request,Model model) {
		// 准备左侧查询条件数据字典
		List<QueryBean> conditionList = new ArrayList<QueryBean>();// 这里为什么不用map，因为map无序，无法控制前端显示顺序
		// 职位类别-职位-级联
		List<Dict> positionTypeList = dictService.queryByTypeCode(DictConstant.DICT_TYPE_CODE_POSITION_TYPE);
		List<Position> positionList = positionService.getAllEntity(new HashMap<String, Object>());// 职位
		Map<String, List<Position>> positionMap = new HashMap<String, List<Position>>();
		for (Position position : positionList) {
			if (positionMap.containsKey(position.getTypeCode()) && null != positionMap.get(position.getTypeCode())) {
				positionMap.get(position.getTypeCode()).add(position);
			}else{
				List<Position> list = new ArrayList<Position>();
				list.add(position);
				positionMap.put(position.getTypeCode(), list);
			}
		}
		for (Dict dict : positionTypeList){
			if (positionMap.containsKey(dict.getCode()) && null != positionMap.get(dict.getCode())){
				QueryBean queryBean = new QueryBean(DictConstant.DICT_TYPE_CODE_POSITION, DictConstant.DICT_TYPE_CODE_POSITION_NAME, positionMap.get(dict.getCode()));
				queryBean.setTypeCode(dict.getCode());
				dict.setChildren(queryBean);
			}
		}
		conditionList.add(new QueryBean(DictConstant.DICT_TYPE_CODE_POSITION_TYPE,DictConstant.DICT_TYPE_CODE_POSITION_TYPE_NAME,positionTypeList));
		// 行业
		List<Dict> industryList = dictService.queryByTypeCode(DictConstant.DICT_TYPE_CODE_INDUSTRY);
		conditionList.add(new QueryBean(DictConstant.DICT_TYPE_CODE_INDUSTRY,DictConstant.DICT_TYPE_CODE_INDUSTRY_NAME,industryList));
		// 性质
		List<Dict> natureList = dictService.queryByTypeCode(DictConstant.DICT_TYPE_CODE_NATURE);
		conditionList.add(new QueryBean(DictConstant.DICT_TYPE_CODE_NATURE,DictConstant.DICT_TYPE_CODE_NATURE_NAME,natureList));
		/*// 薪资
		List<Dict> payList = dictService.queryByTypeCode(DictConstant.DICT_TYPE_CODE_PAY);
		conditionList.add(new QueryBean(DictConstant.DICT_TYPE_CODE_PAY,DictConstant.DICT_TYPE_CODE_PAY_NAME,payList));*/
		model.addAttribute("conditionList", conditionList);
		
		return "business/requirement/requirement_search_list";
	}

	// 异步返回json数据
	@RequestMapping("/searchList")
	@ResponseBody
	public Object searchListRequirement(HttpServletRequest request,Model model) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Requirement> page = InitUtil.initPage(request);
		queryParam.addParam("status", RequirementConstant.REQUIREMENT_STATUS_FB);
		queryParam.addParam("userId", SystemContext.getLoginUser().getId());
		page = requirementService.getPageList("com.noboll.business.requirement.dao.RequirementDao.getSearchList", queryParam,
				page);
		return page;
	}
	
	// 异步返回操作信息
	@RequestMapping("/publish")
	@ResponseBody
	public Object publish(String id) {
		requirementService.publish(id);
		return InitUtil.sucessMessage("发布成功");
	}
	
	// 异步返回操作信息
	@RequestMapping("/finish")
	@ResponseBody
	public Object finish(String id) {
		requirementService.finish(id);
		return InitUtil.sucessMessage("操作成功");
	}
	
	// 跳转到详情页面
	@RequestMapping("/toView")
	public String toView(Model model,String id) {
		// 需求信息
		Requirement requirement = requirementService.getEntity(id);
		model.addAttribute("requirement", requirement);
		return "business/requirement/requirement_view";
	}
}
