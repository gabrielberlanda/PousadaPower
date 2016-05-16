(function(window, angular, undefined) {
	'use strict';

	//Start the AngularJS
	var module = angular.module('home', ['ngMessages', 'ngSanitize', 'ngMaterial', 'ui.router', 'eits-md', 'eits-ng' ]);

	/**
	 * 
	 */
	module.config( function( $stateProvider, $urlRouterProvider, $importServiceProvider, $translateProvider ) {
		//-------
		//Broker configuration
		//-------
		$importServiceProvider.setBrokerURL("./broker/interface");
		//-------
		//Translate configuration
		//-------
		$translateProvider.useURL('./bundles');

		//-------
		//URL Router
		//-------
        $urlRouterProvider.otherwise("/");

        //HOME
        $stateProvider
	        .state('home',{
	        	url 		: "/",
	        	templateUrl	: './modules/home/views/home/home-index.html',
	        })
	        .state('hospede', {
	        	url 		: "/hospede",
	        	templateUrl	: './modules/home/views/hospede/hospede-index.html',
	        	controller	: 'HospedeController',
	        	abstract 	: true,
	        })
	        .state('hospede.lista', {
	        	url 		: "/lista",
	        	templateUrl	: './modules/home/views/hospede/hospede-lista.html',
	        })
	        .state('hospede.detalhe', {
	        	url 		: "/detalhe/{id:[0-9]{1,10}}",
	        	templateUrl	: './modules/home/views/hospede/hospede-detalhe.html',
	        })
	        .state('hospede.novo', {
	        	url 		: "/novo",
	        	templateUrl	: './modules/home/views/hospede/hospede-formulario.html',
	        })
	        .state('hospede.editar', {
	        	url         : "/editar/{id:[0-9]{1,10}}",
	        	templateUrl	: './modules/home/views/hospede/hospede-formulario.html',
	        });
	});

	/**
	 * 
	 */
	module
	.config(function($mdThemingProvider) {
	  $mdThemingProvider.theme('default')
	    .primaryPalette('blue-grey')
	});
	/**
	 * 
	 */
	module.run( function( $rootScope, $window, $state, $stateParams ) {
		//$rootScope.$usuario 	= $window.usuario;
		$rootScope.$state 		= $state;
		$rootScope.$stateParams = $stateParams;
	});

	/**
	 * 
	 */
	angular.element(document).ready( function() {
		angular.bootstrap( document, ['home']);
	});

})(window, window.angular);