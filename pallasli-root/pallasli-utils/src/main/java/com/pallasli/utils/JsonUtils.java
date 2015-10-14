package com.pallasli.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {
	private static final Gson gson = new Gson();
	private static final JsonParser gsonParser = new JsonParser();

	/**
	 * 将json字符串转换为json对象/数组
	 * 
	 * @param json
	 * @return
	 */
	public static JsonElement fromStringToJsonElement(String json) {
		return json == null ? null : gsonParser.parse(json.trim());
	}

	/**
	 * 将json对象转化为指定对象
	 * 
	 * @param <T>
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T extends Object> T fromJsonToObject(JsonObject json,
			Class<T> cls) {
		T o = gson.fromJson(json, cls);
		return o;
	}

	/**
	 * 将json数组转化为List
	 * 
	 * @param <T>
	 * @param jsonArray
	 * @param cls
	 * @return
	 */
	public static <T extends Object> List<T> fromJsonArrayToList(
			JsonArray jsonArray, Class<T> cls) {

		List<T> list = new ArrayList<T>();
		Iterator<JsonElement> it = jsonArray.iterator();
		while (it.hasNext()) {
			JsonElement json = it.next();
			T o = gson.fromJson(json, cls);
			list.add(o);
		}
		return list;
	}

	/**
	 * 将javaBean转化为json对象
	 * 
	 * @param <T>
	 * @param t
	 * @return
	 */
	public static <T extends Object> JsonObject fromObjectToJson(T t) {
		return gson.toJsonTree(t).getAsJsonObject();
	}

	/**
	 * 将List转化为jsonArray对象
	 * 
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T extends Object> JsonArray fromListToJsonArray(List<T> list) {
		JsonArray jsonArray = new JsonArray();
		for (T o : list) {
			jsonArray.add(gson.toJsonTree(o));
		}
		return jsonArray;
	}

	public static String getJsonContent(String urlStr) {
		try {// 获取HttpURLConnection连接对象
			URL url = new URL(urlStr);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();
			// 设置连接属性
			httpConn.setConnectTimeout(3000);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("GET");
			// 获取相应码
			int respCode = httpConn.getResponseCode();
			if (respCode == 200) {
				return ConvertStream2Json(httpConn.getInputStream());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private static String ConvertStream2Json(InputStream inputStream) {
		String jsonStr = "";
		// ByteArrayOutputStream相当于内存输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		// 将输入流转移到内存输出流中
		try {
			while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, len);
			}
			// 将内存流转换为字符串
			jsonStr = new String(out.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
	}
}
