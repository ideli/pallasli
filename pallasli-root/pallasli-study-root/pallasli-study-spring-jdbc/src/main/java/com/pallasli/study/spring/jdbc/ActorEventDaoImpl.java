package com.pallasli.study.spring.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ActorEventDaoImpl implements ActorEventDao {

	private JdbcTemplate jdbcTemplate;

	// NamedParameterJdbcTemplate对JdbcTemplate封装，增加了命名参数特性
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	// SimpleJdbcTemplate对JdbcTemplate封装,某些特性要在java5以上才工作
	private SimpleJdbcTemplate simpleJdbcTemplate;

	// 简化插入数据操作
	private SimpleJdbcInsert inserActor;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
		this.inserActor = new SimpleJdbcInsert(dataSource).withTableName("actors")
				.usingColumns("first_name", "last_name")// 插入这些字段
				.usingGeneratedKeyColumns("id");// 带回生成的id
	}

	/**
	 * 用SimpleJdbcInsert插入一条记录
	 */
	@Override
	public long inserOneActor(Actor actor) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("first_name", actor.getFirstName());
		parameters.put("last_name", actor.getLastName());
		return inserActor.executeAndReturnKey(parameters).longValue();
	}

	/**
	 * 统计firstName相同的总数
	 * 
	 * @param firstName
	 * @return
	 */
	@Override
	public int findCountOfActorsByFirstName(String firstName) {
		String sql = "select count(0) from actors where first_name = :first_name";
		SqlParameterSource namedParameters = new MapSqlParameterSource("first_name", firstName);
		// Map namedParameter =
		// Collections.singletonMap("first_name",firstName);
		// 还有一种Bean封装的方式
		// SqlParameterSource namedParameters = new
		// BeanPropertySqlParameterSource(exampleActor);
		return this.namedParameterJdbcTemplate.queryForInt(sql, namedParameters);
	}

	/**
	 * 根据SQL建表
	 * 
	 * @param sql
	 */
	@Override
	public void createTableBySQL(String sql) {
		this.jdbcTemplate.execute(sql);
	}

	/**
	 * 插入记录并返回自动生成的主键Id(MySQL中不行，Oracle可以)
	 * 
	 * @param ps
	 * @return
	 */
	@Override
	public KeyHolder insertActor(final Actor actor) {
		final String addSql = "insert into actors(first_name,last_name) values (?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(addSql, new String[] { "id" });// 返回id
				ps.setString(1, actor.getFirstName());
				ps.setString(2, actor.getLastName());
				return ps;
			}

		});
		System.out.println(keyHolder.getKey());
		return keyHolder;
	}

	/**
	 * 插入/更新/删除数据
	 * 
	 * @param sql
	 *            有参数语句
	 * @param obj
	 *            参数值数组
	 */
	@Override
	public int operateActor(String sql, Object[] obj) {
		return this.jdbcTemplate.update(sql, obj);
	}

	/**
	 * 根据SQL查询记录总数
	 * 
	 * @param sql
	 * @return
	 */
	@Override
	public int findRowCountBySQL(String sql) {
		return this.jdbcTemplate.queryForInt(sql);
	}

	/**
	 * 根据Id查找指定对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Actor findActorById(long id) {
		Actor actor = (Actor) this.jdbcTemplate.queryForObject(
				"select id,first_name,last_name from actors where id = ?", new Object[] { new Long(id) },
				new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
						Actor act = new Actor();
						act.setId(rs.getLong("id"));
						act.setFirstName(rs.getString("first_name"));
						act.setLastName(rs.getString("last_Name"));
						return act;
					}

				});
		return actor;
	}

	/**
	 * 根据Id查找指定对象
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Actor findActorByIdSimple(long id) {
		String sql = "select id,first_name,last_name from actors where id = ?";

		ParameterizedRowMapper<Actor> mapper = new ParameterizedRowMapper<Actor>() {
			// notice the return type with respect to java 5 covariant return
			// types
			public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
				Actor act = new Actor();
				act.setId(rs.getLong("id"));
				act.setFirstName(rs.getString("first_name"));
				act.setLastName(rs.getString("last_Name"));
				return act;
			}
		};

		return this.simpleJdbcTemplate.queryForObject(sql, mapper, id);
	}

	/**
	 * 返回所有对象
	 * 
	 * @return
	 */
	@Override
	public List findAllActors() {
		return this.jdbcTemplate.query("select id,first_name,last_name from actors", new ActorMapper());
	}

	/**
	 * 定义一个静态内部类，在Dao的方法中被共享
	 * 
	 * @author logcd
	 */
	private static final class ActorMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Actor act = new Actor();
			act.setId(rs.getLong("id"));
			act.setFirstName(rs.getString("first_name"));
			act.setLastName(rs.getString("last_Name"));
			return act;
		}

	}

}

	/**
	 * 批量更新
	 * 
	 * @param actors
	 * @return
	 */
	@Override
	public int[] updateBatchActors(final List actors) {
		int[] updateCounts = this.jdbcTemplate.batchUpdate(
				"update actors set first_name = ?, last_name = ? where id =? ", new BatchPreparedStatementSetter() {

					public int getBatchSize() {
						return actors.size();
					}

					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, ((Actor) actors.get(i)).getFirstName());
						ps.setString(2, ((Actor) actors.get(i)).getLastName());
						ps.setLong(3, ((Actor) actors.get(i)).getId());
					}

				});
		return updateCounts;
	}

	/**  
     * 批量更新  
     * @param actors  
     * @return  
     */  
    @Override
	public int[] updateBatchActorsSimple(final List<Actor> actors){   
        //如果对象数组与占位符出现位置一一对应   
        //SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(actors.toArray());   
        List<Object[]> batch = new ArrayList<Object[]>();   
        for(Actor actor:actors){   
            Object[] values = new Object[]{//注意顺序   
                    actor.getFirstName(),   
                    actor.getLastName(),   
                    actor.getId()};   
            batch.add(values);   
        }   
        int[] updateCounts = this.simpleJdbcTemplate.batchUpdate(   
                "update actors set first_name = ?, last_name = ? where id =? ",   
                batch);   
        return updateCounts;   
    }
