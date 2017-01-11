package com.noboll.business.customer.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.business.contact.entity.Contact;
import com.noboll.business.contact.service.ContactService;
import com.noboll.business.customer.entity.Customer;
import com.noboll.business.customer.service.CustomerService;
import com.noboll.business.customerLabel.entity.CustomerLabel;
import com.noboll.business.customerLabel.service.CustomerLabelService;
import com.noboll.core.base.controller.BaseController;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.util.JsonUtil;
import com.noboll.core.util.StringUtil;
import com.noboll.util.InitUtil;

/**
 * 
 * @ClassName: CustomerController
 * @Description: 客户控制器
 * @author weicb
 * @date 2016年12月19日 下午21:18:46
 *
 */
@Controller
@RequestMapping("/business/customer")
public class CustomerController extends BaseController<Customer> {

	@Resource
	private CustomerService customerService;
	@Resource
	private CustomerLabelService customerLabelService;
	@Resource
	private ContactService contactService;
	
	// 跳转到列表页面
	@RequestMapping("/toList")
	public String toListCustomer(HttpServletRequest request,Model model) {
		return "business/customer/customer_list";
	}

	// 异步返回json数据
	@RequestMapping("/list")
	@ResponseBody
	public Object listCustomer(HttpServletRequest request,Model modeld) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Customer> page = InitUtil.initPage(request);
		page = customerService.getPageList("com.noboll.business.customer.dao.CustomerDao.getList", queryParam,
				page);
		return page;
	}

	// 跳转到新增页面
	@RequestMapping("/toAdd")
	public String toAddCustomer(Model model) {
		return "business/customer/customer_add";
	}

	// 异步返回操作信息
	@RequestMapping("/add")
	@ResponseBody
	public Object addCustomer(Customer customer) {
		customerService.saveCustomer(customer);
		return InitUtil.sucessMessage("新增成功");
	}

	// 跳转到修改页面
	@RequestMapping("/toEdit")
	public String toEditCustomer(Model model, String id) {
		Customer customer = customerService.getEntity(id);
		model.addAttribute("customer", customer);
		// 客户联系人
		List<Contact> contacts = contactService.getByCustomerId(id);
		if (null != contacts &&contacts.size()>0)
			customer.setContactJson(JsonUtil.objToJson(contacts));
		else
			customer.setContactJson("");
		// 标签
		List<CustomerLabel> customerLabels = customerLabelService.getByCustomerId(id);
		if (null != customerLabels && customerLabels.size()>0)
			model.addAttribute("customerLabels", JsonUtil.objToJson(customerLabels));
		else
			model.addAttribute("customerLabels", "");
		return "business/customer/customer_edit";
	}

	// 异步返回操作信息
	@RequestMapping("/edit")
	@ResponseBody
	public Object editCustomer(Customer customer) {
		customerService.updateCustomer(customer);
		return InitUtil.sucessMessage("修改成功");
	}

	// 删除
	@RequestMapping("/remove")
	@ResponseBody
	public Object removeCustomerType(String id) {
		customerService.deleteEntity(id);
		return InitUtil.sucessMessage("删除成功");
	}
	
	// 启用
	@RequestMapping("/enable")
	@ResponseBody
	public Object enable(String id) {
		customerService.enable(id);
		return InitUtil.sucessMessage("启用成功");
	}
	
	// 禁用
	@RequestMapping("/disable")
	@ResponseBody
	public Object disable(String id) {
		customerService.disable(id);
		return InitUtil.sucessMessage("禁用成功");
	}

	// 跳转到列表页面
	@RequestMapping("/toChoose")
	public String toCustomerChoose(Model model, String type) {
		if (StringUtil.isEmpty(type)) {
			type = "0";
		}

		model.addAttribute("type", type);
		return "business/customer/customer_choose";
	}

	// 获取选择器数据
	@RequestMapping(value = "/choose", method = RequestMethod.POST)
	@ResponseBody
	public Object choose(HttpServletRequest request) {
		QueryParam queryParam = InitUtil.initQueryParam(request);
		Page<Customer> page = InitUtil.initPage(request);
		queryParam.addParam("statusName", "0");// 只查询有效的客户
		page = customerService.getPageList("com.noboll.business.customer.dao.CustomerDao.getList",queryParam, page);
		return page;
	}
	
}
