package com.noboll.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.demo.dao.DemoDao;
import com.noboll.demo.entity.Demo;
import com.noboll.demo.service.DemoService;

@Service("demoService")
public class DemoServiceImpl extends BaseServiceImpl<Demo> implements DemoService {

	@Resource
	private DemoDao dao;
	
	@Override
	public BaseDao<Demo> getBaseDao() {		
		return dao;
	}
	
}
