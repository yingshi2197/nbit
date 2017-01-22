package com.noboll.business.interview.dao;

import org.apache.ibatis.annotations.Param;

import com.noboll.business.interview.entity.Interview;
import com.noboll.core.base.dao.BaseDao;

public interface InterviewDao extends BaseDao<Interview>{

	/**
	 * 根据投递记录id和类型得到面试记录
	 */
	public Interview getByDeliverIdAndType(@Param("deliverId") String deliverId,@Param("type") String dictCodeDhms);

}
