package com.godchris.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * String的校验
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlank {
	/**
	 * 是否必要
	 *
	 * @return
	 */
	boolean require() default true;

	/**
	 * 注释
	 *
	 * @return
	 */
	String message() default "The property must not be blank";
}
