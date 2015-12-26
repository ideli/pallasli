package com.pallas.knowledge.dao;

import java.util.List;

import com.pallas.knowledge.bean.KnowledgeType;

public interface KnowledgeTypeDAO {
	/**
	 * 增加知识点分类
	 * 
	 * @param knowledgeType
	 * @return
	 */
	public KnowledgeType insert(KnowledgeType knowledgeType);

	/**
	 * 修改知识点分类
	 * 
	 * @param knowledgeType
	 * @return
	 */
	public boolean update(KnowledgeType knowledgeType);

	/**
	 * 删除知识点分类
	 * 
	 * @param knowledgeType
	 * @return
	 */
	public boolean delete(KnowledgeType knowledgeType);

	/**
	 * 根据知识点分类主键id获得知识点分类
	 * 
	 * @param id
	 * @return
	 */
	public KnowledgeType select(long id);

	/**
	 * 根据知识点分类id获取子分类列表
	 * 
	 * @param parentId
	 * @return
	 */
	public List<KnowledgeType> selectChildrenByParentId(long parentId);

	/**
	 * 获得知识点分类记录数
	 * 
	 * @return
	 */
	public int count();

	/**
	 * 获得知识点分类包含的子分类记录数
	 * 
	 * @return
	 */
	public int countChildrenByParentId(long parentId);

	/**
	 * 获得知识点分类表最大id
	 * 
	 * @return
	 */
	public long getMaxId();
}
