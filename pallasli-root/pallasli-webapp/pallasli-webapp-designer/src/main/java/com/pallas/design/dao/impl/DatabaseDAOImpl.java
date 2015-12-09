package com.pallas.design.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.design.bean.Database;
import com.pallas.design.dao.DatabaseDAO;
import com.pallasli.hibernate421.factory.HibernateSessionFactory;

public class DatabaseDAOImpl implements DatabaseDAO {
	@Override
	public Database insert(Database database) {
		Session session = HibernateSessionFactory.designSession();
		session.beginTransaction();
		if (database.getDatabaseName() == null) {
			database.setDatabaseName(database.getDatabaseSchema());
		}
		if (database.getDatabaseCaption() == null) {
			database.setDatabaseCaption(database.getDatabaseSchema());
		}
		session.save(database);
		session.getTransaction().commit();
		session.flush();
		return database;
	}

	@Override
	public List<Database> selectAll() {
		Session session = HibernateSessionFactory.designSession();
		session.clear();
		List<Database> list = new ArrayList<Database>();
		try {
			Query query = session.createQuery("from Database ");
			list = query.list();
			query = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return list;
	}

	@Override
	public boolean update(Database database) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		session.beginTransaction();
		try {
			session.merge(database);
			session.getTransaction().commit();
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}

	@Override
	public boolean delete(Database database) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		try {
			session.delete(database);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}
}
