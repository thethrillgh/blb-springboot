var bondController = function($scope, $state, $stateParams, bonds, apiService){
    $scope.detail = $stateParams.obj;
    $scope.data = bonds.data.data;
    var yieldPercent = Array.from($scope.data, function(data){
        return data.value
    });
    var tradeTime = Array.from($scope.data, function(data){
        var x = data.tradeTime.split(" ")[0];
        return new Date(x);
    });
    var tick = Array.from(['03-01-2010', '03-01-2011', '03-01-2012', '03-01-2013', '03-01-2014', '03-01-2015', '03-01-2016'], function(data){
        return new Date(data);
    })
    yieldPercent.unshift('yield');
    tradeTime.unshift('date');
    var test2 = ['data2', 50, 20, 10, 40, 15, 25];
    setTimeout(function(){
        var chart = c3.generate({
            title: {
                text: 'My Line Chart',
                y: 100
              },
            padding: {
              bottom: 20  
            },
            bindto: '#chart',
            data: {
                xFormat: '%m%d%y',
                xs: {
                    'yield': 'date',
                },
                columns: [
                    tradeTime,
                    yieldPercent
                ],
                type: 'area-spline'
            },
            axis: {
                x: {
                    type: 'timeseries',
                    tick: {
                        format: "%m-%d-%y",
                        values: tick
                    },
                    label: {
                        text: 'Trade Date',
                        position: 'outer-right'
                    }
                },
                y: {
                    label: {
                        text: 'Yield %',
                        position: 'outer-right'
                    }
                }
            },
            legend: {
                show: false
            },
            title: {
                text: "Yield"
            }
        });
        d3.select("svg").append("text")
            .attr("x", 300 )
            .attr("y", 10)
            .style("text-anchor", "middle")
            .style('font-size', '2em')
            .style("dominant-baseline", "central")
            .text("Yield");
    }, 200);
    
    
    $scope.load = function(){
        chart.load({
            unload: true,
            columns: [
                test2
            ] 
        });
    }
}

angular.module('blb').controller('bondController', bondController)