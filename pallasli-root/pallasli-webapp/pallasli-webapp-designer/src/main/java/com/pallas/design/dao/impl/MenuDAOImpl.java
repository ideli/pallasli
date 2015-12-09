package com.pallas.design.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pallas.design.bean.Menu;
import com.pallas.design.dao.MenuDAO;
import com.pallasli.hibernate421.factory.HibernateSessionFactory;

public class MenuDAOImpl implements MenuDAO {

	@Override
	public List<Menu> selectSysMenus() {

		Session session = HibernateSessionFactory.designSession();
		List<Menu> list = new ArrayList<Menu>();
		try {
			Query query = session
					.createQuery("from Menu where menu_type_code=?");
			query.setString(0, "10");
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
	public List<Menu> selectMenusByParent(Menu sysMenu) {
		Session session = HibernateSessionFactory.designSession();
		session.clear();
		List<Menu> list = new ArrayList<Menu>();
		try {
			Query query = session.createQuery("from Menu where parentKey=?");
			query.setString(0, sysMenu.getMenuKey());
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
	public Menu insert(Menu menu) {
		Session session = HibernateSessionFactory.designSession();
		session.beginTransaction();
		session.save(menu);
		session.getTransaction().commit();
		session.flush();
		return menu;
	}

	@Override
	public List<Menu> selectAll() {
		Session session = HibernateSessionFactory.designSession();
		session.clear();
		List<Menu> list = new ArrayList<Menu>();
		try {
			Query query = session.createQuery("from Menu ");
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
	public boolean update(Menu menu) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		session.beginTransaction();
		try {
			session.merge(menu);
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
	public boolean delete(Menu menu) {
		boolean success = false;
		Session session = HibernateSessionFactory.knowledgeSession();
		try {
			session.delete(menu);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return success;
	}

	@Override
	public Menu select(Long id) {

		Session session = HibernateSessionFactory.designSession();
		Menu menu = null;
		try {
			menu = (Menu) session.get(Menu.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return menu;
	}

	@Override
	public Menu selectByKey(String key) {

		Session session = HibernateSessionFactory.designSession();
		session.clear();
		Menu menu = null;
		try {
			Query query = session.createQuery("from Menu where menu_key=?");
			query.setString(0, key);
			menu = (Menu) query.uniqueResult();
			query = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return menu;
	}

	@Override
	public List<Menu> selectAllMenuGroups() {
		Session session = HibernateSessionFactory.designSession();
		session.clear();
		List<Menu> list = new ArrayList<Menu>();
		try {
			Query query = session
					.createQuery("from Menu where menu_type_code=? ");
			query.setString(0, "0");
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
