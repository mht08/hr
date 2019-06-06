package edu.hebeu.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class ApiCommonUtil {

	public static void main(String[] args) {
		String req= "callback({'accessToken':'eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmYW5ydWFuIiwiaWF0IjoxNTU5NzIxNzEwLCJleHAiOjE1NTk3MjUzMTAsInN1YiI6ImFkbWluIiwiZGVzY3JpcHRpb24iOiJbN2NmYl1bN2VkZl1bN2JhMV1bNzQwNl1bNTQ1OF0oYWRtaW4pIiwianRpIjoiand0In0.-GsHqFD7CcHVRee7JQH2JfM5n1Ps8bk9YdZpxbZixkk','url':'http://10.91.229.88:8080/webroot/decision?fine_username=admin&fine_password=111111&validity=-1'})";
		
		req = req.substring(req.indexOf("(")+1, req.lastIndexOf(")"));
		try {
			Map ma = JSONUtils.jsonToMap(req);
			System.out.println(ma.get("accessToken"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取TOKEN
	 * @param request
	 * @return
	 */
	public static String getToken() {
		String token = getRequest().getHeader("token");
		return token;
	}
	
	/**
	 * 获取用户
	 * @param request
	 * @return
	 */
	public static User getUser() {
		User user = (User) JedisUtils.getObject(UserUtils.USER_CACHE + getToken());
		return user;
	}
	
	/**
	 * 判断当前用户是否有权限
	 * @param permitted
	 * @param request
	 * @return
	 */
	public static Boolean isPermitted(String permitted) {
		Map<String,String> permissionMap =	JedisUtils.getMap(UserUtils.USER_PERMISSION_CACHE+ApiCommonUtil.getToken());
		if(permissionMap != null && permissionMap.containsKey(permitted)) {
			return true;
		}
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
