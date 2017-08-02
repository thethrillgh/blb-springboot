(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('UserAddressMySuffixDialogController', UserAddressMySuffixDialogController);

    UserAddressMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UserAddress', 'UserAccount'];

    function UserAddressMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UserAddress, UserAccount) {
        var vm = this;

        vm.userAddress = entity;
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
            if (vm.userAddress.id !== null) {
                UserAddress.update(vm.userAddress, onSaveSuccess, onSaveError);
            } else {
                UserAddress.save(vm.userAddress, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('buylocalbondsApp:userAddressUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
