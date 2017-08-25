// Production steps of ECMA-262, Edition 6, 22.1.2.1
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

    // The length property of the from method is 1.
    return function from(arrayLike/*, mapFn, thisArg */) {
      // 1. Let C be the this value.
      var C = this;

      // 2. Let items be ToObject(arrayLike).
      var items = Object(arrayLike);

      // 3. ReturnIfAbrupt(items).
      if (arrayLike == null) {
        throw new TypeError('Array.from requires an array-like object - not null or undefined');
      }

      // 4. If mapfn is undefined, then let mapping be false.
      var mapFn = arguments.length > 1 ? arguments[1] : void undefined;
      var T;
      if (typeof mapFn !== 'undefined') {
        // 5. else
        // 5. a If IsCallable(mapfn) is false, throw a TypeError exception.
        if (!isCallable(mapFn)) {
          throw new TypeError('Array.from: when provided, the second argument must be a function');
        }

        // 5. b. If thisArg was supplied, let T be thisArg; else let T be undefined.
        if (arguments.length > 2) {
          T = arguments[2];
        }
      }

      // 10. Let lenValue be Get(items, "length").
      // 11. Let len be ToLength(lenValue).
      var len = toLength(items.length);

      // 13. If IsConstructor(C) is true, then
      // 13. a. Let A be the result of calling the [[Construct]] internal method 
      // of C with an argument list containing the single item len.
      // 14. a. Else, Let A be ArrayCreate(len).
      var A = isCallable(C) ? Object(new C(len)) : new Array(len);

      // 16. Let k be 0.
      var k = 0;
      // 17. Repeat, while k < len… (also steps a - h)
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
      // 18. Let putStatus be Put(A, "length", len, true).
      A.length = len;
      // 20. Return A.
      return A;
    };
  }());
}

var bondController = function($scope, $state, $stateParams, apiService, user, $mdDialog, $mdToast){
    $scope.logout = function(){
        apiService.logout().then(function(data){
            if(data.status==200){
                localStorage.setItem("id", 0);
                $state.go('landing');
            }
        });
    };
    $scope.user = user.data.data;
    $scope.detail = $stateParams.obj;
    console.log($scope.detail)
    $scope.sell = function(ev) {
        var confirm = $mdDialog.prompt()
          .title('Sell Bond')
          .textContent('How many bonds would you like to sell')
          .placeholder('Quantity')
          .initialValue(10)
          .targetEvent(ev)
          .ok('SELL')
          .cancel('Cancel transaction');

        $mdDialog.show(confirm).then(function(result) {
            apiService.sell($scope.detail.assocBond.bondid, parseInt(result)).then(function(data){
                if(data.data.status=="Success"){
                    $scope.detail.assocBond.quantity -= parseInt(result);
                    $scope.detail.assocOrder.numbondspurchased -= parseInt(result);
                    $mdToast.show(
                        $mdToast.simple()
                        .textContent('You sold ' + result + ' bonds.')
                        .position("top right")
                        .hideDelay(4000)
                    );
                }
            })

        }, function() {
            $mdToast.show(
              $mdToast.simple()
                .textContent('Transaction cancelled.')
                .position("top right")
                .hideDelay(4000)
            );
        });
    };
    $scope.buy = function(ev) {
        var confirm = $mdDialog.prompt()
          .title('Buy Bond')
          .textContent('How many bonds would you like to buy')
          .placeholder('Quantity')
          .initialValue(10)
          .targetEvent(ev)
          .ok('BUY')
          .cancel('Cancel transaction');

        $mdDialog.show(confirm).then(function(result) {
            apiService.buy($scope.detail.assocBond.bondid, parseInt(result)).then(function(data){
                if(data.data.status=="Success"){
                    $scope.detail.assocBond.quantity += parseInt(result);
                    $scope.detail.assocOrder.numbondspurchased += parseInt(result);
                    $mdToast.show(
                        $mdToast.simple()
                        .textContent('You bought ' + result + ' bonds.')
                        .position("top right")
                        .hideDelay(4000)
                    );
                }
            })

        }, function() {
            $mdToast.show(
              $mdToast.simple()
                .textContent('Transaction cancelled.')
                .position("top right")
                .hideDelay(4000)
            );
        });
    };
    function closest(num, arr){
       var curr = arr[0]
       arr.forEach(function(val){
         if(Math.abs(num - val) < Math.abs(num - curr)){
           curr = val;
         }
       })
       return curr
    }
    apiService.bond($scope.detail.assocBond.bondid).then(function(data){
        var bond = data.data.history;
        var yieldPercent = Array.from(bond, function(data){
            return data.yieldask.toFixed(2);
        });
        var dates = [];
        var tradeTime = Array.from(bond, function(data){
            var momentjsobject = moment(data.time);
            dates.push(data.time)
            var dateObject = new Date(momentjsobject.format('YYYY-MM-DD'));
            return dateObject;
        });
        var regionChart = closest(1479618000000, dates);
        console.log($scope.detail.assocBond.issuedate, regionChart, dates)
        yieldPercent.unshift("Yield");
        tradeTime.unshift('Date');
        setTimeout(function(){
            var chart = c3.generate({
                padding: {
                    bottom: 20  
                },
                data: {
                    x:"Date",
                    xFormat: '%Y-%m-%d',
                    columns: [
                        tradeTime, yieldPercent
                    ]
                },
                legend: {
                    show: false
                },
                axis: {
                    x: {
                        type: 'timeseries',
                        tick: {
                                format: '%Y-%m-%d' // how the date is displayed
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
                        },
                        tick: {
                            format: d3.format('.2f')
                        }
                    }
                },
                grid: {
                    x: {
                        show: true
                    },
                    y: {
                        show: true
                    }
                },
                regions: [
//                    {start: '2016-11-04'}
                    {start: regionChart}
                ],
                zoom: {
                    enabled: true
                },
                point: {
                    show: true
                }
            });
            d3.select("svg").append("text")
            .attr("x", 300 )
            .attr("y", 10)
            .style("text-anchor", "middle")
            .style('font-size', '2em')
            .style("dominant-baseline", "central")
            .text("Yield");
        }, 200)
    })
}

angular.module('blb')
    .controller('bondController', bondController)