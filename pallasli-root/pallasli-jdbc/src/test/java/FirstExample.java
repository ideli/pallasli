//STEP 1. Import required packages
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstExample {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/EMP";

	// Database credentials
	static final String USER = "username";
	static final String PASS = "password";

	public static void main(String[] args) {
		/**
		 * 导入JDBC包： 添加import语句到Java程序导入所需的类在Java代码中。
		 * 
		 * 注册JDBC驱动程序：这一步会导致JVM加载所需的驱动程序实现到内存中，因此它可以实现JDBC请求。
		 * 
		 * 数据库URL制定：这是创建格式正确的地址指向到要连接的数据库。
		 * 
		 * 创建连接对象：最后，代码调用DriverManager对象的getConnection()方法来建立实际的数据库连接。
		 * 
		 * 注册一个驱动程序中最常用的方法是使用Java的Class.forName()方法来动态加载驱动程序的类文件到内存中，它会自动将其注册。
		 * 这种方法是可取的，因为它允许使驱动注册配置，便于携带。
		 */
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// //可以使用getInstance()方法来解决不兼容的JVM，但要编写了两个额外的例外情况如下：

			// try {
			// Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			// }
			// catch(ClassNotFoundException ex) {
			// System.out.println("Error: unable to load driver class!");
			// System.exit(1);
			// catch(IllegalAccessException ex) {
			// System.out.println("Error: access problem while loading!");
			// System.exit(2);
			// catch(InstantiationException ex) {
			// System.out.println("Error: unable to instantiate driver!");
			// System.exit(3);
			// }
			// STEP 3: Open a connection

			// MySQL com.mysql.jdbc.Driver jdbc:mysql://hostname/ databaseName
			// ORACLE oracle.jdbc.driver.OracleDriver
			// jdbc:oracle:thin:@hostname:port Number:databaseName
			// DB2 COM.ibm.db2.jdbc.net.DB2Driver jdbc:db2:hostname:port
			// Number/databaseName
			// Sybase com.sybase.jdbc.SybDriver jdbc:sybase:Tds:hostname: port
			// Number/databaseName
			System.out.println("Connecting to database...");
			// jdbc:oracle:driver:username/password@database
			// conn = DriverManager.getConnection(DB_URL );
			// String URL = "jdbc:oracle:thin:@amrood:1521:EMP";
			// Properties info = new Properties( );
			// info.put( "user", "username" );
			// info.put( "password", "password" );
			//
			// Connection conn = DriverManager.getConnection(URL, info);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
			}
			// STEP 6: Clean-up environment stmt = conn.createStatement();
			sql = "SELECT id, first, last, age FROM Employees";
			rs = stmt.executeQuery(sql);

			int id = rs.getInt(1);
			if (rs.wasNull()) {
				id = 0;
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try

		}// end try
		System.out.println("Goodbye!");
		PreparedStatement pstmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			String sql = "UPDATE Employees set age=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);

			// Bind values into the parameters.
			pstmt.setInt(1, 35); // This would set age
			pstmt.setInt(2, 102); // This would set ID

			// Let us update age of the record with ID = 102;
			int rows = pstmt.executeUpdate();
			System.out.println("Rows impacted : " + rows);

			// Let us select all the records and display them.
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
			}
			// STEP 6: Clean-up environment
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("Goodbye!");

		// IN 它的值是在创建SQL语句时未知的参数。将值绑定到setXXX（）方法的参数。
		// OUT 其值是由它返回的SQL语句提供的参数。你从OUT参数的getXXX（）方法检索值。
		// INOUT 同时提供输入和输出值的参数。绑定setXXX（）方法的变量，并与getXXX（）方法检索值。
		CallableStatement cstmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			String sql = "{call getEmpName (?, ?)}";
			cstmt = conn.prepareCall(sql);

			// Bind IN parameter first, then bind OUT parameter
			int empID = 102;
			cstmt.setInt(1, empID); // This would set ID as 102
			// Because second parameter is OUT so register it
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);

			// Use execute method to run stored procedure.
			System.out.println("Executing stored procedure...");
			cstmt.execute();

			// Retrieve employee name with getXXX method
			String empName = cstmt.getString(2);
			System.out.println("Emp Name with ID:" + empID + " is " + empName);
			cstmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (cstmt != null)
					cstmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("Goodbye!");
	}// end main

}// end FirstExample