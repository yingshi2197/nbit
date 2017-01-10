package com.noboll.business.entrant.dao;

import com.noboll.business.entrant.entity.Entrant;
import com.noboll.core.base.dao.BaseDao;

public interface EntrantDao extends BaseDao<Entrant>{

	/**
	 * 通过投递记录id得到入职记录
	 */
	public Entrant getByDeliverId(String deliverId);

}
