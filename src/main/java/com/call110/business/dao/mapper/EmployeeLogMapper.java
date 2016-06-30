package com.call110.business.dao.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.call110.business.entity.EmployeeLog;
import com.call110.business.vo.LogVO;
public interface EmployeeLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EmployeeLog record);

    int insertSelective(EmployeeLog record);

    EmployeeLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EmployeeLog record);

    int updateByPrimaryKey(EmployeeLog record);

	List<LogVO> list(@Param("eid") String eid, @Param("beginTime") Long beginTime, @Param("endTime") Long endTime, @Param("page") int page);

	long maxPage(@Param("eid") String eid, @Param("beginTime") Long beginTime, @Param("endTime") Long endTime);
}