package com.noboll.business.resumePosition.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.resumePosition.dao.ResumePositionDao;
import com.noboll.business.resumePosition.entity.ResumePosition;
import com.noboll.business.resumePosition.service.ResumePositionService;
import com.noboll.context.SystemContext;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;

@Service("resumePositionService")
public class ResumePositionServiceImpl extends BaseServiceImpl<ResumePosition> implements ResumePositionService {

	@Resource
	private ResumePositionDao resumePositionDao;
	
	@Override
	public BaseDao<ResumePosition> getBaseDao() {
		return resumePositionDao;
	}

	@Override
	public void batchInsert(String resumeId, String[] positions) {
		List<ResumePosition> list = new ArrayList<ResumePosition>();
		for (int i = 0; i < positions.length; i++) {
			ResumePosition rp=new ResumePosition(resumeId,positions[i]);
			rp.setCreateUserId(SystemContext.getLoginUser().getId());
			rp.setLastModifyUserId(SystemContext.getLoginUser().getId());
			list.add(rp);
		}
		resumePositionDao.batchInsert(list);
	}

	@Override
	public void deleteByResumeId(String resumeId) {
		resumePositionDao.deleteByResumeId(resumeId);
	}

}
