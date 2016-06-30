package com.call110.common.security;

import java.util.stream.Collectors;

import com.call110.business.service.EmployeeService;
import com.call110.business.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.call110.business.entity.Employee;

public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private RoleService roleService;
	
	/**
	 * 权限认证，为当前登录的Subject授予角色和权限 
	 * 本例中该方法的调用时机为需授权资源被访问时
	 * 并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
	 * 如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//	    logger.info("##################执行Shiro权限认证##################");
	    //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
	    String loginName = (String) super.getAvailablePrincipal(principalCollection); 
	    //到数据库查是否有此对象
	    Employee employee = employeeService.findByUsername(loginName);// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
	    if(employee != null){
	        //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
	        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//	        List<Role> list = roleService.findRolesByEmp(employee.getId());
//	        List<Permission> lists = list.get(0).getPermissions();
	        info.addStringPermissions(roleService.findRolesByEmp(employee.getId()).stream()
	        		.peek(r->info.addRole(r.getName())).flatMap(r->r.getPermissions().stream())
	        		.map(p->p.getName()).collect(Collectors.toSet()));
	        return info;
	    }
	    // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
	    return null;
	}
	
	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
	    //UsernamePasswordToken对象用来存放提交的登录信息
	    UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
	    //查出是否有此用户
	    Employee employee = employeeService.findByUsername(token.getUsername());
	    // 获取当前的Subject
		
	    if(employee != null){
	    	SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(employee.getUsername(), employee.getPassword(), getName());
	    	Subject currentUser = SecurityUtils.getSubject();
	    	Session session = currentUser.getSession();
	    	session.setAttribute("user", employee);
	        // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
	        return info;
	    }
	    return null;
	}

}
