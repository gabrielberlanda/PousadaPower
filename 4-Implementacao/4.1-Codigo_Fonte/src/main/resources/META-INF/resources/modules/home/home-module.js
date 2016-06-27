(function(window, angular, undefined) {
	'use strict';

	//Start the AngularJS
	var module = angular.module('home', ['ngMessages', 'ngSanitize', 'ngMaterial', 'ui.router', 'eits-md', 'eits-ng', 'md.data.table' ]);

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
	        .state('usuario', {
	        	url 		: "/usuario",
	        	templateUrl	: './modules/home/views/usuario/usuario-index.html',
	        	controller	: 'UsuarioController',
	        	abstract 	: true,
	        })
	        .state('usuario.lista', {
	        	url 		: "/lista",
	        	templateUrl	: './modules/home/views/usuario/usuario-lista.html',
	        })
	        .state('usuario.detalhe', {
	        	url 		: "/detalhe/{id:[0-9]{1,10}}",
	        	templateUrl	: './modules/home/views/usuario/usuario-detalhe.html',
	        })
	        .state('usuario.novo', {
	        	url 		: "/novo",
	        	templateUrl	: './modules/home/views/usuario/usuario-formulario.html',
	        })
	        .state('usuario.editar', {
	        	url         : "/editar/{id:[0-9]{1,10}}",
	        	templateUrl	: './modules/home/views/usuario/usuario-formulario.html',
	        })
	        .state('profile',{
                url         : "/profile",
                controller  : 'profileController',
                templateUrl : "./modules/home/views/profile/profile-index.html"
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
	        })
	        .state('produto', {
	        	url 		: "/produto",
	        	templateUrl	: './modules/home/views/produto/produto-index.html',
	        	controller	: 'ProdutoController',
	        	abstract 	: true,
	        })
	        .state('produto.lista', {
	        	url 		: "/lista",
	        	templateUrl	: './modules/home/views/produto/produto-lista.html',
	        })
	        .state('produto.detalhe', {
	        	url 		: "/detalhe/{id:[0-9]{1,10}}",
	        	templateUrl	: './modules/home/views/produto/produto-detalhe.html',
	        })
	        .state('produto.novo', {
	        	url 		: "/novo",
	        	templateUrl	: './modules/home/views/produto/produto-formulario.html',
	        })
	        .state('produto.editar', {
	        	url         : "/editar/{id:[0-9]{1,10}}",
	        	templateUrl	: './modules/home/views/produto/produto-formulario.html',
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
		$rootScope.$usuario 	= $window.usuario;
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