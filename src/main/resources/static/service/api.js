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
    var bond = function(id){
        return $http.get("/findbyid?id="+id)
    }
    var login = function(data){
        return $http.post("/login", data)
    }
    var signup = function(data){
        return $http.post("/signup", data)
    }
    var user = function(id){
        return $http.get("/user?id="+id)
    }
    var logout = function(){
        return $http.get("/logout")
    }
    var portfolio = function(){
        return $http.get("/portfolio")
    }

    return {
        getBonds: getBonds,
        accounts: accounts,
        yieldData: yieldData,
        mybonds: mybonds,
        signup: signup,
        login: login,
        signup: signup,
        bond: bond,
        user: user,
        logout: logout,
        portfolio: portfolio
    };
};

angular.module('blb').service('apiService', apiService)
