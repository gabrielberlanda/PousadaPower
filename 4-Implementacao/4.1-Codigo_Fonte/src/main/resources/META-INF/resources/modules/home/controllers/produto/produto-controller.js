(function ( angular ) {
    'use strict';
    
/**
 * 
 * @param $scope
 * @param $state
 */
angular.module('home')
	   .controller('ProdutoController', function( $q, $rootScope, $scope, $state, $importService, $mdToast, $mdDialog, $mdSidenav, $translate, $timeout, $filter ) {

	   
		    $importService("produtoService");
		    $importService("fornecedorService");
		 
		    /*-------------------------------------------------------------------
		     *                          ATTRIBUTES
		     *-------------------------------------------------------------------*/
		    $scope.NEW_PRODUTO_STATE        = "produto.novo";
		    $scope.EDIT_PRODUTO_STATE      = "produto.editar";
		    $scope.DETAIL_PRODUTO_STATE   	 = "produto.detalhe";
		    $scope.LIST_PRODUTO_STATE     	 = "produto.lista";
		 
		     /**
		      *
		      */
		    $scope.model = {
		 
		        entity : {
		        	tipoProduto : "BEBIDA"
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
		            $scope.listProdutosByFilters ( $scope.model.query.filter.name );
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
		            case $scope.LIST_PRODUTO_STATE :
		                $scope.listProdutosByFilters( null );
		            break;
		            case $scope.EDIT_PRODUTO_STATE :
		                $scope.findProdutoById( toParams.id );
		            break;
		            case $scope.DETAIL_PRODUTO_STATE :
		                $scope.findProdutoById ( toParams.id );
		            break;
		        };
		 
		     });
		 
		     /**
		      *
		      * @param ev
		      * @param user
		      */
		     $scope.changeToEdit = function( ev, produto ) {
		         $state.go( $scope.EDIT_PRODUTO_STATE, {id: produto.id} );
		     }
		 
		     /**
		      *
		      * @param ev
		      * @param user
		      */
		     $scope.changeToDetail = function( ev, produto ) {
		         var tagName = ev.target.tagName.toLowerCase();
		         if ( tagName != "button" && tagName != "md-icon" ) {
		             $state.go( $scope.DETAIL_PRODUTO_STATE, {id: produto.id} );
		         }
		     }
		 
		     /**
		     *
		     */
		    $scope.listProdutosByFilters = function ( filter ) {
		 
		         var pageable = angular.copy($scope.model.pageRequest.pageable);
		         if ( pageable.page > 0 ) {
		             pageable.page = pageable.page -1;
		         }
		 
		         produtoService.listProdutosByFilters( filter, pageable, {
		             callback: function (result) {
		            	 console.log(result.content);
		            	 $scope.model.pageRequest.content = result.content; 

		                $scope.$apply();
		             }
		         });
		 
		     };
		 

		     $scope.listFornecedoresByFilters = function( filter ) {
		    	 var deferred = $q.defer();
		    	 
		         fornecedorService.listFornecedoresByFilters ( filter, null, {
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
		    $scope.findProdutoById = function ( id ) {
		         produtoService.findProdutoById( id, {
		             callback: function (result) {
		                 $scope.model.entity = result;
		                 $scope.$apply();
		             }
		         });
		 
		     };
		 
		     $scope.removeProduto = function( ev, produto ) {
		 
		         var confirmConfig = {
		             title   : "Remover produto",
		             content : "Você realmente deseja remover o produto " + produto.nome + "?",
		             ok      : "Remover"
		         }
		 
		         $rootScope.confirmDialog( ev, confirmConfig )
		 
		             .then(function() {
		            	 produtoService.removeProduto ( produto.id, {
		                     callback: function ( result ){
		                         $rootScope.toast("Produto removido com sucesso!", "green");
		                         if ( $state.current.name == $scope.DETAIL_PRODUTO_STATE ) {
		                             $state.go( $scope.LIST_PRODUTO_STATE );
		                         }
		                         if ( $scope.model.pageRequest.content.indexOf(produto) > -1 ) {
		                             $scope.model.pageRequest.content.splice( $scope.model.pageRequest.content.indexOf(produto), 1);
		 
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
		     $scope.save = function( produto ){
		    	 var produtoForm = angular.element( document.querySelector('#produtoForm') ).scope()['produtoForm'];
		    	 if ( produto && produtoForm.$valid ) {
		             if ( !produto.id ) {
		            	 produtoService.insertProduto ( produto, {
		                     callback: function ( result ) {
		                         $rootScope.toast("Produto inserido com sucesso!", "green");
		                         $state.go ( $scope.LIST_PRODUTO_STATE );
		                         $scope.$apply ();
		                     }, errorHandler: function ( message, exception ) {
		                             $rootScope.toast(message, "red");
		                     }
		                 })
		             }else {
		            	 produtoService.updateProduto( produto, {
		                     callback: function ( result ) {
		                         $rootScope.toast("As informações do produto foram atualizadas", "green");
		                         $state.go ( $scope.LIST_PRODUTO_STATE );
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
		        $scope.listProdutosByFilters ( $scope.model.query.filter.name );
		    };
		 
		 
		    $scope.onPaginationChange = function (page, limit) {
		        $scope.model.pageRequest.pageable.page = page ;
		        $scope.model.pageRequest.pageable.size = limit;
		    };
		 
});

}(window.angular));