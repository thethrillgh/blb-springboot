(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('bond-my-suffix', {
            parent: 'entity',
            url: '/bond-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Bonds'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/bond/bondsmySuffix.html',
                    controller: 'BondMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('bond-my-suffix-detail', {
            parent: 'bond-my-suffix',
            url: '/bond-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Bond'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/bond/bond-my-suffix-detail.html',
                    controller: 'BondMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Bond', function($stateParams, Bond) {
                    return Bond.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'bond-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('bond-my-suffix-detail.edit', {
            parent: 'bond-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bond/bond-my-suffix-dialog.html',
                    controller: 'BondMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Bond', function(Bond) {
                            return Bond.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('bond-my-suffix.new', {
            parent: 'bond-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bond/bond-my-suffix-dialog.html',
                    controller: 'BondMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                cusip: null,
                                isOwned: null,
                                sandpRating: null,
                                moodyRating: null,
                                fitchRating: null,
                                couponType: null,
                                yieldChange: null,
                                yieldChangePercent: null,
                                debtOrAssetClass: null,
                                securityId: null,
                                issuerId: null,
                                issuerDescrip: null,
                                productType: null,
                                couponRate: null,
                                maturityDate: null,
                                marketPrice: null,
                                faceValue: null,
                                bondSymbol: null,
                                callable: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('bond-my-suffix', null, { reload: 'bond-my-suffix' });
                }, function() {
                    $state.go('bond-my-suffix');
                });
            }]
        })
        .state('bond-my-suffix.edit', {
            parent: 'bond-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bond/bond-my-suffix-dialog.html',
                    controller: 'BondMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Bond', function(Bond) {
                            return Bond.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('bond-my-suffix', null, { reload: 'bond-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('bond-my-suffix.delete', {
            parent: 'bond-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bond/bond-my-suffix-delete-dialog.html',
                    controller: 'BondMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Bond', function(Bond) {
                            return Bond.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('bond-my-suffix', null, { reload: 'bond-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
