angular.module('blb', ['ui.router', 'ngMaterial', 'ngAnimate', 'ngMessages', 'md.data.table']);

var mainController = function($scope, $state){
    
};

var dateFilter = function(){
    return function(x){
        var date = new Date(x);
        var newDate = moment(date).format("MMM Do YYYY");
        return newDate;
    }
}

var timeFilter = function(){
    return function(x){
        var date = new Date(x);
        var newDate = moment(date).format("hh:mm:ss");
        return newDate;
    }
}

var formatter = new Intl.NumberFormat('en-US', {
  style: 'currency',
  currency: 'USD',
  minimumFractionDigits: 2,
});

var roundNum = function(){
    return function(x){
        return x.toFixed(2);
    }
}

var currency = function(){
    return function(x){
        return formatter.format(x);
    }
}

var semiyearly = function(){
    return function(x){
        var date = new Date(x);
        var newDate = moment(date).add(6, 'months');
        return moment(newDate).format("MMM Do YYYY");
    }
}

var accrued = function(){
    return function(x){
        var date = new Date(x);
        var today = new Date();
        var newDate = moment(date).diff(today, 'days');
        return newDate;
    }
}

angular.module('blb')
    .controller('mainController', mainController)
    .filter("mydate", dateFilter)
    .filter("mytime", timeFilter)
    .filter("roundNum", roundNum)
    .filter("semiyearly", semiyearly)
    .filter("currency", currency)
    .filter("accrued", accrued)
    .run(['$rootScope', '$state', 'apiService', function($rootScope, $state, apiService) {
        $rootScope.$on('$stateChangeError', function(e, toState, toParams, fromState, fromParams, error) {
            if(error === "Not Authorized"){
                $state.go("login");
            }
            $state.go("login");
        });
        $rootScope.$on('$stateChangeStart', function(e, toState, toParams, fromState, fromParams, error) {
            if(toState.url=="/bond"){
                if(toParams.obj == null){
                    e.preventDefault();
                    $state.go("dashboard")
                }
            }
        });
    }]);
    