package com.godchris.common.Exception;

/**
 * 异常枚举，方便定义异常枚举抛出异常信息
 *
 * @author Chris
 * @create 2020-04-22 16:25
 **/
public enum ErrorCode implements EnumException {

	ERROR_DEFAULT(500, "系统繁忙，请稍后重试"),
	SUCCESS(0, "success"),
	ERROR(-1, "error"),
	INVALID_PARAMS(-2, "请求参数缺失或无效");

	private Integer code;
	private String msg;

	ErrorCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg == null ? "" : msg.trim();
	}
}
