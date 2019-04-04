package com.beercraft.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.beercraft.ejb.criteria.EqualsCriteria;
import com.beercraft.ejb.criteria.TableCriteriaBuilder;
import com.beercraft.ejb.entity.Fermentable;
import com.beercraft.ejb.entity.Hop;
import com.beercraft.ejb.entity.MiscIngredient;
import com.beercraft.ejb.entity.Style;
import com.beercraft.ejb.entity.Yeast;

/**
 * Service for retrieving ingredient information
 *
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class IngredientService extends BaseService{
	
	/**
	 * Retrieves all built in hops
	 * @return
	 */
	public List<Hop> getAllHops() {
		
		TableCriteriaBuilder criteria = TableCriteriaBuilder.newTableCriteria()
			.add(new EqualsCriteria("isBuiltIn", true));
		
		return repository.getAllEntitiesForCriteria(Hop.class, criteria, null);
	}
	
	/**
	 * Gets all built in fermentables
	 * @return
	 */
	public List<Fermentable> getAllFermentables() {
		
		TableCriteriaBuilder criteria = TableCriteriaBuilder.newTableCriteria()
				.add(new EqualsCriteria("isBuiltIn", true));
		
		return repository.getAllEntitiesForCriteria(Fermentable.class, criteria, null);
	}

	/**
	 * Gets all built in yeast
	 * @return
	 */
	public List<Yeast> getAllYeast() {
		TableCriteriaBuilder criteria = TableCriteriaBuilder.newTableCriteria()
				.add(new EqualsCriteria("isBuiltIn", true));
		
		return repository.getAllEntitiesForCriteria(Yeast.class, criteria, null);
	}

	/**
	 * Get all built in misc ingredients
	 * @return
	 */
	public List<MiscIngredient> getAllMiscIngredients() {
		TableCriteriaBuilder criteria = TableCriteriaBuilder.newTableCriteria()
				.add(new EqualsCriteria("isBuiltIn", true));
		
		return repository.getAllEntitiesForCriteria(MiscIngredient.class, criteria, null);
	}
	
	/**
	 * Gets all styles
	 * @return
	 */
	public List<Style> getAllStyles() {
		return repository.getAllEntities(Style.class);
	}
	
}
