package com.call110.business.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.call110.business.dao.mapper.EmployeeLogMapper;
import com.call110.business.vo.LogVO;
import com.call110.common.pageable.Page;
import com.call110.common.util.DateUtil;

@Service
public class EmployeeLogService {
	
	@Autowired EmployeeLogMapper mapper;
	public List<LogVO> list(String eid, Long beginTime, Long endTime, int page) {
		return mapper.list(eid, beginTime, endTime, page);
	}
	public long maxPage(String eid, Long beginTime, Long endTime) {
		return mapper.maxPage(eid, beginTime, endTime);
	}
	public void list(HttpServletRequest request) {
		String eid = StringUtils.trimToNull(request.getParameter("eid"));//搜索条件：员工id
		String _beginTime = StringUtils.trimToNull(request.getParameter("beginTime"));//搜索条件：开始时间
		String _endTime = StringUtils.trimToNull(request.getParameter("endTime"));//搜索条件：结束时间
		
		//处理请求参数
		Long beginTime = StringUtils.isEmpty(_beginTime) ? null : DateUtil.parse(_beginTime).getTime();
		Long endTime = StringUtils.isEmpty(_endTime) ? null : DateUtil.parse(_endTime).getTime();
		request.setAttribute("list", list(eid,beginTime,endTime,Page.of(request)));
		request.setAttribute("maxPage", maxPage(eid,beginTime,endTime));
	}

}
