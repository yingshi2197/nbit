package com.noboll.business.dict.service;


import java.util.List;

import com.noboll.business.dict.entity.Dict;
import com.noboll.core.base.service.BaseService;


public interface DictService extends BaseService<Dict> {
	public void saveDict(Dict dic);
	
	public void updateDict(Dict dic);
	
	public int getMaxSeqByTypeId(String typeId); 
	
	public List<Dict> queryByTypeCode(String typeCode);

}
