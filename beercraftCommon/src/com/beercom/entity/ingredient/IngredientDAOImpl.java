package com.beercom.entity.ingredient;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.beercom.entity.BaseDAO;
import com.beercom.entity.Fermentable;
import com.beercom.entity.Hop;
import com.beercom.entity.MiscIngredient;
import com.beercom.entity.Yeast;

@ApplicationScoped
public class IngredientDAOImpl extends BaseDAO implements IngredientDAO, Serializable{

	private static final long serialVersionUID = 2838569254403985782L;

	//TODO: throw some sort of exception
	@Override
	public List<Hop> getAllHops() {
		@SuppressWarnings("unchecked")
		List<Hop> hopList = getSession().createCriteria(Hop.class).add(Restrictions.eq("isBuiltIn", Boolean.TRUE)).list();

		return hopList;
	}

	@Override
	public void saveHop(Hop hop) {
		saveOrUpdate(hop);
	}

	@Override
	public void deleteHop(Hop hop) {
		delete(hop);
	}
	
	@Override
	public Hop searchHopByName(String name){
		
		Hop hop = null;
		
		Criteria crit = getSession().createCriteria(Hop.class);
		
		@SuppressWarnings("rawtypes")
		List hopList =  crit.add(Restrictions.eq("name", name)).list();
		
		if(!hopList.isEmpty()){
			hop = (Hop)hopList.get(0);
		}
		
		return hop;
	}

	@Override
	public List<Fermentable> getAllFermentables() {
		@SuppressWarnings("unchecked")
		List<Fermentable> fermentableList = getSession().createCriteria(Fermentable.class).add(Restrictions.eq("isBuiltIn", Boolean.TRUE)).list();
		
		return fermentableList;
	}

	@Override
	public void saveFermentable(Fermentable fermentable) {
		saveOrUpdate(fermentable);
	}

	@Override
	public void deleteFermentable(Fermentable fermentable) {
		delete(fermentable);
	}

	@Override
	public List<Yeast> getAllYeast() {
		@SuppressWarnings("unchecked")
		List<Yeast> yeastList = getSession().createCriteria(Yeast.class).add(Restrictions.eq("isBuiltIn", Boolean.TRUE)).list();
		
		return yeastList;
	}

	@Override
	public void saveYeast(Yeast yeast) {
		saveOrUpdate(yeast);
	}

	@Override
	public void deleteYeast(Yeast yeast) {
		delete(yeast);
	}

	@Override
	public List<MiscIngredient> getAllMiscIngredients() {
		@SuppressWarnings("unchecked")
		List<MiscIngredient> ingredientList = getSession().createCriteria(MiscIngredient.class).add(Restrictions.eq("isBuiltIn", Boolean.TRUE)).list();
		
		return ingredientList;
	}

	@Override
	public void saveMiscIngredient(MiscIngredient miscIngredient) {
		saveOrUpdate(miscIngredient);
	}

	@Override
	public void deleteMiscIngredient(MiscIngredient miscIngredient) {
		delete(miscIngredient);
	}

}
