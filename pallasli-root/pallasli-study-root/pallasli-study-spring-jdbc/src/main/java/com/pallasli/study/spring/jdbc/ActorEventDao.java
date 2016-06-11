package com.pallasli.study.spring.jdbc;

import java.util.List;

import org.springframework.jdbc.support.KeyHolder;

public interface ActorEventDao {
	/**
	 * 根据SQL建表
	 * 
	 * @param sql
	 */
	public void createTableBySQL(String sql);

	/**
	 * 统计firstName相同的总数
	 * 
	 * @param firstName
	 * @return
	 */
	public int findCountOfActorsByFirstName(String firstName);

	/**
	 * 插入记录并返回自动生成的主键Id
	 * 
	 * @param ps
	 * @return
	 */
	public KeyHolder insertActor(final Actor actor);

	/**
	 * 用SimpleJdbcInsert插入一条记录:mysql测试成功
	 */
	public long inserOneActor(Actor actor);

	/**
	 * 插入/更新/删除数据
	 * 
	 * @param sql
	 *            有参数语句
	 * @param obj
	 *            参数值数组
	 */
	public int operateActor(String sql, Object[] obj);

	/**
	 * 根据SQL查询记录总数
	 * 
	 * @param sql
	 * @return
	 */
	public int findRowCountBySQL(String sql);

	/**
	 * 根据Id查找指定对象
	 * 
	 * @param id
	 * @return
	 */
	public Actor findActorById(long id);

	/**
	 * 根据Id查找指定对象
	 * 
	 * @param id
	 * @return
	 */
	public Actor findActorByIdSimple(long id);

	/**
	 * 返回所有对象
	 * 
	 * @return
	 */
	public List findAllActors();

	/**
	 * 批量更新
	 * 
	 * @param actors
	 * @return
	 */
	public int[] updateBatchActors(final List actors);

	/**
	 * 批量更新
	 * 
	 * @param actors
	 * @return
	 */
	public int[] updateBatchActorsSimple(final List<Actor> actors);

}