angular.module('HackSheffield').directive('barChart', function(d3Service, 
    BackendService, $timeout) {
    return {
        restrict: 'E',
        replace: true,
        scope: {
            isLarge: '@'
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
                width = $(document).width()/100*70 - margin.left - margin.right;
                height = $(document).height()/100*80 - margin.top - margin.bottom;
            } else {

            }
            

            var x = d3.scaleBand()
                .range([0, width])
                .padding(0.1);

            var y = d3.scaleLinear()
                .range([height, 0]);

            var svg = d3.select(".bar-svg-container").append("svg")
                .attr("width", width + margin.left + margin.right)
                .attr("height", height + margin.top + margin.bottom)
              .append("g")
                .attr("transform", 
                      "translate(" + margin.left + "," + margin.top + ")");

            var bars;

            function init() {
                BackendService.getData().then(function(data) {

                    console.log("got data  from backend ", data);

                    x.domain(data.map(function(d) { return d.name; }));
                    y.domain([0, d3.max(data, function(d) { return d.value; })]);

                    svg.append("g")
                      .attr("transform", "translate(0," + height + ")")
                      .call(d3.axisBottom(x));

                    svg.append("g")
                      .call(d3.axisLeft(y));

                    bars = svg.selectAll(".bar")
                      .data(data);

                    bars.exit().remove();
                    
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
                });
            }

            init();

            scope.changeBarColor = function() {
                d3.selectAll(".bar").transition().call(changeBarColor, "red");
            };

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

            function changeBarColor(transition, fill) {
              transition
                  .style("fill", fill)
                  .duration(1000);
            }
        }
    };
});
