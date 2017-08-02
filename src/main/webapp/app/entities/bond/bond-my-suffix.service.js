(function() {
    'use strict';
    angular
        .module('buylocalbondsApp')
        .factory('Bond', Bond);

    Bond.$inject = ['$resource', 'DateUtils'];

    function Bond ($resource, DateUtils) {
        var resourceUrl =  'api/bonds/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.maturityDate = DateUtils.convertLocalDateFromServer(data.maturityDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.maturityDate = DateUtils.convertLocalDateToServer(copy.maturityDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.maturityDate = DateUtils.convertLocalDateToServer(copy.maturityDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
