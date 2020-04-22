package com.godchris.common.utils;

import com.godchris.common.Exception.BusinessException;
import com.godchris.common.Exception.ErrorCode;
import com.godchris.common.annotation.NotBlank;
import com.godchris.common.annotation.NotEmpty;
import com.godchris.common.annotation.NotNull;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 对象参数校验，配合自定义注解使用
 *
 * @author Chris
 * @create 2020-04-22 15:43
 **/
public class ParamsCheck {
	private final static List<Object> ANNOTATION_LIST = Lists.newArrayList();

	static {
		ANNOTATION_LIST.add(NotNull.class);
		ANNOTATION_LIST.add(NotEmpty.class);
		ANNOTATION_LIST.add(NotBlank.class);
	}

	public static List<String> checkForList(Object source) throws IllegalAccessException {
		ObjectsCheck.requireNonNull(source, ErrorCode.INVALID_PARAMS);
		//获取所有类中声明的字段
		Field[] fields = source.getClass().getDeclaredFields();
		//字段校验提示
		List<String> result = null;
		if (fields.length > 0) {
			result = new ArrayList<>();
			for (Field field : fields) {
				//访问权限，需提前设置
				field.setAccessible(true);
				Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
				if (declaredAnnotations == null || declaredAnnotations.length == 0) {
					continue;
				}
				for (Annotation temp : declaredAnnotations) {
					if (ANNOTATION_LIST.contains(temp.annotationType())) {
						String message = check(source, field, temp);
						if (StringUtils.isNotBlank(message)) {
							StringBuilder builder = new StringBuilder(field.getName());
							result.add(builder.append(":").append(message).toString());
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * @param sourse     对象
	 * @param field      字段
	 * @param annotation 注解对象
	 * @return
	 * @throws IllegalAccessException
	 */
	private static String check(Object sourse, Field field, Annotation annotation) throws IllegalAccessException {
		if (annotation == null) {
			return null;
		}
		field.setAccessible(true);
		Object value = field.get(sourse);
		if (value instanceof String) {
			//字符串类型
			String str = (String) value;
			//做空字符串检查(包含了null、empty校验)
			return checkNotBlank(annotation, str);
		} else {
			//非字符串类型
			//1、null校验
			String message = checkNotNull(annotation, value);
			if (StringUtils.isNotBlank(message)) {
				return message;
			}
			//2、第1步通过后，做notEmpty检查(Collection、Map)
			message = checkNotEmpty(annotation, value);
			return message;
		}
	}

	private static String checkNotNull(Annotation annotation, Object o) {
		if (annotation == null) {
			throw new BusinessException("annotation is not defined");
		}
		if (o == null && annotation instanceof NotNull) {
			NotNull notNull = (NotNull) annotation;
			if (!notNull.require()) {
				return null;
			}
			return notNull.message();
		}
		return null;
	}

	private static String checkNotBlank(Annotation annotation, String str) {
		if (annotation == null) {
			throw new BusinessException("annotation is not defined");
		}
		if (StringUtils.isBlank(str) && annotation instanceof NotBlank) {
			NotBlank notBlank = (NotBlank) annotation;
			if (!notBlank.require()) {
				return null;
			}
			return notBlank.message();
		}
		return null;
	}

	private static String checkNotEmpty(Annotation annotation, Object o) {
		if (annotation == null) {
			throw new BusinessException("annotation is not defined");
		}
		if ((o instanceof Collection && CollectionUtils.isEmpty((Collection) o) || o instanceof Map && CollectionUtils.isEmpty((Map) o)) && annotation instanceof NotEmpty) {
			NotEmpty notEmpty = (NotEmpty) annotation;
			if (!notEmpty.require()) {
				return null;
			}
			return notEmpty.message();
		}
		return null;
	}
}
