package com.godchris.common.Exception;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chris
 * @create 2020-04-22 15:27
 **/
public class BaseException extends RuntimeException implements Serializable {
	/**
	 * 自定义各类异常
	 */
	public static final int UNKNOWN_EXCEPTION = 0;
	public static final int NETWORK_EXCEPTION = 1;
	public static final int TIMEOUT_EXCEPTION = 2;
	public static final int BIZ_EXCEPTION = 3;
	public static final int FORBIDDEN_EXCEPTION = 4;
	public static final int SERIALIZATION_EXCEPTION = 5;

	protected int code;
	protected final Map<String, Object> context = new HashMap();

	public BaseException() {
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(int code) {
		this.code = code;
	}

	public BaseException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public BaseException(int code, String message) {
		super(message);
		this.code = code;
	}

	public BaseException(int code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public BaseException setContextValue(String key, Object value) {
		if (value == null) {
			this.context.remove(key);
		} else {
			this.context.put(key, value);
		}

		return this;
	}

	public Object getContextValue(String key) {
		return this.context.get(key);
	}

	public boolean isBiz() {
		return this.code == 3;
	}

	public boolean isForbidded() {
		return this.code == 4;
	}

	public boolean isTimeout() {
		return this.code == 2;
	}

	public boolean isNetwork() {
		return this.code == 1;
	}

	public boolean isSerialization() {
		return this.code == 5;
	}
}
