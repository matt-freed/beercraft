package com.beercom.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import com.beercom.calculation.AttenuationCalculator;
import com.beercom.calculation.BoilCalculator;
import com.beercom.calculation.ErrorCalculator;
import com.beercom.calculation.FermentableCalculator;
import com.beercom.calculation.IBUCalculator;
import com.beercom.entity.Batch;
import com.beercom.entity.FermentableAddition;
import com.beercom.entity.HopAddition;
import com.beercom.entity.Recipe;
import com.beercom.entity.YeastAddition;
import com.beercom.entity.FermentableAddition.FermentableUse;
import com.beercom.entity.HopAddition.HopUse;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class RecipeCalculator {
	
	@Inject
	private FermentableCalculator fermentableCalculator;
	
	@Inject
	private IBUCalculator ibuCalculator;
	
	@Inject
	private AttenuationCalculator attenuationCalculator;
	
	@Inject
	private BoilCalculator boilCalculator;
	
	@Inject
	private ErrorCalculator errorCalculator;
	
	public Recipe calculateRecipe(Recipe recipe){
		
		calculateSG(recipe);
		
		calculateBoilOff(recipe);
		
		calculateIbu(recipe);
		
		calculateFG(recipe);
		
		calculateAbv(recipe);
		
		calculateColor(recipe);
		
		calculateBitternessRatio(recipe);
		
		return recipe;
	}
	
	
	public Batch calculateBatch(Recipe recipe){
		
		if(recipe.getBatch() == null){
			return null;
		}
		
		Batch batch = recipe.getBatch();
		
		if(batch.getActualOg() != null && recipe.getOriginalGravity() != null && recipe.getOriginalGravity() > 0){
			Double ogError = errorCalculator.calculateGravityError(batch.getActualOg(), recipe.getOriginalGravity());
			batch.setOgError(ogError);
		}
		else{
			batch.setOgError(null);
		}
		
		if(batch.getActualFg() != null && recipe.getFinalGravity() != null && recipe.getFinalGravity() > 0){
			Double fgError = errorCalculator.calculateGravityError(batch.getActualFg(), recipe.getFinalGravity());
			batch.setFgError(fgError);
		}
		else{
			batch.setFgError(null);
		}
		
		if(batch.getActualIbu() != null && recipe.getIbu() != null && recipe.getIbu() > 0){
			//Note: we don't show decimal places in the view, so we round here to make calculations make more sense to users
			Double ibuError = errorCalculator.calculateError(batch.getActualIbu(), Math.round(recipe.getIbu()));
			batch.setIbuError(ibuError);
		}
		else{
			batch.setIbuError(null);
		}
		
		if(batch.getActualOg() != null && batch.getActualFg() != null && batch.getActualOg() > batch.getActualFg() && batch.getActualOg() > 1){
			double actualAbv = attenuationCalculator.calculateAbvBasic(batch.getActualOg(), batch.getActualFg());
			batch.setActualAbv(actualAbv);
			
			double apparentAttenuation = attenuationCalculator.calculateAttenuation(batch.getActualOg(), batch.getActualFg());
			batch.setActualAttenuation(apparentAttenuation * 100);
		}
		else{
			batch.setActualAbv(null);
			batch.setActualAttenuation(null);
		}
		
		if(batch.getActualAbv() != null && recipe.getAbv() != null && recipe.getAbv() > 0){
			Double abvError = errorCalculator.calculateError(batch.getActualAbv(), recipe.getAbv());
			batch.setAbvError(abvError);
		}
		else{
			batch.setAbvError(null);
		}
		
		if(batch.getActualOg() != null && recipe.getMaxPotential() != null && recipe.getMaxPotential() > 0 
				&& batch.getActualVolGal() != null && batch.getActualVolGal() > 0){
			double efficiency = fermentableCalculator.calculateEfficiency(batch.getActualOg(),
					recipe.getMaxPotential(), batch.getActualVolGal());
			batch.setActualEfficiency(efficiency * 100);
		}
		else{
			batch.setActualEfficiency(null);
		}
		
		/*if(batch.getActualOg() != null && batch.getActualFg() != null && batch.getActualOg() > 1){
			double apparentAttenuation = attenuationCalculator.calculateAttenuation(batch.getActualOg(), batch.getActualFg());
			batch.setActualAttenuation(apparentAttenuation * 100);
		}
		else{
			batch.setActualAttenuation(null);
		}*/

		return batch;
		
	}
	
	public double calculateSG(Recipe recipe) {
		double gravityPoints = 0;
		double maxPotential = 0;
			
		double totalGrainBillLbs = 0;
		for(FermentableAddition fermentable: recipe.getFermentableAdditions()){
			
			double ppg = 0;
			//try to use the ppg directly if possible
			if(fermentable.getFermentable().getPpg() != null){
				ppg = fermentable.getFermentable().getPpg();
			}

			//If we have a yield use that
			if(fermentable.getFermentable().getYield() != null){
				ppg = fermentable.getFermentable().getYield() / 100 * 46;
				fermentable.getFermentable().setPpg(ppg);
			}
			//if not see if ppg is there
			else if(fermentable.getFermentable().getPpg() != null){
				ppg = fermentable.getFermentable().getPpg();
			}
			
			//Steeping reduces yield by about 50%
			double efficiency = recipe.getEfficiency() / 100;
			if(fermentable.getUse().equals(FermentableUse.STEEP)){
				efficiency = efficiency * .5;
			}
			
			totalGrainBillLbs += fermentable.getWeightLbs();
			
			gravityPoints += fermentableCalculator.calculatePoints(fermentable.getMass(), ppg,
					efficiency, recipe.getBatchSizeGallons());
			maxPotential += fermentableCalculator.calculateMaxPotential(fermentable.getMass(), ppg);
			
		}
		
		recipe.setGrainBillLbs(totalGrainBillLbs);
		recipe.setMaxPotential(maxPotential);
		
		double sg = fermentableCalculator.ppgToSg(gravityPoints);
		
		recipe.setOriginalGravity(sg);
		
		return sg;
	}
	
	public void calculateBoilOff(Recipe recipe){
		
		double trubLossG = 0;
		if(recipe.getTrubLossGal() != null){
			trubLossG = recipe.getTrubLossGal();
		}
		double boilLossGPH = 0;
		if(recipe.getBoilLossGPH() != null){
			boilLossGPH = recipe.getBoilLossGPH();
		}
		double timeH = recipe.getBoilMinutes() / 60;
		double targetVolG = recipe.getBatchSizeGallons();
		
		double preBoilVol = boilCalculator.calculatePreBoilFromTarget(trubLossG, boilLossGPH, timeH, targetVolG);
		
		double og = 1;
		//If we don't have an og calculated (which we should), calculate it now
		if(recipe.getOriginalGravity() != null){
			og = recipe.getOriginalGravity();
		}
		else{
			calculateSG(recipe);
		}
		
		//TODO: this is not quite true, total efficiency is based on total sugar loss
		//(which includes trub loss). This doesn't take that into account
		double preBoilSG = boilCalculator.calculatePreBoilSgFromOG(og, preBoilVol, targetVolG);
		
		recipe.setPreBoilVolGal(preBoilVol);
		recipe.setPreBoilSG(preBoilSG);
	}
	
	public double calculateColor(Recipe recipe) {
		double mcu = 0, srm = 0;
			
		for(FermentableAddition fermentable: recipe.getFermentableAdditions()){
			mcu = mcu + fermentableCalculator.calculateMCU(fermentable.getFermentable().getColor(), fermentable.getMass(), recipe.getBatchSizeGallons());
		}

		srm = fermentableCalculator.calculateSRM(mcu);
		
		recipe.setColor(srm);
		
		return srm;
	}
	
	public double calculateIbu(Recipe recipe){
		double ibuTotal = 0;
		
		//Default gravity if it is not yet calculated
		double gravity = 1;
		if(recipe.getOriginalGravity() != null && recipe.getOriginalGravity() > 0){
			gravity = recipe.getOriginalGravity();
		}
		
		double litersOfWater = recipe.getBatchSizeGallons() * 3.78;
		
		//TODO: Build Unit Converter
		double totalHopBillOz = 0;
		for(HopAddition hop: recipe.getHopAdditions()){
			//Dry hop and hop back does not add IBU's
			if(hop.getUse() != HopUse.DRYHOP && hop.getUse() != HopUse.HOPBACK){
				double gramsOfHops = hop.getWeightOz() * 28.35;
				double aaPercent = hop.getHop().getAlphaAcid() / 100;
	
				totalHopBillOz +=hop.getWeightOz();
				
				double ibuContribution = ibuCalculator.calculateIbu(gramsOfHops, aaPercent, litersOfWater, gravity, hop.getBoilTimeMin());
				hop.setIbuContribution(ibuContribution);
				
				ibuTotal += ibuContribution; 
			}
			else{
				hop.setIbuContribution(Double.valueOf(0));
				hop.setBoilTimeMin(Integer.valueOf(0));
			}
		}
		recipe.setHopBillOz(totalHopBillOz);
		recipe.setIbu(ibuTotal);
		
		return ibuTotal;
	}
	
	public double calculateFG(Recipe recipe) {
		double maxAttenuation = 0;
		
		double gravity = 1;
		if(recipe.getOriginalGravity() != null && recipe.getOriginalGravity() > 0){
			gravity = recipe.getOriginalGravity();
		}
		//Use the highest attentuation rate if multiple yeast additions
		for(YeastAddition yeast: recipe.getYeastAdditions()){
			if(yeast.getYeast().getAttenuation() > maxAttenuation){
				maxAttenuation = yeast.getYeast().getAttenuation();
			}
		}
		
		recipe.setAttenuation(maxAttenuation);

		maxAttenuation = maxAttenuation / 100;
		
		double fg = attenuationCalculator.calculateFg(gravity, maxAttenuation);
		
		recipe.setFinalGravity(fg);
		
		return fg;
	}

	public double calculateAbv(Recipe recipe) {
		double originalGravity = 1;
		if(recipe.getOriginalGravity() != null && recipe.getOriginalGravity() > 0){
			originalGravity = recipe.getOriginalGravity();
		}
		
		double finalGravity = 1;
		if(recipe.getFinalGravity() != null && recipe.getFinalGravity() > 0){
			finalGravity = recipe.getFinalGravity();
		}
		
		double abv = attenuationCalculator.calculateAbvAlternate(originalGravity,  finalGravity);
		
		recipe.setAbv(abv);
		
		return abv;
	}
	
	public double calculateBitternessRatio(Recipe recipe){
		
		double ibu = 0;
		if(recipe.getIbu() != null && recipe.getIbu() > 0){
			ibu = recipe.getIbu();
		}
		
		double og = 1;
		if(recipe.getOriginalGravity() != null && recipe.getOriginalGravity() > 0){
			og = recipe.getOriginalGravity();
		}
		
		double ratio = attenuationCalculator.calculateBitternessRatio(ibu, og);
		
		recipe.setIbuFgRatio(ratio);
		
		return ratio;
	}

	public AttenuationCalculator getAttenuationCalculator() {
		return attenuationCalculator;
	}

	public void setAttenuationCalculator(AttenuationCalculator attenuationCalculator) {
		this.attenuationCalculator = attenuationCalculator;
	}
	
	public IBUCalculator getIbuCalculator() {
		return ibuCalculator;
	}

	public void setIbuCalculator(IBUCalculator ibuCalculator) {
		this.ibuCalculator = ibuCalculator;
	}

	public FermentableCalculator getFermentableCalculator() {
		return fermentableCalculator;
	}

	public void setFermentableCalculator(FermentableCalculator fermentableCalculator) {
		this.fermentableCalculator = fermentableCalculator;
	}

}
