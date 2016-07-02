package com.call110.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController{
	
	@RequestMapping("/")
	public String toIndex() {
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String main() {
		return "index";
	}
	
	@RequestMapping("/403")
	public String $403() {
		return "403";
	}
	
	@RequestMapping("/browser")
	public String tobrowser() {
		return "using-shit-browser";
	}
	
}