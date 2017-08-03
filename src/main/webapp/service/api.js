var apiService = function($http){
    var getBonds = function(){
        return $http.get("bond.json")
    }
    var accounts = function(){
        return $http.get("data.json")
    }
    var yieldData = function(){
        return $http.get("test.json")
    }
    return {
        getBonds: getBonds,
        accounts: accounts,
        yieldData: yieldData
    };
};

angular.module('blb').service('apiService', apiService)