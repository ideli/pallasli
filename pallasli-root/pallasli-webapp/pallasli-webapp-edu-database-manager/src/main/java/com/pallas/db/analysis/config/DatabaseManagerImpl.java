package com.pallas.db.analysis.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManagerImpl implements DatabaseManager {

	@Override
	public void saveDatabase(Database db) {

	}

	@Override
	public void addDatabase(Database db) {
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:~/test",
					"sa", "");
			// add application code here
			Statement stmt = conn.createStatement();
			stmt.execute("");
			ResultSet rs = stmt.executeQuery("SELECT * FROM TEST ");
			while (rs.next()) {
				System.out
						.println(rs.getInt("ID") + "," + rs.getString("NAME"));
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alterDatabase(Database db) {

	}

	@Override
	public void deleteDatabase(Database db) {

	}

}
