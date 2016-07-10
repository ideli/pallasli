package pallasli.webapp.log;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogPush {
	public void push(String data) {

		BasicConfigurator.configure();
		BasicConfigurator.configure(); // 自动快速地使用缺省Log4j环境。
		PropertyConfigurator.configure(
				"/Users/lyt1987/Desktop/GitHub/pallasli/pallasli-root/pallasli-webapp/pallasli-webapp-log/src/main/resources/log4j.properties");// 读取使用Java的特性文件编写的配置文件。
		// DOMConfigurator.configure ( String filename ) ;//读取XML形式的配置文件。

		Logger log = Logger.getLogger(this.getClass());

		log.info(data);
		log.warn(data);
		log.debug(data);
		log.error(data);
	}
}
