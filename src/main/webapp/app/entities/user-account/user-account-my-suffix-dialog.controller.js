(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('UserAccountMySuffixDialogController', UserAccountMySuffixDialogController);

    UserAccountMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'UserAccount', 'UserAddress', 'BankAcct', 'Order'];

    function UserAccountMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, UserAccount, UserAddress, BankAcct, Order) {
        var vm = this;

        vm.userAccount = entity;
        vm.clear = clear;
        vm.save = save;
        vm.useraddresses = UserAddress.query({filter: 'assocacct-is-null'});
        $q.all([vm.userAccount.$promise, vm.useraddresses.$promise]).then(function() {
            if (!vm.userAccount.userAddressId) {
                return $q.reject();
            }
            return UserAddress.get({id : vm.userAccount.userAddressId}).$promise;
        }).then(function(userAddress) {
            vm.useraddresses.push(userAddress);
        });
        vm.assocbankaccounts = BankAcct.query({filter: 'assocaccount-is-null'});
        $q.all([vm.userAccount.$promise, vm.assocbankaccounts.$promise]).then(function() {
            if (!vm.userAccount.assocBankAccountId) {
                return $q.reject();
            }
            return BankAcct.get({id : vm.userAccount.assocBankAccountId}).$promise;
        }).then(function(assocBankAccount) {
            vm.assocbankaccounts.push(assocBankAccount);
        });
        vm.orders = Order.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.userAccount.id !== null) {
                UserAccount.update(vm.userAccount, onSaveSuccess, onSaveError);
            } else {
                UserAccount.save(vm.userAccount, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('buylocalbondsApp:userAccountUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
