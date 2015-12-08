package com.pallas.designer.action;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DesignObjectManager {

	private static DesignObjectManager singleton;

	protected static DesignObjectManager instance() {
		if (singleton == null) {
			singleton = new DesignObjectManager();
		}
		return singleton;
	}

	/**
	 * 创建设计对象
	 * 
	 * @param folderKey
	 * @param f_class
	 * @param key
	 * @return
	 */
	protected DesignObject createObject(String folderKey, String f_class,
			String key) {
		return null;
	}

	/**
	 * 拷贝设计对象
	 * 
	 * @param f_key
	 * @param parentKey
	 * @param newkey
	 * @return
	 */
	protected DesignObject pasteObject(String f_key, String parentKey,
			String newkey) {
		return null;
	}

	/**
	 * 删除设计对象
	 * 
	 * @param f_key
	 * @return
	 */
	protected boolean deleteObject(String f_key) {
		return false;
	}

	/**
	 * 批量删除设计对象
	 * 
	 * @param keys
	 */
	protected void deleteObjects(JsonArray keys) {
	}

	/**
	 * 保存设计对象
	 * 
	 * @param key
	 * @param updated
	 * @return
	 */
	protected DesignObject saveObject(String key, JsonObject updated) {
		return null;
	}

	/**
	 * 强制保存设计对象（递归保存父对象）
	 * 
	 * @param key
	 * @return
	 */
	protected void forceSaveObject(String key) {
	}

	/**
	 * 装载对象
	 * 
	 * @param key
	 * @return
	 */
	protected DesignObject loadObject(String key) {
		return null;
	}

	/**
	 * 递归装载设计对象
	 * 
	 * @param parent
	 * @param mclass
	 * @return
	 */
	private List<DesignObject> getAllChildDesignObject(DesignObject parent,
			String mclass) {
		return null;

	}

	/**
	 * 递归获得所有子设计对象
	 * 
	 * @param mclass
	 * @param parentKey
	 * @return
	 */
	protected List<DesignObject> getAllSubObjectList(String mclass,
			String parentKey) {
		return null;
	}

	/**
	 * 获得子对象列表
	 * 
	 * @param parentKey
	 * @param f_class
	 * @return
	 */
	protected List<DesignObject> getSubObjectList(String parentKey,
			String f_class) {
		return null;
	}

	/**
	 * 设计对象更名
	 * 
	 * @param oldKey
	 * @param newKey
	 * @return
	 */
	protected DesignObject renameObject(String oldKey, String newKey) {
		return null;

	}
}
