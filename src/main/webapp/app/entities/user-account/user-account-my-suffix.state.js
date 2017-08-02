(function() {
    'use strict';

    angular
        .module('buylocalbondsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('user-account-my-suffix', {
            parent: 'entity',
            url: '/user-account-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'UserAccounts'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-account/user-accountsmySuffix.html',
                    controller: 'UserAccountMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('user-account-my-suffix-detail', {
            parent: 'user-account-my-suffix',
            url: '/user-account-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'UserAccount'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-account/user-account-my-suffix-detail.html',
                    controller: 'UserAccountMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'UserAccount', function($stateParams, UserAccount) {
                    return UserAccount.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'user-account-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('user-account-my-suffix-detail.edit', {
            parent: 'user-account-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-account/user-account-my-suffix-dialog.html',
                    controller: 'UserAccountMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserAccount', function(UserAccount) {
                            return UserAccount.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-account-my-suffix.new', {
            parent: 'user-account-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-account/user-account-my-suffix-dialog.html',
                    controller: 'UserAccountMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                acctID: null,
                                firstName: null,
                                lastName: null,
                                phoneNum: null,
                                acctEmail: null,
                                acctPassword: null,
                                acctSSN: null,
                                ssnLastFour: null,
                                passSalt: null,
                                acctBalance: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('user-account-my-suffix', null, { reload: 'user-account-my-suffix' });
                }, function() {
                    $state.go('user-account-my-suffix');
                });
            }]
        })
        .state('user-account-my-suffix.edit', {
            parent: 'user-account-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-account/user-account-my-suffix-dialog.html',
                    controller: 'UserAccountMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserAccount', function(UserAccount) {
                            return UserAccount.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-account-my-suffix', null, { reload: 'user-account-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-account-my-suffix.delete', {
            parent: 'user-account-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-account/user-account-my-suffix-delete-dialog.html',
                    controller: 'UserAccountMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UserAccount', function(UserAccount) {
                            return UserAccount.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-account-my-suffix', null, { reload: 'user-account-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
