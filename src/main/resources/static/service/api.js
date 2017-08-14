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
    var mybonds = function(){
        return $http.get("/findall")
    }
    var login = function(data){
        return $http.post("/login", data)
    }
    
    return {
        getBonds: getBonds,
        accounts: accounts,
        yieldData: yieldData,
        mybonds: mybonds,
        login: login
    };
};

angular.module('blb').service('apiService', apiService)