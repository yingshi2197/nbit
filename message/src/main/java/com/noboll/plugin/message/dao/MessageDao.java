package com.noboll.plugin.message.dao;

import java.util.List;

import com.noboll.core.base.dao.BaseDao;
import com.noboll.plugin.message.entity.Message;

public interface MessageDao extends BaseDao<Message> {
	public void batchInsert(List<Message> list);
}
