(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('UserAddressMySuffixDetailController', UserAddressMySuffixDetailController);

    UserAddressMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserAddress', 'UserAccount'];

    function UserAddressMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, UserAddress, UserAccount) {
        var vm = this;

        vm.userAddress = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('buylocalbondsApp:userAddressUpdate', function(event, result) {
            vm.userAddress = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
