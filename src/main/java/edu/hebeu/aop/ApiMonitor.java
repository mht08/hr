package edu.hebeu.aop;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.hebeu.util.ApiCommonUtil;
import edu.hebeu.util.CookieUtil;
import edu.hebeu.util.RedisUtil;

@Aspect
@Component
public class ApiMonitor {
	private static Logger logger = Logger.getLogger(ApiMonitor.class);
	@Autowired
	private RedisUtil redisUtil;
	
	@Pointcut("execution(* edu.hebeu.controller.*.*(..))")
	private void pointCutMethod(){
		
	}
	
	@Around("pointCutMethod()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("进入方法---环绕通知");
		Object result = null;
		String targetName = pjp.getTarget().getClass().getSimpleName();
		String methodName = pjp.getSignature().getName();
		logger.info("----------------执行方法-----------------");
		logger.info("类名：" + targetName + " 方法名：" + methodName);
		
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes)ra;
		HttpServletRequest request = sra.getRequest();
		HttpServletResponse response = sra.getResponse();
		
		// 白名单
		Set<String> whitSet = new HashSet<String>();
		whitSet.add("EmployeeController_toLogin");
		whitSet.add("EmployeeController_checkLogin");
		
		
		if(!whitSet.contains(targetName+"_"+methodName)) {
			String token = CookieUtil.getUid(request, ApiCommonUtil.TOKEN_NAME);
			if(StringUtils.isNotBlank(token)) {
				result = pjp.proceed();
			} else {
				// http跳转处理
				String path = request.getContextPath();    
		        String basePath = request.getScheme() + "://" + request.getServerName()  + ":" + request.getServerPort() + path + "/";    
		        response.sendRedirect(basePath + "employee/login.do");   
			}
		} else {
			result = pjp.proceed();
		}
		logger.info("退出方法---环绕通知");
		return result;
	}
}
