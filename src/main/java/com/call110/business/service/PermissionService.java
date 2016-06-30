package com.call110.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.call110.business.dao.mapper.PermissionMapper;
import com.call110.business.entity.Permission;
@Service
public class PermissionService{
	@Autowired
	private PermissionMapper permissionMapper;
	
	public Permission findById(Long id) {
		return permissionMapper.selectByPrimaryKey(id);
	}

	public List<Permission> findAll() {
		return permissionMapper.findAll();
	}
	
	public void save(Permission permission) {
		permissionMapper.insert(permission);

	}

	public void update(Permission permission) {
		permissionMapper.updateByPrimaryKeySelective(permission);
	}

}
