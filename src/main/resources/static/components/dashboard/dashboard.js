var dashboardController = function($scope, $state, $mdEditDialog, $q, $timeout, user, apiService, portfolio){
  $scope.bonds = portfolio.data.data.holdings;
  $scope.bondRows = $scope.bonds.length;
  $scope.user = user.data.data;
  $scope.logout = function(){
        apiService.logout().then(function(data){
            if(data.status==200){
                localStorage.setItem("id", 0);
                $state.go('landing');
            }
        });
    };
  $scope.go = function(id){
  //id of bond clicked
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
    order: 'cusip',
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
            order: 'cusip',
            limit: 5,
            page: 1
        };
    }, 2000);
  }
  
  $scope.logItem = function (item) {
//    console.log(item.name, 'was selected');
  };
  
  $scope.logOrder = function (order) {
//    console.log('order: ', order);
  };
  
  $scope.logPagination = function (page, limit) {
//    console.log('page: ', page);
//    console.log('limit: ', limit);
  }
};

//var myfilter = function(
//    return function(data){
//        
//    })

angular.module('blb')
    .controller('dashboardController', dashboardController)
//    .filer('myfilter', myfilter);
