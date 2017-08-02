(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('BondMySuffixDialogController', BondMySuffixDialogController);

    BondMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Bond', 'Order'];

    function BondMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Bond, Order) {
        var vm = this;

        vm.bond = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.orders = Order.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.bond.id !== null) {
                Bond.update(vm.bond, onSaveSuccess, onSaveError);
            } else {
                Bond.save(vm.bond, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('buylocalbondsApp:bondUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.maturityDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
