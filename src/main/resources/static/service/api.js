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
    var user = function(){
        return $http.get("/user?id="+localStorage.getItem("id"))
    }
    
    return {
        getBonds: getBonds,
        accounts: accounts,
        yieldData: yieldData,
        mybonds: mybonds,
        login: login,
        user: user
    };
};

angular.module('blb').service('apiService', apiService)