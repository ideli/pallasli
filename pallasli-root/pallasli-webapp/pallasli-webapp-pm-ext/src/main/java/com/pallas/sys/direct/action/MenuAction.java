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

import com.pallas.sys.bean.Menu;

public class MenuAction {

	public List<Menu> loadSysMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	public Menu getMenu(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Menu> loadModuleMenu(Menu sysMenu) {

		List<Menu> list = new ArrayList<Menu>();
		SqlSession sqlSession = getSessionFactory().openSession();
		String sql = "select * from t_app_menu where parent_key='"
				+ sysMenu.getMenuKey() + "'";
		try {
			ResultSet rs = sqlSession.getConnection().createStatement()
					.executeQuery(sql);
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getLong("ID"));
				menu.setMenuKey(rs.getString("MENU_KEY"));
				menu.setAppKey(rs.getString("APP_KEY"));
				menu.setMenuName(rs.getString("MENU_NAME"));
				menu.setMenuCaption(rs.getString("MENU_CAPTION"));
				menu.setUrlPath(rs.getString("URL_PATH"));
				menu.setMenuTypeCode(rs.getString("MENU_TYPE_CODE"));
				list.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public Menu saveMenu(Menu menu) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteMenu(Menu menu) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Menu> getChildMenu(String key) {

		List<Menu> list = new ArrayList<Menu>();
		SqlSession sqlSession = getSessionFactory().openSession();
		String sql = "select * from t_sys_menu ";
		try {
			ResultSet rs = sqlSession.getConnection().createStatement()
					.executeQuery(sql);
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getLong("ID"));
				menu.setAppKey(rs.getString("APP_KEY"));
				menu.setMenuKey(rs.getString("MENU_KEY"));
				menu.setMenuName(rs.getString("MENU_NAME"));
				menu.setMenuCaption(rs.getString("MENU_CAPTION"));
				list.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public Menu getMenu(String key) {

		Menu menu = new Menu();
		SqlSession sqlSession = getSessionFactory().openSession();
		String sql = "select * from t_sys_menu ";
		try {
			ResultSet rs = sqlSession.getConnection().createStatement()
					.executeQuery(sql);
			while (rs.next()) {
				menu.setId(rs.getLong("ID"));
				menu.setAppKey(rs.getString("APP_KEY"));
				menu.setMenuKey(rs.getString("MENU_KEY"));
				menu.setMenuName(rs.getString("MENU_NAME"));
				menu.setMenuCaption(rs.getString("MENU_CAPTION"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return menu;
	}

	/**
	 * 获得MyBatis SqlSessionFactory
	 * SqlSessionFactory负责创建SqlSession，一旦创建成功，就可以用SqlSession实例来执行映射语句
	 * ，commit，rollback，close等方法。
	 * 
	 * @return
	 */
	private SqlSessionFactory getSessionFactory() {
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

}
