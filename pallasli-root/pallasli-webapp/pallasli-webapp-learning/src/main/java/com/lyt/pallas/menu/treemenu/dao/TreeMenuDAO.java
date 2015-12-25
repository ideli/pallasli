package com.lyt.pallas.menu.treemenu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lyt.pallas.menu.treemenu.model.TreeMenu;

public class TreeMenuDAO {

	String driverClassName = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@10.24.78.42:1521:lyt";
	String username = "lyt";
	String password = "lyt";

	public List<TreeMenu> findAllByParentId(long parentId) {
		List<TreeMenu> list = new ArrayList<TreeMenu>();
		try {
			Class.forName(driverClassName);
			Connection conn = DriverManager.getConnection(url, username,
					password);
			String sql = "SELECT * FROM TREE_MENU WHERE PARENT_ID=? ORDER BY IS_LEAF,ORDER_NUM";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, parentId);
			ResultSet rt = null;
			rt = pstmt.executeQuery();
			while (rt.next()) {
				TreeMenu treeMenu = new TreeMenu();
				treeMenu.setId(rt.getLong("id"));
				treeMenu.setIsLeaf(rt.getString("is_leaf"));
				treeMenu.setName(rt.getString("name"));
				treeMenu.setOrderNum(rt.getLong("order_num"));
				treeMenu.setParentId(rt.getLong("parent_id"));
				treeMenu.setUrlCode(rt.getString("url_code"));
				treeMenu.setUrlPath(rt.getString("url_path"));

				list.add(treeMenu);
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}

}
