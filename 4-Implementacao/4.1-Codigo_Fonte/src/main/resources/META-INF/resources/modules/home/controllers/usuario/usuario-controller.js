(function ( angular ) {
    'use strict';
    
/**
 * 
 * @param $scope
 * @param $state
 */
angular.module('home')
	   .controller('UsuarioController', function( $q, $rootScope, $scope, $state, $importService, $mdToast, $mdDialog, $mdSidenav, $translate, $timeout, $filter ) {

	   
		    $importService("accountService");
		    $importService("enderecoService");
		 
		    /*-------------------------------------------------------------------
		     *                          ATTRIBUTES
		     *-------------------------------------------------------------------*/
		    
		    $scope.NEW_USUARIO_STATE        = "usuario.novo";
		    $scope.EDIT_USUARIO_STATE      = "usuario.editar";
		    $scope.DETAIL_USUARIO_STATE   	 = "usuario.detalhe";
		    $scope.LIST_USUARIO_STATE     	 = "usuario.lista";
		 
		     /**
		      *
		      */
		    $scope.model = {
		 
		        entity : {
		        	role : "RECEPCIONISTA",
		            
		        },
		        
		        changePassword : false,
		        
		        query : {
		            filter  : {
		            	name : null,
		            }
		        },
		        pageRequest: {//PageImpl 
		           	content: [],
		           	pageable :{ size: 9,
		           	page: 0,
		               	sort:null
		               	}
		           },
	           sort: [{//Sort
	               	direction: 'ASC', properties: 'id', nullHandlingHint:null
               	}],

		    };
		 
		    /**
		     *
		     */
		    $scope.$watch( "[model.pageRequest.pageable.size, model.pageRequest.pageable.page]" , function( value, oldValue ){
		        if( $scope.model.pageRequest.content ) {
		            $scope.listUsersByFilters ( $scope.model.query.filter.name );
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
		 
		        $scope.model.entity = {
		        		role: "RECEPCIONISTA"
		        };
		        $scope.model.changePassword = false;
		        
		        switch( toState.name ){
		            case $scope.LIST_USUARIO_STATE :
		                $scope.listUsersByFilters( null );
		            break;
		            case $scope.EDIT_USUARIO_STATE :
		                $scope.findUserById( toParams.id );
		            break;
		            case $scope.DETAIL_USUARIO_STATE :
		                $scope.findUserById ( toParams.id );
		            break;
		        };
		 
		     });
		 
		     /**
		      *
		      * @param ev
		      * @param user
		      */
		     $scope.changeToEdit = function( ev, usuario ) {
		         $state.go( $scope.EDIT_USUARIO_STATE, {id: usuario.id} );
		     }
		 
		     /**
		      *
		      * @param ev
		      * @param user
		      */
		     $scope.changeToDetail = function( ev, usuario ) {
		         var tagName = ev.target.tagName.toLowerCase();
		         if ( tagName != "button" && tagName != "md-icon" ) {
		             $state.go( $scope.DETAIL_USUARIO_STATE, {id: usuario.id} );
		         }
		     }
		 
		     /**
		     *
		     */
		    $scope.listUsersByFilters = function ( filter ) {
		 
		         var pageable = angular.copy($scope.model.pageRequest.pageable);
		         if ( pageable.page > 0 ) {
		             pageable.page = pageable.page -1;
		         }
		 
		         accountService.listUsersByFilters( filter, pageable, {
		             callback: function (result) {
		            	 console.log(result.content);
		            	 $scope.model.pageRequest.content = result.content; 
		                  
//		                // Retorna um page se o content for 0 e possuir mais de 1 page, isto ocorre quando é excluido o ultimo item do page na tela de detalhe
//		                if( $scope.model.pageRequest.content.length == 0 && $scope.model.pageRequest.pageable.page > 0 ) {
//		                    $scope.model.pageRequest.pageable.page--;
//		                } 
		                //FIXME Dwr não dispara evento de finização da chamada
		                $scope.$apply();
		             }
		         });
		 
		     };
		 
		     /**
		     *
		     */
		    $scope.findUserById = function ( id ) {
		         accountService.findUserById( id, {
		             callback: function (result) {
		                 $scope.model.entity = result;
		                 $scope.model.entity.password = "";
		                 $scope.$apply();
		             }
		         });
		 
		     };
		 
		     $scope.removeHospede = function( ev, hospede ) {
		 
		         var confirmConfig = {
		             title   : "Remover Hospede",
		             content : "Você realmente deseja remover o hospede " + hospede.nome + "?",
		             ok      : "Remover"
		         }
		 
		         $rootScope.confirmDialog( ev, confirmConfig )
		 
		             .then(function() {
		            	 pessoaService.removeHospede ( hospede.id, {
		                     callback: function ( result ){
		                         $rootScope.toast("hospede removido com sucesso!", "green");
		                         if ( $state.current.name == $scope.DETAIL_HOSPEDE_STATE ) {
		                             $state.go( $scope.LIST_HOSPEDE_STATE );
		                         }
		                         if ( $scope.model.pageRequest.content.indexOf(hospede) > -1 ) {
		                             $scope.model.pageRequest.content.splice( $scope.model.pageRequest.content.indexOf(hospede), 1);
		 
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
		     $scope.save = function( usuario ){
		    	 var usuarioForm = angular.element( document.querySelector('#usuarioForm') ).scope()['usuarioForm'];
		    	 if ( usuario && usuarioForm.$valid ) {
		             if ( !usuario.id ) {
		            	 accountService.insertUser ( usuario, {
		                     callback: function ( result ) {
		                         $rootScope.toast("Usuário inserido com sucesso!", "green");
		                         $state.go ( $scope.LIST_USUARIO_STATE );
		                         $scope.$apply ();
		                     }, errorHandler: function ( message, exception ) {
		                             $rootScope.toast(message, "red");
		                     }
		                 })
		             }else {
		            	 if ( !$scope.model.changePassword ) {
		            		 usuario.password = null;
		            		 usuario.confirmPassword = null;
		            	 } 
		            	 
		            	 if ( $scope.model.changePassword && ( usuario.password && !usuario.confirmPassword ) )
	            		 {
		            		 $rootScope.toast("Preencha as senhas")
	            		 } else {
	            			 
	            			 accountService.updateUserByAdmin( usuario.id, usuario.name, usuario.email, usuario.role, usuario.enabled, usuario.password, usuario.confirmPassword, {
	            				 callback: function ( result ) {
	            					 $rootScope.toast("As informações do usuário foram atualizadas", "green");
	            					 $state.go ( $scope.LIST_USUARIO_STATE );
	            					 $scope.$apply ();
	            				 }
	            			 })
	            			 
	            		 }
		            	 
		            	
		             } 
		         }else {
		             $rootScope.toast( "Formulário inválido!", "red" );
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
		        $scope.listUsersByFilters ( $scope.model.query.filter.name );
		    };

		    
		    /**
		     * 
		     */
		    $scope.activateUser = function( ev, usuario ) {
				 
		         var confirmConfig = {
		             title   : "Ativar Usuário",
		             content : "Você realmente deseja ativar o usuário " + usuario.name + "?",
		             ok      : "Ativar"
		         }
		 
		         $rootScope.confirmDialog( ev, confirmConfig )
		 
		             .then(function() {
		 		    	accountService.activateUser( usuario.id, {
				    		callback : function ( result ) {
				    			$rootScope.toast("Usuário ativo com sucesso!", "green");
		        				 if ( $state.current.name == $scope.LIST_USUARIO_STATE	) {
		        					 $scope.model.pageRequest.content[ $scope.model.pageRequest.content.indexOf(usuario) ].enabled = true;
		        				 }else {
		        					 $scope.model.entity.enabled = true;
		        				 }
				    			$scope.$apply();
				    		}
				    	})
		             }, function() {
		             });
		     }
		    
		    /**
		     * 
		     */
		    $scope.deactivateUser = function( ev, usuario ) {
				 
		         var confirmConfig = {
		             title   : "Desativar Usuário",
		             content : "Você realmente deseja desativar o usuário " + usuario.name + "?",
		             ok      : "Desativar"
		         }
		 
		         $rootScope.confirmDialog( ev, confirmConfig )
		 
		             .then(function() {
		 		    	accountService.deactivateUser( usuario.id, {
				    		callback : function ( result ) {
				    			$rootScope.toast("Usuário desativado com sucesso!", "green");

		        				 if ( $state.current.name == $scope.LIST_USUARIO_STATE	) {
		        					 $scope.model.pageRequest.content[ $scope.model.pageRequest.content.indexOf(usuario) ].enabled = false;
		        				 }else {
		        					 $scope.model.entity.enabled = false;
		        				 }

				    			$scope.$apply();
				    		}
				    	})
		             }, function() {
		             });
		     }
		    
		    $scope.statusBind = function ( status ) {
		    	if ( status ) { 
		    		return "Ativo";
		    	} else {
		    		return "Desativado";
		    	}
		    }
		    
		    $scope.onPaginationChange = function (page, limit) {
		        $scope.model.pageRequest.pageable.page = page ;
		        $scope.model.pageRequest.pageable.size = limit;
		    };
		 
});

}(window.angular));