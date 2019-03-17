angular.module('beercraftApp.directives', []).
/**
 * Common directives
 */
directive('commonNavBar', function(){
	return{
		restrict: 'A',
		templateUrl: 'common/navbar.html'
	};
}).
directive('aboutModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'common/aboutModal.html'
	};
}).
directive('helpModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'common/helpModal.html'
	};
}).
directive('signInModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'common/signInModal.html'
	};
}).
directive('googleSignIn', function(){
	return{
		restrict: 'A',
		template: '<div id="googleSignIn" class="g-signin2 gSignInCenter" ng-hide="isSignedIn"></div>',
		link: function(scope, element, attrs){
			gapi.signin2.render('googleSignIn', {
				'onsuccess': scope.onSignIn,
				'width':175,
				'theme':'light',
				'longtitle': true
			});
		}
	};
}).
directive('googleSignOut', function(){
	return{
		restrict: 'A',
		link: function(scope, element, attrs){
			gapi.signin2.render('googleSignIn', {
				'onsuccess': scope.onSignIn
			});
		}
	};
}).
directive('plusButton', function(){
	return{
		restrict: 'E',
		template:'<button class="btn btn-default glyphicon glyphicon-plus"></button>',
	};
}).
directive('deleteButton', function(){
	return{
		restrict: 'E',
		template:'<button class="btn btn-default glyphicon glyphicon-remove"></button>',
	};
}).
/**
 * Detail modals
 */
directive('hopDetailModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'views/ingredients/hopDetailModal.html'
	};
}).
directive('hopEditModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'views/recipeEdit/hopEditModal.html',
		require:'^form',
		link: function(scope, element, attrs, formCtrl){
			$('#hopEditModal').on('hidden.bs.modal', function(e){
				//When the modal is closed,
				scope.$apply(function(){
					if(!formCtrl.$invalid){
						//Only save copy to model if form valid
						scope.recipe.hopAdditions[scope.selectedHopIndex].hop = scope.selectedHop;
						scope.calculateRecipe();
					}
					else{
						scope.selectedHop = scope.recipe.hopAdditions[scope.selectedHopIndex].hop;
					}
				});
			});
		}
	};
}).
directive('fermentableDetailModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'views/ingredients/fermentableDetailModal.html'
	};
}).
directive('fermentableEditModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'views/recipeEdit/fermentableEditModal.html',
		require:'^form',
		link: function(scope, element, attrs, formCtrl){
			$('#fermentableEditModal').on('hidden.bs.modal', function(e){
				//When the modal is closed,
				scope.$apply(function(){
					if(!formCtrl.$invalid){
						//Only save copy to model if form valid
						scope.recipe.fermentableAdditions[scope.selectedFermentableIndex].fermentable = scope.selectedFermentable;
						scope.calculateRecipe();
					}
					else{
						scope.selectedFermentable = scope.recipe.fermentableAdditions[scope.selectedFermentableIndex].fermentable;
					}
				});
			});
		}
	};
}).
directive('yeastDetailModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'views/ingredients/yeastDetailModal.html'
	};
}).
directive('yeastEditModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'views/recipeEdit/yeastEditModal.html',
		require:'^form',
		link: function(scope, element, attrs, formCtrl){
			$('#yeastEditModal').on('hidden.bs.modal', function(e){
				//When the modal is closed,
				scope.$apply(function(){
					if(!formCtrl.$invalid){
						//Only save copy to model if form valid
						scope.recipe.yeastAdditions[scope.selectedYeastIndex].yeast = scope.selectedYeast;
						scope.calculateRecipe();
					}
					else{
						scope.selectedYeast = scope.recipe.yeastAdditions[scope.selectedYeastIndex].yeast;
					}
				});
			});
		}
	};
}).
directive('miscDetailModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'views/ingredients/miscDetailModal.html'
	};
}).
directive('miscEditModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'views/recipeEdit/miscEditModal.html',
		require:'^form',
		link: function(scope, element, attrs, formCtrl){
			$('#miscEditModal').on('hidden.bs.modal', function(e){
				//When the modal is closed,
				scope.$apply(function(){
					if(!formCtrl.$invalid){
						//Only save copy to model if form valid
						scope.recipe.miscAdditions[scope.selectedMiscIndex].miscIngredient = scope.selectedMisc;
					}
					else{
						scope.selectedMisc = scope.recipe.miscAdditions[scope.selectedMiscIndex].miscIngredient;
					}
				});
			});
		}
	};
}).
directive('styleDetailModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'views/styles/styleDetailModal.html'
	};
}).
/**
 * Picker modals
 */
directive('hopPickerModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'views/recipeEdit/hopPickerModal.html'
	};
}).
directive('fermentablePickerModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'views/recipeEdit/fermentablePickerModal.html'
	};
}).
directive('yeastPickerModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'views/recipeEdit/yeastPickerModal.html'
	};
}).
directive('miscPickerModal', function(){
	return{
		restrict: 'A',
		templateUrl: 'views/recipeEdit/miscPickerModal.html'
	};
}).
/**
 * Validation
*/
directive('lessThan', function(){
	return{
		require:'ngModel',
		link: function(scope, element, attrs, ctrl){
			
			var validate = function(inputValue){
				var compareValue = attrs.lessThan;
				//Only validate if we have both values
				if(!inputValue || !compareValue){
					ctrl.$setValidity('lessThan', true);
					return inputValue;
				}
				
				var valid = parseFloat(inputValue) <= parseFloat(compareValue);
				ctrl.$setValidity('lessThan', valid);
				
				return inputValue;
			};
			
			//Add validators to parser and formatters
			ctrl.$parsers.unshift(validate);
			ctrl.$formatters.push(validate);
			
			//When other value changes, revalidate
			attrs.$observe('lessThan', function(compareValue){
				return validate(ctrl.$viewValue);
			});
		}
	};
});