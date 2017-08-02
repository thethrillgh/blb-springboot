(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('OrderMySuffixDetailController', OrderMySuffixDetailController);

    OrderMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Order', 'Bond', 'UserAccount'];

    function OrderMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Order, Bond, UserAccount) {
        var vm = this;

        vm.order = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('buylocalbondsApp:orderUpdate', function(event, result) {
            vm.order = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
