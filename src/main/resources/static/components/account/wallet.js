var profileController = function($scope, $state, user, apiService, $mdToast){
    $scope.user = user.data.data;
    $scope.logout = function(){
        apiService.logout().then(function(data){
            if(data.status==200){
                localStorage.setItem("id", 0);
                $state.go('landing');
            }
        });
    };
    $scope.bankInfo = $scope.user.banks[0];
    console.log($scope.user.banks[0]);
}

angular.module('blb').controller('profileController', profileController);
