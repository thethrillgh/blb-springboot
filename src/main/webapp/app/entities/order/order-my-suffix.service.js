(function() {
    'use strict';
    angular
        .module('buylocalbondsApp')
        .factory('Order', Order);

    Order.$inject = ['$resource', 'DateUtils'];

    function Order ($resource, DateUtils) {
        var resourceUrl =  'api/orders/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.orderTimeStamp = DateUtils.convertDateTimeFromServer(data.orderTimeStamp);
                        data.tradeDate = DateUtils.convertLocalDateFromServer(data.tradeDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.tradeDate = DateUtils.convertLocalDateToServer(copy.tradeDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.tradeDate = DateUtils.convertLocalDateToServer(copy.tradeDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
