package com.noboll.plugin.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.plugin.message.dao.MessageDao;
import com.noboll.plugin.message.entity.Message;
import com.noboll.plugin.message.service.MessageService;

@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService {

	@Resource
	private MessageDao messageDao;
	
	@Override
	public BaseDao<Message> getBaseDao() {
		return messageDao;
	}
	
	public void saveEntity(Message entity) {
		// 当不是消息时，都需要发送邮件
		if("1".equals(entity.getType())) {
			entity.setStatus("1");// 表示消息发送成功
		}else {
			entity.setStatus("3");// 设置为发送中
		}
		super.saveEntity(entity);
	}

	public void saveForBatch(List<Message> list) {
		messageDao.batchInsert(list);
	}
	
}
