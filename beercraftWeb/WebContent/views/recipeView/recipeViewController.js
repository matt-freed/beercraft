angular.module('beercraftApp.recipeViewView',[]).

/**
 * Controller for the create recipe page
 */
controller('recipeViewCtrl', function($scope, $location, recipeService, currentObjects, googleAuthService){
	
	$scope.recipeList = [];
	$scope.sort = {column:'name', descending:false};

	$scope.retrieveRecipes = function(){
		recipeService.getRecipesForUser(googleAuthService.getCurrentToken()).
			then(function(response){
				$scope.recipeList = response.data;
			},
			function(error){
				$scope.alerts.push({msg:"Could not load recipes, try again later.", type:'danger'});
		});
	};
	
	/**
	 * If user navigated here from another page and is already signed in, retrieve recipes immediately
	 */
	if($scope.isSignedIn){
		$scope.retrieveRecipes();
	}
	//If this is the first page hit, we must wait until the sign in process has completed to load the recipe
	else{
		$scope.$on('userSignInEvent', function(){
			$scope.retrieveRecipes();
		});
	};
	
	/**
	 * When the user signs out, we need to clear any state associated with them
	 */
	$scope.$on('userSignOutEvent', function(){
		$scope.recipeList = [];
	});
	
	/**
	 * Sets the recipe selected from the table, navigates
	 * to the recipe edit page
	 */
	$scope.loadRecipe = function(recipe){
		currentObjects.setCurrentRecipe(recipe);
		$location.url("/recipeEdit");
	};
	
	/**
	 * Deletes a recipe
	 */
	$scope.deleteRecipe = function(recipe){
		if(confirm("Are you sure you want to delete Recipe and all corresponding Batches?")){
			recipeService.deleteRecipe(googleAuthService.getCurrentToken(), recipe).
				then(function(response){
					for(var i = 0; i<$scope.recipeList.length; i++){
						if($scope.recipeList[i].id == recipe.id){
							$scope.recipeList.splice(i, 1);
							break;
						}
					}
					$scope.alerts.push({msg:"Recipe successfully deleted.", type:'success'});
				},
				function(error){
					$scope.alerts.push({msg:"Something went wrong, recipe could not be deleted.", type:'danger'});
					$('html,body').scrollTop(0);
			});
		};
	};
	
	/**
	 * Sort methods
	 */
	$scope.changeSort = function(heading){
		if($scope.sort.column == heading){
			$scope.sort.descending = !$scope.sort.descending;
		}
		$scope.sort.column = heading;
	};
	$scope.sortClass = function(heading){
		if($scope.sort.column == heading){
			if($scope.sort.descending){
				return "glyphicon-sort-by-attributes";
			}else{
				return "glyphicon-sort-by-attributes-alt";
			}
		}
		else{
			return "";
		}
	};
});