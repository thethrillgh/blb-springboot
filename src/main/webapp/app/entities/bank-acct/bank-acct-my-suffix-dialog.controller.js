(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('BankAcctMySuffixDialogController', BankAcctMySuffixDialogController);

    BankAcctMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'BankAcct', 'UserAccount'];

    function BankAcctMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, BankAcct, UserAccount) {
        var vm = this;

        vm.bankAcct = entity;
        vm.clear = clear;
        vm.save = save;
        vm.useraccounts = UserAccount.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.bankAcct.id !== null) {
                BankAcct.update(vm.bankAcct, onSaveSuccess, onSaveError);
            } else {
                BankAcct.save(vm.bankAcct, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('buylocalbondsApp:bankAcctUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
