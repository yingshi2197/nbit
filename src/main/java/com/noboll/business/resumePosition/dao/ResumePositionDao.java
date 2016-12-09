package com.noboll.business.resumePosition.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.noboll.business.resumePosition.entity.ResumePosition;
import com.noboll.core.base.dao.BaseDao;

public interface ResumePositionDao extends BaseDao<ResumePosition> {

	/**
	 * 根据简历id删除意向职位关联记录，物理删除
	 * @param resumeId 简历id
	 */
	public void deleteByResumeId(String resumeId);

	/**
	 * 批量增加简历意向职位记录
	 */
	public void batchInsert(@Param("list")List<ResumePosition> list);

}
