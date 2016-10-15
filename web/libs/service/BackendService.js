angular.module('HackSheffield').factory('BackendService',function($http) {

    var restPath = 'http://localhost:8090/';

    

    var BackendService = {};

    BackendService.getData = function() {
        return $http.get(restPath + 'api/data').then(function(res) {
            if (res && res.data) {
                return res.data;
            }
            
        });
    };

    BackendService.getMetaData = function() {
        return $http.get(restPath + 'api/metadata').then(function(res) {
            if (res && res.data) {
                return res.data;
            }
            
        });
    };




    return BackendService;
});