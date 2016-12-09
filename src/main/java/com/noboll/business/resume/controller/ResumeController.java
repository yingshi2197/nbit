package com.noboll.business.resume.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.position.service.PositionService;
import com.noboll.business.resume.entity.Resume;
import com.noboll.business.resume.service.ResumeService;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/business/resume")
public class ResumeController extends BaseController<Resume> {

	@Resource
	private ResumeService resumeService;
	@Resource
	private PositionService positionService;
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListResume(HttpServletRequest request,Model model,String typeId) {
		model.addAttribute("typeId", typeId);
		return "business/resume/resume_list";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listResume(HttpServletRequest request,Model model,String typeId) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Resume> page = InitUtil.initPage(request);
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

}
