package com.beercom.ejb;

import javax.ejb.EJB;

import com.beercom.ejb.repository.RecipeRepository;

public abstract class BaseService {

	@EJB
	protected RecipeRepository repository;
}
