import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 使用@WebListener注解将实现了ServletContextListener接口的MyServletContextListener标注为监听器
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ServletContex销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ServletContex初始化");
		System.out.println(sce.getServletContext().getServerInfo());
	}
}