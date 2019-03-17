package com.beercom.facade.entity;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.beercom.entity.Fermentable;
import com.beercom.entity.Hop;
import com.beercom.entity.MiscIngredient;
import com.beercom.entity.Yeast;
import com.beercom.entity.ingredient.IngredientBO;
import com.beercom.interceptor.Transactional;

@Dependent
@Transactional
public class IngredientFacadeImpl implements IngredientFacade, Serializable{

	private static final long serialVersionUID = -8390813082369911130L;
	
	@Inject
	private IngredientBO ingredientBO;
	
	@Override
	public List<Hop> getAllHops() {
		return ingredientBO.getAllHops();
	}

	@Override
	public void saveHop(Hop hop) {
		ingredientBO.saveHop(hop);		
	}

	@Override
	public Hop newUserHop(String userId, Hop original) {
		return ingredientBO.newUserHop(userId, original);
	}

	@Override
	public void deleteHop(Hop hop) {
		ingredientBO.deleteHop(hop);	
	}
	
	@Override
	public Hop searchHopByName(String name) {
		return ingredientBO.searchHopByName(name);
	}

	@Override
	public List<Fermentable> getAllFermentables() {
		return ingredientBO.getAllFermentables();
	}

	@Override
	public void saveFermentable(Fermentable fermentable) {
		ingredientBO.saveFermentable(fermentable);
	}

	@Override
	public Fermentable newUserFermentable(String userId, Fermentable original) {
		return ingredientBO.newUserFermentable(userId, original);
	}

	@Override
	public void deleteFermentable(Fermentable fermentable) {
		ingredientBO.deleteFermentable(fermentable);
	}

	@Override
	public List<Yeast> getAllYeast() {
		return ingredientBO.getAllYeast();
	}

	@Override
	public void saveYeast(Yeast yeast) {
		ingredientBO.saveYeast(yeast);
	}

	@Override
	public Yeast newUserYeast(String userId, Yeast original) {
		return ingredientBO.newUserYeast(userId, original);
	}

	@Override
	public void deleteYeast(Yeast yeast) {
		ingredientBO.deleteYeast(yeast);
	}

	public IngredientBO getIngredientBO() {
		return ingredientBO;
	}

	public void setIngredientBO(IngredientBO ingredientBO) {
		this.ingredientBO = ingredientBO;
	}

	@Override
	public List<MiscIngredient> getAllMiscIngredients() {
		return ingredientBO.getAllMiscIngredients();
	}

	@Override
	public void saveMiscIngredient(MiscIngredient miscIngredient) {
		ingredientBO.saveMiscIngredient(miscIngredient);
	}

	@Override
	public MiscIngredient newUserMiscIngredient(String userId, MiscIngredient original) {
		return ingredientBO.newUserMiscIngredient(userId, original);
	}

	@Override
	public void deleteMiscIngredient(MiscIngredient miscIngredient) {
		ingredientBO.deleteMiscIngredient(miscIngredient);
	}

}
