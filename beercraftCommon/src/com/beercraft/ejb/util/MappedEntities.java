package com.beercraft.ejb.util;

import java.util.Arrays;
import java.util.Collection;

import com.beercraft.ejb.entity.Batch;
import com.beercraft.ejb.entity.Fermentable;
import com.beercraft.ejb.entity.FermentableAddition;
import com.beercraft.ejb.entity.FermentationProcess;
import com.beercraft.ejb.entity.Hop;
import com.beercraft.ejb.entity.HopAddition;
import com.beercraft.ejb.entity.MashProcess;
import com.beercraft.ejb.entity.MashStep;
import com.beercraft.ejb.entity.MiscIngredient;
import com.beercraft.ejb.entity.MiscIngredientAddition;
import com.beercraft.ejb.entity.Recipe;
import com.beercraft.ejb.entity.Style;
import com.beercraft.ejb.entity.User;
import com.beercraft.ejb.entity.Yeast;
import com.beercraft.ejb.entity.YeastAddition;

/**
 * Centralize the list of mapped hibernate entities,
 * for use in the HibernateUtil
 */
public final class MappedEntities {
	
	public static final Collection<Class<?>> mappedEntities = 
			Arrays.asList(new Class<?>[]{
				Hop.class,
				HopAddition.class,
				Fermentable.class,
				FermentableAddition.class,
				Recipe.class,
				Style.class,
				Yeast.class,
				YeastAddition.class,
				MashProcess.class,
				MashStep.class,
				FermentationProcess.class,
				MiscIngredient.class,
				MiscIngredientAddition.class,
				User.class,
				Batch.class
				});

}
