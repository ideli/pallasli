package com.pallasli.authority.orgnization.manager;

import java.util.List;

import com.google.gson.JsonObject;
import com.pallasli.authority.orgnization.bean.ModuleRoleAuth;
import com.pallasli.authority.orgnization.bean.User;

public interface AuthorityManager {

	public JsonObject getAllMenuList();

	public JsonObject getAllModuleRoleAuthList(String applicationKey);

	public JsonObject getAllMenu();

	public JsonObject getAllModuleRole(String applicationKey);

	/**
	 * 获得所有菜单
	 * 
	 * @return
	 */
	public List<JsonObject> getMenus();

	/**
	 * 获得菜单对象
	 * 
	 * @param id
	 * @return
	 */
	public JsonObject getMenuById(long id);

	/**
	 * 保存菜单权限
	 * 
	 * @param id
	 * @param auth
	 * @param authCaption
	 * @return
	 */
	public boolean saveMenuAuth(long id, String auth, String authCaption);

	public boolean saveMenuAuthobject(long id, String auth, String authCaption);

	/**
	 * 获得模块角色权限定义
	 * 
	 * @param key
	 * @return
	 */
	public JsonObject getModuleRoleAuthByKey(String key);

	/**
	 * 获得所有模块角色权限定义
	 * 
	 * @return
	 */
	public List<JsonObject> getModuleRoleAuths();

	/**
	 * 保存模块角色权限
	 * 
	 * @param key
	 * @param auth
	 * @param authCaption
	 * @return
	 */
	public boolean saveModuleRoleAuth(String appkey, String key, String auth,
			String authCaption);

	public boolean saveModuleRoleAuthObject(String appkey, String key,
			String auth, String authCaption, long id);

	public boolean isUserHasModuleRole(User user, ModuleRoleAuth auth);
}
