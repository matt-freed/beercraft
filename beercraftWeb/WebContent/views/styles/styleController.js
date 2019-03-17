angular.module('beercraftApp.styleView',[]).

/**
 * Controller for the style page
 */
controller('styleCtrl', function($scope){
	
	$scope.selectedStyle;
	$scope.sort = {column:'name', descending:false};
	
	$scope.showDetailModal = function(style){
		$scope.selectedStyle = style;
		$('#styleDetailModal').modal('show');
	};
	
	/**
	 * Sort methods
	 */
	$scope.changeSort = function(heading){
		if($scope.sort.column.toString() == heading.toString()){
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
