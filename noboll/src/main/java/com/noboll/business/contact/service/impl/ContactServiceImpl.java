package com.noboll.business.contact.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.contact.dao.ContactDao;
import com.noboll.business.contact.entity.Contact;
import com.noboll.business.contact.service.ContactService;
import com.noboll.business.resume.service.ResumeService;
import com.noboll.context.SystemContext;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;

@Service("contactService")
public class ContactServiceImpl extends BaseServiceImpl<Contact>
		implements ContactService {

	@Resource
	private ContactDao contactDao;
	@Resource
	private ResumeService resumeService;
	
	@Override
	public BaseDao<Contact> getBaseDao() {
		return contactDao;
	}

	@Override
	public void saveContact(Contact contact) {
		// 添加入职信息
		this.saveEntity(contact);
	}

	@Override
	public void updateContact(Contact contact) {
		this.updateEntity(contact);
	}
	
	/**
	 * 批量为客户新增联系人
	 */
	@Override
	public void batchInsert(String customerId,Contact[] contacts){
		List<Contact> list = new ArrayList<Contact>();
		for (int i = 0; i < contacts.length; i++) {
			Contact  contact = contacts[i];
			contact.setCreateUserId(SystemContext.getLoginUser().getId());
			contact.setLastModifyUserId(SystemContext.getLoginUser().getId());
			contact.setCustomerId(customerId);
			list.add(contact);
			
		}
		contactDao.batchInsert(list);
	}

	@Override
	public void deleteByCustomerId(String customerId) {
		contactDao.deleteByCustomerId(customerId);
	}
	
	/**
	 * 通过客户id得到联系人记录
	 */
	public List<Contact> getByCustomerId(String customerId){
		return contactDao.getByCustomerId(customerId);
	}

}
