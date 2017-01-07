package com.noboll.business.resume.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.dict.constant.DictConstant;
import com.noboll.business.dict.entity.Dict;
import com.noboll.business.dict.entity.QueryBean;
import com.noboll.business.dict.service.DictService;
import com.noboll.business.experience.entity.Experience;
import com.noboll.business.experience.service.ExperienceService;
import com.noboll.business.position.entity.Position;
import com.noboll.business.position.service.PositionService;
import com.noboll.business.resume.entity.Resume;
import com.noboll.business.resume.service.ResumeService;
import com.noboll.business.resumeLabel.entity.ResumeLabel;
import com.noboll.business.resumeLabel.service.ResumeLabelService;
import com.noboll.context.SystemContext;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.util.JsonUtil;
import com.noboll.core.util.StringUtil;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/business/resume")
public class ResumeController extends BaseController<Resume> {

	@Resource
	private ResumeService resumeService;
	@Resource
	private PositionService positionService;
	@Resource
	private DictService dictService;
	@Resource
	private ExperienceService experienceService;
	@Resource
	private ResumeLabelService resumeLabelService;
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListResume(HttpServletRequest request,Model model) {
		return "business/resume/resume_list";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listResume(HttpServletRequest request,Model model) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Resume> page = InitUtil.initPage(request);
		page = resumeService.getPageList("com.noboll.business.resume.dao.ResumeDao.getList", queryParam,
				page);
		return page;
	}
	
	// 跳转到列表页面
	@RequestMapping("/toMyList")
	public String toMyList(HttpServletRequest request,Model model) {
		return "business/resume/resume_my_list";
	}

	// 异步返回json数据
	@RequestMapping("/myList")
	@ResponseBody
	public Object myList(HttpServletRequest request,Model model) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Resume> page = InitUtil.initPage(request);
		queryParam.addParam("userId", SystemContext.getLoginUser().getId());
		page = resumeService.getPageList("com.noboll.business.resume.dao.ResumeDao.getList", queryParam,
				page);
		return page;
	}

	// 跳转到新增页面
	@RequestMapping("/toAdd")
	public String toAddResume(Model model,String id) {
		return "business/resume/resume_add";
	}

	// 异步返回操作信息
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Object addResume(Resume resume) {
		resumeService.saveResume(resume);
		return InitUtil.sucessMessage("新增成功");
	}

	// 跳转到修改页面
	@RequestMapping("/toEdit")
	public String toEditResume(Model model, String id) {
		Resume resume = resumeService.getEntity(id);
		model.addAttribute("resume", resume);
		// 项目经验
		List<Experience> experiences = experienceService.getByResumeId(id);
		if (null != experiences &&experiences.size()>0)
			resume.setExperienceJson(JsonUtil.objToJson(experiences));
		else
			resume.setExperienceJson("");
		// 标签
		List<ResumeLabel> resumeLabels = resumeLabelService.getByResumeId(id);
		if (null != resumeLabels &&resumeLabels.size()>0)
			model.addAttribute("resumeLabels", JsonUtil.objToJson(resumeLabels));
		else
			model.addAttribute("resumeLabels", "");
		
		return "business/resume/resume_edit";
	}

	// 异步返回操作信息
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Object editResume(Resume resume) {
		resumeService.updateResume(resume);
		return InitUtil.sucessMessage("修改成功");
	}
	
	// 异步返回操作信息
	@RequestMapping("/remove")
	@ResponseBody
	public Object removeResume(String id) {
		resumeService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}
	
	// 跳转到我的简历选择列表页面
	@RequestMapping("/toMyChoose")
	public String toMyChoose(Model model, String type) {
		if (StringUtil.isEmpty(type)) {
			type = "0";
		}

		model.addAttribute("type", type);
		return "business/resume/resume_my_choose";
	}

	// 获取我的简历选择器数据
	@RequestMapping(value = "/myChoose", method = RequestMethod.POST)
	@ResponseBody
	public Object myChoose(HttpServletRequest request) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		queryParam.getMap().put("userId", SystemContext.getLoginUser().getId());

		Page<Resume> page = InitUtil.initPage(request);
		page = resumeService.getPageList("com.noboll.business.resume.dao.ResumeDao.getList",queryParam, page);
		return page;
	}
	
	// 跳转到需求搜索页面
	@RequestMapping("/toSearchList")
	public String toSearchListResume(HttpServletRequest request,Model model) {
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
		// 学历
		List<Dict> degreeList = dictService.queryByTypeCode(DictConstant.DICT_TYPE_CODE_DEGREE);
		conditionList.add(new QueryBean(DictConstant.DICT_TYPE_CODE_DEGREE,DictConstant.DICT_TYPE_CODE_DEGREE_NAME,degreeList));
		// 年限
		List<Dict> workLifeList = dictService.queryByTypeCode(DictConstant.DICT_TYPE_CODE_WORK_LIFE);
		conditionList.add(new QueryBean(DictConstant.DICT_TYPE_CODE_WORK_LIFE,DictConstant.DICT_TYPE_CODE_WORK_LIFE_NAME,workLifeList));
		// 薪资
		List<Dict> payList = dictService.queryByTypeCode(DictConstant.DICT_TYPE_CODE_PAY);
		conditionList.add(new QueryBean(DictConstant.DICT_TYPE_CODE_PAY,DictConstant.DICT_TYPE_CODE_PAY_NAME,payList));
		model.addAttribute("conditionList", conditionList);
		return "business/resume/resume_search_list";
	}

	// 异步返回json数据
	@RequestMapping("/searchList")
	@ResponseBody
	public Object searchListResume(HttpServletRequest request,Model model) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Resume> page = InitUtil.initPage(request);
		page = resumeService.getPageList("com.noboll.business.resume.dao.ResumeDao.getSearchList", queryParam,
				page);
		return page;
	}

}
