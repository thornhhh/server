package com.call110.config;

import com.call110.business.service.PermissionService;
import com.call110.common.security.RolesAuthorizationFilter;
import com.call110.common.security.ShiroRealm;
import com.call110.common.security.SimpleFilterChainDefinitionsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfiguration {
	private final static Log log = LogFactory.getLog(ShiroConfiguration.class);
	@Bean
	public ShiroRealm shiroRealm(EhCacheManager cacheManager) {
		log.info("+++++++++++++++ShiroRealm++++++++++++++++");
	    ShiroRealm shiroRealm = new ShiroRealm(); 
	    shiroRealm.setCachingEnabled(true);
	    shiroRealm.setAuthorizationCachingEnabled(true);
//	    <property name="credentialsMatcher" ref="credentialsMatcher" />
//		<property name="cachingEnabled" value="true" />
//		<property name="authenticationCachingEnabled" value="true" />
//		<property name="authenticationCacheName" value="authenticationCache" />
//		<property name="authorizationCachingEnabled" value="true" />
//		<property name="authorizationCacheName" value="authorizationCache" />
	    return shiroRealm;
	}
	
	/**
	 * Shiro生命周期处理器
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() { 
		log.info("+++++++++++++++LifecycleBeanPostProcessor++++++++++++++++");
		return new LifecycleBeanPostProcessor();
	}
	
	/**
	 * advisor代理 需要在LifecycleBeanPostProcessor实例化后
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		log.info("+++++++++++++++DefaultAdvisorAutoProxyCreator++++++++++++++++");
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
    }
	
	/** 
	 * 安全管理器
	 * @param shiroRealm
	 * @param cacheManager
	 * @return
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm, EhCacheManager cacheManager) {
		log.info("+++++++++++++++DefaultWebSecurityManager++++++++++++++++");
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm); // <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 --> 
		securityManager.setCacheManager(cacheManager);
		return securityManager;
    }
	
	/** 
	 * shiro安全解析器
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		log.info("+++++++++++++++AuthorizationAttributeSourceAdvisor++++++++++++++++");
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
    }
	
	/**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）
     */
//	private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean, StudentService stuService, IScoreDao scoreDao){ /////////////////////// 下面这些规则配置最好配置到配置文件中 /////////////////////// Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>(); // authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter filterChainDefinitionMap.put("/user", "authc");// 这里为了测试，只限制/user，实际开发中请修改为具体拦截的请求规则 // anon：它对应的过滤器里面是空的,什么都没做 logger.info("##################从数据库读取权限规则，加载到shiroFilter中##################");
		/////////////////////// 下面这些规则配置最好配置到配置文件中 ///////////////////////
//		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//		// authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
//		filterChainDefinitionMap.put("/user", "authc");// 这里为了测试，只限制/user，实际开发中请修改为具体拦截的请求规则
//		// anon：它对应的过滤器里面是空的,什么都没做
//		logger.info("##################从数据库读取权限规则，加载到shiroFilter中##################");
//		filterChainDefinitionMap.put("/user/edit/**", "authc,perms[user:edit]");// 这里为了测试，固定写死的值，也可以从数据库或其他配置中读取
//		
//		filterChainDefinitionMap.put("/login", "anon");
//		filterChainDefinitionMap.put("/**", "anon");//anon 可以理解为不拦截
//		
//		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//    }
	
	/**
     * ShiroFilter
     * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。
     * 实际项目中，请使用自己的Service来处理业务逻辑。
     * @param securityManager
     * @create 2016年1月14日
     */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager,PermissionService permissionService) {
		log.info("+++++++++++++++ShiroFilter++++++++++++++++");
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean(); // 必须设置 SecurityManager  
        shiroFilter.setSecurityManager(securityManager); // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面 
        shiroFilter.setLoginUrl("/login"); // 登录成功后要跳转的连接 
        shiroFilter.setSuccessUrl("/index");
        shiroFilter.setUnauthorizedUrl("/403");
        Map<String, Filter> filterMap = new HashMap<String, Filter>();
        filterMap.put("role", new RolesAuthorizationFilter());
        filterMap.put("authc", new FormAuthenticationFilter());
        shiroFilter.setFilters(filterMap);
        return shiroFilter;
    }
	
	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
		log.info("+++++++++++++++SimpleMappingExceptionResolver++++++++++++++++");
		SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("org.apache.shiro.authz.UnauthorizedException", "/403");
		mappings.setProperty("org.apache.shiro.authz.UnauthenticatedException", "/login");
		simpleMappingExceptionResolver.setExceptionMappings(mappings);
		return simpleMappingExceptionResolver;
	}
	
	@Bean
	public SimpleFilterChainDefinitionsService filterChainDefinitionsService(ShiroFilterFactoryBean shiroFilter) {
		log.info("+++++++++++++++SimpleFilterChainDefinitionsService++++++++++++++++");
		SimpleFilterChainDefinitionsService filterChainDefinitionsService = new SimpleFilterChainDefinitionsService(shiroFilter); // 必须设置 shiroFilter  
		filterChainDefinitionsService.setDefinitionFilePath("classpath:shiro_definitions.properties");
		return filterChainDefinitionsService;
	}
	
	
}
