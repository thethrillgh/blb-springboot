'use strict';

describe('Controller Tests', function() {

    describe('Order Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockOrder, MockBond, MockUserAccount;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockOrder = jasmine.createSpy('MockOrder');
            MockBond = jasmine.createSpy('MockBond');
            MockUserAccount = jasmine.createSpy('MockUserAccount');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Order': MockOrder,
                'Bond': MockBond,
                'UserAccount': MockUserAccount
            };
            createController = function() {
                $injector.get('$controller')("OrderMySuffixDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'buylocalbondsApp:orderUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
