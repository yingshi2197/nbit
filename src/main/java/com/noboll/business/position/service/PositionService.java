package com.noboll.business.position.service;


import com.noboll.business.position.entity.Position;
import com.noboll.core.base.service.BaseService;


public interface PositionService extends BaseService<Position> {
	public void publish(String id);
	public void finish(String id);
}
