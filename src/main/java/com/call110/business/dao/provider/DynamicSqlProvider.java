package com.call110.business.dao.provider;

import java.util.Map;



public class DynamicSqlProvider {
	/**
	 * @Description: 通过id，type动态获取环信用户查询语句
	 * @param id
	 * @param type
	 * @return   
	 * @return String 
	 */
	public String selectImUser(Map<String, Object> parameter){
//		Integer id = (Integer) parameter.get("id");
		Integer type = (Integer) parameter.get("type");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM CR_IM_USER WHERE ");
		if(0 == type){
			sql.append("HR_ID =#{id}");
		}else{
			sql.append("UID =#{id}");
		}
		return sql.toString();
	}
}
