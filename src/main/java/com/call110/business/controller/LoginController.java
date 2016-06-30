package com.call110.business.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.call110.common.util.MD5;


@Controller
public class LoginController{
	
	/**
	 * @Description: 跳转登录页
	 * @return   
	 * @return String 
	 */
	@RequestMapping("/tologin")
	public String toLogin() {
		return "login";
	}
	
	/**
	 * @Description: 登录
	 * @return   
	 * @return String 
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, @RequestParam(required=false) String username, @RequestParam(required=false) String password, RedirectAttributes attr) {
		try {
			// 获取当前的Subject
			Subject currentUser = SecurityUtils.getSubject();
			if(currentUser.isAuthenticated()){
				return "redirect:/index";
			}
			if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
				return "login";
			}
			UsernamePasswordToken token = new UsernamePasswordToken(username.trim(), MD5.md5(password.trim()));
//			token.setRememberMe(true);
			currentUser.login(token);
			//获取用户信息
		} catch (AuthenticationException e) {
//			e.printStackTrace();
			attr.addFlashAttribute("msg", "1");
			return "redirect:login";
		}
		return "redirect:/index";
	}
	/**
	 * @Description: 注销
	 * @return   
	 * @return String 
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		try {
			// 获取当前的Subject
			Subject currentUser = SecurityUtils.getSubject();
			if(currentUser == null){
				return "index";
			}
			currentUser.logout();
		} catch (AuthenticationException e) {
		}
		return "redirect:login";
	}
	
}