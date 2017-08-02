(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('order-my-suffix', {
            parent: 'entity',
            url: '/order-my-suffix?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Orders'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/order/ordersmySuffix.html',
                    controller: 'OrderMySuffixController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
            }
        })
        .state('order-my-suffix-detail', {
            parent: 'order-my-suffix',
            url: '/order-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Order'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/order/order-my-suffix-detail.html',
                    controller: 'OrderMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Order', function($stateParams, Order) {
                    return Order.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'order-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('order-my-suffix-detail.edit', {
            parent: 'order-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order/order-my-suffix-dialog.html',
                    controller: 'OrderMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Order', function(Order) {
                            return Order.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('order-my-suffix.new', {
            parent: 'order-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order/order-my-suffix-dialog.html',
                    controller: 'OrderMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                orderTimeStamp: null,
                                tradeDate: null,
                                transactionAmount: null,
                                numBondsPurchased: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('order-my-suffix', null, { reload: 'order-my-suffix' });
                }, function() {
                    $state.go('order-my-suffix');
                });
            }]
        })
        .state('order-my-suffix.edit', {
            parent: 'order-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order/order-my-suffix-dialog.html',
                    controller: 'OrderMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Order', function(Order) {
                            return Order.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('order-my-suffix', null, { reload: 'order-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('order-my-suffix.delete', {
            parent: 'order-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order/order-my-suffix-delete-dialog.html',
                    controller: 'OrderMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Order', function(Order) {
                            return Order.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('order-my-suffix', null, { reload: 'order-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
