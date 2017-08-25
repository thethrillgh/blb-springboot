// only run when the substr() function is broken
if ('ab'.substr(-1) != 'b') {
  /**
   *  Get the substring of a string
   *  @param  {integer}  start   where to start the substring
   *  @param  {integer}  length  how many characters to return
   *  @return {string}
   */
  String.prototype.substr = function(substr) {
    return function(start, length) {
      // call the original method
      return substr.call(this,
      	// did we get a negative start, calculate how much it is from the beginning of the string
        // adjust the start parameter for negative value
        start < 0 ? this.length + start : start,
        length)
    }
  }(String.prototype.substr);
}
var signUpController = function($scope, $state, apiService){
    $scope.states = ('AL AK AZ AR CA CO CT DE FL GA HI ID IL IN IA KS KY LA MA MD ME MI MN MO ' +
    'MS MT NE NV NH NJ NM NY NC ND OH OK OR PA RI SC SD TN TX UT VT VA WA WV WI ' +
    'WY').split(' ').map(function(state) {
        return {abbrev: state};
      });
    function toTitleCase(str){
        return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
    } 
    $scope.signup = function(form){
        console.log("clicked!!!");
        if(form){
            var ssnlastfour = String($scope.ssn).substring(5,9);
            var data = {
                firstname: toTitleCase($scope.firstname),
                lastname: toTitleCase($scope.lastname),
                phonenum: $scope.phone,
                acctemail: $scope.email,
                acctssn: $scope.ssn,
                acctpass: $scope.password,
                acctssn: $scope.ssn,
                ssnlastfour: ssnlastfour,
                streetaddress: $scope.address,
                city: $scope.city,
                state: $scope.state,
                postalcode: $scope.postalCode,
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
