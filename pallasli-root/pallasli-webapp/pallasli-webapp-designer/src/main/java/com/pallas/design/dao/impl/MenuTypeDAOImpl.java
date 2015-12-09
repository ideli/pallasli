package com.pallas.design.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.design.bean.MenuType;
import com.pallas.design.dao.MenuTypeDAO;
import com.pallasli.hibernate421.factory.HibernateSessionFactory;

public class MenuTypeDAOImpl implements MenuTypeDAO {

	@Override
	public List<MenuType> selectAll() {
		Session session = HibernateSessionFactory.designSession();
		List<MenuType> list = new ArrayList<MenuType>();
		try {
			Query query = session.createQuery("from MenuType ");
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
	public MenuType insert(MenuType menuType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(MenuType menuType) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		session.beginTransaction();
		try {
			session.merge(menuType);
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
	public boolean delete(MenuType menuType) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		try {
			session.delete(menuType);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}

}
