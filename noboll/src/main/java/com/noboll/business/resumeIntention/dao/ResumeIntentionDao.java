package com.noboll.business.resumeIntention.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.noboll.business.resumeIntention.entity.ResumeIntention;
import com.noboll.core.base.dao.BaseDao;

public interface ResumeIntentionDao extends BaseDao<ResumeIntention> {
	
	/**
	 * 根据简历id删除意向地区关联记录，物理删除
	 * @param resumeId
	 */
	public void deleteByResumeId(String resumeId);

	/**
	 * 批量增加简历意向地区记录
	 */
	public void batchInsert(@Param("list")List<ResumeIntention> list);

}
