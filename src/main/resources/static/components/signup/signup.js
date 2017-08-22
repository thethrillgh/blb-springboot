var signUpController = function($scope, $state){
    $scope.states = ('AL AK AZ AR CA CO CT DE FL GA HI ID IL IN IA KS KY LA ME MD MA MI MN MS ' +
    'MO MT NE NV NH NJ NM NY NC ND OH OK OR PA RI SC SD TN TX UT VT VA WA WV WI ' +
    'WY').split(' ').map(function(state) {
        return {abbrev: state};
      });
    $scope.signup = function(form){
        if(form){
            var data = {
                acctemail: $scope.email,
                acctssn: $scope.ssn,
                acctpass: $scope.password
            }
            apiService.signup(data).then(function(data){
                if(data.data.status=="Done"){
                    $state.go('login');
                }
            })
        }
    }
}

angular.module('blb').controller('signUpController', signUpController)