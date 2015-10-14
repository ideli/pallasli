package com.pallas.design.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.design.bean.Page;
import com.pallas.design.dao.PageDAO;
import com.pallasli.hibernate.factory.HibernateSessionFactory;

public class PageDAOImpl implements PageDAO {

	@Override
	public List<Page> selectAll() {
		Session session = HibernateSessionFactory.designSession();
		session.clear();
		List<Page> list = new ArrayList<Page>();
		try {
			Query query = session.createQuery("from Page ");
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
	public Page insert(Page page) {
		Session session = HibernateSessionFactory.designSession();
		session.beginTransaction();
		try {
			session.save(page);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return page;
	}

	@Override
	public boolean update(Page page) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		session.beginTransaction();
		try {
			session.merge(page);
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
	public boolean delete(Page page) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		try {
			session.delete(page);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}
}
