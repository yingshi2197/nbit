package com.noboll.business.dict.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.dict.dao.DictDao;
import com.noboll.business.dict.entity.Dict;
import com.noboll.business.dict.service.DictService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.core.exception.BusinessException;

@Service("dictService")
public class DictServiceImpl extends BaseServiceImpl<Dict>
		implements DictService {

	@Resource
	private DictDao dictDao;
	
	public void saveDict(Dict dic) {
		List<Dict> sysList = dictDao.queryByTypeId(dic.getTypeId());
		List<Dict> sysListAll = dictDao.getAllEntity(null);

		if (sysList != null && sysList.size() > 0) {
			// 增加的名字在本数据字典类型下不能重复
			for (Dict sysDic : sysList) {
				if (sysDic.getName().equals(dic.getName())
						&& !sysDic.getId().equals(dic.getId())) {
					throw new BusinessException("该字典类型下已有同名数据项！");
				}
			}
			// 增加的code在本数据字典项下不能重复
			for (Dict sysDic : sysListAll) {
				if (sysDic.getCode().equals(dic.getCode())) {
					throw new BusinessException("数据字典项中已存在相同的code！");
				}
			}

		}
		super.saveEntity(dic);
		
	}

	public void updateDict(Dict dic) {
		List<Dict> sysList = dictDao.queryByTypeId(dic.getTypeId());
		List<Dict> sysListAll = dictDao.getAllEntity(null);

		if (sysList != null && sysList.size() > 0) {
			// 修改的名字在本数据字典类型下不能重复
			for (Dict sysDic : sysList) {
				if (sysDic.getName().equals(dic.getName())
						&& !sysDic.getId().equals(dic.getId())) {
					throw new BusinessException("该字典类型下已有同名数据项！");
				}
			}
			// 修改的code在本数据字典项下不能重复
			for (Dict sysDic : sysListAll) {
				if (sysDic.getCode().equals(dic.getCode())
						&& !sysDic.getId().equals(dic.getId())) {
					throw new BusinessException("数据字典项中已存在相同的code！");
				}
			}
		}
		super.updateEntity(dic);
	}

	public int getMaxSeqByTypeId(String typeId) {
		return dictDao.getMaxSeqByTypeId(typeId);
	}
	
	@Override
	public BaseDao<Dict> getBaseDao() {
		return dictDao;
	}

	public List<Dict> queryByTypeCode(String typeCode) {
		return dictDao.queryByTypeCode(typeCode);
	}

}
