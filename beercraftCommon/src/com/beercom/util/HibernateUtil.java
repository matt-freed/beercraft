package com.beercom.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * 
 */
public class HibernateUtil {
	
	private static final String MYSQL_DATASOURCE = "jdbc/beercraftdb";
	//private static final String DEFAULT_SCHEMA = "beercraft";
	
	private static final SessionFactory sessionFactory;
	
	static{
		Configuration cfg = new Configuration();
		cfg.setProperty("hibernate.connection.datasource", MYSQL_DATASOURCE);
		cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		//cfg.setProperty("hibernate.default_schema", DEFAULT_SCHEMA);

		cfg.setProperty("hibernate.current_session_context_class", "thread");
		
		cfg.setProperty("show_sql", "true");
		cfg.setProperty("format_sql", "true");
		
		for(Class<?> clazz: MappedEntities.mappedEntities){
			cfg.addAnnotatedClass(clazz);
		}
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
				applySettings(cfg.getProperties()).build();	
		
		sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		
	}
	
	/**
	 * Returns the current hibernate session factory
	 * @return
	 */
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	/**
	 * Begins the hibernate transaction
	 */
	public static void beginTransaction(){
		getSessionFactory().getCurrentSession().beginTransaction();
	}
	
	/**
	 * Commits the current transaction
	 */
	public static void commitTransaction(){
		getSessionFactory().getCurrentSession().getTransaction().commit();
	}
	
	/**
	 * Rolls back the current transaction
	 */
	public static void rollbackTransaction(){
		getSessionFactory().getCurrentSession().getTransaction().rollback();
	}

}
