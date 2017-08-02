(function() {
    'use strict';
    angular
        .module('buylocalbondsApp')
        .factory('BankAcct', BankAcct);

    BankAcct.$inject = ['$resource'];

    function BankAcct ($resource) {
        var resourceUrl =  'api/bank-accts/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
