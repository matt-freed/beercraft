package com.beercom.util;

import java.util.Arrays;
import java.util.Collection;

import com.beercom.entity.Batch;
import com.beercom.entity.Fermentable;
import com.beercom.entity.FermentableAddition;
import com.beercom.entity.FermentationProcess;
import com.beercom.entity.Hop;
import com.beercom.entity.HopAddition;
import com.beercom.entity.MashProcess;
import com.beercom.entity.MashStep;
import com.beercom.entity.MiscIngredient;
import com.beercom.entity.MiscIngredientAddition;
import com.beercom.entity.Recipe;
import com.beercom.entity.Style;
import com.beercom.entity.User;
import com.beercom.entity.Yeast;
import com.beercom.entity.YeastAddition;

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
