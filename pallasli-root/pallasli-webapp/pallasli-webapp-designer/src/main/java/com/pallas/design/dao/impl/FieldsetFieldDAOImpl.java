package com.pallas.design.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.design.bean.FieldsetField;
import com.pallas.design.dao.FieldsetFieldDAO;
import com.pallasli.hibernate421.factory.HibernateSessionFactory;

public class FieldsetFieldDAOImpl implements FieldsetFieldDAO {

	@Override
	public List<FieldsetField> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FieldsetField insert(FieldsetField fieldsetField) {
		Session session = HibernateSessionFactory.designSession();
		session.beginTransaction();
		try {
			session.merge(fieldsetField);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return fieldsetField;
	}

	@Override
	public boolean update(FieldsetField fieldsetField) {
		boolean success = false;
		Session session = HibernateSessionFactory.designSession();
		session.beginTransaction();
		try {
			session.merge(fieldsetField);
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
	public boolean delete(FieldsetField fieldsetField) {
		boolean success = false;
		Session session = HibernateSessionFactory.designSession();
		try {
			session.delete(fieldsetField);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}

	@Override
	public List<FieldsetField> selectByFieldsetKey(String fieldsetKey) {
		Session session = HibernateSessionFactory.designSession();
		session.clear();
		List<FieldsetField> list = new ArrayList<FieldsetField>();
		try {
			Query query = session
					.createQuery("from FieldsetField where fieldset_name =? ");
			query.setString(0, fieldsetKey);
			list = query.list();
			query = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return list;
	}
}
