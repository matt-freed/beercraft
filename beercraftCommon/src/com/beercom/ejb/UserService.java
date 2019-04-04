package com.beercom.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.beercom.ejb.criteria.EqualsCriteria;
import com.beercom.ejb.criteria.TableCriteriaBuilder;
import com.beercom.entity.User;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class UserService extends BaseService {

	/**
	 * Registers a user, or updates an existing user if they already exist
	 * @param user
	 */
	public void registerUser(User user) {
		TableCriteriaBuilder criteria = TableCriteriaBuilder.newTableCriteria()
				.add(new EqualsCriteria("authId", user.getAuthId()));
		
		List<User> foundUsers = repository.getAllEntitiesForCriteria(User.class, criteria, null);
		
		//If not already registered, register them
		if(foundUsers == null || 
				(foundUsers != null && foundUsers.isEmpty())){
			repository.insert(user);
		}
		//Or if their information has changed, re-register
		else {
			User existingUser = foundUsers.get(0);
			
			if(!existingUser.getEmail().equals(user.getEmail()) ||
				!existingUser.getName().equals(user.getName())){
			
				existingUser.setEmail(user.getEmail());
				existingUser.setName(user.getName());
			
				repository.update(user);
			}
		}
	}
}
