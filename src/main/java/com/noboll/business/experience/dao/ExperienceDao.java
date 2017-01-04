package com.noboll.business.experience.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.noboll.business.experience.entity.Experience;
import com.noboll.core.base.dao.BaseDao;

public interface ExperienceDao extends BaseDao<Experience> {

	/**
	 * 根据简历id删除记录，物理删除
	 * @param resumeId 简历id
	 */
	public void deleteByResumeId(String resumeId);

	/**
	 * 批量增加简历意向职位记录
	 */
	public void batchInsert(@Param("list")List<Experience> list);

	/**
	 * 得到简历的工作经历
	 */
	public List<Experience> getByResumeId(String resumeId);

}
