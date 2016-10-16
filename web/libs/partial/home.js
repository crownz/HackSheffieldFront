angular.module('HackSheffield').controller('HomeCtrl',function($scope, d3Service,
 BackendService, $timeout) {

	//Used for starup annimation
	$scope.starting = false;

	//Shows if polling for changes is live
	$scope.started = true;

	//Shows how to display data
	$scope.displayChart = false;
	$scope.displayTable = true;

	//Current data to display
	$scope.currentData = undefined;

	//Type of chart
	$scope.chartType = 'bar';

	//Shows if data display update is in progress
	$scope.updatingChanges = false;

	$scope.dots = 3;

	function waitForStart() {
		$timeout(function() {
			BackendService.askForStartup().then(function(res) {
				if (res.hasBeenTouched) {
					startApp();
				} else {
					waitForStart();
				}
			});
		}, 500);
	}


	function startApp() {
		$scope.starting = true;

		$timeout(function() {
			$scope.starting = false;
			$scope.started = true;
			startLoop();
		}, 1200, true);
	}

	function stopApp() {
		$scope.started = false;
		$scope.displayChart = false;
		$scope.displayTable = false;
		$scope.currentData = undefined;
		$scope.updatingChanges = false;
	}

	function startLoop() {
		BackendService.getMetaData().then(function(meta) {
			$timeout(function() {
				console.log("loop passed");
				if (meta && meta.shouldStopPolling) {
					stopApp();
				} else if (meta && meta.changesMadeSinceLastUpdate) {
					$scope.updatingChanges = true;
					BackendService.getData().then(function(data) {
						console.log("GOT NEW DATA!!!!", data, meta);
						switch(meta.displayElementConfig.type) {
							case 'bar_chart':
								$scope.currentData = data;
								$scope.displayChart = true;
								$scope.chartType = 'bar';
								
								break;
							case 'pie_chart':
								$scope.currentData = data;
								$scope.displayChart = true;
								$scope.chartType = 'pie';
								break;
							case 'table':
								$scope.currentData = data;
								$scope.displayTable = true;
								break;
							default:
								break;
						}

						$timeout(function() {
							$scope.updatingChanges = false;
							startLoop();
						}, 500);
					});
				} else if ($scope.started) {
					startLoop();
				} else {
					console.log("kk stoping wtf", $scope.started, meta);
				}
			}, 500);
		});
	}

	$scope.switchApp = function() {
		$scope.started = !$scope.started;
	};

	function loopDots() {
		if ($scope.dots < 3) {
			$scope.dots++;
		} else {
			$scope.dots = 1;
		}
		
		$timeout(function() {
			loopDots();
		}, 1000);
	}

	loopDots();

	//waitForStart();

});