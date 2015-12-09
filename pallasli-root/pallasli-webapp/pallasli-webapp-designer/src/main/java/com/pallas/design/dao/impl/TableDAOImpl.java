package com.pallas.design.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.design.bean.Table;
import com.pallas.design.dao.TableDAO;
import com.pallasli.hibernate421.factory.HibernateSessionFactory;

public class TableDAOImpl implements TableDAO {

	public List<Table> selectTablesByPrefixion(Table params) {
		return null;
	}

	@Override
	public List<Table> selectAll() {
		Session session = HibernateSessionFactory.designSession();
		session.clear();
		List<Table> list = new ArrayList<Table>();
		try {
			Query query = session.createQuery("from Table ");
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
	public Table insert(Table table) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Table table) {
		boolean success = false;
		Session session = HibernateSessionFactory.designSession();
		session.beginTransaction();
		try {
			session.merge(table);
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
	public boolean delete(Table table) {
		boolean success = false;
		Session session = HibernateSessionFactory.designSession();
		try {
			session.delete(table);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}

	@Override
	public void save(List<Table> list) {
		Session session = HibernateSessionFactory.designSession();
		session.beginTransaction();
		for (Table t : list) {
			if (t.getId() == null || t.getId() < 0) {
				session.save(t);
			} else {
				session.merge(t);
			}
		}
		session.getTransaction().commit();
		session.flush();
	}

	@Override
	public Table loadByTableName(String tableName) {
		Session session = HibernateSessionFactory.designSession();
		session.clear();
		Table t = null;
		try {
			Query query = session.createQuery("from Table where table_name=?");
			query.setString(0, tableName);
			List<Table> list = query.list();
			if (list.size() > 0)
				t = list.get(0);
			query = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return t;
	}

	@Override
	public Table load(Table t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table loadByProjectTableName(String projectName, String tableName) {
		Session session = HibernateSessionFactory.designSession();
		session.clear();
		Table t = null;
		try {
			Query query = session
					.createQuery("from Table where projectName=? and table_name=?");
			query.setString(0, projectName);
			query.setString(1, tableName);
			List<Table> list = query.list();
			if (list.size() > 0)
				t = list.get(0);
			query = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return t;
	}

}
