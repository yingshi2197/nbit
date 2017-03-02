package com.noboll.business.evaluateLabel.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.evaluateLabel.dao.EvaluateLabelDao;
import com.noboll.business.evaluateLabel.entity.EvaluateLabel;
import com.noboll.business.evaluateLabel.service.EvaluateLabelService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;

@Service("evaluateLabelService")
public class EvaluateLabelServiceImpl extends BaseServiceImpl<EvaluateLabel> implements EvaluateLabelService {
	@Resource
	private EvaluateLabelDao evaluateLabelDao;
	
	@Override
	public BaseDao<EvaluateLabel> getBaseDao() {
		return evaluateLabelDao;
	}
	
	/**
	 * 查找标签选择数据
	 */
	public List<EvaluateLabel> getChooseList(Map<String, Object> map){
		return evaluateLabelDao.getChooseList(map);
	}
	
}
