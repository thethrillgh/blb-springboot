var signUpController = function($scope, $state, apiService){
    $scope.states = ('AL AK AZ AR CA CO CT DE FL GA HI ID IL IN IA KS KY LA MA MD ME MI MN MO ' +
    'MS MT NE NV NH NJ NM NY NC ND OH OK OR PA RI SC SD TN TX UT VT VA WA WV WI ' +
    'WY').split(' ').map(function(state) {
        return {abbrev: state};
      });
    $scope.signup = function(form){
        console.log("clicked!!!");
        if(form){
            var data = {
                firstname: $scope.firstname,
                lastname: $scope.lastname,
                phonenum: $scope.phonenum,
                acctemail: $scope.email,
                acctssn: $scope.ssn,
                acctpass: $scope.password,
                acctssn: $scope.acctssn,
                ssnlastfour: String($scope.acctssn).substr(String($scope.acctssn).length - 4),
                streetaddress: $scope.streetaddress,
                city: $scope.city,
                state: $scope.state,
                postalcode: $scope.postalcode,
                acctbalance: 0
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