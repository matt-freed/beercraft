angular.module('beercraftApp', [
	'ngRoute',
	'beercraftApp.commonView',
	'beercraftApp.ingredientView',
	'beercraftApp.recipeEditView',
	'beercraftApp.batchEditView',
	'beercraftApp.recipeViewView',
	'beercraftApp.styleView',
	'beercraftApp.services',
	'beercraftApp.directives',
	'ui.bootstrap'
])

.config(['$routeProvider',
	function($routeProvider){
		$routeProvider.
		when('/ingredients', {
			templateUrl: 'views/ingredients/ingredients.html',
			controller: 'ingredientCtrl'
		}).
		when('/recipeEdit', {
			templateUrl:'views/recipeEdit/recipeEdit.html',
			controller: 'recipeEditCtrl'
		}).
		when('/recipeView', {
			templateUrl:'views/recipeView/recipeView.html',
			controller: 'recipeViewCtrl'
		}).
		when('/styles', {
			templateUrl:'views/styles/styles.html',
			controller: 'styleCtrl'
		}).
		otherwise({
			redirectTo:'/recipeEdit'
		}
	);
}
]);
