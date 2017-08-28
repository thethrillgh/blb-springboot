var apiService = function($http){
    var accounts = function(){
        return $http.get("data.json")
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
    var transactions = function(){
        return $http.get("/transactions")
    }
    var portfolio = function(){
        return $http.get("/portfolio")
    }
    var sell = function(id, num){
        return $http.get("/order/sell?id="+id+"&quantity="+num)
    }
    var buy = function(id, num){
        return $http.get("/order/buy?id="+id+"&quantity="+num)
    }
    var resetPass = function(oldPass, newPass){
        return $http.get("resetpass?old="+oldPass+"&new="+newPass)
    }

    return {
        accounts: accounts,
        mybonds: mybonds,
        signup: signup,
        login: login,
        signup: signup,
        bond: bond,
        user: user,
        logout: logout,
        portfolio: portfolio,
        sell: sell,
        buy: buy,
        transactions: transactions,
        resetPass: resetPass
    };
};

angular.module('blb').service('apiService', apiService)
