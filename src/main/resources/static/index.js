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

var roundNum = function(){
    return function(x){
        return x.toFixed(2);
    }
}

var semiyearly = function(){
    return function(x){
        var date = new Date(x);
        var newDate = moment(newDate).add(6, 'months');
        return moment(newDate).format("MMM Do YYYY");
    }
}

angular.module('blb')
    .controller('mainController', mainController)
    .filter("mydate", dateFilter)
    .filter("mytime", timeFilter)
    .filter("roundNum", roundNum)
    .filter("semiyearly", semiyearly)
    .run(['$rootScope', '$state', 'apiService', function($rootScope, $state, apiService) {
        $rootScope.$on('$stateChangeError', function(e, toState, toParams, fromState, fromParams, error) {
            if(error === "Not Authorized"){
                $state.go("login");
            }
        });
    }]);
    