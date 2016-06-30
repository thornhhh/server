package com.call110.business.dao.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.call110.business.entity.Employee;
import com.call110.business.vo.EmployeeVO;
public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    
    List<EmployeeVO> list(@Param("name") String name, @Param("page") int page);

	long maxPage(@Param("name") String name);
	
	@Update("update cr_employee set is_del=1 where id=#{param1}")
	void delete(Long id);
	
	@Update("update cr_employee set password='e10adc3949ba59abbe56e057f20f883e' where id=#{param1}")
	void reset(Long id);

	EmployeeVO get(long id);
	
    @Select("select * from cr_employee where username=#{username} and is_del = 0")
    @ResultMap("BaseResultMap")
    Employee findByUsername(@Param("username") String username);

    @Select("select count(*) from cr_employee where username=#{param1} and is_del = 0")
	int countOfUserName(String name);
}