(function ( angular ) {
    'use strict';
    
/**
 * 
 * @param $scope
 * @param $state
 */
angular.module('home')
	   .controller('FornecedorController', function( $q, $rootScope, $scope, $state, $importService, $mdToast, $mdDialog, $mdSidenav, $translate, $timeout, $filter ) {

	   
		    $importService("fornecedorService");
		    $importService("enderecoService");
		 
		    /*-------------------------------------------------------------------
		     *                          ATTRIBUTES
		     *-------------------------------------------------------------------*/
		    
		    $scope.NEW_FORNECEDOR_STATE         = "fornecedor.novo";
		    $scope.EDIT_FORNECEDOR_STATE     	 = "fornecedor.editar";
		    $scope.DETAIL_FORNECEDOR_STATE   	 = "fornecedor.detalhe";
		    $scope.LIST_FORNECEDOR_STATE     	 = "fornecedor.lista";
		 
		     /**
		      *
		      */
		    $scope.model = {
		 
		        entity : {
		            
		        },
		 
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
		            $scope.listFornecedoresByFilters ( $scope.model.query.filter.name );
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

		        };
		 
		        switch( toState.name ){
		            case $scope.LIST_FORNECEDOR_STATE :
		                $scope.listFornecedoresByFilters( null );
		            break;
		            case $scope.EDIT_FORNECEDOR_STATE :
		                $scope.findFornecedorById( toParams.id );
		            break;
		            case $scope.DETAIL_FORNECEDOR_STATE :
		                $scope.findFornecedorById ( toParams.id );
		            break;
		        };
		 
		     });
		 
		     /**
		      *
		      * @param ev
		      * @param user
		      */
		     $scope.changeToEdit = function( ev, fornecedor ) {
		         $state.go( $scope.EDIT_FORNECEDOR_STATE, {id: fornecedor.id} );
		     }
		 
		     /**
		      *
		      * @param ev
		      * @param user
		      */
		     $scope.changeToDetail = function( ev, fornecedor ) {
		         var tagName = ev.target.tagName.toLowerCase();
		         if ( tagName != "button" && tagName != "md-icon" ) {
		             $state.go( $scope.DETAIL_FORNECEDOR_STATE, {id: fornecedor.id} );
		         }
		     }
		 
		     /**
		     *
		     */
		    $scope.listFornecedoresByFilters = function ( filter ) {
		 
		         var pageable = angular.copy($scope.model.pageRequest.pageable);
		         if ( pageable.page > 0 ) {
		             pageable.page = pageable.page -1;
		         }
		 
		         fornecedorService.listFornecedoresByFilters( filter, pageable, {
		             callback: function (result) {
		            	 $scope.model.pageRequest.content = result.content; 

		                $scope.$apply();
		             }
		         });
		 
		     };
		 
		     /**
		      * 
		      */
		     $scope.listPaisByFilters = function( filter ) {
		    	 var deferred = $q.defer();
		    	 
		         enderecoService.listPaisByFilters ( filter, null, {
		        	 callback : function( result ) { 
		    			 deferred.resolve(result.content); 
		    			 $scope.$apply(); 
		    		}, errorHandler : function(message, exception) { 
		    			deferred.reject(message); 
		    			$scope.$apply(); 
		    			} 
		    		}); 
		    	 return deferred.promise; 
		     }
		     
		     /**
		      * 
		      */
		     $scope.listEstadoByFilters = function( filter, paisId ) {
		    	 var deferred = $q.defer();
		    	 
		         enderecoService.listEstadoByFilters ( filter, paisId, null, {
		        	 callback : function( result ) { 
		    			 deferred.resolve(result.content); 
		    			 $scope.$apply(); 
		    		}, errorHandler : function(message, exception) { 
		    			deferred.reject(message); 
		    			$scope.$apply(); 
		    			} 
		    		}); 
		    	 return deferred.promise; 
		     }
		     
		     /**
		      * 
		      */
		     $scope.listCidadeByFilters = function( filter, estadoId ) {
		    	 var deferred = $q.defer();
		    	 
		         enderecoService.listCidadeByFilters ( filter, estadoId, null, {
		        	 callback : function( result ) { 
		    			 deferred.resolve(result.content); 
		    			 $scope.$apply(); 
		    		}, errorHandler : function(message, exception) { 
		    			deferred.reject(message); 
		    			$scope.$apply(); 
		    			} 
		    		}); 
		    	 return deferred.promise; 
		     }

		     /**
		     *
		     */
		    $scope.findFornecedorById = function ( id ) {
		    	fornecedorService.findFornecedorById( id, {
		             callback: function (result) {
		                 $scope.model.entity = result;
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
		     $scope.save = function( hospede ){
		    	 var hospedeForm = angular.element( document.querySelector('#hospedeForm') ).scope()['hospedeForm'];
		    	 if ( hospede && hospedeForm.$valid ) {
		             if ( !hospede.id ) {
		            	 pessoaService.insertHospede ( hospede, {
		                     callback: function ( result ) {
		                         $rootScope.toast("Hospede inserido com sucesso!", "green");
		                         $state.go ( $scope.LIST_HOSPEDE_STATE );
		                         $scope.$apply ();
		                     }, errorHandler: function ( message, exception ) {
		                             $rootScope.toast(message, "red");
		                     }
		                 })
		             }else {
		            	 pessoaService.updateHospede( hospede, {
		                     callback: function ( result ) {
		                         $rootScope.toast("As informações do hospede foram atualizadas", "green");
		                         $state.go ( $scope.LIST_HOSPEDE_STATE );
		                         $scope.$apply ();
		                     }
		                 })
		 
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
		        $scope.listFornecedoresByFilters ( $scope.model.query.filter.name );
		    };
		 
		 
		    $scope.onPaginationChange = function (page, limit) {
		        $scope.model.pageRequest.pageable.page = page ;
		        $scope.model.pageRequest.pageable.size = limit;
		    };
		 
});

}(window.angular));