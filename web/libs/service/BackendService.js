angular.module('HackSheffield').factory('BackendService',function($http) {

    var restPath = 'http://localhost:8090/';

    

    var BackendService = {};

    BackendService.getAllAcounts = function() {
        return $http.get(restPath + 'capital/accounts/all').then(function(res) {
            if (res && res.data) {
                console.log("get accounts data: ", res.data);
                return res.data;
            }
            
        });
    };

    return BackendService;
});