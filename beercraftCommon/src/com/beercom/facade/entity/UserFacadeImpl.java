package com.beercom.facade.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.beercom.entity.User;
import com.beercom.entity.user.UserBO;
import com.beercom.interceptor.Transactional;

@Dependent
@Transactional
public class UserFacadeImpl implements UserFacade, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserBO userBO;
	
	@Override
	public void registerUser(User user) {
		userBO.registerUser(user);
	}

}
