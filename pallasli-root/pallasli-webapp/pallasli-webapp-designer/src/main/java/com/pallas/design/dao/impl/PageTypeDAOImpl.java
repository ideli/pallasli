package com.pallas.design.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.design.bean.PageType;
import com.pallas.design.dao.PageTypeDAO;
import com.pallasli.hibernate421.factory.HibernateSessionFactory;

public class PageTypeDAOImpl implements PageTypeDAO {

	@Override
	public List<PageType> selectAll() {
		Session session = HibernateSessionFactory.designSession();
		List<PageType> list = new ArrayList<PageType>();
		try {
			Query query = session.createQuery("from PageType ");
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
	public PageType insert(PageType pageType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(PageType pageType) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		session.beginTransaction();
		try {
			session.merge(pageType);
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
	public boolean delete(PageType pageType) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		try {
			session.delete(pageType);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}

}
