package com.aop;

import org.springframework.stereotype.Component;

/** 
 * @author yanghd 
 * @date 2017年7月25日 下午12:26:12 
 * @description 类说明 
 */
@Component
public class SleepMan implements Sleepable{

	public void sleep() {
		System.out.println("睡觉中");
	}
	
}
