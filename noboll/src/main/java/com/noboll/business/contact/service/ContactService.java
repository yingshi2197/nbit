package com.noboll.business.contact.service;


import java.util.List;

import com.noboll.business.contact.entity.Contact;
import com.noboll.core.base.service.BaseService;


public interface ContactService extends BaseService<Contact> {

	/**
	 * 新增联系人
	 */
	public void saveContact(Contact contact);

	/**
	 * 修改联系人
	 */
	public void updateContact(Contact contact);
	
	/**
	 * 批量为客户新增联系人
	 */
	public void batchInsert(String customerId,Contact[] contacts);

	/**
	 * 删除客户的联系人（物理删除）
	 */
	public void deleteByCustomerId(String customerId);
	
	/**
	 * 通过客户id得到联系人记录
	 */
	public List<Contact> getByCustomerId(String customerId);

}
