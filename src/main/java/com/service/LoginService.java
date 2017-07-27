package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.UsersManager;
import com.domain.User;

/** 
 * @author yanghd 
 * @date 2017年7月18日 下午2:47:39 
 * @description 类说明 
 */
@Component
public class LoginService {
	@Autowired
	UsersManager usersManager;
	
	public List<User> getAllUsers(){
		List<User> users = usersManager.getAllUsers();
		return users;
	}
	 
	
	public List<String> getAdminInfo(){
		List<String> admin = usersManager.getRedisAdmin();
		return admin;
	}
}
