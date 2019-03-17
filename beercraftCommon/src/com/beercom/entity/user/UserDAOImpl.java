package com.beercom.entity.user;

import javax.enterprise.context.ApplicationScoped;

import com.beercom.entity.BaseDAO;
import com.beercom.entity.User;

@ApplicationScoped
public class UserDAOImpl extends BaseDAO implements UserDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public void registerUser(User user) {
		saveOrUpdate(user);
	}

	@Override
	public User getUser(String authId) {
		return (User) getSession().get(User.class, authId);
	}

}
