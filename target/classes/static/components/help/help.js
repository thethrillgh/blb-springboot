var helpController = function($scope, $state, apiService, user){

    $scope.user=user.data.data;
}

angular.module('blb').controller('helpController', helpController)