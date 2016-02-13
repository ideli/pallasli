package com.pallasli.study.hibernate.factory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateSessionFactory {

	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static final Configuration cfg = new Configuration();
	private static SessionFactory sessionFactory;

	private static String DESIGNER_CONFIG_FILE_LOCATION = "/hibernate/designer.cfg.xml";
	private static String DESIGN_ALL_CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	private static String KNOWLEDGE_CONFIG_FILE_LOCATION = "/hibernate/knowledge.cfg.xml";
	private static String DATABASE_ALL_CONFIG_FILE_LOCATION = "/hibernate/databaseAll.cfg.xml";

	/**
	 * 获取当前Session
	 * 
	 * @return Session
	 * @throws HibernateException
	 */
	public static Session designerSession() {
		Session session = threadLocal.get();
		if (session == null) {
			if (sessionFactory == null) {
				cfg.configure(DESIGNER_CONFIG_FILE_LOCATION);

				// 非web配置
				// cfg.setProperty(
				// "hibernate.current_session_context_class", "thread");

				ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
						.applySettings(cfg.getProperties())
						.buildServiceRegistry();
				sessionFactory = cfg.buildSessionFactory(serviceRegistry);
			}
			session = sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}

	public static Session designSession() {
		Session session = threadLocal.get();
		if (session == null) {
			if (sessionFactory == null) {
				cfg.configure(DESIGN_ALL_CONFIG_FILE_LOCATION);

				// 非web配置
				// cfg.setProperty(
				// "hibernate.current_session_context_class", "thread");
				ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
						.applySettings(cfg.getProperties())
						.buildServiceRegistry();
				sessionFactory = cfg.buildSessionFactory(serviceRegistry);
			}
			session = sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}

	/**
	 * 获取当前Session
	 * 
	 * @return Session
	 * @throws HibernateException
	 */
	public static Session knowledgeSession() {
		Session session = threadLocal.get();
		if (session == null) {
			if (sessionFactory == null) {
				cfg.configure(KNOWLEDGE_CONFIG_FILE_LOCATION);

				// 非web配置
				// cfg.setProperty(
				// "hibernate.current_session_context_class", "thread");

				ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
						.applySettings(cfg.getProperties())
						.buildServiceRegistry();
				sessionFactory = cfg.buildSessionFactory(serviceRegistry);
			}
			session = sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}

	/**
	 * 获取当前Session
	 * 
	 * @return Session
	 * @throws HibernateException
	 */
	public static Session databaseAllSession() {
		Session session = threadLocal.get();
		if (session == null) {
			if (sessionFactory == null) {
				cfg.configure(DATABASE_ALL_CONFIG_FILE_LOCATION);

				// 非web配置
				// cfg.setProperty(
				// "hibernate.current_session_context_class", "thread");

				ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
						.applySettings(cfg.getProperties())
						.buildServiceRegistry();
				sessionFactory = cfg.buildSessionFactory(serviceRegistry);
			}
			session = sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}

	/**
	 * 获取当前Session
	 * 
	 * @return Session
	 * @throws HibernateException
	 */
	public static Session currentSession() {
		Session session = threadLocal.get();
		if (session == null) {
			if (sessionFactory == null) {
				cfg.configure(CONFIG_FILE_LOCATION);

				// 非web配置
				// cfg.setProperty(
				// "hibernate.current_session_context_class", "thread");

				ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
						.applySettings(cfg.getProperties())
						.buildServiceRegistry();
				sessionFactory = cfg.buildSessionFactory(serviceRegistry);
			}
			session = sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}

	/**
	 * 关闭当前Session
	 */
	public static void closeSession() {
		Session session = threadLocal.get();
		threadLocal.set(null);
		if (session != null) {
			session.close();
		}
	}
}
