var loginController = function($scope, $state){
    $scope.login = function(form){
        if(form){
            $state.go('dashboard')
        }
    }
}

angular.module('blb').controller('loginController', loginController)