import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

public class log4jExample {
	/* Get actual class name to be printed on */

	@Test
	public void testHtml() {
		PropertyConfigurator
				.configure("src/test/resources/log4j-html.properties");
		pringlog();
	}

	@Test
	public void testFile() {
		PropertyConfigurator
				.configure("src/test/resources/log4j-file.properties");
		pringlog();
	}

	@Test
	public void testUseXmlConfig() {
		PropertyConfigurator.configure("src/test/resources/log4j-xml.xml");
		pringlog();
	}

	@Test
	public void testPattern() {
		PropertyConfigurator
				.configure("src/test/resources/log4j-pattern.properties");
		pringlog();
	}

	@Test
	public void testDailyRollingFile() {
		PropertyConfigurator
				.configure("src/test/resources/log4j-daily-rolling-file.properties");
		pringlog();
	}

	@Test
	public void testDatabase() {
		Connection conn = null;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(
					"jdbc:h2:database/h2db;IFEXISTS=TRUE", "sa", "sa");
		} catch (Exception e) {
			System.out.println("not exists");
		}
		if (conn == null) {

			try {
				conn = DriverManager.getConnection("jdbc:h2:database/h2db",
						"sa", "sa");
				Statement s = conn.createStatement();
				s.execute("CREATE TABLE LOGS   (USER_ID VARCHAR(20) NOT NULL,    DATED   DATE NOT NULL,    LOGGER  VARCHAR(50) NOT NULL,    LEVEL   VARCHAR(10) NOT NULL,    MESSAGE VARCHAR(1000) NOT NULL  )");

				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		PropertyConfigurator
				.configure("src/test/resources/log4j-database.properties");
		pringlog();
		try {
			conn = DriverManager.getConnection("jdbc:h2:database/h2db", "sa",
					"sa");
			Statement s = conn.createStatement();
			ResultSet result = s.executeQuery("select * from LOGS");
			System.out.println(result.getRow());
			conn.commit();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRollingFile() {
		PropertyConfigurator
				.configure("src/test/resources/log4j-rolling-file.properties");
		pringlog();
	}

	public void pringlog() {
		Logger log = Logger.getLogger(log4jExample.class.getName());
		log.setLevel(Level.WARN);

		log.trace("Trace Message!");
		log.debug("Debug Message!");
		log.info("Info Message!");
		log.warn("Warn Message!");
		log.error("Error Message!");
		log.fatal("Fatal Message!");
	}
}
