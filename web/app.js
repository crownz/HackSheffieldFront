angular.module('HackSheffield', ['ui.bootstrap','ngRoute','ngAnimate', 'ngMaterial']);

angular.module('HackSheffield').config(function($routeProvider) {

    $routeProvider.when('/home', {
        templateUrl: 'libs/partial/home.html',
        controller: 'HomeCtrl'
    });

    /* Add New Routes Above */
    $routeProvider.otherwise({redirectTo:'/home'});

});

angular.module('HackSheffield').run(function($rootScope) {

    $rootScope.safeApply = function(fn) {
        var phase = $rootScope.$$phase;
        if (phase === '$apply' || phase === '$digest') {
            if (fn && (typeof(fn) === 'function')) {
                fn();
            }
        } else {
            this.$apply(fn);
        }
    };

});
