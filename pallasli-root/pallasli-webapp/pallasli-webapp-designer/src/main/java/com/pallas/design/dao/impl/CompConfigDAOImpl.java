package com.pallas.design.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.design.bean.CompConfig;
import com.pallas.design.dao.CompConfigDAO;
import com.pallasli.hibernate.factory.HibernateSessionFactory;

public class CompConfigDAOImpl implements CompConfigDAO {

	@Override
	public CompConfig insert(CompConfig compConfig) {
		Session session = HibernateSessionFactory.designSession();
		session.beginTransaction();
		session.save(compConfig);
		session.getTransaction().commit();
		session.flush();
		return compConfig;
	}

	@Override
	public List<CompConfig> selectAll() {
		Session session = HibernateSessionFactory.designSession();
		List<CompConfig> list = new ArrayList<CompConfig>();
		try {
			Query query = session.createQuery("from CompConfig ");
			// query.setLong(0, parentId);
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
	public boolean update(CompConfig compConfig) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		session.beginTransaction();
		try {
			CompConfig reftype = (CompConfig) session.get(CompConfig.class,
					compConfig.getId());
			reftype.setCompName(compConfig.getCompName());
			session.saveOrUpdate(reftype);
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
	public boolean delete(CompConfig compConfig) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		try {
			session.delete(compConfig);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}
}
