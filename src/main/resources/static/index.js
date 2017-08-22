angular.module('blb', ['ui.router', 'ngMaterial', 'ngAnimate', 'ngMessages', 'md.data.table']);

var mainController = function($scope, $state){
    
};

angular.module('blb')
    .controller('mainController', mainController)
    .run(['$rootScope', '$state', 'apiService', function($rootScope, $state, apiService) {
        $rootScope.$on('$stateChangeError', function(e, toState, toParams, fromState, fromParams, error) {
            if(error === "Not Authorized"){
                $state.go("login");
            }
        });
    }]);
    