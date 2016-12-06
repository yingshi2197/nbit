package com.noboll.business.dict.dao;

import java.util.List;

import com.noboll.business.dict.entity.Dict;
import com.noboll.core.base.dao.BaseDao;

public interface DictDao extends BaseDao<Dict>{
	public int getMaxSeqByTypeId(String typeId);
	public List<Dict> queryByTypeId(String typeId);
	public List<Dict> queryByTypeCode(String typeCode);
}
