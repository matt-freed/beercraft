angular.module('beercraftApp.services',[]).

/**
 * Contains methods to retrieve ingredients
 */
factory('ingredientListService', function($http) {
	return{
		listHops: function(){
			return	$http({
					cache:true,
					method: 'GET',
					url: '/beercraftServices/rest/ingredients/hops'
				});
			},
		listFermentables: function(){
			return	$http({
				cache:true,
				method: 'GET',
				url: '/beercraftServices/rest/ingredients/fermentables'
			});
		},
		listYeast: function(){
			return	$http({
				cache:true,
				method: 'GET',
				url: '/beercraftServices/rest/ingredients/yeast'
			});
		},
		listMisc: function(){
			return	$http({
				cache:true,
				method: 'GET',
				url: '/beercraftServices/rest/ingredients/misc'
			});
		}
	};
	
}).
/**
 * The current service stores the recipe and batch the user is currently working
 * on. This allows controllers to share this objects and allows the user
 * to navigate to other pages without losing changes
 */
factory('currentObjects', function(){
	var currentRecipe = null;
	var currentBatch = null;
	
	return {
		getCurrentRecipe : function(){
			return currentRecipe;
		},
		setCurrentRecipe : function(newRecipe){
			currentRecipe = newRecipe;
		},
		getCurrentBatch : function(){
			return currentBatch;
		},
		setCurrentBatch : function(newBatch){
			currentBatch = newBatch;
		}
	};
}).
/**
 * Contains various methods to handle google oauth
 */
factory('googleAuthService', function($http) {
	return{
		register: function(tokenId){
			return $http({
				method: 'POST',
				url: '/beercraftServices/rest/user/register',
				headers:{
					'Authorization': tokenId
				}
			});
		},
		getCurrentToken: function(){
			var currentUser = null;
			if(gapi.auth2){
				currentUser = gapi.auth2.getAuthInstance().currentUser.get();
			}
			
			if(currentUser){
				return currentUser.getAuthResponse().id_token;
			}
			else{
				return null;
			}
		},
		isSignedIn: function(){
			if(gapi.auth2){
				return gapi.auth2.getAuthInstance().isSignedIn.get();
			}
			else{
				return false;
			}
		},
		getName: function(){
			if(this.isSignedIn()){
				return gapi.auth2.getAuthInstance().currentUser.get().getBasicProfile().getName();
			}
			else{
				return null;
			}
		},
		getEmail: function(){
			if(this.isSignedIn()){
				return gapi.auth2.getAuthInstance().currentUser.get().getBasicProfile().getEmail();
			}
			else{
				return null;
			}
		}
		
	};
}).
/**
 * Contains various methods for working with recipes
 */
factory('recipeService', function($http, googleAuthService) {
	return{
		/**
		 * Enum types
		 */
		getHopForms: function(){
			hopForms =
				[{"value":"LEAF","label":"Leaf"},
				 {"value":"PELLET","label":"Pellet"},
				 {"value":"PLUG","label":"Plug"}];
			return hopForms;
		},
		getHopUse: function(){
			hopUses = 
				[{"value":"BOIL","label":"Boil"},
				 {"value":"DRYHOP","label":"Dry Hop"},
				 {"value":"FIRSTWORT","label":"First Wort"},
				 {"value":"HOPBACK","label":"Hop Back"},
				 {"value":"MASH","label":"Mash"},
				 {"value":"CONTINUOUSBOIL","label":"Continuous Boil Addition"}];
			return hopUses;
		},
		getRecipeTypes: function(){
			types = 
				[{"label":"Extract","value":"EXTRACT"},
				 {"label":"All Grain","value":"ALLGRAIN"},
				 {"label":"Partial Mash","value":"PARTIALMASH"}];
			return types;
		},
		getMashStepTypes: function(){
			types = 
				[{"label":"Infusion","value":"INFUSION"},
				 {"label":"Decoction","value":"DECOCTION"},
				 {"label":"Temperature","value":"TEMPERATURE"},
				 {"label":"Sparge","value":"SPARGE"}];
			return types;
		},
		getMiscUseTypes: function(){
			types = 
				[{"label":"Boil","value":"BOIL"},
				 {"label":"Mash","value":"MASH"},
				 {"label":"Primary","value":"PRIMARY"},
				 {"label":"Secondary","value":"SECONDARY"},
				 {"label":"Bottling","value":"BOTTLING"}];
			return types;
		},
		getHopTypes: function(){
			types =
				[{"value":"AROMA", "label":"Aroma"},
				{"value":"BITTERING", "label":"Bittering"},
				{"value":"FLAVOR", "label":"Flavor"},
				{"value":"BOTH", "label":"Aroma/Bittering"},
				{"value":"BITTERINGFLAVOR", "label":"Bittering/Flavor"},
				{"value":"AROMAFLAVOR", "label":"Aroma/Flavor"},
				{"value":"AROMABITTERINGFLAVOR", "label":"Aroma/Bittering/Flavor"},
				{"value":"NONE", "label":"None"}];
			return types;
		},
		getFermentableUses: function(){
			uses =
				[{"value":"MASH", "label":"Mash"},
				{"value":"BOIL", "label":"Boil"},
				{"value":"LATEBOIL", "label":"Late Boil"},
				{"value":"STEEP", "label":"Steep"}];
			return uses;
		},
		getFermentableTypes: function(){
			types =
				[{"value":"ADJUNCT", "label":"Adjunct"},
				{"value":"DRYEXTRRACT", "label":"Dry Extract"},
				{"value":"EXTRACT", "label":"Extract"},
				{"value":"GRAIN", "label":"Grain"},
				{"value":"SUGAR", "label":"Sugar"},
				{"value":"OTHER", "label":"Other"}];
			return types;
		},
		getYeastTypes: function(){
			types =
				[{"value":"ALE", "label":"Ale"},
				{"value":"LAGER", "label":"Lager"},
				{"value":"WHEAT", "label":"Wheat"},
				{"value":"WINE", "label":"Wine"},
				{"value":"CHAMPAGNE", "label":"Champagne"}];
			return types;
		},
		getYeastForms: function(){
			types =
				[{"value":"LIQUID", "label":"Liquid"},
				{"value":"DRY", "label":"Dry"},
				{"value":"SLANT", "label":"Slant"},
				{"value":"CULTURE", "label":"Culture"}];
			return types;
		},
		getMiscTypes: function(){
			types =
				[{"value":"SPICE", "label":"Spice"},
				{"value":"FINING", "label":"Fining"},
				{"value":"WATERAGENT", "label":"Water Agent"},
				{"value":"HERB", "label":"Herb"},
				{"value":"FRUIT", "label":"Fruit"},
				{"value":"FLAVOR", "label":"Flavor"},
				{"value":"OTHER", "label":"Other"}];
			return types;
		},
		/**
		 * New entity types
		 */
		getNewRecipe: function(){
			var newRec = {
				"name":"New Recipe",
				"batchSizeGallons":5.0,
				"volumeDisplayUnit":"gal",
				"boilMinutes":60,
				"efficiency":72,
				"enteredDate":new Date(),
				"fermentableAdditions":[],
				"hopAdditions":[],
				"miscAdditions":[],
				"yeastAdditions":[],
				"boilLossGPH":1,
				"trubLossGal":.5,
				"type":"ALLGRAIN",
				"batch":{}
			};
			// If the user is signed in we can default the name too
			if(googleAuthService.isSignedIn()){
				newRec.author = googleAuthService.getName();
			}
			return newRec;
		},
		getNewUserIng: function(ing){
			ing.derived_from = ing.id;
			ing.id = null;
			ing.isBuiltIn = false;
			return ing;
		},
		determineDefaultFermUse: function(fermentable){
			switch(fermentable.type){
			case "DRYEXTRACT":
			case "EXTRACT":
			case "SUGAR":
			case "OTHER":
				return "BOIL";
				break;
			case "GRAIN":
			case "ADJUNCT":
				return "MASH";
				break;
			default:
				return "BOIL";
				break;
			}
		},
		getNewHopAddition: function(hop){
			return {
				"form":"PELLET",
				"hop":this.getNewUserIng(hop),
				"boilTimeMin":60,
				"weightOz":1.0,
				"use":"BOIL",
				"weightDisplayUnit":"oz"
			};
		},
		getNewFermentableAddition: function(fermentable){
			return {
				"use":this.determineDefaultFermUse(fermentable),
				"fermentable":this.getNewUserIng(fermentable),
				"weightLbs":1.0,
				"weightDisplayUnit":"lbs"
			};
		},
		getNewYeastAddition: function(yeast){
			return {
				"addToSecondary":false,
				"amount":"1 pkg",
				"timesCultured":0,
				"yeast":this.getNewUserIng(yeast)
			};
		},
		getNewMiscAddition: function(misc){
			return {
				"miscIngredient":this.getNewUserIng(misc),
				"miscUse":misc.use
			};
		},
		getNewMashProcess: function(){
			return {
				"name":"Single Infusion",
				"grainTemperatureF":72,
				"ph":6,
				"mashSteps":[]
			};
		},
		getNewMashStep: function(mashProcess){
			var stepNumber;
			if(mashProcess.mashSteps){
				stepNumber = mashProcess.mashSteps.length + 1;
			}
			else{
				stepNumber = 1;
			}
			
			return {
				"stepNumber": stepNumber,
				"type":"INFUSION",
				"temperatureF":154,
				"infuseAmountGal":5,
				"timeMin":40,
				"rampTimeMin":4
			};
		},
		getStyles: function(){
			return $http({
				method: 'GET',
				cache:true,
				url: '/beercraftServices/rest/recipe/styles',
			});
		},
		calculateRecipe: function(recipe){
			return $http({
				method: 'POST',
				url: '/beercraftServices/rest/recipe/calculate',
				data: recipe
			});
		},
		saveRecipe: function(tokenId, recipe){
			return $http({
				method: 'POST',
				url: '/beercraftServices/rest/recipe/save',
				headers:{
					'Authorization': tokenId
				},
				data: recipe
			});
		}, 
		copyRecipe: function(tokenId, recipe){
			return $http({
				method: 'POST',
				url: '/beercraftServices/rest/recipe/copy',
				headers:{
					'Authorization': tokenId
				},
				data: recipe
			});
		}, 
		getRecipesForUser: function(tokenId){
			return $http({
				method: 'GET',
				url: '/beercraftServices/rest/recipe/recipes',
				headers:{
					'Authorization': tokenId
				}
			});
		},
		deleteRecipe: function(tokenId, recipe){
			return $http({
				method: 'POST',
				url: '/beercraftServices/rest/recipe/delete',
				headers:{
					'Authorization': tokenId
				},
				data:recipe
			});
		}
	};
}).
/**
 * Utility to parse all date properties in an object
 */
factory('dateParserService', function() {
	//For each property of an object, if it is a date
	//convert it to a date object
	parseDateProperties = function(respObject){
		for (var key in respObject) {
	        if (!respObject.hasOwnProperty(key)) {
	        	continue;
	        }
	        
	        if( typeof respObject[key] === "object"){
	        	//Check all of its properties, go deeper
	        	recursiveDateParser(respObject[key]);
	        	continue;
	        }
	        //Instead of using regex to see if date matches a pattern,
	        //we take a shortcut here and go by naming convention
	        else if(key.toLowerCase().indexOf("date") > -1){
	        	/*var millis = Date.parse(respObject[key]);
	        	if(!isNaN(millis)){
	        		var 
	        		respObject[key] = new Date(millis);
	        	}*/
	        	var date = new Date(respObject[key]);
	        	if(date){
	        		//The service truncates the time. so When we get it back here it will assume the date is UTC
	        		//time, so we must explicity convert to locale time
	        		respObject[key] = new Date(date.getTime() + date.getTimezoneOffset() * 60000);
	        	}
	        }
	    }
	};
	recursiveDateParser = function(respObject){
		if (typeof respObject !== "object"){
			return respObject;
		}
		
		if(respObject instanceof Array){
			for(var i = 0; i < respObject.length; i++){
				parseDateProperties(respObject[i]);
			}
		}
		else{
			parseDateProperties(respObject);
		}
		return respObject;
	};
		
	return {
		parseDatesForObject: function(respObject){
			recursiveDateParser(respObject);
		}

	};
}).

/**
 * Http interceptor, for all responses coming back we want to convert
 * date strings to date objects. (note we are only looking at the 
 * first level for now, we can recurse if there is a need to go parse
 * dates further down the object graph).
 */
config(["$httpProvider", function ($httpProvider) {
	
	var dateInterceptor = ['dateParserService', function(dateParserService){
		return {
			response: function(responseData){
				dateParserService.parseDatesForObject(responseData.data);
				return responseData;
			}
		};
	}];

	$httpProvider.interceptors.push(dateInterceptor);

}]);
