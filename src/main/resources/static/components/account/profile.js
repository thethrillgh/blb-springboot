var profileController = function($scope, $state, accounts){
    $scope.currentNavItem = $state.current.name.split('.')[1];
    $scope.states = ('AL AK AZ AR CA CO CT DE FL GA HI ID IL IN IA KS KY LA ME MD MA MI MN MS ' +
    'MO MT NE NV NH NJ NM NY NC ND OH OK OR PA RI SC SD TN TX UT VT VA WA WV WI ' +
    'WY').split(' ').map(function(state) {
        return {abbrev: state};
      });
    $scope.logout = function(){
        $state.go('landing');
    };
    console.log(accounts.data)
}

angular.module('blb').controller('profileController', profileController);