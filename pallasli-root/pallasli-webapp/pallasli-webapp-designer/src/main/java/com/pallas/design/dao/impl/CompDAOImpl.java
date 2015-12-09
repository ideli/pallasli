package com.pallas.design.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.design.bean.Comp;
import com.pallas.design.dao.CompDAO;
import com.pallasli.hibernate421.factory.HibernateSessionFactory;

public class CompDAOImpl implements CompDAO {

	@Override
	public List<Comp> selectCompsByTypeId(Long typeId) {
		Session session = HibernateSessionFactory.designSession();
		List<Comp> list = new ArrayList<Comp>();
		try {
			Query query = session
					.createQuery("from CompType where comp_type_id=?");
			query.setLong(0, typeId);
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
	public Comp insert(Comp comp) {
		Session session = HibernateSessionFactory.designSession();
		session.beginTransaction();
		session.save(comp);
		session.getTransaction().commit();
		session.flush();
		return comp;
	}

	@Override
	public List<Comp> selectAll() {
		Session session = HibernateSessionFactory.designSession();
		List<Comp> list = new ArrayList<Comp>();
		try {
			Query query = session.createQuery("from Comp ");
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
	public boolean update(Comp comp) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		session.beginTransaction();
		try {
			Comp reftype = (Comp) session.get(Comp.class, comp.getId());
			reftype.setCompCaption(comp.getCompCaption());
			reftype.setCompName(comp.getCompName());
			reftype.setCompTypeCode(comp.getCompTypeCode());
			reftype.setCompClass(comp.getCompClass());
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
	public boolean delete(Comp comp) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		try {
			session.delete(comp);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}

}
