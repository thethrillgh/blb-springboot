var exploreController = function($scope, $state, $mdEditDialog, $q, $timeout, user, apiService, mybonds){
  $scope.mybonds = mybonds.data.data;
  $scope.bondRows = $scope.mybonds.length;
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
        $state.go('explorebond', {obj: id})
    }
  $scope.removeFilter = function(){
      $scope.filter.search = "";
      $scope.filter.show = false;
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
    limit: 5 ,
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
            limit: 10,
            page: 1
        };
    }, 2000);
  }
  var graphData = [];
  $scope.mybonds.forEach(function(data){
        var item = data.history;
        var temp = [data.cusip];
        item.forEach(function(data1){
            temp.push(data1.yieldask.toFixed(2))
        })
        graphData.push(temp)
  });
  setTimeout(function(){
      var chart = c3.generate({
        data: {
            columns: graphData,
            onclick: function (d, i) { 
                console.log(d.name);
                $scope.mybonds.forEach(function(data){
                    if(data.cusip == d.name){
                        $state.go('explorebond', {obj: data})
                    }
                })
            }
        },
        axis:{
            y: {
                label: {
                    text: 'Yield %',
                    position: 'outer-right'
                },
                tick: {
                    format: d3.format('.2f')
                }
            },
            x: {
                show: false
            }
        }
      });
  }, 200)
};


angular.module('blb')
    .controller('exploreController', exploreController)