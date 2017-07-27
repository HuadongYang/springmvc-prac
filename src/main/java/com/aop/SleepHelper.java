package com.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/** 
 * @author yanghd 
 * @date 2017年7月25日 下午12:14:36 
 * @description 类说明 
 */
@Component
@Aspect
public class SleepHelper {
	public SleepHelper(){

	}
	
	@Pointcut("execution(* *.sleep())")
	public void sleepPoint(){}
	
	@Before("sleepPoint()")
	public void beforeSleep(){
		System.out.println("睡觉前");
	}
	
	@AfterReturning("sleepPoint()")
	public void afterSleep(){
		System.out.println("睡觉后");
	}
}
