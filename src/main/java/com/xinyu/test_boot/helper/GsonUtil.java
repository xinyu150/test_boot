package com.xinyu.test_boot.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("unchecked")
public class GsonUtil {

	/**
	 * json字符串转换为List对象
	 * @param json 需要转换的json字符串
	 * @param type = new TypeToken<List<Object>>(){};其中Object为需要转换的对象名称
	 * @return
	 */
	public static <T> T toList(String json,TypeToken<T> type) {
		if(null==json || "".equals(json)){
			return null;
		}
		Object obj = null;
		try {
			Gson gson = new Gson();
			obj = null;
			obj = gson.fromJson(json,type.getType());
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return (T) obj;
	}
	
	/**
	 * json字符串转换为List<Map>对象
	 * @param json 需要转换的字符串
	 * @return
	 */
	public static List<Map<String, Object>> toMaps(String json) {
		if(null==json || "".equals(json)){
			return null;
		}
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(json,new TypeToken<List<Map<String, Object>>>() {}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
	/**
	 * json字符串转换为指定对象
	 * @param json 需要转换的json字符串
	 * @param pojoCalss Object.class
	 * @return
	 */
	public static <T> T toMap(String json) {
		if(null==json || "".equals(json)){
			return null;
		}
		
    	Object pojo = null;
    	try{
			pojo = new Gson().fromJson(json, HashMap.class);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
    	return (T) pojo;
	}
	
	
	/**
	 * json字符串转换为指定对象
	 * @param json 需要转换的json字符串
	 * @param pojoCalss Object.class
	 * @return
	 */
	public static <T> T toObject(String json, Class<T> pojoCalss) {
		if(StringUtils.isEmpty(json)){
			return null;
		}
    	Object pojo = null;
    	try{
			pojo = new Gson().fromJson(json, pojoCalss);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
    	return (T) pojo;
	}
    
	/**
	 * 对象直接转换为json
	 * @param obj
	 * @return
	 */
    public static String toJson(Object obj){
    	if(null==obj){
			return null;
		}
    	String json = null;
    	try {
			json = new Gson().toJson(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	return json;
    }
    
    
    public static String toJsonForDate(Object obj){
    	if(null==obj){
			return null;
		}
    	String json = null;
    	
    	try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();  
			json = gson.toJson(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	return json;
    }
    public static <T> Map<String, Object> beanToMap(T bean) {  
        Map<String, Object> map = new HashMap<String, Object>();
        if (bean != null) {  
            BeanMap beanMap = BeanMap.create(bean);  
            for (Object key : beanMap.keySet()) {  
                map.put(key+"", beanMap.get(key));  
            }             
        }  
        return map;  
    }  
    
}
