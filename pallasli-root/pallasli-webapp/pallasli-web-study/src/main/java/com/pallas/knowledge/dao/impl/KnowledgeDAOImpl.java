package com.pallas.knowledge.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.factory.HibernateSessionFactory;
import com.pallas.knowledge.bean.Knowledge;
import com.pallas.knowledge.dao.KnowledgeDAO;

public class KnowledgeDAOImpl implements KnowledgeDAO {
	Session session = HibernateSessionFactory.knowledgeSession();

	@Override
	public Knowledge insert(Knowledge knowledge) {

		session.beginTransaction();
		session.save(knowledge);
		session.getTransaction().commit();
		session.flush();
		return knowledge;
	}

	@Override
	public boolean update(Knowledge knowledge) {
		boolean success = false;
		session.beginTransaction();
		try {
			Knowledge ref = (Knowledge) session.get(Knowledge.class,
					knowledge.getId());
			ref.setCaption(knowledge.getCaption());
			ref.setContent(knowledge.getContent());
			session.saveOrUpdate(ref);
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
	public boolean delete(Knowledge knowledge) {
		boolean success = false;
		try {
			session.delete(knowledge);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}

	@Override
	public Knowledge select(long id) {
		Knowledge k = (Knowledge) HibernateSessionFactory.knowledgeSession()
				.load(Knowledge.class, id);
		session.flush();
		return k;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Knowledge> selectChildrenByParentId(long parentId) {
		List<Knowledge> list = new ArrayList<Knowledge>();
		try {
			Query query = session
					.createQuery("from Knowledge where parentId=? ");
			query.setLong(0, parentId);
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
	public int count() {
		int count = -1;
		try {
			Query query = session
					.createSQLQuery("select count(*) from knowledges");
			count = ((BigInteger) query.uniqueResult()).intValue();
			query = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return count;
	}

	@Override
	public int countChildrenByParentId(long parentId) {
		int count = -1;
		try {
			Query query = session
					.createSQLQuery("select count(*) from knowledges where parentId=?");
			query.setLong(0, parentId);
			count = ((BigInteger) query.uniqueResult()).intValue();
			query = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return count;
	}

	@Override
	public long getMaxId() {
		long maxId = -1;
		try {
			Query query = session
					.createSQLQuery("select max(id) from knowledges");
			maxId = (Long) query.uniqueResult();
			query = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return maxId;
	}

}
