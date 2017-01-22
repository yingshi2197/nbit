package com.noboll.business.project.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.project.entity.Project;
import com.noboll.business.project.service.ProjectService;
import com.noboll.business.user.entity.User;
import com.noboll.context.SystemContext;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.util.PropertiesUtil;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/business/project")
public class ProjectController extends BaseController<Project> {
	@Resource
	private ProjectService projectService;
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListProject(HttpServletRequest request,Model model,String typeId) {
		model.addAttribute("typeId", typeId);
		model.addAttribute("formal",( (User)SystemContext.getLoginUser()).getFormal());
		return "business/project/project_list";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listProject(HttpServletRequest request,Model model,String typeId) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Project> page = InitUtil.initPage(request);
		page = projectService.getPageList("com.noboll.business.project.dao.ProjectDao.getList", queryParam,
				page);
		return page;
	}

	// 跳转到新增页面
	@RequestMapping("/toAdd")
	public String toAddProject(Model model) {
		model.addAttribute("port", "8080");
		model.addAttribute("container", PropertiesUtil.getSettingValue("sys.deploy.contain.default.path"));
		return "business/project/project_add";
	}
	
	// 跳转到新增页面
	@RequestMapping("/toProjectLog")
	public String toProjectLog(Model model,String id) {
		Project project=projectService.getEntity(id);
		model.addAttribute("project", project);
		return "business/project/project_log";
	}

	// 异步返回操作信息
	@RequestMapping("/add")
	@ResponseBody
	public Object addProject(Project project) {
		projectService.saveEntity(project);
		return InitUtil.sucessMessage("新增成功");
	}

	// 跳转到修改页面
	@RequestMapping("/toEdit")
	public String toEditProject(Model model, String id) {
		Project project = projectService.getEntity(id);
		model.addAttribute("project", project);
		return "business/project/project_edit";
	}

	// 异步返回操作信息
	@RequestMapping("/edit")
	@ResponseBody
	public Object editProject(Project project) {
		projectService.updateEntity(project);
		return InitUtil.sucessMessage("修改成功");
	}
	
	// 异步返回操作信息
	@RequestMapping("/remove")
	@ResponseBody
	public Object removeProject(String id) {
		projectService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}
}
