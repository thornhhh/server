package com.call110.business.dao.mapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import com.call110.business.entity.EmployeeRole;
public interface EmployeeRoleMapper {
    int deleteByPrimaryKey(EmployeeRole key);

    int insert(EmployeeRole record);

    int insertSelective(EmployeeRole record);
    
    @Delete("delete from cr_employee_role where eid = #{param1}")
    void delete(Long eid);
    
    @Insert("insert into cr_employee_role (eid,rid) values(#{param1},#{param2})")
    void set(Long eid, Long rid);
}