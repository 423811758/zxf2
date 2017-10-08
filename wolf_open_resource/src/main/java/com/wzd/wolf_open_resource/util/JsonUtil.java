package com.wzd.wolf_open_resource.util;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Json 和 Java 对象转化工具类
 */
public class JsonUtil {
	
	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}
	
	/**
	 * bean 到 json 转化
	 */
	public static String bean2Json ( Object bean ) throws Exception {
		String s = objectMapper.writeValueAsString(bean);
		return s;
	}

	/**
	 * json 到 bean 转化 
	 */
	public static Object json2Bean ( String json, Class<?> cls ) throws Exception {
		return objectMapper.readValue(json, cls);
	}

}
