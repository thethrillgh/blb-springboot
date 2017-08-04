var bondController = function($scope, $state, $stateParams, bonds, apiService){
    $scope.detail = $stateParams.obj;
    $scope.data = bonds.data;
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
    var chart = c3.generate({
        bindto: '#chart',
        data: {
            xFormat: '%m%d%y',
            xs: {
                'yield': 'date',
            },
            columns: [
                tradeTime,
                yieldPercent
            ]
        },
        axis: {
            x: {
                type: 'timeseries',
                tick: {
                    format: "%m-%d-%y",
                    values: tick
                }
            }
        }
    });
    
    $scope.load = function(){
        chart.load({
            unload: true,
            columns: [
                test2
            ] 
        });
    }
    
//    apiService.user().then(function(data){
//        console.log(data)
//    })
    
}

angular.module('blb').controller('bondController', bondController)