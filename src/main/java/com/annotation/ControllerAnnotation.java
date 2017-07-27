package com.annotation;
import java.lang.annotation.*; 
/** 
 * @author yanghd 
 * @date 2017年7月25日 下午2:25:56 
 * @description 类说明 
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface ControllerAnnotation {
	String description()  default "";
}
