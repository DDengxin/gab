package com.tengzhi.section;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tengzhi.service.Logservice;
import com.tengzhi.tool.RequestUtils;

@Component
@Aspect
public class LogSection {
	@Autowired
	private Logservice logservice;
	ThreadLocal<Long> currentTime = new ThreadLocal<>();
	private static String[] types = { "java.lang.Integer", "java.lang.Double", "java.lang.Float", "java.lang.Long", "java.lang.Short", "java.lang.Byte", "java.lang.Boolean",
			"java.lang.Char", "java.lang.String", "int", "double", "long", "short", "byte", "boolean", "char", "float" };

	/**
	 * 配置切入点
	 */
	@Pointcut("@annotation(com.tengzhi.annotations.Log)")
	public void logPrint() {
	}

	/**
	 * 配置环绕通知,使用在方法logPointcut()上注册的切入点
	 *
	 * @param joinPoint 
	 */
	@Around("logPrint()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result;
		currentTime.set(System.currentTimeMillis());
		result = joinPoint.proceed();
		com.tengzhi.model.Log log = new com.tengzhi.model.Log("1", System.currentTimeMillis() - currentTime.get());
		currentTime.remove();
		HttpServletRequest request = RequestUtils.getHttpServletRequest();
		String classType = joinPoint.getTarget().getClass().getName();
		Class<?> clazz = Class.forName(classType);
		log.setClassAddress(clazz.getName());// 类名地址
		log.setMethod(joinPoint.getSignature().getName());// 方法名
		log.setBrowser(RequestUtils.getBrowser(request));// 浏览器
		log.setCreateTime(new Timestamp(System.currentTimeMillis()));
		log.setRequestIp(RequestUtils.getIp(request));// ip
		log.setUsername(RequestUtils.getUsername());// 用户名
		log.setAddress(RequestUtils.getCityInfo(RequestUtils.getIp(request)));// ip地址
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		com.tengzhi.annotations.Log aopLog = method.getAnnotation(com.tengzhi.annotations.Log.class);
		if("2".equals(aopLog.type())) {
			log.setDescription("登录");//描述
		}else if("3".equals(aopLog.type())){
			log.setDescription("注销");//描述
		}else{
			// 参数
			String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
			log.setParams(tojson(argNames, joinPoint));
			log.setDescription(aopLog.value());//描述
		}
		logservice.save(log);

		return result;
	}

	/**
	 * 配置异常通知
	 *
	 * @param joinPoint
	 * @param e
	 * @throws ClassNotFoundException 
	 */
	@AfterThrowing(pointcut = "logPrint()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) throws ClassNotFoundException {
		currentTime.set(System.currentTimeMillis());
		com.tengzhi.model.Log log = new com.tengzhi.model.Log("2", System.currentTimeMillis() - currentTime.get());
		currentTime.remove();
		HttpServletRequest request = RequestUtils.getHttpServletRequest();
		String classType = joinPoint.getTarget().getClass().getName();
		Class<?> clazz = Class.forName(classType);
		log.setClassAddress(clazz.getName());// 类名地址
		log.setMethod(joinPoint.getSignature().getName());// 方法名
		log.setBrowser(RequestUtils.getBrowser(request));// 浏览器
		log.setRequestIp(RequestUtils.getIp(request));// ip
		log.setUsername(RequestUtils.getUsername());// 用户名
		log.setCreateTime(new Timestamp(System.currentTimeMillis()));
		log.setAddress(RequestUtils.getCityInfo(RequestUtils.getIp(request)));// ip地址
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		com.tengzhi.annotations.Log aopLog = method.getAnnotation(com.tengzhi.annotations.Log.class);
		log.setDescription(aopLog.value());//描述
		log.setExceptionDetail(e.getMessage());
		logservice.save(log);
	}

	/**
	 * 得到参数的值
	 * 
	 * @param obj
	 */
	public static String getFieldsValue(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		String typeName = obj.getClass().getTypeName();
		for (String t : types) {
			if (t.equals(typeName)) {
                return "";
            }
		}
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (Field f : fields) {
			f.setAccessible(true);
			try {
				for (String str : types) {
					if (f.getType().getName().equals(str)) {
						sb.append(f.getName() + " = " + f.get(obj) + ", ");
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if (sb.length() - 2 > 0) {
            sb = new StringBuilder(sb.substring(0, sb.length() - 2));
        }
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 把方法里面的参数转换成一个json格式的字符串
	 * 
	 * @param paramNames
	 * @param joinPoint
	 * @return
	 */
	private static String tojson(String[] paramNames, JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		StringBuilder sb = new StringBuilder();
		StringBuilder lastSb = new StringBuilder();
		boolean clazzFlag = true;
		sb.append("{");
		for (int k = 0; k < args.length; k++) {
			Object arg = args[k];
			sb.append("\"" + paramNames[k] + "\"" + " ");
			// 获取对象类型
			String typeName = arg.getClass().getTypeName();

			for (String t : types) {
				if (t.equals(typeName)) {
					sb.append(": " + "\"" + arg + "\"" + ", ");
				}
			}
			if (clazzFlag) {
				sb.append(getFieldsValue(arg));
			}
		}
		if (sb.length() - 1 > 0) {
            lastSb.append(sb.substring(0, sb.length() - 1));
        } else {
            lastSb.append(sb);
        }
		lastSb.append("}");
		return lastSb.toString();
	}

	@Override
	protected void finalize( ){
	}
}
