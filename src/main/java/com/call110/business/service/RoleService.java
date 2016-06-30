package com.call110.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.call110.business.dao.mapper.RoleMapper;
import com.call110.business.entity.Role;

@Service
public class RoleService{
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Transactional(readOnly=true)
	public List<Role> findRolesByEmp(Long employeeId) {
		return roleMapper.findByEid(employeeId);
	}

	@Transactional(readOnly=true)
	public List<Role> list() {
		return roleMapper.list();
	}

}
