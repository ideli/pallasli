package com.pallasli.study.hibernate.client;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import com.pallasli.study.hibernate.model.UserModel;

public class HibernateClient {

	public static void main(String[] args) {
		// SessionFactory sf = buildSessionFactory3();
		SessionFactory sf = buildSessionFactory4();
		Session s = null;
		Transaction t = null;

		try {
			// 准备数据
			UserModel um = new UserModel();
			um.setUuid("1");
			um.setUserId("id1");
			um.setName("name1");
			um.setAge(1);
			s = sf.openSession();
			t = s.beginTransaction();
			s.save(um);
			t.commit();
			// s = sf.openSession();
			// t = s.beginTransaction();
			UserModel model = (UserModel) s.get(UserModel.class, "1");
			System.out.println(model.getName());
			// t.commit();
			List<UserModel> l = s.createQuery("from UserModel where 1=?")
					.setInteger(0, 1).list();
			System.out.println(l.get(0).getName());
			l = s.createQuery("select u from UserModel u").list();
			System.out.println(l.get(0).getName());
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
	}

	private static SessionFactory buildSessionFactory4() {
		Configuration cfg = new Configuration();
		// .configure()表示调用xml文件，否则调用properties文件
		SessionFactory sf = cfg.configure().buildSessionFactory(
				new ServiceRegistryBuilder().applySettings(cfg.getProperties())
						.buildServiceRegistry());
		// ServiceRegistryBuilder builder = new ServiceRegistryBuilder()
		// .configure();
		// // builder.applySetting("connection.driver_class",
		// // "oracle.jdbc.driver.OracleDriver");
		// // builder.applySetting("connection.url",
		// // "jdbc:oracle:thin:@localhost:1521:orcl");
		// // builder.applySetting("connection.username", "ztb");
		// // builder.applySetting("connection.password", "ztb");
		// // builder.applySetting("connection.pool_size", "2");
		// // builder.applySetting("hibernate.dialect",
		// // "org.hibernate.dialect.OracleDialect");
		// // builder.applySetting("show_sql", "true");
		// //
		// MetadataSources sources = new MetadataSources(
		// builder.buildServiceRegistry());
		// sources.addResource("com/pallasli/study/hibernate5/model/UserModel.hbm.xml");
		//
		// MetadataImpl metadata = (MetadataImpl) sources.buildMetadata();
		// SessionFactory sf = metadata.getSessionFactoryBuilder()
		// .buildSessionFactory();
		return sf;
	}

	private static SessionFactory buildSessionFactory3() {
		return new Configuration().configure().buildSessionFactory();
	}

}
