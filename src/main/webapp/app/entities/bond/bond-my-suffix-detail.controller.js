(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('BondMySuffixDetailController', BondMySuffixDetailController);

    BondMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Bond', 'Order'];

    function BondMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Bond, Order) {
        var vm = this;

        vm.bond = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('buylocalbondsApp:bondUpdate', function(event, result) {
            vm.bond = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
