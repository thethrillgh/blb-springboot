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
    var signup = function(data){
        return $http.post("/signup", data)
    }
    var user = function(){
        return $http.get("/user")
    }
    var logout = function(){
        return $http.get("/logout")
    }
    
    return {
        getBonds: getBonds,
        accounts: accounts,
        yieldData: yieldData,
        mybonds: mybonds,
        login: login,
        signup: signup,
        user: user,
        logout: logout
    };
};

angular.module('blb').service('apiService', apiService)