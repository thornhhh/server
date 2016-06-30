package com.call110.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.call110.business.dao.mapper.EmployeeRoleMapper;

@Service
public class EmployeeRoleService {
	@Autowired
	private EmployeeRoleMapper mapper;

	@Transactional(readOnly=false)
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Transactional(readOnly=false)
	public void set(Long eid, Long rid) {
		mapper.set(eid, rid);
	}
	
	
	
	
}
