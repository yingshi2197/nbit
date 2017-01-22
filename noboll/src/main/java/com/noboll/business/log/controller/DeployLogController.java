package com.noboll.business.log.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.noboll.business.log.entity.DeployLog;
import com.noboll.business.log.service.DeployLogService;
import com.noboll.business.project.entity.Project;
import com.noboll.business.project.service.ProjectService;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.constants.SystemConstant;
import com.noboll.core.util.StringUtil;
import com.noboll.util.InitUtil;

@Controller
@RequestMapping("/business/log")
public class DeployLogController extends BaseController<DeployLog> {
	
	@InitBinder  
    protected  void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat(SystemConstant.DATE_FORMAT);  
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
        binder.registerCustomEditor(Date.class,"startTime", new CustomDateEditor(dateTimeFormat, true));  
    }  
	 
	 
	@Resource
	private DeployLogService deployLogService;
	@Resource
	private ProjectService projectService;
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListDeployLog(HttpServletRequest request,Model model,String id) {
		model.addAttribute("projectId", id);
		return "business/log/log_list";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listDeployLog(HttpServletRequest request,Model model,String projectId) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<DeployLog> page = InitUtil.initPage(request);
		if(!StringUtil.isEmpty(projectId)) {
			queryParam.addParam("projectId", projectId);
		}
		page = deployLogService.getPageList("com.noboll.business.log.dao.DeployLogDao.getList", queryParam,
				page);
		return page;
	}

	// 跳转到新增页面
	@RequestMapping("/toAdd")
	public String toAddDeployLog(Model model,String id) {
		Project p=projectService.getEntity(id);
		DeployLog log=new DeployLog();
		log.setProjectId(p.getId());
		log.setCode(p.getCode());
		log.setProjectName(p.getName());
		log.setSource(p.getSource());
		log.setTarget(p.getTarget());
		log.setPath(p.getPath());
		log.setContainer(p.getContainer());
		model.addAttribute("log", log);
		return "business/log/log_add";
	}

	// 异步返回操作信息
	@RequestMapping("/add")
	@ResponseBody
	public Object addDeployLog(DeployLog deployLog,HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");
		//System.out.println(file.getOriginalFilename());
		deployLogService.saveEntity(deployLog,file);
		return InitUtil.sucessMessage("新增成功");
	}

	// 跳转到修改页面
	@RequestMapping("/toEdit")
	public String toEditDeployLog(Model model, String id) {
		DeployLog deployLog = deployLogService.getEntity(id);
		model.addAttribute("deployLog", deployLog);
		return "business/log/log_edit";
	}

	// 异步返回操作信息
	@RequestMapping("/edit")
	@ResponseBody
	public Object editDeployLog(DeployLog deployLog) {
		deployLogService.updateEntity(deployLog);
		return InitUtil.sucessMessage("修改成功");
	}
	
	// 异步返回操作信息
	@RequestMapping("/remove")
	@ResponseBody
	public Object removeDeployLog(String id) {
		deployLogService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}
	
	// 跳转到修改页面
	@RequestMapping("/toView")
	public String toView(Model model, String id) {
		DeployLog deployLog = deployLogService.getEntity(id);
		model.addAttribute("log", deployLog);
		return "business/log/log_view";
	}
}
