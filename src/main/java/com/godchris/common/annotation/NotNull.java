package com.godchris.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一般对象的空校验
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
	/**
	 * 是否必要，true不可为null
	 *
	 * @return
	 */
	boolean require() default true;

	/**
	 * 注释
	 *
	 * @return
	 */
	String message() default "The property must not be null";
}
