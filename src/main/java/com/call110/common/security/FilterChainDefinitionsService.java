package com.call110.common.security;

import java.util.Map;

import com.call110.business.entity.Permission;

public interface FilterChainDefinitionsService {
	public static final String PREMISSION_STRING = "perms[{0}]"; // 资源结构格式  
	public static final String ROLE_STRING = "role[{0}]"; // 角色结构格式  
	public static final String PREMISSION_PREFIX = "$";
	public static final String ROLE_PREFIX = "@";
	/** 初始化框架权限资源配置 */  
	public abstract void init();  
	  
	/** 重新加载框架权限资源配置 (强制线程同步) */  
	public abstract void updatePermission();  
	  
	/** 初始化第三方权限资源配置 */  
	public abstract Map<String, String> initOtherPermission();
	
	/** 转换成Definition 如 account:index To perms[account:index] */ 
	public String convertPermissionDefinition(Permission permission);
	
}
