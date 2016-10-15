angular.module('HackSheffield').controller('HomeCtrl',function($scope, d3Service,
 BackendService, $timeout) {

	$scope.starting = false;
	$scope.notStarted = true;
	$scope.displayChart = false;
	$scope.displayTable = false;

	$scope.chartType = 'bar';

	

	$timeout(function() {
		startApp();
	}, 2000);


	function startApp() {
		$scope.starting = true;

		$timeout(function() {
			$scope.notStarted = false;
			$timeout(function() {
				$scope.displayChart = true;
			});
			
		}, 1500);
	}

});