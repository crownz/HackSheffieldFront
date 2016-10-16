angular.module('HackSheffield').directive('dtTable', function() {
    return {
        restrict: 'E',
        replace: true,
        scope: { 
            data: '='
        },
        templateUrl: 'libs/directive/data-table/data-table.html',
        link: function(scope, element, attrs, fn) {
            //var v1 = {name: 'grtgrtgtr', value: 15};
            //var v2 = {name: 'fffx', value: 31};
            //scope.sampleDataObj = v1;
            //scope.data = scope.data || [v1, v2];
            scope.sampleDataObj = scope.data[0];
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
