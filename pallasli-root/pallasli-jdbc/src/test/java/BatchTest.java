import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {
	static Connection conn = null;

	public static void main(String[] args) throws SQLException {
		// Create statement object
		Statement stmt = conn.createStatement();

		// Set auto-commit to false
		conn.setAutoCommit(false);

		// Create SQL statement
		String SQL = "INSERT INTO Employees (id, first, last, age) "
				+ "VALUES(200,'Zia', 'Ali', 30)";
		// Add above SQL statement in the batch.
		stmt.addBatch(SQL);

		// Create one more SQL statement
		SQL = "INSERT INTO Employees (id, first, last, age) "
				+ "VALUES(201,'Raj', 'Kumar', 35)";
		// Add above SQL statement in the batch.
		stmt.addBatch(SQL);

		// Create one more SQL statement
		SQL = "UPDATE Employees SET age = 35 " + "WHERE id = 100";
		// Add above SQL statement in the batch.
		stmt.addBatch(SQL);

		// Create an int[] to hold returned values
		int[] count = stmt.executeBatch();

		// Explicitly commit statements to apply changes
		conn.commit();

		// Create SQL statement
		SQL = "INSERT INTO Employees (id, first, last, age) "
				+ "VALUES(?, ?, ?, ?)";

		// Create PrepareStatement object
		PreparedStatement pstmt = conn.prepareStatement(SQL);

		// Set auto-commit to false
		conn.setAutoCommit(false);

		// Set the variables
		pstmt.setInt(1, 400);
		pstmt.setString(2, "Pappu");
		pstmt.setString(3, "Singh");
		pstmt.setInt(4, 33);
		// Add it to the batch
		pstmt.addBatch();

		// Set the variables
		pstmt.setInt(1, 401);
		pstmt.setString(2, "Pawan");
		pstmt.setString(3, "Singh");
		pstmt.setInt(4, 31);
		// Add it to the batch
		pstmt.addBatch();

		// add more batches
		// Create an int[] to hold returned values
		count = stmt.executeBatch();
		System.out.println(count);
		// Explicitly commit statements to apply changes
		conn.commit();
	}
}
