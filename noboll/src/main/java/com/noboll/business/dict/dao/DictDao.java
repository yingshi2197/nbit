package com.noboll.business.dict.dao;

import java.util.List;

import com.noboll.business.dict.entity.Dict;
import com.noboll.core.base.dao.BaseDao;

public interface DictDao extends BaseDao<Dict>{
	/**
	 * 根据数据字典类型查找子项列表中最大的seq
	 */
	public int getMaxSeqByTypeId(String typeId);
	/**
	 * 根据理性查找子项列表
	 */
	public List<Dict> queryByTypeId(String typeId);
	/**
	 * 根据数据字典类型编码查找子项集合
	 */
	public List<Dict> queryByTypeCode(String typeCode);
	/**
	 * 根据数据字典编码查找数据字典
	 */
	public Dict getByCode(String dictCode);
}
