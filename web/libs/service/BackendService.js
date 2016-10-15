angular.module('HackSheffield').factory('BackendService',function($http) {

    var restPath = 'http://localhost:8090/';

    

    var BackendService = {};

    BackendService.getData = function() {
        return $http.get(restPath + 'api/getData').then(function(res) {
            if (res && res.data) {
                console.log("get accounts data: ", res.data);
                return res.data.data;
            }
            
        });
    };

    return BackendService;
});