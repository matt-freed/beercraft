angular.module('beercraftApp.recipeEditView',[]).

/**
 * Controller for the create recipe page
 */
controller('recipeEditCtrl', function($scope, $location, recipeService, currentObjects, googleAuthService){

	$scope.recipe = {};
	$scope.batch = {};
	
	/**
	 * Values for populating the dropdowns
	 */
	$scope.recipeTypes = recipeService.getRecipeTypes();
	$scope.hopForms = recipeService.getHopForms();
	$scope.hopUses = recipeService.getHopUse();
	$scope.hopTypes = recipeService.getHopTypes();
	$scope.fermentableUses = recipeService.getFermentableUses();
	$scope.fermentableTypes = recipeService.getFermentableTypes();
	$scope.mashStepTypes = recipeService.getMashStepTypes();
	$scope.miscUseTypes = recipeService.getMiscUseTypes();
	$scope.miscTypes = recipeService.getMiscTypes();
	$scope.yeastTypes = recipeService.getYeastTypes();
	$scope.yeastForms = recipeService.getYeastForms();
	
	/**
	 * On initial load:
	 * If coming from the browse recipe page, get the selected recipe,
	 * otherwise get a new recipe
	 */
	if(currentObjects.getCurrentRecipe()){
		$scope.recipe = currentObjects.getCurrentRecipe();
	}
	else{
		$scope.recipe = recipeService.getNewRecipe();
		//Update parameters on initial load
		recipeService.calculateRecipe($scope.recipe).
			then(function(response){
				currentObjects.setCurrentRecipe(response.data);
				$scope.recipe = response.data;
		});
	}
	
	/**
	 * When the user clicks the New Recipe option, this handler
	 * will respond to the event by getting a new recipe
	 */
	$scope.$on('newRecipeEvent', function(event){
		$scope.recipe = recipeService.getNewRecipe();
		currentObjects.setCurrentRecipe($scope.recipe);
	});
	
	/**
	 * When the user signs in, set the name if they haven't already set one
	 */
	$scope.$on('userSignInEvent', function(){
		if(!$scope.recipe.author){ 
			$scope.recipe.author = googleAuthService.getName();
		}
	});
	
	/**
	 * Display modal popups
	 */
	$scope.showHopPicker = function(){
		$('#hopPickerModal').modal('show');
	};
	$scope.showFermentablePicker = function(){
		$('#fermentablePickerModal').modal('show');
	};
	$scope.showYeastPicker = function(){
		$('#yeastPickerModal').modal('show');
	};
	$scope.showMiscPicker = function(){
		$('#miscPickerModal').modal('show');
	};
	
	/**
	 * Add/remove ingredient additions
	 */
	$scope.addHopAddition = function(hop){
		$scope.recipe.hopAdditions.push(recipeService.getNewHopAddition(hop));
		$scope.calculateRecipe();
	};
	$scope.removeHopAddition = function(hopAddition){
		for(var i = 0; i<$scope.recipe.hopAdditions.length; i++){
			if($scope.recipe.hopAdditions[i] === hopAddition){
				$scope.recipe.hopAdditions.splice(i, 1);
				break;
			}
		}
		$scope.calculateRecipe();
	};
	$scope.addFermentableAddition = function(fermentable){
		$scope.recipe.fermentableAdditions.push(recipeService.getNewFermentableAddition(fermentable));
		$scope.calculateRecipe();
	};
	$scope.removeFermentableAddition = function(fermentableAddition){
		for(var i = 0; i<$scope.recipe.fermentableAdditions.length; i++){
			if($scope.recipe.fermentableAdditions[i] === fermentableAddition){
				$scope.recipe.fermentableAdditions.splice(i, 1);
				break;
			}
		}
		$scope.calculateRecipe();
	};
	$scope.addYeastAddition = function(yeast){
		$scope.recipe.yeastAdditions.push(recipeService.getNewYeastAddition(yeast));
		$scope.calculateRecipe();
	};
	$scope.removeYeastAddition = function(yeastAddition){
		for(var i = 0; i<$scope.recipe.yeastAdditions.length; i++){
			if($scope.recipe.yeastAdditions[i] === yeastAddition){
				$scope.recipe.yeastAdditions.splice(i, 1);
				break;
			}
		}
		$scope.calculateRecipe();
	};
	$scope.addMiscAddition = function(misc){
		$scope.recipe.miscAdditions.push(recipeService.getNewMiscAddition(misc));
	};
	$scope.removeMiscAddition = function(miscAddition){
		for(var i = 0; i<$scope.recipe.miscAdditions.length; i++){
			if($scope.recipe.miscAdditions[i] === miscAddition){
				$scope.recipe.miscAdditions.splice(i, 1);
				break;
			}
		}
	};
	$scope.newMashStep = function(){
		if(!$scope.recipe.mashProcess){
			$scope.recipe.mashProcess = recipeService.getNewMashProcess();
		}
		$scope.recipe.mashProcess.mashSteps.push(recipeService.getNewMashStep($scope.recipe.mashProcess));
	};
	$scope.removeMashStep = function(mashStep){
		for(var i = 0; i<$scope.recipe.mashProcess.mashSteps.length; i++){
			if($scope.recipe.mashProcess.mashSteps[i] === mashStep){
				$scope.recipe.mashProcess.mashSteps.splice(i, 1);
			}
			//If the removed wasn't the last 
			if($scope.recipe.mashProcess.mashSteps[i]){
				$scope.recipe.mashProcess.mashSteps[i].stepNumber = i + 1;
			}
		}
	};
	
	/**
	 * Saves the recipe
	 */
	$scope.saveRecipe = function(){
		
		//User must be signed in
		if(!$scope.isSignedIn){
			$scope.showSignInModal();
			return;
		}
		
		//Form must be valid
		if($scope.recipeForm.$invalid){
			$('html,body').scrollTop(0);
			$scope.alerts.push({msg:"Please correct the fields highlighted below", type:'danger'});
			return;
		}
		
		recipeService.saveRecipe(googleAuthService.getCurrentToken(), $scope.recipe).
			then(function(response){
				$scope.alerts.push({msg:"Recipe successfully saved!", type:'success'});
				$scope.recipe = response.data;
				$('html,body').scrollTop(0);
			},
			function(error){
				$scope.alerts.push({msg:"Something went wrong, recipe could not be saved.", type:'danger'});
				$('html,body').scrollTop(0);
		});
	};
	
	/**
	 * Sends the recipe to the service to be processed and
	 * the copy persisted
	 */
	$scope.copyRecipe = function(){
		
		//User must be signed in
		if(!$scope.isSignedIn){
			$scope.showSignInModal();
			return;
		}
		
		//Form must be valid
		if($scope.recipeForm.$invalid){
			$('html,body').scrollTop(0);
			$scope.alerts.push({msg:"Please correct the fields highlighted below", type:'danger'});
			return;
		}
		
		recipeService.copyRecipe(googleAuthService.getCurrentToken(), $scope.recipe).
			then(function(response){
				$scope.alerts.push({msg:"Recipe successfully copied!", type:'success'});
				$scope.recipe = response.data;
				$('html,body').scrollTop(0);
			},
			function(error){
				$scope.alerts.push({msg:"Something went wrong, recipe could not be copied.", type:'danger'});
				$('html,body').scrollTop(0);
		});
	};

	
	/**
	 * Calculate recipe parameters
	 */
	$scope.calculateRecipe = function(){
		if(!$scope.recipeForm.$invalid){
			recipeService.calculateRecipe($scope.recipe).
				then(function(response){
					currentObjects.setCurrentRecipe(response.data);
					$scope.recipe = response.data;
				},
				function(error){
					$scope.alerts.push({msg:"Something went wrong, recipe could not be updated.", type:'danger'});
			});
		}
	};

	/**
	 * When given the amount of grains for a particular addition,
	 * calculate it's percentage of the whole grain bill
	 */
	$scope.calculateGrainContribution = function(fermentableLbs){
		if($scope.recipe.grainBillLbs > 0 && angular.isNumber(fermentableLbs)){
			return fermentableLbs/$scope.recipe.grainBillLbs * 100;
		}
		else{
			return 0;
		}
	};
	
	/**
	 * Compares recipe parameters with style expectations
	 */
	$scope.getStyleResultsClass = function(calculated, styleMin, styleMax){
		if(calculated && styleMin && styleMax){
			if(calculated < styleMin || calculated > styleMax){
				return "styleWarning";
			}
		}
	};

	/**
	 * Displays the selected ingredient in the modal for editing.
	 * Because editing is done in a modal, we create a copy and only
	 * persist the copy back to the model if the form was valid (see corresponding directive)
	 */
	$scope.selectedHop;
	$scope.selectedHopIndex;
	$scope.editHop = function(index){
		$scope.selectedHopIndex = index;
		$scope.selectedHop = angular.copy($scope.recipe.hopAdditions[index].hop);
		$("#hopEditModal").modal('show');
	};
	$scope.selectedFermentable;
	$scope.selectedFermentableIndex;
	$scope.editFermentable = function(index){
		$scope.selectedFermentableIndex = index;
		$scope.selectedFermentable = angular.copy($scope.recipe.fermentableAdditions[index].fermentable);
		$("#fermentableEditModal").modal('show');
	};
	$scope.selectedYeast;
	$scope.selectedYeastIndex;
	$scope.editYeast = function(index){
		$scope.selectedYeastIndex = index;
		$scope.selectedYeast = angular.copy($scope.recipe.yeastAdditions[index].yeast);
		$("#yeastEditModal").modal('show');
	};
	$scope.selectedMisc;
	$scope.selectedMiscIndex;
	$scope.editMisc = function(index){
		$scope.selectedMiscIndex = index;
		$scope.selectedMisc = angular.copy($scope.recipe.miscAdditions[index].miscIngredient);
		$("#miscEditModal").modal('show');
	};
	
});