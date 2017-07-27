package com.controller;

import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Component;


/** 
 * @author yanghd 
 * @date 2017年7月26日 下午1:43:28 
 * @description 类说明 
 */
@Component
public class Test {
	Properties prop =  new Properties();  
	public String getResources(){
		try {
			prop.load(Test.class.getResourceAsStream("/redis.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String host = prop.getProperty("redis.host"); 
		return host;
	}
}
