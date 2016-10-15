angular.module('HackSheffield').factory('d3Service',function() {

    var d3 = window.d3;

    

    var d3Service = {};

    d3Service.printD3 = function() {
        console.log("d3 is: ", d3);
    };

    return d3Service;
});