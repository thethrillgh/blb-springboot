(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('UserAccountMySuffixDetailController', UserAccountMySuffixDetailController);

    UserAccountMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserAccount', 'UserAddress', 'BankAcct', 'Order'];

    function UserAccountMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, UserAccount, UserAddress, BankAcct, Order) {
        var vm = this;

        vm.userAccount = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('buylocalbondsApp:userAccountUpdate', function(event, result) {
            vm.userAccount = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
