angular.module('HackSheffield').factory('d3Service',function() {

    var d3 = window.d3;

    d3.printD3 = function() {
        console.log("d3 is: ", d3);
    };

    return d3;
});