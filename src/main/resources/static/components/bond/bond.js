/* Poyfill for < ES2015 */
if (!Array.from) {
  Array.from = (function () {
    var toStr = Object.prototype.toString;
    var isCallable = function (fn) {
      return typeof fn === 'function' || toStr.call(fn) === '[object Function]';
    };
    var toInteger = function (value) {
      var number = Number(value);
      if (isNaN(number)) { return 0; }
      if (number === 0 || !isFinite(number)) { return number; }
      return (number > 0 ? 1 : -1) * Math.floor(Math.abs(number));
    };
    var maxSafeInteger = Math.pow(2, 53) - 1;
    var toLength = function (value) {
      var len = toInteger(value);
      return Math.min(Math.max(len, 0), maxSafeInteger);
    };
    return function from(arrayLike) {
      var C = this;
      var items = Object(arrayLike);
      if (arrayLike == null) {
        throw new TypeError('Array.from requires an array-like object - not null or undefined');
      }
      var mapFn = arguments.length > 1 ? arguments[1] : void undefined;
      var T;
      if (typeof mapFn !== 'undefined') {
          throw new TypeError('Array.from: when provided, the second argument must be a function');
        }
        if (arguments.length > 2) {
          T = arguments[2];
        }
      }
      var len = toLength(items.length);
      var A = isCallable(C) ? Object(new C(len)) : new Array(len);
      var k = 0;
      var kValue;
      while (k < len) {
        kValue = items[k];
        if (mapFn) {
          A[k] = typeof T === 'undefined' ? mapFn(kValue, k) : mapFn.call(T, kValue, k);
        } else {
          A[k] = kValue;
        }
        k += 1;
      }
      A.length = len;
      return A;
    };
  }());
}

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