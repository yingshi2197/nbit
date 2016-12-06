package com.noboll.business.log.dao;

import com.noboll.business.log.entity.DeployLog;
import com.noboll.core.base.dao.BaseDao;

public interface DeployLogDao extends BaseDao<DeployLog> {
	public void deploy(DeployLog log);
}
