 var routingConfig = function($stateProvider, $urlRouterProvider){
        $stateProvider
            .state('landing', {
                url: '/',
                templateUrl: 'components/landing/landing.html',
                controller: "mainController"
            })
            .state('login', {
                url: '/login',
                templateUrl: 'components/login/login.html',
                controller: "loginController",
            })
            .state('bond', {
                url: '/bond',
                params: {
                  obj: null
                },
                templateUrl: 'components/bond/bond.html',
                controller: 'bondController',
                resolve: {
                    bonds: function(apiService){
                        return apiService.yieldData();
                    }
                }
            })
            .state('signup', {
                url: '/signup',
                templateUrl: 'components/signup/signup.html',
                controller: "signUpController",
            })
            .state('dashboard', {
                url: '/dashboard',
                templateUrl: 'components/dashboard/dashboard.html',
                controller: 'dashboardController',
                resolve: {
                    security: ['$q', function($q){
                       if(localStorage.getItem("id") == 0){
                          return $q.reject("Not Authorized");
                       }
                    }],
                    bonds: function(apiService){
                        return apiService.getBonds();
                    },
                    user: function(apiService){
                        if(localStorage.getItem("id") == "" || localStorage.getItem("id") == null){
                            localStorage.setItem("id", 0);
                            
                        }
                        return apiService.user(localStorage.getItem("id"));
                    }
                }
            })
            .state('profile', {
                url: '/profile',
                templateUrl: 'components/account/profile.html',
                resolve: {
                    security: ['$q', function($q){
                       if(localStorage.getItem("id") == 0){
                          return $q.reject("Not Authorized");
                       }
                    }],
                    user: function(apiService){
                        if(localStorage.getItem("id") == "" || localStorage.getItem("id") == null){
                            localStorage.setItem("id", 0);
                            
                        }
                        return apiService.user(localStorage.getItem("id"));
                    }
                },
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
    };

angular.module('blb').config(routingConfig);