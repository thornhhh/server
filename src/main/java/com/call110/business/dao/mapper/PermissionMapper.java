/*
 * PermissionMapper.java

 * All rights reserved.
 * -----------------------------------------------
 * 2016-06-18 Created
 */
package com.call110.business.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.call110.business.entity.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    @Select("select * from cr_permission where is_del=0")
    @ResultMap("BaseResultMap")
    List<Permission> findAll();
}