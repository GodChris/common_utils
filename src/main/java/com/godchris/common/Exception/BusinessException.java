package com.godchris.common.Exception;

import java.text.MessageFormat;

/**
 * 业务异常定义类
 *
 * @author Chris
 * @create 2020-04-22 15:18
 **/
public class BusinessException extends BaseException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6888891304358182599L;

	/**
	 * 前端特殊显示异常数据返回
	 */
	private Object errorData;

	/**
	 * 设置555为默认异常状态码 因500要作为前端的判断条件，而BusinessException需要给用户友好提示
	 * 在此不使用500，改为555
	 */
	private static final int DEFAULT_CODE = 555;

	/**
	 * 批量操作异常的code
	 */
	private static final int FORM_DATA_RESULT = -100;

	public BusinessException() {
		super(DEFAULT_CODE);
	}

	public BusinessException(Integer code, String errorMsg) {
		super(code, errorMsg);
	}

	public BusinessException(Integer code, String errorMsg, Object errorData) {
		super(code, errorMsg);
		this.errorData = errorData;
	}

	public BusinessException(String errorMsg, Object errorData) {
		super(DEFAULT_CODE, errorMsg);
		this.errorData = errorData;
	}

	public BusinessException(EnumException enumClass) {
		super(enumClass.getCode(), enumClass.getMsg());
	}

	/**
	 * 填充占位符x
	 *
	 * @param enumClass
	 * @param message
	 */
	public BusinessException(EnumException enumClass, Object... message) {
		super(enumClass.getCode(), MessageFormat.format(enumClass.getMsg(), message));
	}

	public BusinessException(String errorMsg) {
		super(DEFAULT_CODE, errorMsg);
	}

	public int getCode() {
		return code;
	}

	public BusinessException setCode(Integer code) {
		this.code = code;
		return this;
	}

	public Object getErrorData() {
		return errorData;
	}

	public BusinessException setErrorData(Object errorData) {
		this.errorData = errorData;
		return this;
	}
}
