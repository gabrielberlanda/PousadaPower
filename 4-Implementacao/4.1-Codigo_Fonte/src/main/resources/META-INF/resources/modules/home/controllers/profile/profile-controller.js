(function ( angular ) {
    'use strict';

/**
 *
 * @param $scope
 * @param $state
 */
angular.module('home')
	   .controller('profileController', [ "$scope", "$rootScope", "$http", "$mdToast", "$window", "$importService", "$translate", function( $scope, $rootScope, $http, $mdToast, $window, $importService, $translate ) {

	$importService("accountService");

    /*-------------------------------------------------------------------
     * 		 				 	ATTRIBUTES
     *-------------------------------------------------------------------*/

	/**
	 *
	 */
	$scope.model = {

		entity	: {
			currentPassword : null,
			password : null,
			confirmPassword : null
		},

		query : {
			filter	: {
				name	: null,
				status : 0
			}
		},

	};


    /*-------------------------------------------------------------------
     * 		 				 	  HANDLERS
     *-------------------------------------------------------------------*/

    /**
    *
    * @param event,
    * @param toState,
    * @param toParams,
    * @param fromState,
    * @param fromParams
    */
   $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
 		$scope.findUserById( $rootScope.$usuario.id );
   });

	/**
    *
    * @param password,
    * @param confirmPassword,
    * @param field
    */
     $scope.validPassword = function( password, confirmPassword, field ) {
    	 angular.element(eval(field)).scope().$eval(field).$setValidity( 'passwordDoesntMatch', password == confirmPassword );
     }


    /**
     *
     * @param id
     */
    $scope.findUserById = function ( id ) {
		 accountService.findUserById( id, {
			 callback: function (result) {
				 $scope.model.entity = result;
				 $scope.model.entity.password = null;
				 $scope.$apply();
			 }
		 });

	 };


	/**
	 * @param user
	 */
    $scope.updateUser = function( user ) {
    	var profileForm = angular.element( document.querySelector('#profileForm') ).scope()['profileForm'];
    	if( profileForm.$valid ) {
    		 accountService.updateProfile( user.id, user.name, user.email, user.currentPassword, user.password, user.confirmPassword, {
	    		 callback: function ( result ) {
	    			 $rootScope.toast( "As informações foram atualizadas", "green");
	    			 $scope.model.changePassword = false;
	    			 $scope.model.entity.currentPassword 	 = null;
	    			 $scope.model.entity.password 	 = null;
	    			 $scope.model.entity.confirmPassword = null;
	    			 $scope.$apply ();
	    		 }
			 });
    	} else {
    		$rootScope.toast( "Formulário inválido" ,"red");
    	}
    };

}]);

}(window.angular));
