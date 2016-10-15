angular.module('HackSheffield').controller('HomeCtrl',function($scope, d3Service,
 BackendService) {
	
	BackendService.getData();

});