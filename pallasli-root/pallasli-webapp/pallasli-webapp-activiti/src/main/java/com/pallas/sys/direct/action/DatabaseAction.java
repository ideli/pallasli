package com.pallas.sys.direct.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pallas.activiti.model.Menu;

public class DatabaseAction {
	/**
	 * 获得MyBatis SqlSessionFactory
	 * SqlSessionFactory负责创建SqlSession，一旦创建成功，就可以用SqlSession实例来执行映射语句
	 * ，commit，rollback，close等方法。
	 * 
	 * @return
	 */
	private SqlSessionFactory getSessionFactory() {
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("****************");
		SqlSessionFactory sessionFactory = null;
		String resource = "configuration.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader(resource));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sessionFactory;
	}
	public List<Menu> loadTables() {

		List<Menu> list = new ArrayList<Menu>();
		SqlSession sqlSession = getSessionFactory().openSession();
		String sql = "show tables";
		try {
			ResultSet rs = sqlSession.getConnection().createStatement()
					.executeQuery(sql);
			long i=1;
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(i++);
				menu.setMenuKey(rs.getString("Tables_in_activiti"));
				menu.setMenuName(rs.getString("Tables_in_activiti"));
				menu.setMenuCaption(rs.getString("Tables_in_activiti"));
				list.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return list;
	}
	public JsonObject loadColumns(String tableName) {
		JsonObject result = new JsonObject();
		JsonArray array = new JsonArray();
		SqlSession sqlSession = getSessionFactory().openSession();
		String sql = "desc "+ tableName;
		String fields="";
		try {
			ResultSet rs = sqlSession.getConnection().createStatement()
					.executeQuery(sql);
			while (rs.next()) {
				JsonObject json=new JsonObject();
				json.addProperty("dataIndex", rs.getString("Field"));
				json.addProperty("text", rs.getString("Field"));
				json.addProperty("flex", 1);
				json.addProperty("align","left");
				//json.addProperty("Type", rs.getString("Type"));
				//json.addProperty("Null", rs.getString("Null"));
				//json.addProperty("Key", rs.getString("Key"));
				//json.addProperty("Default", rs.getString("Default"));
				//json.addProperty("Extra", rs.getString("Extra"));
				//
				array.add(json);
				fields+=",'"+rs.getString("Field")+"'";
			}
			if(fields.lastIndexOf(",")>0)fields=fields.replaceFirst(",", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		result.add("columns", array);
		result.addProperty("fields", fields);
		return result;
	}
	public JsonArray selectDataFromTable(String tableName,String columns) {
		JsonArray array = new JsonArray();
		SqlSession sqlSession = getSessionFactory().openSession();
		columns=columns.replaceAll("'","");
		String sql = "select "+columns +" from "+ tableName;
		try {
			ResultSet rs = sqlSession.getConnection().createStatement()
					.executeQuery(sql);
			String[] columnarr=columns.split(",");
			int i=0;
			while (rs.next()) {
				JsonObject json=new JsonObject();
				for(String colName:columnarr){
					int type=rs.getType();
					try {
					switch(type){
					case 0: json.addProperty(colName, rs.getString(colName));  break;
					case 1: json.addProperty(colName, rs.getString(colName));  break;
					case 2: json.addProperty(colName, rs.getString(colName));  break;
					case 3: json.addProperty(colName, rs.getString(colName));  break;
					case 4: json.addProperty(colName, rs.getString(colName));  break;
					case 5: json.addProperty(colName, rs.getString(colName));  break;
				//	case 1003:  break;
					default:json.addProperty(colName, rs.getString(colName));  break;
					
					}
					} catch (SQLException e) {
						System.out.println(type);
						System.out.println(colName);
						e.printStackTrace();
					} 
				}
				if(i++<20){
				array.add(json);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return array;
	}

}
