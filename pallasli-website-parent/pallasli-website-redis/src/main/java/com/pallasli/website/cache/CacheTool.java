package com.pallasli.website.cache;

public interface CacheTool {
	/**
	 * 
	 * 保存缓存
	 * 
	 * @param name
	 *            名字
	 * @param value
	 *            值
	 */
	public void saveCache(String name, String value);

	/**
	 * 读取缓存
	 */
	public void getCache();

	/**
	 * 更新缓存
	 */
	public void updateCache();

	/**
	 * 删除缓存
	 */
	public void deleteCache();
}
