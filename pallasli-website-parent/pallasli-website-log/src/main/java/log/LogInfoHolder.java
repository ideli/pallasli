package log;

import com.pallasli.website.logger.entity.LogInfo;

public class LogInfoHolder {
	public static final ThreadLocal<LogInfo> LOG = new ThreadLocal<LogInfo>();

	public static LogInfo getLog() {
		LogInfo log = LOG.get();
		if (log != null) {
			return LOG.get();
		} else {
			LOG.set(new LogInfo());
			return LOG.get();
		}
	}

	public static void setLog(LogInfo log) {
		LOG.set(log);
	}

	public static void remove() {
		LOG.remove();
	}

}
