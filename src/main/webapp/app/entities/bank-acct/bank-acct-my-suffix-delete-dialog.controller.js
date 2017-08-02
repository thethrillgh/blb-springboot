(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('BankAcctMySuffixDeleteController',BankAcctMySuffixDeleteController);

    BankAcctMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'BankAcct'];

    function BankAcctMySuffixDeleteController($uibModalInstance, entity, BankAcct) {
        var vm = this;

        vm.bankAcct = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            BankAcct.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
