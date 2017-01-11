package com.noboll.business.contact.dao;

import java.util.List;

import com.noboll.business.contact.entity.Contact;
import com.noboll.core.base.dao.BaseDao;

public interface ContactDao extends BaseDao<Contact>{

	/**
	 * 通过客户id得到联系人记录
	 */
	public List<Contact> getByCustomerId(String customerId);

	/**
	 * 批量新增联系人
	 */
	public void batchInsert(List<Contact> list);

	/**
	 * 删除客户的联系人（物理）
	 */
	public void deleteByCustomerId(String customerId);

}
