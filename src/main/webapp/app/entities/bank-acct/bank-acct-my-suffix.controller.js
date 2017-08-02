(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .controller('BankAcctMySuffixController', BankAcctMySuffixController);

    BankAcctMySuffixController.$inject = ['BankAcct'];

    function BankAcctMySuffixController(BankAcct) {

        var vm = this;

        vm.bankAccts = [];

        loadAll();

        function loadAll() {
            BankAcct.query(function(result) {
                vm.bankAccts = result;
                vm.searchQuery = null;
            });
        }
    }
})();
