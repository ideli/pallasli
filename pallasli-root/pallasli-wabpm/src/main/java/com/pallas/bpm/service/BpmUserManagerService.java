package com.pallas.bpm.service;

import java.util.List;

import com.pallas.bpm.service.bean.GroupInfo;
import com.pallas.bpm.service.bean.UserInfo;

public interface BpmUserManagerService {
	/**
	 * 查找流程用户
	 * 
	 * @param keyword
	 *            用户名，用户ID
	 * @param pageSize
	 *            每页纪录数
	 * @param pageNum
	 *            当前页数
	 * @return
	 */
	List<UserInfo> findUserList(String keyword, int pageSize, int pageNum);

	/**
	 * 查找流程分组
	 * 
	 * @param keyword
	 *            分组名称，分组ID
	 * @param pageSize
	 *            每页纪录数
	 * @param pageNum
	 *            当前页数
	 * @return
	 */
	List<GroupInfo> findGroupList(String keyword, int pageSize, int pageNum);

	/**
	 * 查询流程用户拥有的流程分组
	 * 
	 * @param userId
	 *            流程用户ID
	 * @param pageSize
	 *            每页纪录数
	 * @param pageNum
	 *            当前页数
	 * @return
	 */
	List<UserInfo> findGroupListByUser(String userId, int pageSize, int pageNum);

	/**
	 * 查询流程分组包含的流程用户
	 * 
	 * @param groupId
	 *            流程分组ID
	 * @param pageSize
	 *            每页纪录数
	 * @param pageNum
	 *            当前页数
	 * @return
	 */
	List<GroupInfo> findUserListByGroup(String groupId, int pageSize,
			int pageNum);

	/**
	 * 添加分组用户
	 * 
	 * @param groupId流程分组ID
	 * @param userId流程用户ID
	 * @return
	 */
	boolean addGroupUser(String groupId, String userId);

	/**
	 * 删除分组用户
	 * 
	 * @param groupId流程分组ID
	 * @param userId流程用户ID
	 * @return
	 */
	boolean removeGroupUser(String groupId, String userId);
}
