(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('BondMySuffixDeleteController',BondMySuffixDeleteController);

    BondMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'Bond'];

    function BondMySuffixDeleteController($uibModalInstance, entity, Bond) {
        var vm = this;

        vm.bond = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Bond.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
