angular.module('blb', ['ngMaterial']);

var apiService = function(){
    return [];
};

var mainController = function($scope, $mdDialog, $mdMenu, $mdSidenav){
    var originatorEv;
    $scope.openMenu = function($mdMenu, ev) {
      originatorEv = ev;
      $mdMenu.open(ev);
    };
    $scope.redial = function(){
      $mdDialog.show(
        $mdDialog.alert()
          .targetEvent(originatorEv)
          .clickOutsideToClose(true)
          .parent('body')
          .title('Suddenly, a redial')
          .textContent('You are broke, re-evaluate your life!, No seriously, go ahead and check')
          .ok('Close')
      );
    };
    originatorEv = null;
    $scope.openSidenav = function(){
        $mdSidenav('left').toggle();
    }
}

angular.module('blb', ['ngMaterial'])
    .controller('mainController', mainController);