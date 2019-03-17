package com.beercom.entity;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.beercom.util.HibernateUtil;

/**
 * Base functionality for all hibernate DAO's
 */
public class BaseDAO implements Serializable{

	private static final long serialVersionUID = -4105215976594702216L;
	
	private SessionFactory sessionFactory;

	/**
	 * Gets the current session factory
	 */
	public BaseDAO(){
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	/**
	 * Saves a new object, or updates an existing one
	 * @param obj
	 */
	public void saveOrUpdate(Object obj){
		getSession().saveOrUpdate(obj);
	}
	
	/**
	 * Deletes the persisted object, removes its reference (assigns null)
	 * @param obj
	 */
	public void delete(Object obj){
		getSession().delete(obj);
		obj = null;
	}
	
	/**
	 * Gets the current session
	 * @return
	 */
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


}
