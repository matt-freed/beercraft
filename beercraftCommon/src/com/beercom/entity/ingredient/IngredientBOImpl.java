package com.beercom.entity.ingredient;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import com.beercom.entity.Fermentable;
import com.beercom.entity.Hop;
import com.beercom.entity.MiscIngredient;
import com.beercom.entity.Yeast;

@ApplicationScoped
public class IngredientBOImpl implements IngredientBO, Serializable {
	
	private static final long serialVersionUID = -4331236906528493687L;
	
	@Inject
	private IngredientDAO ingredientDAO;

	@Override
	public List<Hop> getAllHops() {
		return ingredientDAO.getAllHops();
	}
	
	@Override
	public void saveHop(Hop hop) {
		ingredientDAO.saveHop(hop);
	}
	
	@Override
	public Hop newUserHop(String userId, Hop original) {
		Hop hop = new Hop();
		
		try {
			BeanUtils.copyProperties(hop, original);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		hop.setId(null);
		hop.setDerivedFrom(original.getId());
		
		hop.setIsBuiltIn(false);
		hop.setOwner(userId);
		
		return hop;
	}

	@Override
	public void deleteHop(Hop hop) {
		ingredientDAO.deleteHop(hop);
	}
	
	@Override
	public Hop searchHopByName(String name){
		return ingredientDAO.searchHopByName(name);
	}

	@Override
	public List<Fermentable> getAllFermentables() {
		return ingredientDAO.getAllFermentables();
	}

	@Override
	public void saveFermentable(Fermentable fermentable) {
		ingredientDAO.saveFermentable(fermentable);
	}

	@Override
	public Fermentable newUserFermentable(String userId, Fermentable original) {
		Fermentable fermentable = new Fermentable();
		
		try {
			BeanUtils.copyProperties(fermentable, original);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fermentable.setId(null);
		fermentable.setDerivedFrom(original.getId());
		
		fermentable.setIsBuiltIn(false);
		fermentable.setOwner(userId);
		
		return fermentable;
	}

	@Override
	public void deleteFermentable(Fermentable fermentable) {
		ingredientDAO.deleteFermentable(fermentable);
	}

	@Override
	public List<Yeast> getAllYeast() {
		return ingredientDAO.getAllYeast();
	}

	@Override
	public void saveYeast(Yeast yeast) {
		ingredientDAO.saveYeast(yeast);
	}

	@Override
	public Yeast newUserYeast(String userId, Yeast original) {
		Yeast yeast = new Yeast();
		
		try {
			BeanUtils.copyProperties(yeast, original);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		yeast.setId(null);
		yeast.setDerivedFrom(original.getId());
		
		yeast.setIsBuiltIn(false);
		yeast.setOwner(userId);
		
		return yeast;
	}

	@Override
	public void deleteYeast(Yeast yeast) {
		ingredientDAO.deleteYeast(yeast);
	}
	
	public IngredientDAO getIngredientDAO() {
		return ingredientDAO;
	}

	public void setIngredientDAO(IngredientDAO ingredientDAO) {
		this.ingredientDAO = ingredientDAO;
	}

	@Override
	public List<MiscIngredient> getAllMiscIngredients() {
		return ingredientDAO.getAllMiscIngredients();
	}

	@Override
	public void saveMiscIngredient(MiscIngredient miscIngredient) {
		ingredientDAO.saveMiscIngredient(miscIngredient);
	}

	@Override
	public MiscIngredient newUserMiscIngredient(String userId, MiscIngredient original) {
		MiscIngredient miscIngredient = new MiscIngredient();
		
		try {
			BeanUtils.copyProperties(miscIngredient, original);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		miscIngredient.setId(null);
		miscIngredient.setDerivedFrom(original.getId());
		
		miscIngredient.setIsBuiltIn(false);
		miscIngredient.setOwner(userId);
		
		return miscIngredient;
	}

	@Override
	public void deleteMiscIngredient(MiscIngredient miscIngredient) {
		ingredientDAO.deleteMiscIngredient(miscIngredient);
	}
}
