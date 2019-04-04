package com.beercraft.web.resource;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.beercraft.ejb.IngredientService;
import com.beercraft.ejb.entity.Fermentable;
import com.beercraft.ejb.entity.Hop;
import com.beercraft.ejb.entity.MiscIngredient;
import com.beercraft.ejb.entity.Style;
import com.beercraft.ejb.entity.Yeast;

@RequestScoped
@Path("/ingredients")
public class IngredientResource {

	@Inject
	private IngredientService ingredientService;
	
	@GET
	@Path("/hops")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Hop> getHops(){
		List<Hop> hops = ingredientService.getAllHops();
		return hops;
	}
	
	@GET
	@Path("/fermentables")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fermentable> getFermentables(){
		List<Fermentable> fermentables = ingredientService.getAllFermentables();
		return fermentables;
	}
	
	@GET
	@Path("/yeast")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Yeast> getYeast(){
		List<Yeast> yeast = ingredientService.getAllYeast();
		return yeast;
	}
	
	@GET
	@Path("/misc")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MiscIngredient> getMiscIngredients(){
		List<MiscIngredient> misc = ingredientService.getAllMiscIngredients();
		return misc;
	}
	
	@GET
	@Path("/styles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Style> getStyles(){
		List<Style> styles = ingredientService.getAllStyles();
		return styles;
	}

}
