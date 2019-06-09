package edu.hebeu.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Bill.Tang on 2018-9-27.
 */

public class JsonUtils {
	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 将对象转换成json字符串。
	 */
	public static String objectToJson(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json结果集转化为对象
	 */
	public static <T> T jsonToPoJo(String jsonData, Class<T> beanType) {
		try {
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成pojo对象list
	 */
	public static <T> T jsonToList(String jsonData, TypeReference<T> typeReference) {
		try {
			return MAPPER.readValue(jsonData, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		String json="[{'id':1,'pid':0,'status':1,'name':'用户管理','permissionValue':'open:user:manage'},";
		json+="{'id':2,'pid':0,'status':1,'name':'系统管理','permissionValue':'open:system:manage'},";
		json+="{'id':3,'pid':1,'status':1,'name':'新增用户','permissionValue':'open:user:add'},";
		json+="{'id':4,'pid':1,'status':1,'name':'修改用户','permissionValue':'open:user:edit'},";
		json+="{'id':5,'pid':1,'status':0,'name':'删除用户','permissionValue':'open:user:del'},";
		json+="{'id':6,'pid':2,'status':1,'name':'系统配置管理','permissionValue':'open:systemconfig:manage'},";
		json+="{'id':7,'pid':6,'status':1,'name':'新增配置','permissionValue':'open:systemconfig:add'},";
		json+="{'id':8,'pid':6,'status':1,'name':'修改配置','permissionValue':'open:systemconfig:edit'},";
		json+="{'id':9,'pid':6,'status':0,'name':'删除配置','permissionValue':'open:systemconfig:del'},";
		json+="{'id':10,'pid':2,'status':1,'name':'系统日志管理','permissionValue':'open:log:manage'},";
		json+="{'id':11,'pid':10,'status':1,'name':'新增日志','permissionValue':'open:log:add'},";
		json+="{'id':12,'pid':10,'status':1,'name':'修改日志','permissionValue':'open:log:edit'},";
		json+="{'id':13,'pid':10,'status':0,'name':'删除日志','permissionValue':'open:log:del'}]";
		
		
		Map<String,String> detail = new HashMap<String,String>();
		detail.put("id", "1");
		detail.put("pid", "0");
		detail.put("status", "1");
		detail.put("name", "用户管理");
		detail.put("permissionValue", "open:user:manage");
		
		List<Map<String,String>> ma = jsonToList(json, new TypeReference<List<Map<String,String>>>() {} );
		for (Map<String, String> map : ma) {
			for(Map.Entry<String, String> entry:map.entrySet()) {
				System.out.println(entry.getKey()+"_"+entry.getValue());
			}
			
		}
	}
}