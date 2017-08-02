(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('UserAddressMySuffixDeleteController',UserAddressMySuffixDeleteController);

    UserAddressMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'UserAddress'];

    function UserAddressMySuffixDeleteController($uibModalInstance, entity, UserAddress) {
        var vm = this;

        vm.userAddress = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UserAddress.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
