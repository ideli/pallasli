package com.pallas.design.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.design.bean.TableField;
import com.pallas.design.dao.TableFieldDAO;
import com.pallasli.hibernate.factory.HibernateSessionFactory;

public class TableFieldDAOImpl implements TableFieldDAO {

	@Override
	public List<TableField> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableField insert(TableField tableField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(TableField tableField) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		session.beginTransaction();
		try {
			session.merge(tableField);
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
	public boolean delete(TableField tableField) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		try {
			session.delete(tableField);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}

	@Override
	public List<TableField> selectByTableName(String tableName) {
		Session session = HibernateSessionFactory.designSession();
		session.clear();
		List<TableField> list = new ArrayList<TableField>();
		try {
			Query query = session
					.createQuery("from TableField where table_name=?");
			query.setString(0, tableName);
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
	public void saveTableFieds(List<TableField> list) {
		Session session = HibernateSessionFactory.designSession();
		session.beginTransaction();
		for (TableField t : list) {
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
	public TableField loadTableFieldByTableNameAndFieldName(String tableName,
			String fieldName) {
		Session session = HibernateSessionFactory.designSession();
		session.clear();
		TableField t = null;
		try {
			Query query = session
					.createQuery("from TableField where table_name=? and field_name=?");
			query.setString(0, tableName);
			query.setString(1, fieldName);
			List<TableField> list = query.list();
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
	public TableField loadByProjectTableFieldName(String projectName,
			String tableName, String fieldName) {
		Session session = HibernateSessionFactory.designSession();
		session.clear();
		TableField t = null;
		try {
			Query query = session
					.createQuery("from TableField where projectName=? and table_name=? and field_name=?");
			query.setString(0, projectName);
			query.setString(1, tableName);
			query.setString(2, fieldName);
			List<TableField> list = query.list();
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
