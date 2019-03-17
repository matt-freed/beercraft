package com.beercom.util;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;

/**
 * CDIUtil allows you to inject CDI Beans in classes that aren't injection targets.
 * Use of this class should be as limited as possible, as we should be able to 
 * inject the required bean under most circumstances
 */
public class CDIUtil {
	
	private static BeanManager beanManager;
	
	public static BeanManager getApplicationContext(){
		if(beanManager == null){
			beanManager = lookupBeanManager();
		}
		return beanManager;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T findBean(Class<?> clazz, Annotation... annotations){
		BeanManager beanManager = CDIUtil.getApplicationContext();
		Set<Bean<?>> beans = beanManager.getBeans(clazz, annotations);
		Bean<T> bean = (Bean<T>) beanManager.resolve(beans);
		
		Class<?> beanClass = bean.getBeanClass();
		CreationalContext<T> createCreationalContext = beanManager.createCreationalContext(bean);
		T resolvedBean = (T) beanManager.getReference(bean, beanClass, createCreationalContext);
		
		if(resolvedBean == null){
			throw new RuntimeException("Could not find instance of bean");
		}
		
		return resolvedBean;
	}
	
	private static BeanManager lookupBeanManager(){
		try{
			return (BeanManager) InitialContext.doLookup("java:comp/BeanManager");
		}
		catch(Exception e){
			throw new RuntimeException("Could not lookup bean manager");
		}
	}

}
