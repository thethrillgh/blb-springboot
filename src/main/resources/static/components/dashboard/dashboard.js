var dashboardController = function($scope, $state, $mdEditDialog, $q, $timeout, user, apiService, portfolio){
  $scope.bonds = portfolio.data.data.holdings;
  $scope.bondRows = $scope.bonds.length;
  $scope.user = user.data.data;
  var chart0 = c3.generate({
        data: {
            columns: [
                ['bulls', 30],
                ['lakers', 50],
            ],
            type : 'donut',
        },
        donut: { width: 10 }
  });
  $scope.logout = function(){
        apiService.logout().then(function(data){
            if(data.status==200){
                localStorage.setItem("id", 0);
                $state.go('landing');
            }
        });
    };
  $scope.go = function(id){
        $state.go('bond', {obj: id})
    }
  $scope.selected = [];
  $scope.limitOptions = [5, 10, 15];
  
  $scope.options = {
    rowSelection: false,
    multiSelect: false,
    autoSelect: true,
    decapitate: false,
    largeEditDialog: false,
    boundaryLinks: false,
    limitSelect: true,
    pageSelect: true
  };
  
  $scope.query = {
    order: 'assocBond.cusip',
    limit: 5,
    page: 1
  };
  
  $scope.toggleLimitOptions = function () {
    $scope.limitOptions = $scope.limitOptions ? undefined : [5, 10, 15];
  };
  
  $scope.loadStuff = function () {
    $scope.promise = $timeout(function () {
      // loading
        $scope.query = {
            order: 'assocBond.cusip',
            limit: 5,
            page: 1
        };
    }, 2000);
  }
};


angular.module('blb')
    .controller('dashboardController', dashboardController)