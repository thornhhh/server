package com.call110.common.security;

import java.util.Map;
import java.util.stream.Collectors;

import com.call110.business.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import com.call110.business.entity.Permission;

public class SimpleFilterChainDefinitionsService extends AbstractFilterChainDefinitionsService {
	
	public SimpleFilterChainDefinitionsService(ShiroFilterFactoryBean shiroFilter) {
		super(shiroFilter);
	}
	
	@Autowired
	private PermissionService permissionService;
	
	@Override
	public Map<String, String> initOtherPermission() {
		return permissionService.findAll().stream().collect(Collectors.toMap(Permission::getUrl, this::convertPermissionDefinition));
	}
	
	@Override
	public String convertPermissionDefinition(Permission permission) {
		if(StringUtils.isBlank(permission.getName()) || permission.getType() == null){
			return "";
		}
		if(permission.getType() == 1){
			return PREMISSION_STRING.replace("{0}", permission.getName().trim());
		}else if(permission.getType() == 2){
			return ROLE_STRING.replace("{0}", permission.getName().trim());
		}else{
			return permission.getName().trim();
		}
	}

}
