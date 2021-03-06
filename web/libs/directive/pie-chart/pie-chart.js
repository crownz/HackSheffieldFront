angular.module('HackSheffield').directive('pieChart', function(d3Service, 
    BackendService, $timeout) {
    return {
        restrict: 'E',
        replace: true,
        scope: {
            isLarge: '=',
            data: '=',
            customColors: '='
        },
        templateUrl: 'libs/directive/pie-chart/pie-chart.html',
        link: function(scope, element, attrs, fn) {

            var d3 = d3Service;

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

            var width, height;



            if (scope.isLarge) {
                height = $(window).height() * 0.8;
            } else {
                var one =  $(window).width() * 0.7 / 2;
                var two = $(window).height() * 0.8;
                if (one < two) {
                    height = one;
                } else {
                    height = two;
                }
            }

            width = height;
            
            var radius = Math.min(width, height) / 2;

            var color = d3.scaleOrdinal(["#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"]);

            var arc = d3.arc()
              .outerRadius(radius - 10)
              .innerRadius(0);

            var labelArc = d3.arc()
              .outerRadius(radius - 60)
              .innerRadius(radius - 60);

            var pie = d3.pie()
              .sort(null)
              .value(function(d) { return d.value; });

            var svg = d3.select(".pie-svg-container").append("svg")
                .attr("width", width)
                .attr("height", height)
              .append("g")
                .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

            var update, enter;

            function init() {
                console.log("constructing pie chart with data ", scope.data);
                update = svg.selectAll(".arc")
                  .data(pie(scope.data));

                update.exit().remove();

                enter = update.enter()
                  .append("g")
                  .attr("class", "arc");

                enter.append("path")
                  .attr("class", "piepath")
                  .style("fill", function(d) { return color(d.value); })
                  .transition().duration(2000).attrTween('d', enterTween);

                enter.append("text")
                   .attr("transform", function(d) { return "translate(" + labelArc.centroid(d) + ")"; })
                   .attr("dy", ".35em")
                   .attr("opacity", 0)
                   .text(function(d) { return d.data.name; })
                   .transition()
                   .duration(1000)
                   .attr("opacity", 1);

                enter.exit().remove();
            }

            function enterTween(d) {
                var i = d3.interpolate(d.startAngle+0.1, d.endAngle);
                   return function(t) {
                       d.endAngle = i(t);
                     return arc(d);
                   };
            }

            function updateArcTween(d) {
                console.log("updating tween", d);
                var i = d3.interpolate(d.startAngle+0.1, d.endAngle);
                   return function(t) {
                       d.endAngle = i(t);
                     return arc(d);
                   };
            }

            scope.changeData = function() {
                var update = svg.selectAll(".arc")
                    .data(pie(newData));

                console.log("updated data with new data ", pie(newData));

                update.exit().remove();

                update.append("path")
                    .attr("class", "piepath")
                    .style("fill", function(d) { return color(d.value); })
                    .transition().duration(750)
                    .attrTween('d', updateArcTween);

                update.selectAll("text").remove();

                update.append("text")
                    .attr("transform", function(d) { return "translate(" + labelArc.centroid(d) + ")"; })
                    .attr("dy", ".35em")
                    .attr("opacity", 0)
                    .text(function(d) { return d.data.name; })
                    .transition()
                    .duration(1000)
                    .attr("opacity", 1);
                update.exit().remove();
            };

            init();

            function changeBarColor(transition, colors) {
              var newColor = d3.scaleOrdinal(colors);
              transition
                  .style("fill", function(d) { return newColor(d.value); })
                  .duration(1000);
            }

            var firstLoad = true;

            scope.$watch('customColors', function() {
              console.log("custom colors changed ", scope.customColors);
              if (firstLoad) {
                firstLoad = false;
              } else {
                //$timeout(function() {
                  svg.selectAll(".piepath").transition().call(changeBarColor, scope.customColors);
                //}, 1500);
              }
              
            });
            

        }
    };
});
