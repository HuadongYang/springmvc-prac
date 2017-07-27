package com.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/** 
 * @author yanghd 
 * @date 2017年7月25日 下午2:30:58 
 * @description 类说明 
 */
@Component
@Aspect
public class AnnotationInterceptor {
	public AnnotationInterceptor() {
		
	}
	
	@Pointcut("@annotation(com.annotation.ControllerAnnotation)")
	public void annotateCut(){}
	
	@Before("annotateCut()")
	public void beforeAnnotate(){
		System.out.println("进入之前");
	}
	
	@AfterReturning("annotateCut()")
	public void AfterAnnotate(){
		System.out.println("出来之后");
	}
}
