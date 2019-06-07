package edu.hebeu.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.hebeu.entity.Employee;


public class ApiCommonUtil {

	public static String TOKEN_NAME = "hr_token";
	
	/**
	 * 获取TOKEN
	 * @param request
	 * @return
	 */
	public static String getToken() {
//		String token = getRequest().getHeader("token");
		String token = CookieUtil.getUid(getRequest(), TOKEN_NAME);
		return token;
	}
	
	/**
	 * 获取用户
	 * @param request
	 * @return
	 */
	public static Employee getEmployyee(RedisUtil redisUtil) {
		Employee employee = JsonUtils.jsonToPoJo(redisUtil.get(getToken()), Employee.class);
		return employee;
	}
	
	/**
	 * 判断当前用户是否有权限
	 * @param permitted
	 * @param request
	 * @return
	 */
	public static Boolean isPermitted(String permitted) {
//		Map<String,String> permissionMap =	JedisUtils.getMap(UserUtils.USER_PERMISSION_CACHE+ApiCommonUtil.getToken());
//		if(permissionMap != null && permissionMap.containsKey(permitted)) {
//			return true;
//		}
		return false;
	}

	/** 
	 * 获取当前请求对象 
	 * @return 
	 */  
	public static HttpServletRequest getRequest(){  
	    try{  
	        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
	    }catch(Exception e){  
	        return null;  
	    }  
	}  
	
}
