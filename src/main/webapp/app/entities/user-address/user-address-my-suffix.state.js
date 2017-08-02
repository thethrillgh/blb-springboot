(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('user-address-my-suffix', {
            parent: 'entity',
            url: '/user-address-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'UserAddresses'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-address/user-addressesmySuffix.html',
                    controller: 'UserAddressMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('user-address-my-suffix-detail', {
            parent: 'user-address-my-suffix',
            url: '/user-address-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'UserAddress'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-address/user-address-my-suffix-detail.html',
                    controller: 'UserAddressMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'UserAddress', function($stateParams, UserAddress) {
                    return UserAddress.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'user-address-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('user-address-my-suffix-detail.edit', {
            parent: 'user-address-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-address/user-address-my-suffix-dialog.html',
                    controller: 'UserAddressMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserAddress', function(UserAddress) {
                            return UserAddress.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-address-my-suffix.new', {
            parent: 'user-address-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-address/user-address-my-suffix-dialog.html',
                    controller: 'UserAddressMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                streetAddress: null,
                                city: null,
                                state: null,
                                postalCode: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('user-address-my-suffix', null, { reload: 'user-address-my-suffix' });
                }, function() {
                    $state.go('user-address-my-suffix');
                });
            }]
        })
        .state('user-address-my-suffix.edit', {
            parent: 'user-address-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-address/user-address-my-suffix-dialog.html',
                    controller: 'UserAddressMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserAddress', function(UserAddress) {
                            return UserAddress.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-address-my-suffix', null, { reload: 'user-address-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-address-my-suffix.delete', {
            parent: 'user-address-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-address/user-address-my-suffix-delete-dialog.html',
                    controller: 'UserAddressMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UserAddress', function(UserAddress) {
                            return UserAddress.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-address-my-suffix', null, { reload: 'user-address-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
