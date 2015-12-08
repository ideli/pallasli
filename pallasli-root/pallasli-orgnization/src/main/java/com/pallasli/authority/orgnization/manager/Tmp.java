package com.pallasli.authority.orgnization.manager;

import java.util.List;

import com.google.gson.JsonObject;
import com.pallasli.authority.orgnization.bean.Department;
import com.pallasli.authority.orgnization.bean.DeptUser;
import com.pallasli.authority.orgnization.bean.Role;
import com.pallasli.authority.orgnization.bean.RoleUser;
import com.pallasli.authority.orgnization.bean.User;

public interface Tmp {

	/**
	 * 根据ID获得用户对象
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(long id);

	/**
	 * 根据用户登录名查找用户
	 * 
	 * @param name
	 * @return
	 */
	public User getUserByName(String name);

	/**
	 * 根据用户姓名获得用户对象
	 * 
	 * @param caption
	 * @return
	 */
	public User getUserByCaption(String caption);

	/**
	 * 获取所有部门对象
	 * 
	 * @return
	 */
	public List<Department> getAllDepts();

	/**
	 * 根据ID获得部门对象
	 * 
	 * @param id
	 * @return
	 */
	public Department getDeptById(long id);

	/**
	 * 根据父ID获得所有角色用户
	 * 
	 * @param id
	 * @return
	 */
	public List<Department> getDeptListByparentId(long parentid);

	/**
	 * 根据名称获得用户对象
	 * 
	 * @param name
	 * @return
	 */
	public Department getDeptByCaption(String caption);

	/**
	 * 获得用户默认部门
	 * 
	 * @param user
	 * @return
	 */
	public Department getDeptByUser(User user);

	/**
	 * 根据ID获得角色对象
	 * 
	 * @param id
	 * @return
	 */
	public Role getRoleById(long id);

	/**
	 * 根据角色名获得角色对象
	 * 
	 * @param value
	 * @return
	 */
	public Role getRoleByCaption(String value);

	/**
	 * 根据ID获得部门用户关联对象
	 * 
	 * @param id
	 * @return
	 */
	public DeptUser getDeptUserById(long id);

	/**
	 * 根据ID获得角色用户关联对象
	 * 
	 * @param id
	 * @return
	 */
	public RoleUser getRoleUserById(long id);

	public RoleUser findRoleUser(long roleid, long userid);

	/**
	 * 获得所有用户
	 * 
	 * @return
	 */
	public List<User> getAllUsers();

	/**
	 * 获得所有角色
	 * 
	 * @return
	 */
	public List<Role> getAllRoles();

	/**
	 * 重置密码
	 * 
	 * @param userId
	 * @return
	 */
	public boolean resetUserPassword(long userId);

	/**
	 * 修改用户密码
	 * 
	 * @param id
	 * @param oldPass
	 * @param newPass
	 * @return
	 */
	public boolean changeUserPassword(long id, String oldPass, String newPass);

	/**
	 * 保存用户
	 * 
	 * @param json
	 * @return
	 */
	public boolean saveUser(JsonObject json);

	/**
	 * 保存部门
	 * 
	 * @param json
	 * @return
	 */
	public boolean saveDept(JsonObject json);

	/**
	 * 更新成员部门名称
	 * 
	 * @param deptId
	 */
	public void updateChildDeptName(long deptId);

	/**
	 * 保存部门用户关联对象
	 * 
	 * @param json
	 * @return
	 */
	public boolean saveDeptUser(JsonObject json);

	/**
	 * 保存角色
	 * 
	 * @param json
	 * @return
	 */
	public boolean saveRole(JsonObject json);

	/**
	 * 保存角色用户对象
	 * 
	 * @param json
	 * @return
	 */
	public boolean saveRoleUser(JsonObject json);

	/**
	 * 删除部门用户关联
	 * 
	 * @param deptUser
	 * @return
	 */
	public boolean delDeptUser(DeptUser deptUser);

	/**
	 * 删除角色用户关联
	 * 
	 * @param roleUser
	 * @return
	 */
	public boolean delRoleUser(RoleUser roleUser);

	public boolean delUser(User user);

	/**
	 * 处理部门删除（递归调用）
	 * 
	 * @param dept
	 */
	public boolean delDept(Department dept);

	/**
	 * 处理角色删除
	 * 
	 * @param role
	 * @return
	 */
	public boolean delRole(Role role);

	/**
	 * 更新用户所在的部门
	 * 
	 * @param dept
	 * @param order
	 * @return
	 */
	public boolean updateUserDept(DeptUser deptUser);

	/**
	 * 更新部门编号（递归处理）
	 * 
	 * @param dept
	 * @param order
	 * @return
	 */
	public boolean updateDeptNo(String parentNo, Department dept, int order);

	/**
	 * 获得部门的下级部门
	 * 
	 * @param deptId
	 * @return
	 */
	public List<Department> getDeptsByParentDeptId(long deptId);

	/**
	 * 获得部门的所有上级部门
	 * 
	 * @param deptId
	 * @return
	 */
	public List<Department> getDeptsByChildDept(long deptId);

	/**
	 * 获得部门用户列表
	 * 
	 * @param deptId
	 * @return
	 */
	public List<User> getUsersByDeptId(long deptId);

	/**
	 * 获得用户所属部门列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<Department> getDeptsByUserId(long userId);

	/**
	 * 获得用户所管理的所有部门
	 * 
	 * @param userId
	 * @return
	 */
	public List<Department> getDeptsByLeaderUserId(long userId);

	/**
	 * 获得用户直接管理的所有部门
	 * 
	 * @param userId
	 * @return
	 */
	public List<Department> getDeptsByDirectLeaderUserId(long userId);

	/**
	 * 获得角色用户列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<User> getUsersByRoleId(long roleId);

	/**
	 * 获得用户的角色列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<Role> getRolesByUserId(long userId);

	/**
	 * 获得部门的用户关联
	 * 
	 * @param deptId
	 * @return
	 */
	public List<DeptUser> getDeptUsersByDeptId(long deptId);

	/**
	 * 获得用户的所有部门关联
	 * 
	 * @param userId
	 * @return
	 */
	public List<DeptUser> getDeptUsersByUserId(long userId);

	/**
	 * 获得角色的用户关联
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleUser> getRoleUsersByRoleId(long roleId);

	/**
	 * 获得用户的所有角色关联
	 * 
	 * @param userId
	 * @return
	 */
	public List<RoleUser> getRoleUsersByUserId(long userId);

	/**
	 * 获得部门用户关联的用户对象
	 * 
	 * @param deptUser
	 * @return
	 */
	public User getUserByDeptUser(DeptUser deptUser);

	/**
	 * 获得部门用户关联的部门对象
	 * 
	 * @param deptUser
	 * @return
	 */
	public Department getDeptByDeptUser(DeptUser deptUser);

	/**
	 * 获得角色用户关联的用户对象
	 * 
	 * @param roleUser
	 * @return
	 */
	public User getUserByRoleUser(RoleUser roleUser);

	/**
	 * 获得角色用户关联的角色对象
	 * 
	 * @param roleUser
	 * @return
	 */
	public Role getRoleByRoleUser(RoleUser roleUser);

	public List<User> getDirectLeaders(Department dept);

	/**
	 * 得到用户所有直接领导
	 * 
	 * @param user
	 * @return
	 */
	public List<User> getDirectLeader(User user);

	/**
	 * 得到用户所有领导
	 * 
	 * @param user
	 * @return
	 */
	public List<User> getAllLeaders(User user);

	/**
	 * 得到用户所在部门直接下属
	 * 
	 * @param user
	 * @return
	 */
	public List<User> getDirectUnderlings(User user);

	/**
	 * TODO: 得到用户所有下属
	 * 
	 * @param user
	 * @return
	 */
	public List<User> getAllUnderlings(User user);

	/**
	 * TODO: 得到用户所在部门分管领导
	 * 
	 * @param user
	 * @return
	 */
	public List<User> getViceLeaders(User user);

	/**
	 * 排序数据库对象
	 * 
	 * @param list
	 *            待排序对象列表，默认排序f_order,f_name
	 */
	public <T> void sortEntityDaoObjectList(List<T> list);

	/**
	 * 
	 * @param list
	 *            待排序对象列表
	 * @param fields
	 *            排序字段："filename1,filename2"
	 */
	public <T> void sortEntityDaoObjectList(List<T> list, String... strings);

	public List<User> getUserByType(int type);

	/**
	 * 判断用户是否属于某个角色
	 * 
	 * @param user
	 * @param rolestr
	 * @return
	 */
	public boolean isUserRoleOf(User user, String rolestr);

}
