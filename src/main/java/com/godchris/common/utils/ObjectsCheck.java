package com.godchris.common.utils;

import com.godchris.common.Exception.BusinessException;
import com.godchris.common.Exception.EnumException;
import com.godchris.common.Exception.ErrorCode;
import org.apache.commons.lang3.StringUtils;

/**
 * 对象校验
 *
 * @author Chris
 * @create 2020-04-22 16:16
 **/
public class ObjectsCheck {

	public static <T> T requireNonNull(T obj) {
		return requireNonNull(obj, ErrorCode.INVALID_PARAMS);
	}

	public static <T> T requireNonNull(T obj, String message) {
		if (StringUtils.isBlank(message)) {
			throw new NullPointerException();
		}
		if (obj == null) {
			throw new BusinessException(message);
		} else {
			if (obj instanceof String) {
				if ("".equals(((String) obj).trim())) {
					throw new BusinessException(message);
				}
			}
		}
		return obj;
	}

	public static <T> T requireNonNull(T obj, EnumException ex) {
		if (ex == null) {
			throw new NullPointerException();
		}

		if (obj == null) {
			throw new BusinessException(ex);
		} else {
			if (obj instanceof String) {
				if ("".equals(((String) obj).trim())) {
					throw new BusinessException(ex);
				}
			}
		}
		return obj;
	}
}
