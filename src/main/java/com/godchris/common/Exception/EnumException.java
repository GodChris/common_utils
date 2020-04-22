package com.godchris.common.Exception;

/**
 * 枚举异常接口
 *
 * @author Chris
 * @create 2020-04-22 15:25
 **/
public interface EnumException {
	/**
	 * 获取枚举的状态码
	 *
	 * @return
	 */
	Integer getCode();

	/**
	 * 获取枚举的异常信息
	 *
	 * @return
	 */
	String getMsg();
}
