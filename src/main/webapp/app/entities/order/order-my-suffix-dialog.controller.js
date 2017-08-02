(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('OrderMySuffixDialogController', OrderMySuffixDialogController);

    OrderMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Order', 'Bond', 'UserAccount'];

    function OrderMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Order, Bond, UserAccount) {
        var vm = this;

        vm.order = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.bonds = Bond.query();
        vm.useraccounts = UserAccount.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.order.id !== null) {
                Order.update(vm.order, onSaveSuccess, onSaveError);
            } else {
                Order.save(vm.order, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('buylocalbondsApp:orderUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.orderTimeStamp = false;
        vm.datePickerOpenStatus.tradeDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
