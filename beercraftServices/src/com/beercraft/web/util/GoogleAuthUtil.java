package com.beercraft.web.util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.lang3.StringUtils;

import com.beercraft.ejb.entity.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * Utility class to handle verification of Google OAuth tokenId
 * passed to us from an authenticated user
 */
@ApplicationScoped
public class GoogleAuthUtil {
	
	private final String CLIENT_ID = "586144694863-5fg7dhp0ahlhil3l6efcdhrihvr8h7q6.apps.googleusercontent.com";

	/**
	 * Uses the Google client API's to verify that the token is valid,
	 * that it was issued by a request from this app
	 * @param tokenId
	 * @return
	 * @throws GeneralSecurityException 
	 */
	public Payload getVerifiedToken(String tokenId) throws GeneralSecurityException{
		
		if(StringUtils.isBlank(tokenId)){
			throw new GeneralSecurityException();
		}
		
		HttpTransport transport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();
		
		Payload payload = null;
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
			.setAudience(Arrays.asList(CLIENT_ID)).build();

		try{
			GoogleIdToken idToken = verifier.verify(tokenId);
			if(idToken != null){
				payload = idToken.getPayload();
				if(!payload.getAuthorizedParty().equals(CLIENT_ID)){
					throw new GeneralSecurityException();
				}		
			}
			else{
				throw new GeneralSecurityException();
			}
		}
		catch(IllegalArgumentException | IOException | GeneralSecurityException e){
			throw new GeneralSecurityException();
		} 
		
		return payload;
	}
	
	/**
	 * Verifies and then extracts user information
	 * from the tokenid
	 * @param tokenId
	 * @return
	 * @throws GeneralSecurityException
	 */
	public User getUserInfo(String tokenId) throws GeneralSecurityException{
		
		Payload payload = getVerifiedToken(tokenId);
		
		User user = new User();
		
		user.setEmail(payload.getEmail());
		user.setAuthId(payload.getSubject());
		user.setName((String) payload.getUnknownKeys().get("name"));
		
		return user;
	}

}
