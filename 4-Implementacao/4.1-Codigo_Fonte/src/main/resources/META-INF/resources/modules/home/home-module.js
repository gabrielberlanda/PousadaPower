(function(window, angular, undefined) {
	'use strict';

	//Start the AngularJS
	var module = angular.module('home', ['ngMessages', 'ngSanitize', 'ngMaterial', 'ui.router', 'eits-md', 'eits-ng', 'md.data.table', 'ui.mask' ]);

	/**
	 * 
	 */
	module.config( function( $stateProvider, $urlRouterProvider, $importServiceProvider, $translateProvider, $mdDateLocaleProvider ) {
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
	        })
	        .state('fornecedor', {
	        	url 		: "/fornecedor",
	        	templateUrl	: './modules/home/views/fornecedor/fornecedor-index.html',
	        	controller	: 'FornecedorController',
	        	abstract 	: true,
	        })
	        .state('fornecedor.lista', {
	        	url 		: "/lista",
	        	templateUrl	: './modules/home/views/fornecedor/fornecedor-lista.html',
	        })
	        .state('fornecedor.detalhe', {
	        	url 		: "/detalhe/{id:[0-9]{1,10}}",
	        	templateUrl	: './modules/home/views/fornecedor/fornecedor-detalhe.html',
	        })
	        .state('fornecedor.novo', {
	        	url 		: "/novo",
	        	templateUrl	: './modules/home/views/fornecedor/fornecedor-formulario.html',
	        })
	        .state('fornecedor.editar', {
	        	url         : "/editar/{id:[0-9]{1,10}}",
	        	templateUrl	: './modules/home/views/fornecedor/fornecedor-formulario.html',
	        })
	        .state('tipoQuarto', {
	        	url 		: "/tipo-quarto",
	        	templateUrl	: './modules/home/views/tipo-quarto/tipo-quarto-index.html',
	        	controller	: 'TipoQuartoController',
	        	abstract 	: true,
	        })
	        .state('tipoQuarto.lista', {
	        	url 		: "/lista",
	        	templateUrl	: './modules/home/views/tipo-quarto/tipo-quarto-lista.html',
	        })
	        .state('tipoQuarto.detalhe', {
	        	url 		: "/detalhe/{id:[0-9]{1,10}}",
	        	templateUrl	: './modules/home/views/tipo-quarto/tipo-quarto-detalhe.html',
	        })
	        .state('tipoQuarto.novo', {
	        	url 		: "/novo",
	        	templateUrl	: './modules/home/views/tipo-quarto/tipo-quarto-formulario.html',
	        })
	        .state('tipoQuarto.editar', {
	        	url         : "/editar/{id:[0-9]{1,10}}",
	        	templateUrl	: './modules/home/views/tipo-quarto/tipo-quarto-formulario.html',
	        });
        
        $mdDateLocaleProvider.formatDate = function(date) {
   	     var m = moment(date);
   	     return m.isValid() ? m.format('L') : '';
       };

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