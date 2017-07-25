angular.module('blb', ['ui.router', 'ngMaterial', 'ngAnimate', 'ngMessages']);

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
    $scope.loginForm = function(){
        
    }
}

var signUpController = function($scope, $state){
    
}

var dashboardController = function($scope, $state){
    
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
            })
            .state('signup', {
                url: '/signup',
                templateUrl: 'components/signup.html',
                controller: "signUpController",
            })
            .state('dashboard', {
                url: '/dashboard',
                templateUrl: 'components/dashboard.html',
                controller: 'dashboardController'
            })
        $urlRouterProvider.otherwise('/landing');
    }


angular.module('blb')
    .controller('mainController', mainController)
    .controller('loginController', loginController)
    .controller('signUpController', signUpController)
    .controller('dashboardController', dashboardController)
    .config(function($mdThemingProvider){
        
        $mdThemingProvider.theme('default')
            .primaryPalette('grey', {
                'hue-1': '50',
                'hue-2': '900'
            })
            .accentPalette('blue')
            .warnPalette('red')
    })
    .config(routingConfig);
    