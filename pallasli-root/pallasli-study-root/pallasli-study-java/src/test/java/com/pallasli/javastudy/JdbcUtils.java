package com.pallasli.javastudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.junit.Test;

public class JdbcUtils {
	@Test
	public void rowOpertion() throws Exception {
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:derby://localhost:1527/testDb", "name", "pass");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		String query = "select * from emp";
		ResultSet rs = stmt.executeQuery(query);
		rs.last();
		System.out.println("No of rows in table=" + rs.getRow());
		rs.moveToInsertRow();
		rs.updateInt("id", 9);
		rs.updateString("name", "sujay");
		rs.updateString("job", "trainee");
		rs.insertRow();
		System.out.println("Row added");
		rs.first();
		rs.deleteRow();
		System.out.println("first row deleted");
	}

	@Test
	public void multiInstance() throws Exception {
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:derby://localhost:1527/testDb", "name", "pass");
		Statement stmt = con.createStatement();
		String query = "select * from emp order by name";
		ResultSet rs = stmt.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		System.out.println("no of columns in the table= "
				+ rsmd.getColumnCount());
		System.out.println("Name of the first column " + rsmd.getColumnName(1));
		System.out.println("Type of the second column "
				+ rsmd.getColumnTypeName(2));
		System.out.println("No of characters in 3rd column "
				+ rsmd.getColumnDisplaySize(2));
	}

}
