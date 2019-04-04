package com.beercom.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beercom.ejb.criteria.EqualsCriteria;
import com.beercom.ejb.criteria.TableCriteriaBuilder;
import com.beercom.entity.CustomizableEntity;
import com.beercom.entity.FermentableAddition;
import com.beercom.entity.HopAddition;
import com.beercom.entity.MashStep;
import com.beercom.entity.MiscIngredientAddition;
import com.beercom.entity.Recipe;
import com.beercom.entity.User;
import com.beercom.entity.YeastAddition;

/**
 * Service for managing recipes in the repository
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RecipeService extends BaseService {
	
	private static final Logger log = LogManager.getLogger(RecipeService.class);

	/**
	 * Retrieves all recipes belonging to the user
	 * @param user
	 * @return
	 */
	public List<Recipe> getRecipesForUser(User user) {
		TableCriteriaBuilder criteria = TableCriteriaBuilder.newTableCriteria()
				.add(new EqualsCriteria("owner", user.getAuthId()));
		
		return repository.getAllEntitiesForCriteria(Recipe.class, criteria, null);
	}

	/**
	 * Deletes the recipe
	 * @param recipe
	 * @param user
	 */
	public void deleteRecipe(Recipe recipe, User user) {
		verifyAuthorizations(recipe, user, true);
		repository.delete(recipe);
	}

	/**
	 * Saves a recipe
	 * @param recipe
	 * @param user
	 */
	public void saveRecipe(Recipe recipe, User user) {
		
		verifyAuthorizations(recipe, user, false);
		
		prepareRecipeForSave(recipe, user.getAuthId());
		
		if(recipe.getId() != null) {
			repository.update(recipe);
		}
		else {
			repository.insert(recipe);
		}
	}
	
	/**
	 * Copies a recipe
	 * @param recipe
	 * @return
	 */
	public Recipe copyRecipe(Recipe recipe){
		List<CustomizableEntity> recipeEntities = getFlattenedRecipeGraph(recipe);
		
		recipe.setDerivedFrom(recipe.getId());
		
		for(CustomizableEntity customizableEntity: recipeEntities){
			customizableEntity.setId(null);
		}
		
		recipe.setName(StringUtils.substring(recipe.getName(), 0, 40) + "-Copy");

		return recipe;
	}
	
	/**
	 * Sets properties that we want to set on every save
	 * @param customizableEntity
	 * @param authId
	 */
	private void prepareRecipeForSave(Recipe recipe, String authId){
		
		List<CustomizableEntity> recipeEntities = getFlattenedRecipeGraph(recipe);
		
		for(CustomizableEntity customizableEntity: recipeEntities){
			prepareEntityForSave(customizableEntity, authId);
		}
	}
	
	/**
	 * Sets certain properties on a specific entity for a particular user
	 * @param customizableEntity
	 * @param authId
	 */
	private void prepareEntityForSave(CustomizableEntity customizableEntity, String authId){
		customizableEntity.setMaintId(authId);
		customizableEntity.setOwner(authId);
		customizableEntity.setIsBuiltIn(false);
	}
	
	
	/**
	 * Looks at each element in the recipe graph and verifies ownership
	 * 
	 * @param recipe
	 * @param user
	 * @param isDelete
	 */
	private void verifyAuthorizations(Recipe recipe, User user, boolean isDelete){
		List<CustomizableEntity> entityList = getFlattenedRecipeGraph(recipe);
		
		for(CustomizableEntity entity: entityList){
			verifyOwner(entity, user, isDelete);
		}
	}
	
	/**
	 * Looks at the current owner id in the db, compares
	 * it to the current owner id
	 * @param entity
	 * @param user
	 */
	@SuppressWarnings("unchecked")
	private <T extends CustomizableEntity> void verifyOwner(T entity, User user, boolean isDelete){
		if(entity.getId() != null){
			
			TableCriteriaBuilder criteria = TableCriteriaBuilder.newTableCriteria()
					.add(new EqualsCriteria("id", entity.getId()));
			
			List<T> savedEntities = (List<T>) repository.getAllEntitiesForCriteria(entity.getClass(), criteria, null);
			
			//If this id doesn't exist in the db, reset it so one will be autogenerated
			if(savedEntities == null || savedEntities.isEmpty()){
				entity.setId(null);
			}
			else if(!savedEntities.get(0).getOwner().equals(user.getAuthId())){
				//We could throw security exception, but we don't want to give clues that 
				//this id exists. just change it to null so a new one will be generated
				entity.setId(null);
				log.warn("Attempt to save entity " + entity + " " + entity.getId() + " by user " + user.getAuthId());
				log.warn("Actual owner is: " + savedEntities.get(0).getOwner());
			}
		}
		else if(isDelete){
			throw new SecurityException("Id must not be null to delete");
		}
	}
	
	/**
	 * Generates a flattened list of all descendants of the Recipe that are 
	 * customizable entities. Note this method must be maintained as entities are added
	 * to recipe (in contrast to the reflection method)
	 * @param recipe
	 * @return
	 */
	public List<CustomizableEntity> getFlattenedRecipeGraph(Recipe recipe){
		List<CustomizableEntity> entityList = new ArrayList<>();

		entityList.add(recipe);
			
		//Fermentation Process
		if(recipe.getFermentation() != null){
			entityList.add(recipe.getFermentation());
		}
		
		//Batch log
		if(recipe.getBatch() != null){
			entityList.add(recipe.getBatch());
		}
			
		//Mash Process
		if(recipe.getMashProcess() != null){
			entityList.add(recipe.getMashProcess());

			//Mash Steps
			if(recipe.getMashProcess().getMashSteps() != null){
				for(MashStep step : recipe.getMashProcess().getMashSteps()){
					entityList.add(step);
				}
			}
		}
		
		//Fermentable additions
		if(recipe.getFermentableAdditions() != null){
			for(FermentableAddition fermAdd : recipe.getFermentableAdditions()){
				entityList.add(fermAdd);
				
				if(fermAdd.getFermentable() != null){
					entityList.add(fermAdd.getFermentable());
				}
			}
		}

		//Hop additions
		if(recipe.getHopAdditions() != null){
			for(HopAddition hopAdd : recipe.getHopAdditions()){
				entityList.add(hopAdd);
				
				if(hopAdd.getHop() != null){
					entityList.add(hopAdd.getHop());
				}
			}
		}
		
		//Yeast additions
		if(recipe.getYeastAdditions() != null){
			for(YeastAddition yeastAdd : recipe.getYeastAdditions()){
				entityList.add(yeastAdd);
				
				if(yeastAdd.getYeast() != null){
					entityList.add(yeastAdd.getYeast());
				}
			}
		}
		//MiscIngredient additions
		if(recipe.getMiscAdditions() != null){
			for(MiscIngredientAddition miscAdd : recipe.getMiscAdditions()){
				entityList.add(miscAdd);
				
				if(miscAdd.getMiscIngredient() != null){
					entityList.add(miscAdd.getMiscIngredient());
				}
			}
		}
		
		return entityList;
	}
}
