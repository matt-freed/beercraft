angular.module('beercraftApp.ingredientView',[]).

/**
 * 
 */
controller('ingredientCtrl', function($scope){

	/**
	 * Changes the sort to the selected column,
	 * or reverses the sort
	 */
	$scope.changeSort = function(sort, heading){
		if(sort.column == heading.property){
			sort.descending = !sort.descending;
		}
		sort.column = heading.property;
	};
	
	/**
	 * Gets the ascending or descending glyphicon based on sort direction
	 */
	$scope.sortClass = function(sort, heading){
		if(sort.column == heading.property){
			if(sort.descending){
				return "glyphicon-sort-by-attributes";
			}else{
				return "glyphicon-sort-by-attributes-alt";
			}
		}
		else{
			return "";
		}
	};
	
	/**
	 * Table Headings
	 */
	//TODO: custom filter for ENUM types (bittering, etc)
	$scope.hopTable = {
		headings: [
			{label:'Name', property:'name', filter: null},
			{label:'Type', property:'type', filter: null},
			{label:'AA%', property:'alphaAcid', filter: '\|number:2'},
			{label:'BA%', property:'betaAcid', filter: '\|number:2'},
			{label:'Origin', property:'origin', filter: null}],
		list: $scope.hopList,
		openDetail: function(hop){
			$scope.selectedHop = hop;
			$('#hopDetailModal').modal('show');},
		sort: {column:'name', descending:false}
	};
	$scope.fermentableTable = {
		headings: [
			{label:'Name', property:'name', filter: null},
			{label:'Origin', property:'origin', filter: null},
			{label:'Yield(%)', property:'yield', filter: '\|number:2'},
			{label:'Type', property:'type', filter: null},],
		list: $scope.fermentableList,
		openDetail: function(fermentable){
				$scope.selectedFermentable = fermentable;
				$('#fermentableDetailModal').modal('show');},
		sort: {column:'name', descending:false}
	};
	$scope.yeastTable = {
		headings: [
			{label:'Name', property:'name', filter: null},
			{label:'Product Id', property:'productId', filter: null},
			{label:'Form', property:'form', filter: null},
			{label:'Lab', property:'laboratory', filter: null},
			{label:'Attenuation', property:'attenuation', filter: null},], 
		list: $scope.yeastList,
		openDetail: function(yeast){
				$scope.selectedYeast = yeast;
				$('#yeastDetailModal').modal('show');},
		sort: {column:'name', descending:false}
	};
	$scope.miscTable = {
		headings: [
			{label:'Name', property:'name', filter: null},
			{label:'Type', property:'type', filter: null},
			{label:'Use', property:'use', filter: null}], 
		list: $scope.miscList,
		openDetail: function(misc){
				$scope.selectedMisc = misc;
				$('#miscDetailModal').modal('show');},
		sort: {column:'name', descending:false}
	};	
	
	/**
	 * The currently selected table
	 */
	$scope.selectedIngTable=$scope.hopTable;
	$scope.selectTable = function(table){
		$scope.selectedIngTable = table;
	};
	
	/**
	 * Detail Modals
	 */
	$scope.selectedHop;
	$scope.selectedFermentable;
	$scope.selectedYeast;
	$scope.selectedMisc;
	
});