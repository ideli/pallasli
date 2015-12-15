import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class log4jExample {
	/* Get actual class name to be printed on */

	public static void main(String[] args) throws IOException, SQLException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException {

		// Driver driver = (Driver)
		// Class.forName("org.h2.Driver").newInstance();
		// Properties info = new Properties();
		// info.setProperty("username", "sa");
		// info.setProperty("password", "sa");
		// Connection conn = driver.connect("jdbc:h2:database/h2db", info);
		// Statement s = conn.createStatement();
		// s.execute("CREATE TABLE LOGS   (USER_ID VARCHAR(20) NOT NULL,    DATED   DATE NOT NULL,    LOGGER  VARCHAR(50) NOT NULL,    LEVEL   VARCHAR(10) NOT NULL,    MESSAGE VARCHAR(1000) NOT NULL  )");
		// conn.commit();
		// conn.close();

		PropertyConfigurator
				.configure("src/test/resources/log4j-database.properties");
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
