angular.module('HackSheffield').directive('dtTable', function() {
    return {
        restrict: 'E',
        replace: true,
        scope: { 
            data: '='
        },
        templateUrl: 'libs/directive/data-table/data-table.html',
        link: function(scope, element, attrs, fn) {
            console.log("starting data table");
            //scope.sampleDataObj = scope.data[0];
            scope.sampleDataObj = {name: 'xxxxx', fererfer: 'ferfreref', ferfererf: 'grgtrrt'};
            scope.sampleDataObj2 = {name: 'ferfer', fererfer: 'fe', ferfererf: 'dd'};
            scope.data = scope.data || [scope.sampleDataObj, scope.sampleDataObj2];
            var dataLength = Object.keys(scope.sampleDataObj).length;
            scope.widthStyle = {};



            

            function formWidthStyle(length) {
                console.log("forming style ", length);
                if (length <= 5) {
                    scope.widthStyle.width = '20%';
                } else if (length <= 10) {
                    scope.widthStyle.width = '10%';
                } else {
                    scope.widthStyle.width = '5%';
                }
            }

            formWidthStyle(dataLength);

        }
    };
});
