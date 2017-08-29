var dashboardController = function($scope, $state, $mdEditDialog, $q, $timeout, user, apiService, portfolio, transactions){
  $scope.bonds = portfolio.data.data.holdings;
  $scope.bondRows = $scope.bonds.length;
  if ($scope.bondRows > 0) {
      $scope.showTable = true;
  }
  else {
      $scope.showTable = false;
  }
  $scope.transactions = transactions.data.data;
  $scope.transactionsRows = $scope.transactions.length;
  if(transactions.data.status="Success" && transactions.data.data.message){
      $scope.transactions = []
  }
  $scope.user = user.data.data;
  if($scope.user.totalinvested != null || $scope.user.totalprofits != null){
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
                title: 'Your Funds',
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
  }
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
  $scope.removeFilter = function(){
      $scope.filter.search = "";
      $scope.filter.show = false;
  }
  $scope.query = {
    order: 'assocBond.cusip',
    limit: 5,
    page: 1
  };
    
  $scope.query2 = {
    order: '-timestamp',
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
  $scope.loadStuff2 = function () {
    $scope.promise = $timeout(function () {
      // loading
        $scope.query2 = {
            order: '-timestamp',
            limit: 5,
            page: 1
        };
    }, 2000);
  }
};


angular.module('blb')
    .controller('dashboardController', dashboardController)