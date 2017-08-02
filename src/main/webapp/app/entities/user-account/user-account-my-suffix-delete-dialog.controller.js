(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('UserAccountMySuffixDeleteController',UserAccountMySuffixDeleteController);

    UserAccountMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'UserAccount'];

    function UserAccountMySuffixDeleteController($uibModalInstance, entity, UserAccount) {
        var vm = this;

        vm.userAccount = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UserAccount.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
