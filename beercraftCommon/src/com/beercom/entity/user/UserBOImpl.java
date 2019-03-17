package com.beercom.entity.user;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.beercom.entity.User;

@ApplicationScoped
public class UserBOImpl implements UserBO, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserDAO userDAO;
	
	@Override
	public void registerUser(User user) {
		
		User existingUser = userDAO.getUser(user.getAuthId());
		
		//If not already registered, register them
		if(existingUser == null){
			userDAO.registerUser(user);
		}
		//Or if their information has changed, re-register
		else if(!existingUser.getEmail().equals(user.getEmail()) ||
				!existingUser.getName().equals(user.getName())){
			existingUser.setEmail(user.getEmail());
			existingUser.setName(user.getName());
			userDAO.registerUser(existingUser);
		}
	}

}
