var loginController = function($scope, $state, apiService){
    $scope.login = function(form){
        if(form){
            var data = {
                acctemail: $scope.email,
                ssnlastfour: $scope.ssn4,
                acctpass: $scope.password
            }
            apiService.login(data).then(function(data){
                if(data.data.status=="Done"){
                    localStorage.setItem("id", data.data.data.userid)
                    $state.go('dashboard');
                }
            })
        }
    }
}

angular.module('blb').controller('loginController', loginController)