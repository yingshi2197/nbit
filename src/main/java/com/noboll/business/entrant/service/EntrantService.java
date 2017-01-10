package com.noboll.business.entrant.service;


import com.noboll.business.entrant.entity.Entrant;
import com.noboll.core.base.service.BaseService;


public interface EntrantService extends BaseService<Entrant> {

	/**
	 * 新增入职
	 */
	public void saveEntrant(Entrant entrant);

	/**
	 * 修改入职
	 */
	public void updateEntrant(Entrant entrant);
	
	/**
	 * 新增\修改入职记录
	 */
	public void saveUpdateDhEntrant(Entrant entrant);
	
	/**
	 * 通过投递记录id得到入职记录
	 */
	public Entrant getByDeliverId(String deliverId);

}
