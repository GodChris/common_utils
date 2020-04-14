package com.godchris.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.godchris.common.constant.Constant;
import io.github.logtube.Logtube;
import io.github.logtube.core.IEventLogger;

/**
 * 通用工具
 *
 * @author Chris
 * @create 2020-04-14 10:30
 **/
public abstract class CommonUtil {
	/**
	 * XLog引入使用
	 */
	private static final IEventLogger LOGGER = Logtube.getLogger();

	/**
	 * 提供子类拓展
	 * @return
	 */
	protected abstract Class initClass();

	/**
	 * 日志打印
	 * @param logKey keyword
	 * @param logLevel 日志信息级别
	 * @param logInfo 日志信息
	 */
	protected void writeLog(String logKey, String logLevel, Object logInfo) {
		writeLogByField(logLevel, logInfo, logKey);
	}

	/**
	 * 日志打印
	 * @param logLevel
	 * @param logInfo
	 * @param logKey
	 */
	protected void writeLogByField(String logLevel, Object logInfo, Object... logKey) {
		if (Constant.LOG_DEBUG.equals(logLevel)) {
			printDebugLog(logInfo, logKey);
		} else if (Constant.LOG_ERROR.equals(logLevel)) {
			printErrorLog(logInfo, logKey);
		} else {
			printInfoLog(logInfo, logKey);
		}
	}

	/**
	 * 打印info日志
	 *
	 * @param logInfo
	 * @param logKey
	 */
	protected void printInfoLog(Object logInfo, Object... logKey) {
		if (logInfo instanceof JSONObject) {
			LOGGER.keyword(logKey).info(((JSONObject) logInfo).toJSONString());
		} else {
			LOGGER.keyword(logKey).info(String.valueOf(logInfo));
		}
	}

	/**
	 * 打印error日志
	 *
	 * @param logInfo
	 * @param logKey
	 */
	protected void printErrorLog(Object logInfo, Object... logKey) {
		if (logInfo instanceof JSONObject) {
			LOGGER.keyword(logKey).error(((JSONObject) logInfo).toJSONString());
		} else {
			LOGGER.keyword(logKey).error(String.valueOf(logInfo));
		}
	}

	/**
	 * 打印debug日志
	 *
	 * @param logInfo
	 * @param logKey
	 */
	protected void printDebugLog(Object logInfo, Object... logKey) {
		if (logInfo instanceof JSONObject) {
			LOGGER.keyword(logKey).debug(((JSONObject) logInfo).toJSONString());
		} else {
			LOGGER.keyword(logKey).debug(String.valueOf(logInfo));
		}
	}

	/**
	 * 非空判断
	 *
	 * @param object
	 * @return
	 */
	protected boolean isNotEmpty(Object object) {

		return !(object == null || "".equals(object) || Constant.NULL.equals(object));
	}

	/**
	 * 空判断
	 *
	 * @param object
	 * @return
	 */
	protected boolean isEmpty(Object object) {

		return (object == null || "".equals(object) || Constant.NULL.equals(object));
	}
}
