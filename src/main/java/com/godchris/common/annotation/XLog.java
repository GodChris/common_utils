package com.godchris.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XLog {
	//XLog keyword
	String logKey() default "";
	//entityField to print
	String[] entityField() default "";
}
