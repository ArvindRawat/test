package com.arvind.dao.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public abstract class BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> persistObject;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected final Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	/*public BaseDAO(Class<T> persistentClass) {
		this.persistObject = persistentClass;
	}*/

	public Class<T> getPersistentClass() {
		return persistObject;
	}

	public void update(T entity) {

		getSession().update(entity);

	}

    public void delete(Object obj) {
        getSession().delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		List<T> getList = new ArrayList<T>();
		try {
			Criteria criteria = getSession().createCriteria(getPersistentClass());
			getList = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return getList;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(String isActive) {
		List<T> getList = new ArrayList<T>();
		try {
			Criteria criteria = getSession().createCriteria(getPersistentClass());
			criteria.add(Restrictions.eq("isActive", isActive));
			getList = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return getList;
	}

	public Serializable save(T entity) {
		return (Long) getSession().save(entity);

	}

	public void saveOrUpdate(T entity) {

		getSession().saveOrUpdate(entity);

	}

	public void persist(T entity) {
		getSession().persist(entity);
	}
	
/*	@Override
	@SuppressWarnings("unchecked")
	public T getEntity(Class<T> clazz, Long id) {
		return (T) getSession().get(clazz, id);
	}

    @SuppressWarnings("unchecked")
    @Override
    public T getEntity(Class<T> clazz, Integer id) {
        return (T) getSession().get(clazz, id);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T getEntity(Class<T> clazz, Byte id) {
        return (T) getSession().get(clazz, id);
    }*/
	
}
