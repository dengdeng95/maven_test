package com.dhf.aop;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;

/**
 * 
 * @author denghf
 *
 */
@Inherited
@Target({ElementType.FIELD,ElementType.METHOD}) //1.CONSTRUCTOR:用于描述构造器 2.FIELD:用于描述域 3.LOCAL_VARIABLE:用于描述局部变量 4.METHOD:用于描述方法 5.PACKAGE:用于描述包 6.PARAMETER:用于描述参数 7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
@Retention(RetentionPolicy.RUNTIME) //1.SOURCE:在源文件中有效（即源文件保留）  2.CLASS:在class文件中有效（即class保留） 3.RUNTIME:在运行时有效（即运行时保留）
public @interface AnnotationCustom {
	String value() default "";
}
