angular.module('beercraftApp.commonView',[]).

/**
 * Controller for the common area of the app
 */
controller('commonCtrl', function($scope, $q, $location, $window, ingredientListService, recipeService, currentObjects, recipeService, googleAuthService){

	/**
	 * Common alerts for every page
	 */
	$scope.alerts = [];
	
	/**
	 * Common lists for use across the app
	 */
	$scope.hopList = [];
	$scope.fermentableList = [];
	$scope.yeastList = [];
	$scope.miscList = [];
	$scope.styles = [];
	
	/**
	 * Asynchronously load all ingredients, then wait for them all to complete
	 */
	$q.all([
		ingredientListService.listHops(),
		ingredientListService.listFermentables(),
		ingredientListService.listYeast(),
		ingredientListService.listMisc(),
	])
	.then(function(response){
		Array.prototype.push.apply($scope.hopList, response[0].data);
		Array.prototype.push.apply($scope.fermentableList, response[1].data);
		Array.prototype.push.apply($scope.yeastList, response[2].data);
		Array.prototype.push.apply($scope.miscList, response[3].data);
	},
	function(error){
		$scope.alerts.push({msg:"Ingredients could not be retrieved, try again later.", type:'danger'});
	});
	/**
	 * Load styles separately, as it takes longer to download and is not critical for 
	 * immediate use of the app
	 */
	ingredientListService.listStyles().
		then(function(response){
			$scope.styles = response.data;
	});
	
	/**
	 * When the view changes, clear alerts
	 */
	$scope.$on('$routeChangeStart', function(){
		$scope.alerts = [];
		$('.modal').modal('hide');
	});
	
	/**
	 * Uses the gapi service to get user information
	 */
	$scope.isSignedIn = false;
	$scope.getEmail = function(){
		return googleAuthService.getEmail();
	};
	
	/**
	 * Utilizes OAuth Google SignIn to log the user
	 * in. Only name and email are retrieved
	 */
	$scope.onSignIn = function(googleUser){
		var tokenId = googleUser.getAuthResponse().id_token;

		googleAuthService.register(tokenId).
			then(function(){
				$scope.isSignedIn = true;
				
				//Alerts any controllers that are waiting for the user to sign in
				$scope.$broadcast('userSignInEvent');
				$scope.hideSignInModal();
			},
			function(error){
				$scope.alerts.push({msg:"We could not authenticate you.", type:'danger'});
				$scope.hideSignInModal();
		});

	};
	
	/**
	 * Uses the Google API to disassociate their Google Account with this
	 * app. (only "sign out" from this app)
	 */
	$scope.signOut = function(){
		gapi.auth2.getAuthInstance().signOut();
		$scope.isSignedIn = false;
		
		$scope.$broadcast('userSignOutEvent');
	};
	
	/**
	 * User selects new recipe from the dropdown
	 */
	$scope.newRecipe = function(){
		//If there is already a recipe in progress, confirm first
		if(currentObjects.getCurrentRecipe()){
			if(confirm("You have a recipe in progress. Are you sure you want to create a new one?")){
				currentObjects.setCurrentRecipe(null);
				//Tell listeners (recipeEditCtrl) to load a new recipe
				$scope.$broadcast('newRecipeEvent');
				$location.path("/recipeEdit");
			}
		}
		else{
			$location.path("/recipeEdit");
		}
	};
	
	/**
	 * Display link if there is a current recipe to edit, 
	 * but not if user is currently on the edit page
	 */
	$scope.displayEditCurrentRecipeLink = function(){
		if(currentObjects.getCurrentRecipe() && $location.path() != "/recipeEdit"){
			return true;
		}
		else{
			return false;
		}
	};
	
	/**
	 * Closes an alert
	 */
	$scope.closeAlert = function(index){
		$scope.alerts.splice(index, 1);
	};
	
	/**
	 * Shows or hides the sign in the prompt to sign in
	 */
	$scope.showSignInModal = function(){
		$("#signInModal").modal('show');
	};
	$scope.hideSignInModal = function(){
		$("#signInModal").modal('hide');
	};
	
	/**
	 * Shows the about modal window
	 */
	$scope.showAboutModal = function(){
		$('#aboutModal').modal('show');
	};
	/**
	 * Shows the help modal window
	 */
	$scope.showHelpModal = function(){
		$('#helpModal').modal('show');
	};

});