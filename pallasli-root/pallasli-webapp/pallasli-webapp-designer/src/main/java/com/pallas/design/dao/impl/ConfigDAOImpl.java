package com.pallas.design.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.design.bean.Config;
import com.pallas.design.dao.ConfigDAO;
import com.pallasli.hibernate421.factory.HibernateSessionFactory;

public class ConfigDAOImpl implements ConfigDAO {

	@Override
	public List<Config> selectAll() {
		Session session = HibernateSessionFactory.designSession();
		session.clear();
		List<Config> list = new ArrayList<Config>();
		try {
			Query query = session.createQuery("from Config ");
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
	public Config insert(Config config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Config config) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		session.beginTransaction();
		try {
			session.merge(config);
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
	public boolean delete(Config config) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		try {
			session.delete(config);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}
}
