package com.noboll.business.dict.service;


import java.util.List;

import com.noboll.business.dict.entity.Dict;
import com.noboll.core.base.service.BaseService;


public interface DictService extends BaseService<Dict> {
	/**
	 * 新增数据字典
	 */
	public void saveDict(Dict dic);
	
	/**
	 * 修改数据字典
	 */
	public void updateDict(Dict dic);
	
	/**
	 * 得到该类型下子项列表最大的seq
	 */
	public int getMaxSeqByTypeId(String typeId); 
	
	/**
	 * 通过数据字典类型编码查找子项集合
	 */
	public List<Dict> queryByTypeCode(String typeCode);

	/**
	 * 通过数据字典编码查找数据字典
	 */
	public Dict getByCode(String dictCode);

}
