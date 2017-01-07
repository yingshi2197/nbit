package com.noboll.business.label.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.label.dao.LabelDao;
import com.noboll.business.label.entity.Label;
import com.noboll.business.label.service.LabelService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;

@Service("labelService")
public class LabelServiceImpl extends BaseServiceImpl<Label> implements LabelService {
	@Resource
	private LabelDao labelDao;
	
	@Override
	public BaseDao<Label> getBaseDao() {
		return labelDao;
	}
	
	/**
	 * 查找标签选择数据
	 */
	public List<Label> getChooseList(Map<String, Object> map){
		return labelDao.getChooseList(map);
	}
	
}
