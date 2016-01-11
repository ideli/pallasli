package com.pallasli.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	@SuppressWarnings("unchecked")
	public static <T extends Object> Map<String, T> str2Map(
			String jsonArrayStr, Class<T> cls) {
		Map<String, JSONObject> objMap = (Map<String, JSONObject>) JSON
				.parse(jsonArrayStr);
		Map<String, T> tMap = new HashMap<String, T>();
		Set<String> set = objMap.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			JSONObject json = objMap.get(key);
			T t = json2Object(json, cls);
			tMap.put(key, t);
		}

		return tMap;
	}

	public static <T extends Object> T str2Object(String jsonStr, Class<T> cls) {
		return (T) JSON.parseObject(jsonStr, cls);
	}

	public static String object2Str(Object obj) {
		return JSON.toJSONString(obj);
	}

	public static String object2PrettyStr(Object obj) {
		return JSON.toJSONString(obj, true);
	}

	public static <T extends Object> T json2Object(JSONObject json, Class<T> cls) {
		return (T) JSON.parseObject(json2Str(json), cls);
	}

	public static String json2Str(JSON json) {
		return json.toJSONString();
	}

	public static JSONObject str2Json(String json) {
		return JSON.parseObject(json);
	}

	public static JSONArray str2JsonArray(String json) {
		return JSON.parseArray(json);
	}

	public static <T extends Object> Map<String, T> json2Map(
			JSONArray jsonArray, Class<T> cls) {
		return str2Map(json2Str(jsonArray), cls);
	}

	public static <T extends Object> List<T> str2List(String jsonArrayStr,
			Class<T> cls) {
		return JSON.parseArray(jsonArrayStr, cls);
	}

	public static <T extends Object> List<T> json2List(JSONArray jsonArray,
			Class<T> cls) {
		return str2List(json2Str(jsonArray), cls);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Object> JSONArray list2JsonArray(List<T> list) {

		return new JSONArray((List<Object>) list);
	}

}
