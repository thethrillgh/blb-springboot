var profileController = function($scope, $state, user, apiService, $mdToast, $mdDialog, $rootScope){
    $scope.user = user.data.data;
    $scope.logout = function(){
        apiService.logout().then(function(data){
            if(data.status==200){
                localStorage.setItem("id", 0);
                $state.go('landing');
            }
        });
    };
    $scope.currentNavItem = $state.current.name.split('.')[1];
    $scope.states = ('AL AK AZ AR CA CO CT DE FL GA HI ID IL IN IA KS KY LA ME MD MA MI MN MS ' +
    'MO MT NE NV NH NJ NM NY NC ND OH OK OR PA RI SC SD TN TX UT VT VA WA WV WI ' +
    'WY').split(' ').map(function(state) {
        return {abbrev: state};
      });
    $scope.reset = function(form){
        if(form){
            apiService.resetPass($scope.password, $scope.password_new).then(function(data){
                if(data.data.data.status="Success"){
                    $scope.password = "";
                    $scope.password_new = "";
                    $scope.password_new_confirm = "";
                    $mdToast.show(
                      $mdToast.simple()
                        .textContent(data.data.data)
                        .position("top right")
                        .hideDelay(2500)
                    );
                }
            })
        }
    }
    $scope.bankInfo = $scope.user.banks[0];
    $scope.nobank = true;
    if($scope.bankInfo != undefined){
        $scope.nobank = false;
    };
    $scope.showAdvanced = function(ev) {
        $mdDialog.show({
          controller: DialogController,
          templateUrl: 'components/account/bankdialog.html',
          parent: angular.element(document.body),
          targetEvent: ev,
          clickOutsideToClose:true,
          locals: {user: $scope.user}
        })
        .then(function(answer) {
          $scope.status = 'You said the information was "' + answer + '".';
        }, function() {
            $scope.status = 'You cancelled the dialog.';
        });
    };
}

function DialogController($scope, $mdDialog, $rootScope, user, apiService, $mdToast, $state) {
    $scope.hide = function() {
      $mdDialog.hide();
    };

    $scope.cancel = function() {
      $mdDialog.cancel();
    };

    $scope.answer = function(answer) {
      $mdDialog.hide(answer);
    };
    
    $scope.addCard = function(form){
        if(form){
            var bank = {
                acctnum: $scope.accountnumber,
                routingnum: $scope.routingnumber,
                accttype: $scope.cardtype
            }
            apiService.bankSave(bank).then(function(data){
                if(data.data.status == "Done"){
                    $mdDialog.hide();
                    $mdToast.show(
                      $mdToast.simple()
                        .textContent("Succesfully added bank account!")
                        .position("top right")
                        .hideDelay(2500)
                    );
                    setTimeout(function(data){
                        $state.reload();
                    }, 2500)
                }
            })
        }
    }
  }

angular.module('blb')
    .controller('profileController', profileController)
    .controller('DialogController', DialogController);


