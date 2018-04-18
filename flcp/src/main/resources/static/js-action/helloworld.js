var myApp = angular.module('helloworld', ['ui.router']);

myApp.config(function($stateProvider,$locationProvider,$urlRouterProvider) {
    $locationProvider.hashPrefix('');
    var dashboardState = {
        name: 'hello',
        url: '/dashboard',
        templateUrl:'dashboard.html'
    }
    var usersState = {
        name: 'about',
        url: '/users',
        templateUrl:'users.html'
    }
    $urlRouterProvider.otherwise("/dashboard");
    $stateProvider.state(dashboardState);
    $stateProvider.state(usersState);
});