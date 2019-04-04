package com.beercraft.web.resource;

import java.security.GeneralSecurityException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.beercraft.ejb.UserService;
import com.beercraft.ejb.entity.User;
import com.beercraft.web.util.GoogleAuthUtil;

@RequestScoped
@Path("/user")
public class UserResource {
	
	@Inject
	private GoogleAuthUtil authUtil;
	
	@Inject
	private UserService userFacade;
	
	/**
	 * Registers a user on the db if they do not exist,
	 * otherwise returns
	 */
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerUser(@HeaderParam("Authorization") String tokenId){
		try {
			User user = authUtil.getUserInfo(tokenId);
			
			userFacade.registerUser(user);
			
		} catch (GeneralSecurityException e) {
			//If we can't verify, return 401 not authorized
			throw new NotAuthorizedException("User could not be authorized");
		}
	}
	
	
	

}
