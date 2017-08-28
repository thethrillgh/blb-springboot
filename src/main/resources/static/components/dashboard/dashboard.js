var dashboardController = function($scope, $state, $mdEditDialog, $q, $timeout, user, apiService, portfolio){
  $scope.bonds = portfolio.data.data.holdings;
  $scope.bondRows = $scope.bonds.length;
  $scope.user = user.data.data;
  console.log($scope.user)
  setTimeout(function(){
      var chart = c3.generate({
        data: {
            columns: [
                ['Total Invested', $scope.user.totalinvested],
                ['Total Profits', $scope.user.totalprofits]
            ],
            type : 'donut'
        },
        legend: { show: true },
        donut: { 
            width: 7,
            title: 'Total Balance',
            label: {
                show: false
            }
        },
        size: {
            height: 130
        },
        color: {
            pattern: ['#2196F3', '#FFC107']
        }
      });
  }, 200);
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