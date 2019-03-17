package com.beercom.entity.ingredient;

import java.util.List;

import com.beercom.entity.Fermentable;
import com.beercom.entity.Hop;
import com.beercom.entity.MiscIngredient;
import com.beercom.entity.Yeast;

public interface IngredientDAO {
	
	public List<Hop> getAllHops();
	
	public void saveHop(Hop hop);
	
	public void deleteHop(Hop hop);
	
	public Hop searchHopByName(String name);
	
	
	public List<Fermentable> getAllFermentables();
	
	public void saveFermentable(Fermentable fermentable);
	
	public void deleteFermentable(Fermentable fermentable);
	
	
	public List<Yeast> getAllYeast();
	
	public void saveYeast(Yeast yeast);
	
	public void deleteYeast(Yeast yeast);

	
	public List<MiscIngredient> getAllMiscIngredients();
	
	public void saveMiscIngredient(MiscIngredient miscIngredient);
	
	public void deleteMiscIngredient(MiscIngredient miscIngredient);

}
