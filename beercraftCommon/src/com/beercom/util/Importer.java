package com.beercom.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Pattern;

import javax.inject.Inject;

import com.beercom.entity.Fermentable;
import com.beercom.entity.Hop;
import com.beercom.entity.MiscIngredient;
import com.beercom.entity.Style;
import com.beercom.entity.Yeast;
import com.beercom.entity.Style.StyleType;
import com.beercom.facade.entity.IngredientFacade;
import com.beercom.facade.entity.RecipeFacade;

/**
 * Temporary utility for importing data from csv file to populate DB
 */
public class Importer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6385101443082923216L;

	@Inject
	private IngredientFacade ingredientFacade;
	
	@Inject
	private RecipeFacade recipeFacade;

	public static final String fileName="C:\\Users\\Matt\\Documents\\Google Drive\\CloudStorage\\Beercraft\\sqldata\\misc.csv";
	
	public static final String type="MISC";
	
	public  void importDatafile(){
		
		try {
			FileReader fileReader = new FileReader(fileName);
			
			BufferedReader reader = new BufferedReader(fileReader);
			
			String dataLine = reader.readLine();
			System.out.println(dataLine);
			
			String fieldLine;
			while((fieldLine = reader.readLine()) != null){
				String[] fields = fieldLine.split(Pattern.quote("|"));
				System.out.println(fieldLine);
				
				switch(type){
				case "HOP":
					createNewHop(fields);
					break;
				case "FERMENTABLE":
					createNewFermentable(fields);
					break;
				case "YEAST":
					createNewYeast(fields);
					break;
				case "STYLE":
					createNewStyle(fields);
					break;
				case "HOPSUB":
					createNewHopSub(fields);
					break;
				case "MISC":
					createNewMisc(fields);
					break;
				}
				
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void createNewHop(String[] fields){
		Hop hop = ingredientFacade.newUserHop(null, null);
		
		hop.setName(fields[1]);
		hop.setAlphaAcid(Double.parseDouble(fields[2]));
		hop.setNotes(fields[3]);
		hop.setBetaAcid(Double.parseDouble(fields[4]));
		hop.setOrigin(fields[5]);
		hop.setSubstitutes(fields[6]);
		hop.setHumulene(Double.parseDouble(fields[7]));
		hop.setCaryophyllene(Double.parseDouble(fields[8]));
		hop.setCohumulone(Double.parseDouble(fields[9]));
		hop.setMyrcene(Double.parseDouble(fields[10]));
		hop.setType(Hop.HopType.valueOf(fields[11]));
		
		
		ingredientFacade.saveHop(hop);
	}
	
	public void createNewFermentable(String[] fields){
		Fermentable fermentable = ingredientFacade.newUserFermentable(null, null);
		
		fermentable.setName(fields[1]);
		fermentable.setType(Fermentable.FermentableType.valueOf(fields[2]));
		fermentable.setYield(Double.parseDouble(fields[3]));
		fermentable.setColor(Double.parseDouble(fields[4]));
		fermentable.setOrigin(fields[5]);
		fermentable.setSupplier(fields[6]);
		fermentable.setNotes(fields[7]);
		fermentable.setFineCourseDiff(Double.parseDouble(fields[8]));
		fermentable.setMoisture(Double.parseDouble(fields[9]));
		fermentable.setDiasticPower(Double.parseDouble(fields[10]));
		fermentable.setProtein(Double.parseDouble(fields[11]));
		fermentable.setMaxInBatch(Double.parseDouble(fields[12]));
		fermentable.setRecommendMash(Boolean.parseBoolean(fields[13]));
		
		ingredientFacade.saveFermentable(fermentable);
		
	}
	
	public void createNewYeast(String[] fields){
		Yeast yeast = ingredientFacade.newUserYeast(null, null);
		
		yeast.setName(fields[1]);
		yeast.setType(Yeast.YeastType.valueOf(fields[2]));
		yeast.setForm(Yeast.YeastForm.valueOf(fields[3]));
		yeast.setLaboratory(fields[4]);
		yeast.setProductId(fields[5]);
		yeast.setTemperatureMinC(Double.parseDouble(fields[6]));
		yeast.setTemperatureMaxC(Double.parseDouble(fields[7]));
		yeast.setFlocculation(fields[8]);
		yeast.setAttenuation(Double.parseDouble(fields[9]));
		yeast.setNotes(fields[10]);
		yeast.setBestFor(fields[11]);
		yeast.setMaxReuse(Integer.parseInt(fields[12]));
		
		ingredientFacade.saveYeast(yeast);
	}
	
	public void createNewStyle(String[] fields){
		Style style = recipeFacade.newStyle();
		
		style.setName(fields[1]);
		style.setType(StyleType.valueOf(fields[2].toUpperCase()));
		style.setCategory(fields[3]);
		style.setCategoryNumber(Integer.parseInt(fields[4]));
		if(fields[5].length()>0){
			style.setStyleLetter(fields[5].charAt(0));
		}
		style.setStyleGuide(fields[6]);
		style.setOgMin(Double.parseDouble(fields[7]));
		style.setOgMax(Double.parseDouble(fields[8]));
		style.setFgMin(Double.parseDouble(fields[9]));
		style.setFgMax(Double.parseDouble(fields[10]));
		style.setIbuMin(Double.parseDouble(fields[11]));
		style.setIbuMax(Double.parseDouble(fields[12]));
		style.setColorMin(Double.parseDouble(fields[13]));
		style.setColorMax(Double.parseDouble(fields[14]));
		style.setAbvMin(Double.parseDouble(fields[15]));
		style.setAbvMax(Double.parseDouble(fields[16]));
		style.setNotes(fields[19]);
		style.setProfile(fields[20]);
		style.setIngredients(fields[21]);
		style.setExamples(fields[22]);
		
		recipeFacade.saveStyle(style);
		
	}
	public void createNewHopSub(String[] fields){
		String name = fields[1];
		
		Hop targetHop = ingredientFacade.searchHopByName(name);
		
		targetHop.setSubstitutes(fields[6]);
		
		ingredientFacade.saveHop(targetHop);
		//Note: this will need some thought
		/*String[] examples = fields[6].split(",");
		for(String hopName: examples){
			Hop subHop = ingredientFacade.searchHopByName(hopName.trim());
			if(subHop != null){
				targetHop.getSubstitutes().add(subHop);
			}
			else{
				System.out.println("Cannot find hop for: " + hopName);
			}
		}
		ingredientFacade.saveHop(targetHop);
		*/
	}
	
	public void createNewMisc(String[] fields){
		MiscIngredient ingredient = ingredientFacade.newUserMiscIngredient(null, null);
		
		ingredient.setName(fields[1]);
		ingredient.setType(MiscIngredient.MiscType.valueOf(fields[2].toUpperCase()));
		ingredient.setUse(MiscIngredient.MiscUse.valueOf(fields[3].toUpperCase()));
		if(!fields[7].isEmpty()){
			ingredient.setUseFor(fields[7]);
		}
		if(!fields[8].isEmpty()){
			ingredient.setNotes(fields[8]);
		}
		
		ingredientFacade.saveMiscIngredient(ingredient);
	}
}
