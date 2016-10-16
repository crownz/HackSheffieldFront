angular.module('HackSheffield').factory('BackendService',function($http) {

    var restPath = 'https://dashboards-hack.herokuapp.com/';

    var BackendService = {};

    BackendService.getData = function() {
        return $http.get(restPath + 'api/data').then(function(res) {
            if (res && res.data) {
                return res.data;
            }
            
        });
    };

    BackendService.askForStartup = function() {
        return $http.get(restPath + 'api/session').then(function(res) {
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