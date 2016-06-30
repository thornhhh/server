package com.call110.business.dao.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.call110.business.entity.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    @Select("select r.* from cr_employee_role er, cr_role r where er.eid=#{eid} and er.rid=r.id")
    List<Role> findByEmp(@Param("eid") Long eid);
    
    @Select("select role.id,role.name,p.id as pid,p.name as pname,p.description,p.url,p.type,p.is_del from "
    		+ "(select r.*, rp.pid from cr_employee_role er, cr_role r left join cr_role_permission rp on rp.rid = r.id where r.ID = er.RID and er.eid=#{eid}) "
    		+ "role left join cr_permission p on role.pid = p.id and p.is_del = 0")
    @ResultMap("BaseResultMap")
    List<Role> findByEid(@Param("eid") Long eid);
    
    @Select("select * from cr_role")
    List<Role> list();
}