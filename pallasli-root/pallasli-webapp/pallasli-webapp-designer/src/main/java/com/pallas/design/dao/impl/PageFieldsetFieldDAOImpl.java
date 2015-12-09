package com.pallas.design.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.pallas.design.bean.PageFieldsetField;
import com.pallas.design.dao.PageFieldsetFieldDAO;
import com.pallasli.hibernate421.factory.HibernateSessionFactory;

public class PageFieldsetFieldDAOImpl implements PageFieldsetFieldDAO {

	@Override
	public List<PageFieldsetField> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageFieldsetField insert(PageFieldsetField pageFieldsetField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(PageFieldsetField pageFieldsetField) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		session.beginTransaction();
		try {
			session.merge(pageFieldsetField);
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
	public boolean delete(PageFieldsetField pageFieldsetField) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		try {
			session.delete(pageFieldsetField);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}
}
