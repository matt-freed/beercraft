package com.beercraft.web.resource;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beercom.ejb.IngredientService;
import com.beercom.entity.Fermentable;
import com.beercom.entity.Hop;
import com.beercom.entity.MiscIngredient;
import com.beercom.entity.Style;
import com.beercom.entity.Yeast;

@RequestScoped
@Path("/ingredients")
public class IngredientResource {
	
	private static final Logger log = LogManager.getLogger(IngredientResource.class);

	@Inject
	private IngredientService ingredientService;
	
	@GET
	@Path("/hops")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Hop> getHops(){
		log.debug("Getting all hops");
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
