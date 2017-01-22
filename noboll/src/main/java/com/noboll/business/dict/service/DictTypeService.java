package com.noboll.business.dict.service;


import com.noboll.business.dict.entity.DictType;
import com.noboll.core.base.service.BaseService;


public interface DictTypeService extends BaseService<DictType> {
	public void saveDictType(DictType sysType);
	
	public void updateDictType(DictType sysType);
}
