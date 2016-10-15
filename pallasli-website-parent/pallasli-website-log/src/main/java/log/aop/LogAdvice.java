package log.aop;

import com.pallasli.website.logger.entity.LogInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.Resource;

public class LogAdvice {

	private static final ThreadLocal<LogInfo> TH = new ThreadLocal<LogInfo>();

	@Resource(name = "jmsTemplate")
	private JmsTemplate jmsTemplate;

	// @Autowired
	// private ThreadPoolTaskExecutor poolTaskExecutor;

	public LogInfo getLogObject() {
		if (TH.get() == null) {
			TH.set(new LogInfo());
		}
		return TH.get();
	}

	// before通知方法
	public void beforeShow() {
		System.out.println(getClass().toString() + " before show");
	}

	// after通知方法
	public void afterShow() {
		System.out.println(getClass().toString() + " after show");
	}

	// afterReturn通知方法
	public void afterReturnShow() {
		System.out.println(getClass().toString() + " afterReturn show");
	}

	// afterThrowing通知方法
	public void afterThrowingShow() {
		System.out.println(getClass().toString() + " afterThrowing show");
	}

	// around通知方法
	public void aroundShow(ProceedingJoinPoint jpoint) {

		try {
			System.out.println("aroundShow");
			jpoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		// try{
		// //执行目标对象的连接点处的方法
		// Random random = new Random();
		// String rr= new Integer( Math.abs(random.nextInt())).toString();
		// LogInfo log=new LogInfo(rr);
		// log.setMethodName(jpoint.getTarget().toString());
		// TH.set(log);
		//
		// //开启线程发送
		// LogSend send=new LogSend();
		// send.setJmsTemplate(jmsTemplate);
		// System.out.println(getLogObject().toString()+"生成完");
		// send.setLog(getLogObject());
		// poolTaskExecutor.execute(send);
		// }catch(Throwable e){
		// System.out.println(
		// getClass().toString()+" around afterThrowing show" );
		// }
	}
}
