package com.noboll.business.resumeEvaluateLabel.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.resumeEvaluateLabel.dao.ResumeEvaluateLabelDao;
import com.noboll.business.resumeEvaluateLabel.entity.ResumeEvaluateLabel;
import com.noboll.business.resumeEvaluateLabel.service.ResumeEvaluateLabelService;
import com.noboll.context.SystemContext;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.util.ArrayUtil;

@Service("resumeEvaluateLabelService")
public class ResumeEvaluateLabelServiceImpl extends BaseServiceImpl<ResumeEvaluateLabel> implements ResumeEvaluateLabelService {
	@Resource
	private ResumeEvaluateLabelDao resumeEvaluateLabelDao;
	
	@Override
	public BaseDao<ResumeEvaluateLabel> getBaseDao() {
		return resumeEvaluateLabelDao;
	}
	
	/**
	 * 通过客户id查找
	 */
	public List<ResumeEvaluateLabel> getByResumeId(String resumeId){
		return resumeEvaluateLabelDao.getByResumeId(resumeId);
	}
	
	/**
	 * 批量新增客户标签
	 */
	public void batchInsert(String evaluateId, String resumeId, String[] labels){
		List<ResumeEvaluateLabel> list = new ArrayList<ResumeEvaluateLabel>();
		String[] uniqueLabels = ArrayUtil.arrayUnique(labels);// 去重
		for (int i = 0; i < uniqueLabels.length; i++) {
			String  label = uniqueLabels[i];
			ResumeEvaluateLabel resumeEvaluateLabel = new ResumeEvaluateLabel();
			resumeEvaluateLabel.setCreateUserId(SystemContext.getLoginUser().getId());
			resumeEvaluateLabel.setLastModifyUserId(SystemContext.getLoginUser().getId());
			resumeEvaluateLabel.setEvaluateId(evaluateId);
			resumeEvaluateLabel.setResumeId(resumeId);
			resumeEvaluateLabel.setEvaluateLabelId(label);
			list.add(resumeEvaluateLabel);
		}
		resumeEvaluateLabelDao.batchInsert(list);	
	}
	
	/**
	 * 清除客户标签记录
	 */
	public void deleteByResumeId(String resumeId){
		resumeEvaluateLabelDao.deleteByResumeId(resumeId);
	}
	
}
