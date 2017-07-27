package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.service.LoginService;
import com.annotation.ControllerAnnotation;
import com.aop.SleepMan;
import com.domain.User;

/** 
 * @author yanghd 
 * @date 2017年7月18日 上午10:13:39 
 * @description 类说明 
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	@Autowired
	LoginService loginService;
	@Autowired
	SleepMan sm;
	@Autowired
	Test test;
	
	private  static  final Logger logger = LoggerFactory.getLogger(IndexController. class);  
	
	@ResponseBody
	@RequestMapping("/wel")
	public String welPage(){
		logger.info("/index/wel");
		String host = test.getResources();
		return host;
	}
	
	@ResponseBody
	@RequestMapping("/allUsers")
	@ControllerAnnotation(description = "删除用户")
	public List<User> getAllUsers(){
		sm.sleep();
		List<User> users = loginService.getAllUsers();
		logger.info("/index/allUsers");
		return users;
	}
	
	@ResponseBody
	@RequestMapping("/adminUser")
	public List<String> getAdmin(){
		List<String> user = loginService.getAdminInfo();
		return user;
	}
	
	
	
}
