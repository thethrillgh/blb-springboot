(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('bank-acct-my-suffix', {
            parent: 'entity',
            url: '/bank-acct-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'BankAccts'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/bank-acct/bank-acctsmySuffix.html',
                    controller: 'BankAcctMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('bank-acct-my-suffix-detail', {
            parent: 'bank-acct-my-suffix',
            url: '/bank-acct-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'BankAcct'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/bank-acct/bank-acct-my-suffix-detail.html',
                    controller: 'BankAcctMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'BankAcct', function($stateParams, BankAcct) {
                    return BankAcct.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'bank-acct-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('bank-acct-my-suffix-detail.edit', {
            parent: 'bank-acct-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bank-acct/bank-acct-my-suffix-dialog.html',
                    controller: 'BankAcctMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BankAcct', function(BankAcct) {
                            return BankAcct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('bank-acct-my-suffix.new', {
            parent: 'bank-acct-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bank-acct/bank-acct-my-suffix-dialog.html',
                    controller: 'BankAcctMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                acctNum: null,
                                routingNum: null,
                                acctType: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('bank-acct-my-suffix', null, { reload: 'bank-acct-my-suffix' });
                }, function() {
                    $state.go('bank-acct-my-suffix');
                });
            }]
        })
        .state('bank-acct-my-suffix.edit', {
            parent: 'bank-acct-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bank-acct/bank-acct-my-suffix-dialog.html',
                    controller: 'BankAcctMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BankAcct', function(BankAcct) {
                            return BankAcct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('bank-acct-my-suffix', null, { reload: 'bank-acct-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('bank-acct-my-suffix.delete', {
            parent: 'bank-acct-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bank-acct/bank-acct-my-suffix-delete-dialog.html',
                    controller: 'BankAcctMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['BankAcct', function(BankAcct) {
                            return BankAcct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('bank-acct-my-suffix', null, { reload: 'bank-acct-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
