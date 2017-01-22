package com.noboll.business.resumeLabel.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.resumeLabel.dao.ResumeLabelDao;
import com.noboll.business.resumeLabel.entity.ResumeLabel;
import com.noboll.business.resumeLabel.service.ResumeLabelService;
import com.noboll.context.SystemContext;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.util.ArrayUtil;

@Service("resumeLabelService")
public class ResumeLabelServiceImpl extends BaseServiceImpl<ResumeLabel> implements ResumeLabelService {
	@Resource
	private ResumeLabelDao resumeLabelDao;
	
	@Override
	public BaseDao<ResumeLabel> getBaseDao() {
		return resumeLabelDao;
	}
	
	/**
	 * 批量新增简历标签
	 */
	public void batchInsert(String resumeId, String[] labels){
		List<ResumeLabel> list = new ArrayList<ResumeLabel>();
		String[] uniqueLabels = ArrayUtil.arrayUnique(labels);// 去重
		for (int i = 0; i < uniqueLabels.length; i++) {
			String  label = uniqueLabels[i];
			ResumeLabel resumeLable = new ResumeLabel();
			resumeLable.setCreateUserId(SystemContext.getLoginUser().getId());
			resumeLable.setLastModifyUserId(SystemContext.getLoginUser().getId());
			resumeLable.setResumeId(resumeId);
			resumeLable.setLabelId(label);
			list.add(resumeLable);
		}
		resumeLabelDao.batchInsert(list);
	}
	
	/**
	 * 清除简历标签记录
	 */
	public void deleteByResumeId(String resumeId){
		resumeLabelDao.deleteByResumeId(resumeId);
	}
	
	/**
	 * 通过简历id获取记录
	 */
	public List<ResumeLabel> getByResumeId(String resumeId){
		return resumeLabelDao.getByResumeId(resumeId);
	}
	
}
