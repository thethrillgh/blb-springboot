angular.module('blb', ['ui.router', 'ngMaterial', 'ngAnimate', 'ngMessages']);

var apiService = function($http){
    var userData = function(){
        return $http.get("data.json")
    }
    var accounts = function(){
        return $http.get("/api/users")
    }
    return {
        userData: userData,
        accounts: accounts
    };
};

var mainController = function($scope, $state){
    
};

var loginController = function($scope, $state){
    $scope.login = function(form){
        if(form){
            $state.go('dashboard')
        }
    }
}

var signUpController = function($scope, $state){
    
}

var bondController = function($scope, $state){
    
}

var dashboardController = function($scope, $state){
    $scope.reload = function(){
        $state.reload();
    }
}

var profileController = function($scope, $state, apiService){
    $scope.currentNavItem = $state.current.name.split('.')[1];
    $scope.states = ('AL AK AZ AR CA CO CT DE FL GA HI ID IL IN IA KS KY LA ME MD MA MI MN MS ' +
    'MO MT NE NV NH NJ NM NY NC ND OH OK OR PA RI SC SD TN TX UT VT VA WA WV WI ' +
    'WY').split(' ').map(function(state) {
        return {abbrev: state};
      });
    $scope.logout = function(){
        $state.go('landing');
    };
    apiService.accounts().then(function(data){
        console.log(data.data)
    })
    
}

 var routingConfig = function($stateProvider, $urlRouterProvider){
        $stateProvider
            .state('landing', {
                url: '/',
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
            .state('bond', {
                url: '/bond',
                templateUrl: 'components/bond.html',
                controller: 'bondController'
            })
            .state('profile', {
                url: '/profile',
                templateUrl: 'components/profile.html',
                controller: 'profileController'
            })
            .state('profile.account', {
                url: '/account',
                templateUrl: 'components/account/account.html',
                controller: 'profileController'
            })
            .state('profile.address', {
                url: '/address',
                templateUrl: 'components/account/address.html',
                controller: 'profileController'
            })
            .state('profile.password', {
                url: '/password',
                templateUrl: 'components/account/password.html',
                controller: 'profileController'
            })
            .state('profile.wallet', {
                url: '/wallet',
                templateUrl: 'components/account/wallet.html',
                controller: 'profileController'
            })
        $urlRouterProvider.otherwise('/');
    }


angular.module('blb')
    .controller('mainController', mainController)
    .controller('loginController', loginController)
    .controller('signUpController', signUpController)
    .controller('dashboardController', dashboardController)
    .controller('profileController', profileController)
    .controller('bondController', bondController)
    .service('apiService', apiService)
    .config(function($mdThemingProvider){
        
        $mdThemingProvider.theme('default')
            .primaryPalette('grey', {
                'hue-1': '50',
                'hue-2': '900'
            })
            .accentPalette('blue', {
                'hue-1': '900'
            })
            .warnPalette('red')
    })
    .config(routingConfig);
    