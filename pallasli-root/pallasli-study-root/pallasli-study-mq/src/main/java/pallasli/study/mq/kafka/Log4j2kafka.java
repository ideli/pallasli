package pallasli.study.mq.kafka;

import org.apache.log4j.Logger;

public class Log4j2kafka {
	private static final Logger logger = Logger.getLogger(Log4j2kafka.class);

	public static void main(String[] args) {
		logger.info("输出信息……");
		logger.trace("随意打印……");
		logger.debug("调试信息……");
		logger.warn("警告信息……");
		try {
			throw new Exception("错误消息啊");
		} catch (Exception e) {
			logger.error("处理业务逻辑的时候发生一个错误……", e);
		}
	}

}
