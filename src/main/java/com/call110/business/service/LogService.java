package com.call110.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.call110.business.dao.mapper.EmployeeLogMapper;
import com.call110.business.entity.EmployeeLog;

@Service
public class LogService{
	
	@Autowired EmployeeLogMapper mapper;
	
	public void create(Long eid, String content) {
		EmployeeLog log = new EmployeeLog();
		log.setContent(content);
		log.setCreateTime(System.currentTimeMillis());
		log.setEid(eid);
		log.setIsDel(0);
		
		mapper.insert(log);
	}

}
