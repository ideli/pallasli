package com.pallas.design.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.design.bean.CompType;
import com.pallas.design.dao.CompTypeDAO;
import com.pallasli.hibernate421.factory.HibernateSessionFactory;

public class CompTypeDAOImpl implements CompTypeDAO {

	@Override
	public CompType insert(CompType compType) {
		Session session = HibernateSessionFactory.designSession();
		session.beginTransaction();
		session.save(compType);
		session.getTransaction().commit();
		session.flush();
		return compType;
	}

	@Override
	public List<CompType> selectAll() {
		Session session = HibernateSessionFactory.designSession();
		List<CompType> list = new ArrayList<CompType>();
		try {
			Query query = session.createQuery("from CompType ");
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
	public boolean update(CompType compType) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		session.beginTransaction();
		try {
			session.merge(compType);
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
	public boolean delete(CompType compType) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		try {
			session.delete(compType);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}

}
