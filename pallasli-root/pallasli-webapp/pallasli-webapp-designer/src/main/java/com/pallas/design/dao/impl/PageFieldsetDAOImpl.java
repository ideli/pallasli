package com.pallas.design.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.pallas.design.bean.PageFieldset;
import com.pallas.design.dao.PageFieldsetDAO;
import com.pallasli.hibernate.factory.HibernateSessionFactory;

public class PageFieldsetDAOImpl implements PageFieldsetDAO {

	@Override
	public List<PageFieldset> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageFieldset insert(PageFieldset pageFieldset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(PageFieldset pageFieldset) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		session.beginTransaction();
		try {
			session.merge(pageFieldset);
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
	public boolean delete(PageFieldset pageFieldset) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		try {
			session.delete(pageFieldset);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}
}
