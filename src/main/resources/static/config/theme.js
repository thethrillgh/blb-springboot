angular.module('blb')
    .config(function($mdThemingProvider){
        $mdThemingProvider.theme('default')
            .primaryPalette('grey', {
                'hue-1': '50',
                'hue-2': '900'
            })
            .accentPalette('blue', {
                'hue-1': '900'
            })
            .warnPalette('red')
    });