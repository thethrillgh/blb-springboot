(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('UserAccountMySuffixController', UserAccountMySuffixController);

    UserAccountMySuffixController.$inject = ['UserAccount'];

    function UserAccountMySuffixController(UserAccount) {

        var vm = this;

        vm.userAccounts = [];

        loadAll();

        function loadAll() {
            UserAccount.query(function(result) {
                vm.userAccounts = result;
                vm.searchQuery = null;
            });
        }
    }
})();
