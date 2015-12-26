package com.pallas.knowledge.dao;

import java.util.List;

import com.pallas.knowledge.bean.Knowledge;

public interface KnowledgeDAO {
	/**
	 * 增加知识点
	 * 
	 * @param knowledge
	 * @return
	 */
	public Knowledge insert(Knowledge knowledge);

	/**
	 * 修改知识点
	 * 
	 * @param knowledge
	 * @return
	 */
	public boolean update(Knowledge knowledge);

	/**
	 * 删除知识点
	 * 
	 * @param knowledge
	 * @return
	 */
	public boolean delete(Knowledge knowledge);

	/**
	 * 根据知识点主键id获得知识点
	 * 
	 * @param id
	 * @return
	 */
	public Knowledge select(long id);

	/**
	 * 根据知识点分类id获取知识点列表
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Knowledge> selectChildrenByParentId(long parentId);

	/**
	 * 获得知识点记录数
	 * 
	 * @return
	 */
	public int count();

	/**
	 * 获得知识点分类包含的知识点记录数
	 * 
	 * @return
	 */
	public int countChildrenByParentId(long parentId);

	/**
	 * 获得知识点表最大id
	 * 
	 * @return
	 */
	public long getMaxId();
}
