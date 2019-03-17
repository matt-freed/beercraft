package com.beercraft.web.resource;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.beercom.entity.Fermentable;
import com.beercom.entity.Hop;
import com.beercom.entity.MiscIngredient;
import com.beercom.entity.Yeast;
import com.beercom.facade.entity.IngredientFacade;

@RequestScoped
@Path("/ingredients")
public class IngredientResource {

	@Inject
	private IngredientFacade ingredientFacade;
	
	@GET
	@Path("/hops")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Hop> getHops(){
		List<Hop> hops = ingredientFacade.getAllHops();
		return hops;
	}
	
	@GET
	@Path("/fermentables")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fermentable> getFermentables(){
		List<Fermentable> fermentables = ingredientFacade.getAllFermentables();
		return fermentables;
	}
	
	@GET
	@Path("/yeast")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Yeast> getYeast(){
		List<Yeast> yeast = ingredientFacade.getAllYeast();
		return yeast;
	}
	
	@GET
	@Path("/misc")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MiscIngredient> getMiscIngredients(){
		List<MiscIngredient> misc = ingredientFacade.getAllMiscIngredients();
		return misc;
	}

}
