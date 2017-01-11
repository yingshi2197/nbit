package com.noboll.business.entrant.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.deliver.entity.Deliver;
import com.noboll.business.deliver.service.DeliverService;
import com.noboll.business.dict.constant.DictConstant;
import com.noboll.business.dict.entity.Dict;
import com.noboll.business.dict.service.DictService;
import com.noboll.business.entrant.constant.EntrantConstant;
import com.noboll.business.entrant.dao.EntrantDao;
import com.noboll.business.entrant.entity.Entrant;
import com.noboll.business.entrant.service.EntrantService;
import com.noboll.business.resume.service.ResumeService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.core.exception.BusinessException;
import com.noboll.core.util.StringUtil;

@Service("entrantService")
public class EntrantServiceImpl extends BaseServiceImpl<Entrant>
		implements EntrantService {

	@Resource
	private EntrantDao entrantDao;
	@Resource
	private ResumeService resumeService;
	@Resource
	private DictService dictService;
	@Resource
	private DeliverService deliverService;
	
	@Override
	public BaseDao<Entrant> getBaseDao() {
		return entrantDao;
	}

	@Override
	public void saveEntrant(Entrant entrant) {
		// 添加入职信息
		this.saveEntity(entrant);
	}

	@Override
	public void updateEntrant(Entrant entrant) {
		this.updateEntity(entrant);
	}
	
	/**
	 * 新增\修改入职记录
	 */
	public void saveUpdateEntrant(Entrant entrant){
		// 查找投递记录
		if (StringUtil.isEmpty(entrant.getDeliverId()))
			throw new BusinessException("无投递记录！");
		Deliver deliver = deliverService.getEntity(entrant.getDeliverId());
		if (null == deliver || StringUtil.isEmpty(deliver.getResumeId())) 
			throw new BusinessException("无投递记录！");
		
		if (StringUtil.isEmpty(entrant.getId())) {
			entrant.setCustomerId(deliver.getCustomerId());
			entrant.setResumeId(deliver.getResumeId());
			// TODO interviewId
			entrant.setStatus(EntrantConstant.STATUS_ENTER);//状态：在职
			this.saveEntity(entrant);
			// 修改投递状态
			Dict dictEnter = dictService.getByCode(DictConstant.DICT_CODE_DELIVER_ENTRANT);
			deliver.setStatus(dictEnter.getId());
			deliverService.updateStatus(deliver);
		}else{	
			this.updateEntity(entrant);
		}
	}
	
	/**
	 * 通过投递记录id得到入职记录
	 */
	public Entrant getByDeliverId(String deliverId){
		return entrantDao.getByDeliverId(deliverId);
	}

}
