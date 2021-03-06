angular.module('HackSheffield').directive('barChart', function(d3Service, 
    BackendService, $timeout) {
    return {
        restrict: 'E',
        replace: true,
        scope: {
            isLarge: '=',
            data: '=',
            customColors: '='
        },
        templateUrl: 'libs/directive/bar-chart/bar-chart.html',
        link: function(scope, element, attrs, fn) {
            var newData = [
                {
                    name: 'xxx',
                    value: 222
                },
                {
                    name: 'xxeex',
                    value: 1231
                },
                {
                    name: 'ddd',
                    value: 3711
                }
            ];

            var d3 = d3Service;

            var margin = {top: 20, right: 20, bottom: 30, left: 40};

            var width, height;

            if (scope.isLarge) {
                width = $(window).width() * 0.6 - margin.left - margin.right;
                height = $(window).height() * 0.8 - margin.top - margin.bottom;
            } else {
                console.log("smallll");
                width = $(window).width() * 0.35 - margin.left - margin.right;
                height = $(window).height() * 0.4 - margin.top - margin.bottom;
            } 

            var x = d3.scaleBand()
                .range([0, width])
                .padding(0.1);

            var y = d3.scaleLinear()
                .range([height, 0]);

            var colorList = ["#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"];

            var color = d3.scaleOrdinal(colorList);

            var svg = d3.select(".bar-svg-container").append("svg")
                .attr("width", width + margin.left + margin.right)
                .attr("height", height + margin.top + margin.bottom)
              .append("g")
                .attr("transform", 
                      "translate(" + margin.left + "," + margin.top + ")");

            var bars;

            function init() {
                x.domain(scope.data.map(function(d) { return d.name; }));
                y.domain([0, d3.max(scope.data, function(d) { return d.value; })]);

                svg.append("g")
                  .attr("transform", "translate(0," + height + ")")
                  .call(d3.axisBottom(x));

                svg.append("g")
                  .call(d3.axisLeft(y));

                bars = svg.selectAll(".bar")
                  .data(scope.data);

                bars.exit().remove();
                
                bars.enter().append("rect")
                    .attr("class", "bar")
                    .style("fill", function(d) { return color(d.value); })
                    .attr("x", function(d) { return x(d.name); })
                    .attr("width", x.bandwidth())
                    .attr("y", function(d) { return height - 1; })
                    .attr("height", 1)
                    .transition()
                    .duration(2000)
                    .attr("y", function(d) { return y(d.value); })
                    .attr("height", function(d) { return height - y(d.value); });
            }

            init();

            

            scope.changeData = function() {
                svg.selectAll("rect").remove();

                bars = svg.selectAll(".bar")
                      .data(newData);

                svg.selectAll("g").remove();

                x.domain(newData.map(function(d) { return d.name; }));
                y.domain([0, d3.max(newData, function(d) { return d.value; })]);

                svg.append("g")
                    .attr("transform", "translate(0," + height + ")")
                    .call(d3.axisBottom(x));

                svg.append("g")
                    .call(d3.axisLeft(y));
                

                bars.enter().append("rect")
                    .attr("class", "bar")
                    .attr("x", function(d) { return x(d.name); })
                    .attr("width", x.bandwidth())
                    .attr("y", function(d) { return height - 1; })
                    .attr("height", 1)
                    .transition()
                    .duration(1000)
                    .attr("y", function(d) { return y(d.value); })
                    .attr("height", function(d) { return height - y(d.value); });
            };

            function changeBarColor(transition, colors) {
              var newColor = d3.scaleOrdinal(colors);
              transition
                  .style("fill", function(d) { return newColor(d.value); })
                  .duration(1000);
            }

            var firstLoad = true;

            scope.$watch('customColors', function() {
                if (firstLoad) {
                    firstLoad = false;
                } else {
                    console.log("custom colors changed ", scope.customColors);
                    svg.selectAll(".bar").transition().call(changeBarColor, scope.customColors);
                }
            });
        }
    };
});
