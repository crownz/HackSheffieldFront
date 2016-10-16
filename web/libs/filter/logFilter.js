angular.module('HackSheffield').filter('logFilter', function() {
    return function(input){
        var out = _.takeRight(input, 5);
        return out;
    };
});