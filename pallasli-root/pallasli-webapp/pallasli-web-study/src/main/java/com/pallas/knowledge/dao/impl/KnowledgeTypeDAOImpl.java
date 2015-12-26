package com.pallas.knowledge.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.knowledge.bean.KnowledgeType;
import com.pallas.knowledge.dao.KnowledgeTypeDAO;
import com.pallasli.hibernate.factory.HibernateSessionFactory;

public class KnowledgeTypeDAOImpl implements KnowledgeTypeDAO {
	Session session = HibernateSessionFactory.knowledgeSession();

	@Override
	public KnowledgeType insert(KnowledgeType knowledgeType) {
		session.beginTransaction();
		session.saveOrUpdate(knowledgeType);
		session.getTransaction().commit();
		return knowledgeType;
	}

	@Override
	public boolean update(KnowledgeType knowledgeType) {
		boolean success = false;
		session.beginTransaction();
		try {
			KnowledgeType reftype = (KnowledgeType) session.get(
					KnowledgeType.class, knowledgeType.getId());
			reftype.setText(knowledgeType.getText());
			reftype.setParentId(knowledgeType.getParentId());
			reftype.setLeaf(knowledgeType.isLeaf());
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
	public boolean delete(KnowledgeType knowledgeType) {
		boolean success = false;
		try {
			session.delete(knowledgeType);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}

	@Override
	public KnowledgeType select(long id) {
		return (KnowledgeType) HibernateSessionFactory.knowledgeSession().load(
				KnowledgeType.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KnowledgeType> selectChildrenByParentId(long parentId) {
		List<KnowledgeType> list = new ArrayList<KnowledgeType>();
		try {
			Query query = null;
			query = session.createQuery("from KnowledgeType where parentId=? ");
			query.setLong(0, parentId);

			list = query.list();
			query = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int count() {
		int count = -1;
		try {
			Query query = session
					.createSQLQuery("select count(*) from knowledge_types");
			count = ((BigInteger) query.uniqueResult()).intValue();
			query = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int countChildrenByParentId(long parentId) {
		int count = -1;
		try {
			Query query = session
					.createSQLQuery("select count(*) from knowledgeTypes where parentId=?");
			query.setLong(0, parentId);
			count = ((BigInteger) query.uniqueResult()).intValue();
			query = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public long getMaxId() {
		long maxId = -1;
		try {
			Query query = session
					.createSQLQuery("select max(id) from knowledgeTypes");
			maxId = (Long) query.uniqueResult();
			query = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxId;
	}

}
