package com.noboll.business.dict.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.noboll.business.dict.dao.DictTypeDao;
import com.noboll.business.dict.entity.DictType;
import com.noboll.business.dict.service.DictTypeService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.core.exception.BusinessException;

@Service("dictTypeService")
public class DictTypeServiceImpl extends BaseServiceImpl<DictType> implements DictTypeService {

	@Resource
	private DictTypeDao dictTypeDao;
	
	public void saveDictType(DictType sysType) {
		List<DictType> listType = dictTypeDao.getAllEntity(null);
		if(listType != null && listType.size()>0){
			//增加的名称在数据字典类型中不能重复
			for (DictType type : listType) {
				System.out.println(type.getName()+"  ==========================================");
				if (type.getName().equals(sysType.getName())
						&& !type.getId().equals(sysType.getId())) {
					throw new BusinessException("该数据字典类型以存在");
				}
			}
			//增加的名称在数据字典类型中不能重复
			for (DictType type : listType) {
				if (type.getCode().equals(sysType.getCode())
						&& !type.getId().equals(sysType.getId())) {
					throw new BusinessException("数据字典类型中已存在相同的code");
				}
			}
		}
		super.saveEntity(sysType);
	}
	
	public void updateDictType(DictType sysType) {
		
		List<DictType> listType = dictTypeDao.getAllEntity(null);
		if(listType != null && listType.size()>0){
			//增加的名称在数据字典类型中不能重复
			for (DictType type : listType) {
				if (type.getName().equals(sysType.getName())
						&& !type.getId().equals(sysType.getId())) {
					throw new BusinessException("该数据字典类型以存在");
				}
			}
			//增加的名称在数据字典类型中不能重复
			for (DictType type : listType) {
				if (type.getCode().equals(sysType.getCode())
						&& !type.getId().equals(sysType.getId())) {
					throw new BusinessException("数据字典类型中已存在相同的code");
				}
			}
		}
		super.updateEntity(sysType);
	}

	public BaseDao<DictType> getBaseDao() {
		return dictTypeDao;
	}

}
