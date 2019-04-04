package com.beercraft.web.resource;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Service application config for beercraft services
 *
 */
@ApplicationPath("/rest")
public class BeercraftServiceApplication extends Application{
	
	private final Set<Class<?>> classes = new HashSet<>();

	@Override
	public Set<Class<?>> getClasses() {
		classes.add(IngredientResource.class);
		classes.add(RecipeResource.class);
		classes.add(UserResource.class);
		return Collections.unmodifiableSet(classes);
	}
}
