angular.module('HackSheffield').directive('pieChart', function(d3Service, 
    BackendService) {
    return {
        restrict: 'E',
        replace: true,
        scope: {

        },
        templateUrl: 'libs/directive/pie-chart/pie-chart.html',
        link: function(scope, element, attrs, fn) {

            var d3 = d3Service;
            
            var width = 960,
            height = 500,
            radius = Math.min(width, height) / 2;

            var color = d3.scaleOrdinal(["#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"]);

            var arc = d3.arc()
              .outerRadius(radius - 10)
              .innerRadius(0);

            var labelArc = d3.arc()
              .outerRadius(radius - 40)
              .innerRadius(radius - 40);

            var pie = d3.pie()
              .sort(null)
              .value(function(d) { return d.value; });

            var svg = d3.select(".pie-svg-container").append("svg")
                .attr("width", width)
                .attr("height", height)
              .append("g")
                .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

            

            function init() {
                BackendService.getData().then(function(data) {

                    console.log("initing pie, have data", data);

                    var fakeData = [
                        {
                            name: 'john',
                            value: 12
                        },
                        {
                            name: 'frefrefre',
                            value: 8
                        },
                        {
                            name: 'xxxxx',
                            value: 16
                        }
                    ];

                    var g = svg.selectAll(".arc")
                      .data(pie(fakeData))
                      .enter().append("g")
                      .attr("class", "arc");

                    g.append("path")
                      .attr("d", arc)
                      .style("fill", function(d) { return color(d.data.age); });

                    // g.append("text")
                    //   .attr("transform", function(d) { return "translate(" + labelArc.centroid(d) + ")"; })
                    //   .attr("dy", ".35em")
                    //   .text(function(d) { return d.data.age; });
                });
            }

            function type(d) {
              d.population = +d.population;
              return d;
            }

            init();

        }
    };
});
