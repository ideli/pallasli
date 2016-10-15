package log.aop;

import com.pallasli.website.log.LogInfoHolder;
import com.pallasli.website.log.LogSend;
import com.pallasli.website.logger.entity.LogInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JmsAdvice {

	private static final ThreadLocal<LogInfo> TH = new ThreadLocal<LogInfo>();

	@Resource(name = "jmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	private ThreadPoolTaskExecutor poolTaskExecutor;

	public LogInfo getLogObject() {
		if (TH.get() == null) {
			TH.set(new LogInfo());
		}
		return TH.get();
	}

	// before通知方法
	public void beforeShow() {
		// Date startTime=new Date();
		// SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		// String start=df.format(startTime);
		// System.out.println( getClass().toString()+" before show"+start);

	}

	// after通知方法
	public void afterShow() {

	}

	// afterReturn通知方法
	public void afterReturnShow() {
		// System.out.println( getClass().toString()+" afterReturn show" );
	}

	// afterThrowing通知方法
	public void afterThrowingShow() {
		// System.out.println( getClass().toString()+" afterThrowing show" );
	}

	// around通知方法
	public Object aroundShow(ProceedingJoinPoint jpoint) {
		// List<Object> agrs=new ArrayList<Object>();
		try {
			System.out.println("aroundShow");
			String methodName = jpoint.getSignature().getName();
			StringBuilder builder = new StringBuilder(" ");
			Object[] args = jpoint.getArgs();
			StopWatch clock = new StopWatch();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
			String start = df.format(new Date());
			clock.start();
			// 执行目标对象的连接点处的方法
			jpoint.getArgs();
			jpoint.getSignature().getName();

			Object rArg = jpoint.proceed();

			clock.stop();
			String stop = df.format(new Date());
			LogInfo log = LogInfoHolder.getLog();
			// LogInfo log=new LogInfo();
			log.setBeginTime(start);
			log.setEndTime(stop);
			log.setMethodName(methodName);
			log.setMethodAgrs(builder.toString());
			log.setTime(Long.toString(clock.getTotalTimeMillis()));
			log.setArgs(args);
			System.out.println(log);
			TH.set(log);
			// 开启线程发送
			LogSend send = new LogSend();
			send.setJmsTemplate(jmsTemplate);
			send.setLog(getLogObject());
			poolTaskExecutor.execute(send);

			return rArg;
		} catch (Throwable e) {
			e.printStackTrace();
			// System.out.println(
			// getClass().toString()+" around afterThrowing show" );
		} finally {
			TH.remove();
		}
		return null;

	}
}
