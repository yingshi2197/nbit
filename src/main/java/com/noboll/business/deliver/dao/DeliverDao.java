package com.noboll.business.deliver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.noboll.business.deliver.entity.Deliver;
import com.noboll.core.base.dao.BaseDao;

public interface DeliverDao extends BaseDao<Deliver>{

	
	/**
	 * 根据需求id和用户id查找投递记录，防止重复投递
	 * @param requirementId 需求id
	 * @param userId 用户id
	 */
	public List<Deliver> getByRequirementAndUserId(@Param("requirementId")String requirementId,@Param("userId") String userId);

	/**
	 * 更新投递记录的状态和面试状态
	 */
	public void updateStatus(Deliver deliver);
}
