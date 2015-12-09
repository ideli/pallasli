package com.pallas.design.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.design.bean.Fieldset;
import com.pallas.design.dao.FieldsetDAO;
import com.pallasli.hibernate421.factory.HibernateSessionFactory;

public class FieldsetDAOImpl implements FieldsetDAO {

	@Override
	public List<Fieldset> selectAll() {
		Session session = HibernateSessionFactory.designSession();
		session.clear();
		List<Fieldset> list = new ArrayList<Fieldset>();
		try {
			Query query = session.createQuery("from Fieldset ");
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
	public Fieldset insert(Fieldset fieldset) {
		Session session = HibernateSessionFactory.designSession();
		session.beginTransaction();
		try {
			session.save(fieldset);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return fieldset;
	}

	@Override
	public boolean update(Fieldset fieldset) {
		boolean success = false;
		Session session = HibernateSessionFactory.designSession();
		session.beginTransaction();
		try {
			session.merge(fieldset);
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
	public boolean delete(Fieldset fieldset) {
		boolean success = false;
		Session session = HibernateSessionFactory.designSession();
		try {
			session.delete(fieldset);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}
}
