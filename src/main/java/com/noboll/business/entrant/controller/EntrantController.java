package com.noboll.business.entrant.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.deliver.entity.Deliver;
import com.noboll.business.deliver.service.DeliverService;
import com.noboll.business.entrant.entity.Entrant;
import com.noboll.business.entrant.service.EntrantService;
import com.noboll.business.resume.entity.Resume;
import com.noboll.business.resume.service.ResumeService;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.exception.BusinessException;
import com.noboll.core.util.StringUtil;
import com.noboll.util.InitUtil;

/**
 * 
 * @ClassName: EntrantController
 * @Description: 面试控制器
 * @author weicb
 * @date 2016年12月26日 下午21:18:46
 *
 */
@Controller
@RequestMapping("/business/entrant")
public class EntrantController extends BaseController<Entrant> {

	@Resource
	private EntrantService entrantService;
	@Resource
	private DeliverService deliverService;
	@Resource
	private ResumeService resumeService;
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListEntrant(HttpServletRequest request,Model model) {
		return "business/entrant/entrant_list";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listEntrant(HttpServletRequest request,Model modeld) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Entrant> page = InitUtil.initPage(request);
		page = entrantService.getPageList("com.noboll.business.entrant.dao.EntrantDao.getList", queryParam,
				page);
		return page;
	}

	// 报到入职（新增、修改）页面
	@RequestMapping("/toAddUpdate")
	public String toDhEntrant(Model model,String id) {
		if (StringUtil.isEmpty(id)) 
			throw new BusinessException("无投递记录！");
		// 查找投递记录
		Deliver deliver = deliverService.getEntity(id);
		if (null == deliver || StringUtil.isEmpty(deliver.getResumeId())) 
			throw new BusinessException("无投递记录！");
		model.addAttribute("deliver", deliver);
		// 查找面试记录
		Entrant entrant = entrantService.getByDeliverId(id);
		if (null == entrant) {
			entrant = new Entrant();
			entrant.setPositionId(deliver.getPositionId());
			entrant.setPositionName(deliver.getPositionName());
		}
		model.addAttribute("entrant", entrant);
		// 查找简历记录
		Resume resume = resumeService.getEntity(deliver.getResumeId());
		model.addAttribute("resume", resume);
		
		return "business/entrant/entrant_addUpdate";
	}

	// 报到入职（新增、修改）提交，异步返回操作信息
	@RequestMapping("addUpdate")
	@ResponseBody
	public Object entrant(Entrant entrant) {
		entrantService.saveUpdateDhEntrant(entrant);
		return InitUtil.sucessMessage("操作成功");
	}

	// 删除
	@RequestMapping("/remove")
	@ResponseBody
	public Object removeEntrantType(String id) {
		entrantService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}
	
}
