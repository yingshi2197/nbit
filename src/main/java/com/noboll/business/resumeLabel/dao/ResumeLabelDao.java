package com.noboll.business.resumeLabel.dao;

import java.util.List;

import com.noboll.business.experience.entity.Experience;
import com.noboll.business.resumeLabel.entity.ResumeLabel;
import com.noboll.core.base.dao.BaseDao;

public interface ResumeLabelDao extends BaseDao<ResumeLabel>{

	/**
	 * 批量新增
	 */
	public void batchInsert(List<ResumeLabel> list);

	/**
	 * 清除简历标签记录
	 */
	public void deleteByResumeId(String id);
	
	/**
	 * 得到简历的工作经历
	 */
	public List<ResumeLabel> getByResumeId(String resumeId);
	
}
