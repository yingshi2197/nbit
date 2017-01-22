package com.noboll.plugin.message.service;

import java.util.List;

import com.noboll.core.base.service.BaseService;
import com.noboll.plugin.message.entity.Message;

public interface MessageService extends BaseService<Message> {
	public void saveForBatch(List<Message> list);
}
