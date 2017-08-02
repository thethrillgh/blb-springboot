(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('UserAddressMySuffixController', UserAddressMySuffixController);

    UserAddressMySuffixController.$inject = ['UserAddress'];

    function UserAddressMySuffixController(UserAddress) {

        var vm = this;

        vm.userAddresses = [];

        loadAll();

        function loadAll() {
            UserAddress.query(function(result) {
                vm.userAddresses = result;
                vm.searchQuery = null;
            });
        }
    }
})();
