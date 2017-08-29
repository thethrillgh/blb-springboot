var loginController = function($scope, $state, apiService, $mdToast){
    $scope.login = function(form){
        if(form){
            var data = {
                acctemail: $scope.email,
                ssnlastfour: $scope.ssn4,
                acctpass: $scope.password
            }
            apiService.login(data).then(function(data){
                if(data.data.status=="Done"){
                    localStorage.setItem("id", data.data.data.userid);
                    $state.go('dashboard');
                }
                else{
                    $mdToast.show(
                      $mdToast.simple()
                        .textContent(data.data.data.message)
                        .position("top right")
                        .hideDelay(2500)
                    );
                }
            })
        }
    }
}

angular.module('blb').controller('loginController', loginController)