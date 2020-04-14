package com.godchris.common.apesct;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.godchris.common.annotation.XLog;
import com.godchris.common.constant.Constant;
import com.godchris.common.utils.CommonUtil;
import com.google.common.collect.Lists;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Xlog切面类，结合自定义注解，实现日志打印
 *
 * @author Chris
 * @Component 把切面类加入到IOC容器中
 * @create 2020-04-14 10:29
 **/
@Aspect
@Component
public class XLogAspect extends CommonUtil {

	@Override
	protected Class initClass() {
		return this.getClass();
	}

	/**
	 * aop处理方法，环绕通知
	 * 目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
	 *
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(com.godchris.common.annotation.XLog)")
	public Object process(ProceedingJoinPoint point) throws Throwable {
		//访问目标方法的参数
		Object[] args = point.getArgs();
		//获取类的全路径
		StringBuffer requestArgs = new StringBuffer();
		for (Object obj : args) {
			try {
				requestArgs.append(JSONObject.toJSONString(obj));
			} catch (Exception e) {
				requestArgs.append(obj.toString());
			}
		}
		writeLogStringByField(Constant.LOG_INFO, requestArgs.toString(), (Object) getLogKey(point, Constant.REQUEST));
		//用改变后的参数执行目标方法
		return point.proceed(args);
	}

	private String[] getLogKey(JoinPoint point, String keyType) {
		List<String> logKeyList = Lists.newArrayList();
		XLog xLogDiversion = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(XLog.class);
		if (isEmpty(xLogDiversion.logKey())) {
			String methodName = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
			logKeyList.add(methodName + Constant.ZWJ + keyType);
		} else {
			logKeyList.add(xLogDiversion.logKey() + Constant.ZWJ + keyType);
		}
		if (isNotEmpty(xLogDiversion.entityField())) {
			String[] fields = xLogDiversion.entityField();
			Object[] target = point.getArgs();
			if (isNotEmpty(target)) {
				setLogKeyByField(fields, target, logKeyList);
			}
		}
		return logKeyList.toArray(new String[logKeyList.size()]);
	}

	private void setLogKeyByField(String[] fields, Object[] target, List<String> logKeyList) {
		for (int i = 0; i < fields.length; i++) {
			for (int x = 0; x < target.length; x++) {
				if (isNotEmpty(target[x])) {
					try {
						JSONObject targetObject = JSON.parseObject(JSONObject.toJSONString(target[x]));
						if (isNotEmpty(targetObject.get(fields[i]))) {
							logKeyList.add(targetObject.get(fields[i]).toString());
						}
					} catch (Exception e) {
						writeLog("setLogKeyByField", Constant.LOG_ERROR, e.getMessage());
					}
				}
			}
		}
	}

	/**
	 * 日志打印
	 *
	 * @param logKey
	 * @param logLevel
	 * @param logInfo
	 */
	protected void writeLogStringByField(String logLevel, String logInfo, Object... logKey) {
		writeLogByField(logLevel, logInfo, logKey);
	}

	/**
	 * 返回通知：目标方法正常执行完毕时执行以下代码
	 *
	 * @param point
	 * @param returnValue
	 * @return
	 * @throws Throwable
	 */
	@AfterReturning(pointcut = "@annotation(com.godchris.common.annotation.XLog)", returning = "returnValue")
	public Object after(JoinPoint point, Object returnValue) throws Throwable {
		//获取类的全路径
		if (returnValue instanceof JSONObject) {
			writeLogStringByField(Constant.LOG_INFO, returnValue.toString(), (Object) getLogKey(point, Constant.RESPONSES));
		} else {
			writeLogByField(Constant.LOG_INFO, returnValue, (Object) getLogKey(point, Constant.RESPONSES));
		}
		return point.getTarget();
	}

	/**
	 * 异常通知：目标方法发生异常的时候执行以下代码
	 *
	 * @param point
	 * @param e
	 */
	@AfterThrowing(pointcut = "@annotation(com.godchris.common.annotation.XLog)", throwing = "e")
	public void afterThrowable(JoinPoint point, Throwable e) {
		writeLogByField(Constant.LOG_ERROR, e.getMessage(), (Object) getLogKey(point, Constant.ERROR));
	}
}
