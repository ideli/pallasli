package com.pallasli.hibernate421.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * 这个类不但在它的静态初始化过程（仅当加载这个类的时候被 JVM 执行一次）中产生全局的org.hibernate.SessionFactory，
 * 而且隐藏了它使用了静态 singleton 的事实。它也可能在应用程序服务器中的 JNDI 查找
 * org.hibernate.SessionFactory。 如果你在配置文件中给 org.hibernate.SessionFactory 一个名字，在
 * 它创建后，Hibernate 会试着把它绑定到 JNDI。 要完全避免这样的代码，你也可以使用 JMX 部署，让具有 JMX 能力的容器来实例化
 * HibernateService 并把它绑定到 JNDI。 这些高级可选项在后面的章节中会讨论到。
 * 再次编译这个应用程序应该不会有问题。最后我们需要配置一个日志（logging)系统 —Hibernate 使用通用日志接口， 允许你在 Log4j 和
 * JDK 1.4 日志之间进行选择。多数开发者更喜欢 Log4j：从 Hibernate 的发布包中（它在 etc/ 目录下）拷贝
 * log4j.properties 到你的 src目录，与 hibernate.cfg.xml 放在一起。看一下配置示例，如果你希望看到更加详细的输出信息，
 * 你可以修改配置。默认情况下，只有 Hibernate 的启动信息才会显示在标准输出上。
 * 
 * @author Administrator
 * 
 */
public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration cfg = new Configuration().configure().setProperty(
					"hibernate.current_session_context_class", "thread");

			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).buildServiceRegistry();
			return cfg.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
