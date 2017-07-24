angular.module('blb', ['ui.router', 'ngMaterial', 'ngAnimate']);

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
};

var loginController = function($scope, $state){
    
}

 var routingConfig = function($stateProvider, $urlRouterProvider){
        $stateProvider
            .state('landing', {
                url: '/landing',
                templateUrl: 'components/landing.html',
                controller: "mainController"
            })
            .state('login', {
                url: '/login',
                templateUrl: 'components/login.html',
                controller: "loginController",
//                resolve: {
//                    bonds: function(genService){
//                        return genService.getUserBonds();
//                    }
//                }
            })
        $urlRouterProvider.otherwise('/landing');
    }


angular.module('blb')
    .controller('mainController', mainController)
    .controller('loginController', loginController)
    .config(function($mdThemingProvider){
        var putnamBlue = $mdThemingProvider.extendPalette('blue', {
            '500': '#0077c2',
            'contrastDefaultColor': 'dark'
        });
        $mdThemingProvider.definePalette('putnamBlue', putnamBlue);
        $mdThemingProvider.theme('default')
            .primaryPalette('grey', {
                'hue-1': '50'
            })
            .accentPalette('blue')
    })
    .config(routingConfig);
    