package com.beercom.entity.user;

import com.beercom.entity.User;

public interface UserDAO {
	
	public void registerUser(User user);
	
	public User getUser(String authId);

}
