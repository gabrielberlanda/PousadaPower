(function ( angular ) {
    'use strict';
    
/**
 * 
 * @param $scope
 * @param $state
 */
angular.module('home')
	   .controller('HospedeController', function( $rootScope, $scope, $state, $importService, $mdToast, $mdDialog, $mdSidenav ) {

	   
		    $importService("pessoaService");
		 
		    /*-------------------------------------------------------------------
		     *                          ATTRIBUTES
		     *-------------------------------------------------------------------*/
		    
		    $scope.NOVO_HOSPEDE_STATE        = "hospede.novo";
		    $scope.EDITAR_HOSPEDE_STATE      = "hospede.editar";
		    $scope.DETALHE_HOSPEDE_STATE   	 = "hospede.detalhe";
		    $scope.LISTA_HOSPEDE_STATE     	 = "hospede.lista";
		 
		     /**
		      *
		      */
		    $scope.model = {
		 
		        hospede : {
		            
		        },
		 
		        query : {
		            filter  : {name : null,
		                status : "",
		            }
		        },
		 
		        pageRequest : {//PageImpl
		            content : null,
		            pageable : {//PageRequest
		                page : 1,
		                size : 15,
		                sort : {
		                    direction: 'ASC', properties: ['id']
		                },
		            }
		        }
		    };
		 
		    /**
		     *
		     */
		    $scope.$watch( "[model.pageRequest.pageable.size, model.pageRequest.pageable.page]" , function( value, oldValue ){
		        if( $scope.model.pageRequest.content ) {
		            $scope.listUsersByFilters ( $scope.model.query.filter.name, $scope.model.query.filter.status);
		        }
		    }, true);
		 
		    /*-------------------------------------------------------------------
		     *                            HANDLERS
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
		 
		        $scope.model.hospede = {

		        };
		 
		        switch( toState.name ){
		            case $scope.LISTA_HOSPEDE_STATE :
		                $scope.listHospedesByFilters( null, null );
		            break;
		            case $scope.EDITAR_HOSPEDE_STATE :
		                $scope.findHospedeById( toParams.id );
		            break;
		            case $scope.DETALHE_HOSPEDE_STATE :
		                $scope.findHospedeById ( toParams.id );
		            break;
		        };
		 
		     });
		 
		     /**
		      *
		      * @param ev
		      * @param user
		      */
		     $scope.changeToEdit = function( ev, hospede ) {
		         $state.go( $scope.EDITAR_HOSPEDE_STATE, {id: hospede.id} );
		     }
		 
		     /**
		      *
		      * @param ev
		      * @param user
		      */
		     $scope.changeToDetail = function( ev, hospede ) {
		         var tagName = ev.target.tagName.toLowerCase();
		         if ( tagName != "button" && tagName != "md-icon" ) {
		             $state.go( $scope.DETALHE_HOSPEDE_STATE, {id: hospede.id} );
		         }
		     }
		 
		     /**
		     *
		     */
		    $scope.listHospedesByFilters = function ( filter, status ) {
		    	alert('listagem de hospede');
//		        //TODO conseguir setar o valor null pelo select da interface
//		        if ( status == "" ) {
//		            status = null;
//		        }
//		 
//		         var pageable = angular.copy($scope.model.pageRequest.pageable);
//		         if ( pageable.page > 0 ) {
//		             pageable.page = pageable.page -1;
//		         }
//		 
//		         accountService.listUsersByFilters( filter, status, pageable, {
//		             callback: function (result) {
//		                 $scope.model.pageRequest = {//PageImpl
//		                    content : result.content,
//		                    total   : result.total,
//		                    pageable : {//PageRequest
//		                        page : result.pageable.page+1,
//		                        size : result.pageable.size,
//		                        sort : {//Sort
//		                            direction: result.pageable.sort.orders[0].direction, properties: [ result.pageable.sort.orders[0].property ]
//		                        },
//		                    }
//		                };
//		                  
//		                // Retorna um page se o content for 0 e possuir mais de 1 page, isto ocorre quando é excluido o ultimo item do page na tela de detalhe
//		                if( $scope.model.pageRequest.content.length == 0 && $scope.model.pageRequest.pageable.page > 0 ) {
//		                    $scope.model.pageRequest.pageable.page--;
//		                } 
//		                //FIXME Dwr não dispara evento de finização da chamada
//		                $rootScope.progress(false);
//		                $scope.$apply();
//		             }
//		         });
		 
		     };
		 
		 
		 
		     /**
		     *
		     */
		    $scope.findHospedeById = function ( id ) {
		    	alert("find by id");
//		         accountService.findUserById( id, {
//		             callback: function (result) {
//		                 $scope.model.entity = result;
//		                 $scope.model.entity.newPassword = null;
//		                 $scope.$apply();
//		             }
//		         });
		 
		     };
		 
		     $scope.removerHospede = function( ev, user ) {
		 
		         var confirmConfig = {
		             title   : $translate("removeUser"),
		             content : $translate("user.removeUserConfirm", user.name),
		             ok      : $translate("remove")
		         }
		 
		         $rootScope.confirmDialog( ev, confirmConfig )
		 
		             .then(function() {
		                 accountService.removeUser ( user.id, {
		                     callback: function ( result ){
		                         $rootScope.toast($translate("user.removeUserSuccess"), "green");
		                         if ( $state.current.name == $scope.DETAIL_USER_STATE ) {
		                             $state.go( $scope.LIST_USER_STATE );
		                         }
		                         if ( $scope.model.pageRequest.content.indexOf(user) > -1 ) {
		                             $scope.model.pageRequest.content.splice( $scope.model.pageRequest.content.indexOf(user), 1);
		 
		                             $scope.model.pageRequest.total--;
		                             if( $scope.model.pageRequest.content.length == 0 ) {
		                                 $scope.model.pageRequest.pageable.page--;
		                             }
		                         }
		                         $scope.$apply();
		 
		                     }
		                 } )
		             }, function() {
		             });
		     }
		 
		     /**
		      *
		      */
		     $scope.save = function( user ){
		 
		         if ( user ) {
		             if ( !user.id ) {
		                 accountService.insertUser ( user, {
		                     callback: function ( result ) {
		                         $rootScope.toast($translate("user.SaveUserSuccess"), "green");
		                         $state.go ( $scope.LIST_USER_STATE );
		                         $scope.$apply ();
		                     }, errorHandler: function ( message, exception ) {
		                             $rootScope.toast($translate("emailUniqueViolation"), "red");
		                             document.getElementById("email").focus();
		                     }
		                 })
		             }else {
		                 accountService.updateUserByAdmin( user.id, user.name, user.email, user.newPassword, user.role, user.language, {
		                     callback: function ( result ) {
		                         $rootScope.toast($translate("user.updateSuccess"), "green");
		                         $state.go ( $scope.LIST_USER_STATE );
		                         $scope.$apply ();
		                     }
		                 })
		 
		             }
		         }else {
		             $rootScope.toast( $translate("user.invalidUser"), "red" );
		         }
		     }
		 
		    /**
		     *
		     */
		    $scope.onOrderChange = function (order) {
		        if ( order[0] == "-" ) {
		            order = order.replace("-","");
		            $scope.model.pageRequest.pageable.sort.direction = "DESC";
		        } else {
		            $scope.model.pageRequest.pageable.sort.direction = "ASC";
		        }
		 
		        $scope.model.pageRequest.pageable.sort.properties[0] = order;
		        $scope.listUsersByFilters ( $scope.model.query.filter.name, $scope.model.query.filter.status );
		    };
		 
		 
		    $scope.onPaginationChange = function (page, limit) {
		        $scope.model.pageRequest.pageable.page = page ;
		        $scope.model.pageRequest.pageable.size = limit;
		    };
		 
});

}(window.angular));