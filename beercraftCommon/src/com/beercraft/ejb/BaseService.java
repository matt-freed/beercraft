package com.beercraft.ejb;

import javax.ejb.EJB;

import com.beercraft.ejb.repository.RecipeRepository;

public abstract class BaseService {

	@EJB
	protected RecipeRepository repository;
}
