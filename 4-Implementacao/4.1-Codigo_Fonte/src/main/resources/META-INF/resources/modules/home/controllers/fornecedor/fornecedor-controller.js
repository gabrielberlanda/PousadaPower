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
		 
		     $scope.removeFornecedor = function( ev, fornecedor ) {
		 
		         var confirmConfig = {
		             title   : "Remover Fornecedor",
		             content : "Você realmente deseja remover o fornecedor " + fornecedor.nomeFantasia + "?",
		             ok      : "Remover"
		         }
		 
		         $rootScope.confirmDialog( ev, confirmConfig )
		 
		             .then(function() {
		            	 fornecedorService.removeFornecedor ( fornecedor.id, {
		                     callback: function ( result ){
		                         $rootScope.toast("Fornecedor removido com sucesso!", "green");
		                         if ( $state.current.name == $scope.DETAIL_FORNECEDOR_STATE ) {
		                             $state.go( $scope.LIST_FORNECEDOR_STATE );
		                         }
		                         if ( $scope.model.pageRequest.content.indexOf(fornecedor) > -1 ) {
		                             $scope.model.pageRequest.content.splice( $scope.model.pageRequest.content.indexOf(fornecedor), 1);
		 
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
		 
		     $scope.validarCNPJ = function ( cnpj ) {
		          
		         cnpj = cnpj.replace(/[^\d]+/g,'');
		       
		         var fornecedorForm = angular.element( document.querySelector('#fornecedorForm') ).scope()['fornecedorForm'];
		          
		         var input = fornecedorForm.cnpj;
		         
		         if(cnpj == '') {
		             input.$setValidity( "cnpj", false );
		             return false;
		         }
		           
		         if (cnpj.length != 14) {
		             input.$setValidity( "cnpj", false );            
		             return false;
		         }
		       
		         // Elimina CNPJs invalidos conhecidos
		         if (cnpj == "00000000000000" || 
		             cnpj == "11111111111111" || 
		             cnpj == "22222222222222" || 
		             cnpj == "33333333333333" || 
		             cnpj == "44444444444444" || 
		             cnpj == "55555555555555" || 
		             cnpj == "66666666666666" || 
		             cnpj == "77777777777777" || 
		             cnpj == "88888888888888" || 
		             cnpj == "99999999999999") {
		             input.$setValidity( "cnpj", false );
		             return false;
		         }
		               
		         // Valida DVs
		         var tamanho = cnpj.length - 2;
		         var numeros = cnpj.substring(0,tamanho);
		         var digitos = cnpj.substring(tamanho);
		         var soma = 0;
		         var pos = tamanho - 7;
		         for (var i = tamanho; i >= 1; i--) {
		           soma += numeros.charAt(tamanho - i) * pos--;
		           if (pos < 2)
		                 pos = 9;
		         }
		         var resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
		         if (resultado != digitos.charAt(0)) {
		             input.$setValidity( "cnpj", false );
		             return false;
		         }
		               
		         tamanho = tamanho + 1;
		         numeros = cnpj.substring(0,tamanho);
		         soma = 0;
		         pos = tamanho - 7;
		         for (var i = tamanho; i >= 1; i--) {
		           soma += numeros.charAt(tamanho - i) * pos--;
		           if (pos < 2)
		                 pos = 9;
		         }
		         resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
		         if (resultado != digitos.charAt(1)) {
		             input.$setValidity( "cnpj", false );
		             return false;
		         }
		         input.$setValidity( "cnpj", true );       
		         return true;
		          
		     }

		     
		     /**
		      *
		      */
		     $scope.save = function( fornecedor ){
		    	 var fornecedorForm = angular.element( document.querySelector('#fornecedorForm') ).scope()['fornecedorForm'];
		    	 if ( fornecedor && fornecedorForm.$valid ) {
		             if ( !fornecedor.id ) {
		            	 fornecedorService.insertFornecedor ( fornecedor, {
		                     callback: function ( result ) {
		                         $rootScope.toast("Fornecedor inserido com sucesso!", "green");
		                         $state.go ( $scope.LIST_FORNECEDOR_STATE );
		                         $scope.$apply ();
		                     }, errorHandler: function ( message, exception ) {
		                             $rootScope.toast(message, "red");
		                     }
		                 })
		             }else {
		            	 fornecedorService.updateFornecedor( fornecedor, {
		                     callback: function ( result ) {
		                         $rootScope.toast("As informações do fornecedor foram atualizadas", "green");
		                         $state.go ( $scope.LIST_FORNECEDOR_STATE );
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