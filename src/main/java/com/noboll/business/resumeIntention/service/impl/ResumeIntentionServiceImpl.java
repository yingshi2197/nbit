package com.noboll.business.resumeIntention.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.resumeIntention.dao.ResumeIntentionDao;
import com.noboll.business.resumeIntention.entity.ResumeIntention;
import com.noboll.business.resumeIntention.service.ResumeIntentionService;
import com.noboll.context.SystemContext;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;

@Service("resumeIntentionService")
public class ResumeIntentionServiceImpl extends BaseServiceImpl<ResumeIntention> implements ResumeIntentionService {

	@Resource
	private ResumeIntentionDao resumeIntentionDao;
	
	@Override
	public BaseDao<ResumeIntention> getBaseDao() {
		return resumeIntentionDao;
	}

	@Override
	public void batchInsert(String resumeId, String[] intentions) {
		List<ResumeIntention> list = new ArrayList<ResumeIntention>();
		for (int i = 0; i < intentions.length; i++) {
			ResumeIntention ri=new ResumeIntention(resumeId,intentions[i]);
			ri.setCreateUserId(SystemContext.getLoginUser().getId());
			ri.setLastModifyUserId(SystemContext.getLoginUser().getId());
			list.add(ri);
		}
		resumeIntentionDao.batchInsert(list);
	}

	@Override
	public void deleteByResumeId(String resumeId) {
		resumeIntentionDao.deleteByResumeId(resumeId);
	}

}
