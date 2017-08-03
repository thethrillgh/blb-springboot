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
    var user = function(){
        return $http.post("/api/user-accounts", {a: 123})
    }
    return {
        getBonds: getBonds,
        accounts: accounts,
        yieldData: yieldData,
        user: user
    };
};

angular.module('blb').service('apiService', apiService)