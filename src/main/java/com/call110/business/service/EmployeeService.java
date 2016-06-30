package com.call110.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.call110.business.dao.mapper.EmployeeMapper;
import com.call110.business.entity.Employee;
import com.call110.business.vo.EmployeeVO;
@Service
public class EmployeeService{
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public Employee findByID(Long id) {
		return employeeMapper.selectByPrimaryKey(id);
	}
	
	@Transactional(readOnly=true)
	public Employee findByUsername(String username) {
		return employeeMapper.findByUsername(username);
	}

	@Transactional(readOnly=true)
	public List<EmployeeVO> list(String name, int page) {

		return employeeMapper.list(name, page);
	}

	@Transactional(readOnly=true)
	public Long maxPage(String name) {
		return employeeMapper.maxPage(name);
	}

	@Transactional(readOnly=true)
	public EmployeeVO get(long id) {
		return employeeMapper.get(id);
	}

	@Transactional(readOnly=false)
	public void delete(long id) {
		employeeMapper.delete(id);
	}

	@Transactional(readOnly=false)
	public void reset(long id) {
		employeeMapper.reset(id);
	}

	@Transactional(readOnly=true)
	public int countOfUserName(String name) {
		return employeeMapper.countOfUserName(name);
	}

	@Transactional(readOnly=true)
	public Employee selectByPrimaryKey(Long id) {
		return employeeMapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly=false)
	public void insert(Employee e) {
		employeeMapper.insert(e);
	}

	@Transactional(readOnly=false)
	public void updateByPrimaryKey(Employee e) {
		employeeMapper.updateByPrimaryKey(e);
	}
	
	

}
