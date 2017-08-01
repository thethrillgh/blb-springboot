angular.module('blb', ['ui.router', 'ngMaterial', 'ngAnimate', 'ngMessages', 'md.data.table']);

var apiService = function($http){
    var getBonds = function(){
        return $http.get("bond.json")
    }
    var accounts = function(){
        return $http.get("/api/users")
    }
    return {
        getBonds: getBonds,
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

var dashboardController = function($scope, $state, $mdEditDialog, $q, $timeout, bonds){
  $scope.bonds = bonds.data;
  $scope.selected = [];
  $scope.limitOptions = [3, 5, 10, 15];
  
  $scope.options = {
    rowSelection: false,
    multiSelect: false,
    autoSelect: true,
    decapitate: false,
    largeEditDialog: false,
    boundaryLinks: false,
    limitSelect: true,
    pageSelect: true
  };
  
  $scope.query = {
    order: 'cusip',
    limit: 5,
    page: 1
  };
  
  $scope.editComment = function (event, dessert) {
    event.stopPropagation(); // in case autoselect is enabled
    
    var editDialog = {
      modelValue: dessert.comment,
      placeholder: 'Add a comment',
      save: function (input) {
        if(input.$modelValue === 'Donald Trump') {
          input.$invalid = true;
          return $q.reject();
        }
        if(input.$modelValue === 'Bernie Sanders') {
          return dessert.comment = 'FEEL THE BERN!'
        }
        dessert.comment = input.$modelValue;
      },
      targetEvent: event,
      title: 'Add a comment',
      validators: {
        'md-maxlength': 30
      }
    };
    
    var promise;
    
    if($scope.options.largeEditDialog) {
      promise = $mdEditDialog.large(editDialog);
    } else {
      promise = $mdEditDialog.small(editDialog);
    }
    
    promise.then(function (ctrl) {
      var input = ctrl.getInput();
      
      input.$viewChangeListeners.push(function () {
        input.$setValidity('test', input.$modelValue !== 'test');
      });
    });
  };
  
  $scope.toggleLimitOptions = function () {
    $scope.limitOptions = $scope.limitOptions ? undefined : [5, 10, 15];
  };
  
  $scope.getTypes = function () {
    return ['Candy', 'Ice cream', 'Other', 'Pastry'];
  };
  
  $scope.loadStuff = function () {
    $scope.promise = $timeout(function () {
      // loading
    }, 2000);
  }
  
  $scope.logItem = function (item) {
    console.log(item.name, 'was selected');
  };
  
  $scope.logOrder = function (order) {
    console.log('order: ', order);
  };
  
  $scope.logPagination = function (page, limit) {
    console.log('page: ', page);
    console.log('limit: ', limit);
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
                controller: 'dashboardController',
                resolve: {
                    bonds: function(apiService){
                        return apiService.getBonds();
                    }
                }
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
    