angular.module('HackSheffield').controller('HomeCtrl',function($scope, d3Service,
 BackendService, $timeout) {

	//Used for starup annimation
	$scope.starting = false;

	//Shows if polling for changes is live
	$scope.started = false;

	//Shows how to display data
	$scope.displayChart = false;
	$scope.displayTable = false;

	//Current data to display
	$scope.currentData = undefined;
	$scope.tableData = undefined;

	//Type of chart
	$scope.chartType = 'bar';

	//Shows if data display update is in progress
	$scope.updatingChanges = false;

	$scope.reinit = true;

	$scope.dots = 3;

	$scope.logItems = [];

	//$scope.colors = ['red', 'blue', 'green'];

	function waitForStart() {
		$timeout(function() {
			BackendService.askForStartup().then(function(res) {
				if (res.hasBeenTouched) {
					$scope.logItems.push('Started app');
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
		$scope.showKittens = false;
		$scope.logItems.push('Stopped app');
		waitForStart();
	}

	function startLoop() {
		BackendService.getMetaData().then(function(meta) {
			$timeout(function() {
				console.log("loop passed");
				if (meta && meta.shouldStopPolling) {
					stopApp();
				} else if (meta && meta.requestType === 'KITTENS') {
					if ($scope.showKittens !== true) {
						$scope.logItems.push('showed some kittens');
					}
					displayKittens();
					startLoop();
				} else if (meta && meta.colors) {
					$scope.colors = meta.colors;
					$scope.logItems.push('changed colors');
					startLoop();
				} else if (meta && meta.changesMadeSinceLastUpdate) {
					$scope.updatingChanges = true;
					$scope.logItems.push('Got a change from Alexa');

					if (meta.hideChart) {
						$scope.displayChart = false;
						$timeout(function() {
							$scope.updatingChanges = false;
							$scope.logItems.push('Hid chart');
							startLoop();
						}, 500);
					} else if (meta.hideTable) {
						$scope.displayTable = false;
						$timeout(function() {
							$scope.updatingChanges = false;
							$scope.logItems.push('Hid table');
							startLoop();
						}, 500);
					} else {
						BackendService.getData().then(function(data) {
							$scope.showKittens = false;
							console.log("GOT NEW DATA!!!!", data, meta);
							switch(meta.displayElementConfig.type) {
								case 'bar_chart':
									$scope.currentData = data;
									if ($scope.chartType === 'bar' && 
											$scope.displayChart === true) {
										reinit();
									}
									$scope.displayChart = true;
									$scope.chartType = 'bar';
									
									break;
								case 'pie_chart':
									$scope.currentData = data;
									if ($scope.chartType === 'pie' && 
											$scope.displayChart === true) {
										reinit();
									}
									$scope.displayChart = true;
									$scope.chartType = 'pie';
									break;
								case 'table':
									$scope.tableData = data;
									$scope.displayTable = true;
									if ($scope.displayChart) {
										reinit();
									}
									break;
								default:
									break;
							}

							$timeout(function() {
								$scope.logItems.push('Updated data');
								$scope.updatingChanges = false;
								startLoop();
							}, 500);
						});
					}

					
					
				} else if ($scope.started) {
					startLoop();
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

	function reinit() {
		$scope.reinit = false;

		$timeout(function() {
			$scope.reinit = true;
		});
	}

	loopDots();

	waitForStart();

	function displayKittens() {
		$scope.showKittens = true;

		$timeout(function() {
			$scope.kittensReady = true;
		}, 1000);
	}
	

});