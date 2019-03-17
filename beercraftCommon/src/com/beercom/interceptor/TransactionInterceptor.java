package com.beercom.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.beercom.util.HibernateUtil;

@Transactional
@Interceptor
public class TransactionInterceptor {
	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception{
		Object ret = null;
		//TODO: Log the method name
		final String methodName = context.getMethod().getName();
		
		try{
			HibernateUtil.beginTransaction();
			
			ret = context.proceed();
			
			HibernateUtil.commitTransaction();
		}
		catch(Exception e){
			HibernateUtil.rollbackTransaction();
			throw e;
		}
		
		return ret;
	}

}
