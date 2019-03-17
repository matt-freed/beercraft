angular.module('beercraftApp.batchEditView',[]).

/**
 * Controller for the create recipe page
 */
controller('batchEditCtrl', function($scope, recipeService, dateParserService){
	
	/**
	 * Generates the dates based on the current date
	 * @param date
	 * @param days
	 * @returns {Date}
	 */
	addDaysToDate = function(date, days){
		var newDate = new Date();
		newDate.setTime(date.getTime() + days * 86400000 );
		return newDate;
	};
	$scope.calculateBatchSchedule = function(){
		var ferm = $scope.recipe.fermentation;
		$scope.recipe.batch.brewDate = new Date();
		$scope.recipe.batch.toSecondaryDate = null;
		$scope.recipe.batch.toTertiaryDate = null;
		$scope.recipe.batch.toBottleKegDate = null;
		$scope.recipe.batch.readyDate = null;
		if(ferm){
			//If we don't have a primary, don't prefill any dates
			if(ferm.primary && ferm.primary.agingDays){
				var nextEventDate = addDaysToDate($scope.recipe.batch.brewDate, ferm.primary.agingDays);
				//If secondary is the next stage, that is the next event
				if(ferm.secondary && ferm.secondary.agingDays){
					$scope.recipe.batch.toSecondaryDate = nextEventDate;
					nextEventDate = addDaysToDate($scope.recipe.batch.toSecondaryDate, ferm.secondary.agingDays);
					//If tertiary is the next stage, that is the next event
					if(ferm.tertiary && ferm.tertiary.agingDays){
						$scope.recipe.batch.toTertiaryDate = nextEventDate;
						//Tertiary will always be followed by keg date
						$scope.recipe.batch.toBottleKegDate = addDaysToDate($scope.recipe.batch.toTertiaryDate, ferm.tertiary.agingDays);
					}
					//If no tertiary, go right to keg date
					else{
						$scope.recipe.batch.toBottleKegDate = nextEventDate;
					}
				}
				//If no secondary, go right to keg date (ignore "skipped" stages)
				else{
					$scope.recipe.batch.toBottleKegDate = nextEventDate;
				}
			}
			//If a keg date was assigned earlier,
			if($scope.recipe.batch.toBottleKegDate){
				//If conditioning period add that to keg date to get ready
				if(ferm.conditioning && ferm.conditioning.agingDays){
					$scope.recipe.batch.readyDate = addDaysToDate($scope.recipe.batch.toBottleKegDate, ferm.conditioning.agingDays);
				}
				//Otherwise ready date is same as keg date
				else{
					$scope.recipe.batch.readyDate = $scope.recipe.batch.toBottleKegDate;
				}
			}
		}
	};
	
});