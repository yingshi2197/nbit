package com.noboll.business.position.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.position.dao.PositionDao;
import com.noboll.business.position.entity.Position;
import com.noboll.business.position.service.PositionService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;

@Service("positionService")
public class PositionServiceImpl extends BaseServiceImpl<Position> implements PositionService {
	@Resource
	private PositionDao positionDao;
	
	@Override
	public BaseDao<Position> getBaseDao() {
		return positionDao;
	}
	
}
