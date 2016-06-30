/*
 * RolePermissionMapper.java

 * All rights reserved.
 * -----------------------------------------------
 * 2016-06-18 Created
 */
package com.call110.business.dao.mapper;

import com.call110.business.entity.RolePermission;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(RolePermission key);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}