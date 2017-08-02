(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('BankAcctMySuffixDetailController', BankAcctMySuffixDetailController);

    BankAcctMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'BankAcct', 'UserAccount'];

    function BankAcctMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, BankAcct, UserAccount) {
        var vm = this;

        vm.bankAcct = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('buylocalbondsApp:bankAcctUpdate', function(event, result) {
            vm.bankAcct = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
