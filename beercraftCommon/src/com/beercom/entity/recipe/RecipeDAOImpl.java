package com.beercom.entity.recipe;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.criterion.Restrictions;

import com.beercom.entity.BaseDAO;
import com.beercom.entity.CustomizableEntity;
import com.beercom.entity.Recipe;
import com.beercom.entity.Style;
import com.beercom.entity.User;

@ApplicationScoped
public class RecipeDAOImpl extends BaseDAO implements RecipeDAO, Serializable{

	private static final long serialVersionUID = -6662467235400343545L;

	@Override
	public List<Recipe> getAllRecipes() {
		@SuppressWarnings("unchecked")
		List<Recipe> recipeList = getSession().createCriteria(Recipe.class).list();
		return recipeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Recipe> getRecipesForUser(User user){
		List<Recipe> recipeList = getSession().createCriteria(Recipe.class)
				.add(Restrictions.eq("owner", user.getAuthId())).list();
		return recipeList;
	}

	@Override
	public void saveRecipe(Recipe recipe) {
		
		removeOrphanChildren(recipe);
		
		saveOrUpdate(recipe);
	}
	
	/**
	 * Hibernate does not provide an easy way to cascade deletions in One to Many relationships
	 * by removing an object from the list while detached. As a result to avoid orphaning records,
	 * we must query the db and remove them manually 
	 * @param recipe
	 */
	@SuppressWarnings("unchecked")
	private void removeOrphanChildren(Recipe recipe){
		
		if(recipe.getId() != null){
			if(recipe.getMashProcess() != null && recipe.getMashProcess().getId() != null){
				
				Object[] savedMashSteps = getSession().createQuery("select mashStep.id from MashStep as mashStep where mash_process_id = :mashProcId")
						.setParameter("mashProcId", recipe.getMashProcess().getId())
						.list().toArray();
				//we are only retrieving one id so we need to convert this
				List<Object[]> objectList = new ArrayList<>();
				for(Object obj: savedMashSteps){
					objectList.add(new Object[]{obj});
				}
				cleanupOrphans(objectList, Arrays.asList("MashStep"), recipe.getMashProcess().getMashSteps());
			}
			
			List<Object[]> savedHopIds = getSession().createQuery("select hopAdd.id, hopAdd.hop.id from HopAddition as hopAdd where recipe_id = :recipeId")
					.setParameter("recipeId", recipe.getId())
					.list();
			cleanupOrphans(savedHopIds, Arrays.asList("HopAddition", "Hop"), recipe.getHopAdditions());
			
			List<Object[]> savedFermIds = getSession().createQuery("select fermAdd.id, fermAdd.fermentable.id from FermentableAddition as fermAdd where recipe_id = :recipeId")
					.setParameter("recipeId", recipe.getId())
					.list();
			cleanupOrphans(savedFermIds, Arrays.asList("FermentableAddition", "Fermentable"), recipe.getFermentableAdditions());
			
			List<Object[]> savedYeastIds = getSession().createQuery("select yeastAdd.id, yeastAdd.yeast.id from YeastAddition as yeastAdd where recipe_id = :recipeId")
					.setParameter("recipeId", recipe.getId())
					.list();
			cleanupOrphans(savedYeastIds, Arrays.asList("YeastAddition", "Yeast"), recipe.getYeastAdditions());
			
			List<Object[]> savedMiscIds = getSession().createQuery("select miscAdd.id, miscAdd.miscIngredient.id from MiscIngredientAddition as miscAdd where recipe_id = :recipeId")
					.setParameter("recipeId", recipe.getId())
					.list();
			cleanupOrphans(savedMiscIds, Arrays.asList("MiscIngredientAddition", "MiscIngredient"), recipe.getMiscAdditions());

		}
	}
	
	/**
	 * Finds all orphans (entities saved in DB, but not in the entity to be saved)
	 * and deletes them. If passed in a list of ids/table names, will perform cascading deletes
	 * @param savedIds each Object[] in list represents tuple of saved ids
	 * @param list of table names
	 * @param currentAdds the list of current entities to be saved (corresponding to the first table)
	 */
	private <T extends CustomizableEntity> void cleanupOrphans(List<Object[]> savedIds, List<String> tableNames, List<T> currentAdds){
		if(!savedIds.isEmpty()){
			//Initialize the map between table name and ids
			Map<String, List<Long>> idOrphanLists = new HashMap<>();
			for(String tableName: tableNames){
				idOrphanLists.put(tableName, new ArrayList<Long>());
			}
			
			//For each of the tuples in db
			for(Object[] savedIdTuple: savedIds){
				//Check if they are orphans
				if(!containsAddition(currentAdds, savedIdTuple)){
					
					//If they are, add each id to the appropriate table in the map
					for(int i = 0; i<tableNames.size(); i++){
						idOrphanLists.get(tableNames.get(i)).add((Long)savedIdTuple[i]);
					}
				}
			}
			
			//back in the map, delete each id for each table
			for(String tableName: tableNames){
				List<Long> idsForTable = idOrphanLists.get(tableName);
				if(!idsForTable.isEmpty()){
					deleteIdsFromTable(tableName, idsForTable);
				}
			}
		}
	}
	
	/**
	 * Given a table name and a list of ids, delete ids from that table
	 * @param tableName
	 * @param ids
	 */
	private void deleteIdsFromTable(String tableName, List<Long> ids){
		getSession().createQuery("delete " + tableName + " where id in :idList")
			.setParameterList("idList", ids)
			.executeUpdate();
	}
	
	/**
	 * Determines if an id tuple is contained in a list of customizable entities
	 * @param list
	 * @param idPair
	 * @return
	 */
	private <T extends CustomizableEntity> boolean containsAddition(List<T> list, Object[] idTuple){
		boolean found = false;
		for(CustomizableEntity id: list){
			if(idTuple[0].equals(id.getId())){
				found = true;
				break;
			}
		}
		return found;
	}

	@Override
	public void deleteRecipe(Recipe recipe) {
		delete(recipe);
	}

	@Override
	public Recipe searchRecipe(Long id) {
		return (Recipe) getSession().get(Recipe.class, id);
	}

	@Override
	public List<Style> getAllStyles() {
		@SuppressWarnings("unchecked")
		List<Style> styleList = getSession().createCriteria(Style.class).list();
		return styleList;
	}

	@Override
	public void saveStyle(Style style) {
		saveOrUpdate(style);
	}

	@Override
	public void deleteStyle(Style style) {
		delete(style);
	}
	
	@Override
	public Style searchStyle(Long id){
		return (Style) getSession().get(Style.class, id);
	}

}
