(function ( angular ) {
    'use strict';
    
/**
 * 
 * @param $scope
 * @param $state
 */
angular.module('home')
	   .controller('TipoQuartoController', function( $q, $rootScope, $scope, $state, $importService, $mdToast, $mdDialog, $mdSidenav, $translate, $timeout, $filter ) {

	   
		    $importService("tipoQuartoService");
		 
		    /*-------------------------------------------------------------------
		     *                          ATTRIBUTES
		     *-------------------------------------------------------------------*/
		    
		    $scope.NEW_TIPO_QUARTO_STATE         = "tipoQuarto.novo";
		    $scope.EDIT_TIPO_QUARTO_STATE     	 = "tipoQuarto.editar";
		    $scope.DETAIL_TIPO_QUARTO_STATE   	 = "tipoQuarto.detalhe";
		    $scope.LIST_TIPO_QUARTO_STATE     	 = "tipoQuarto.lista";
		 
		     /**
		      *
		      */
		    $scope.model = {
		 
		        entity : {
		        	nome : "",
		        	ocupacaoMaxima: null,
		        	observacao:"",
		        	tarifasPadrao: [
		        	                {
		        	                	dia: "SEGUNDA",
		        	                	preco: null,
		        	                },
		        	                {
		        	                	dia: "TERCA",
		        	                	preco: null,
		        	                },
		        	                {
		        	                	dia: "QUARTA",
		        	                	preco: null,
		        	                },
		        	                {
		        	                	dia: "QUINTA",
		        	                	preco: null,
		        	                },
		        	                {
		        	                	dia: "SEXTA",
		        	                	preco: null,
		        	                },
		        	                {
		        	                	dia: "SABADO",
		        	                	preco: null,
		        	                },
		        	                {
		        	                	dia: "DOMINGO",
		        	                	preco: null,
		        	                },
		        	                
		        	                ],
		            
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
		            $scope.listTipoQuartosByFilters ( $scope.model.query.filter.name );
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
			        	nome : "",
			        	ocupacaoMaxima: null,
			        	observacao:"",
			        	tarifasPadrao: [
			        	                {
			        	                	dia: "SEGUNDA",
			        	                	preco: null,
			        	                },
			        	                {
			        	                	dia: "TERCA",
			        	                	preco: null,
			        	                },
			        	                {
			        	                	dia: "QUARTA",
			        	                	preco: null,
			        	                },
			        	                {
			        	                	dia: "QUINTA",
			        	                	preco: null,
			        	                },
			        	                {
			        	                	dia: "SEXTA",
			        	                	preco: null,
			        	                },
			        	                {
			        	                	dia: "SABADO",
			        	                	preco: null,
			        	                },
			        	                {
			        	                	dia: "DOMINGO",
			        	                	preco: null,
			        	                },
			        	                
			        	                ],
			        	
		        };
		 
		        switch( toState.name ){
		            case $scope.LIST_TIPO_QUARTO_STATE :
		                $scope.listTipoQuartosByFilters( null );
		            break;
		            case $scope.EDIT_TIPO_QUARTO_STATE :
		                $scope.findTipoQuartoById( toParams.id );
		            break;
		            case $scope.DETAIL_TIPO_QUARTO_STATE :
		                $scope.findTipoQuartoById ( toParams.id );
		            break;
		        };
		 
		     });
		 
		     /**
		      *
		      * @param ev
		      * @param user
		      */
		     $scope.changeToEdit = function( ev, tipoQuarto ) {
		         $state.go( $scope.EDIT_TIPO_QUARTO_STATE, {id: tipoQuarto.id} );
		     }
		 
		     /**
		      *
		      * @param ev
		      * @param user
		      */
		     $scope.changeToDetail = function( ev, tipoQuarto ) {
		         var tagName = ev.target.tagName.toLowerCase();
		         if ( tagName != "button" && tagName != "md-icon" ) {
		             $state.go( $scope.DETAIL_TIPO_QUARTO_STATE, {id: tipoQuarto.id} );
		         }
		     }
		 
		     /**
		     *
		     */
		    $scope.listTipoQuartosByFilters = function ( filter ) {
		 
		         var pageable = angular.copy($scope.model.pageRequest.pageable);
		         if ( pageable.page > 0 ) {
		             pageable.page = pageable.page -1;
		         }
		 
		         tipoQuartoService.listTipoQuartosByFilters( filter, pageable, {
		             callback: function (result) {
		            	 $scope.model.pageRequest.content = result.content; 

		                $scope.$apply();
		             }
		         });
		 
		     };

		     function ordenarTarifaPadrao ( tarifas )
		     {
		    	 angular.forEach( tarifas, function ( tarifa, key ){
		    		switch (tarifa.dia) {
					case "SEGUNDA":
						tarifa.indice = 0;
						break;
					case "TERCA":
						tarifa.indice = 1;
						break;
					case "QUARTA":
						tarifa.indice = 2;
						break;
					case "QUINTA":
						tarifa.indice = 3;
						break;
					case "SEXTA":
						tarifa.indice = 4;
						break;
					case "SABADO":
						tarifa.indice = 5;
						break;
					case "DOMINGO":
						tarifa.indice = 6;
						break;
					default:
						break;
					}
		    	 })
		    	 
		    	 return tarifas.sort( function(a,b) {
		    		 return a.indice - b.indice;
		    	 })
		     }
		     /**
		     *
		     */
		    $scope.findTipoQuartoById = function ( id ) {
		    	tipoQuartoService.findTipoQuartoById( id, {
		             callback: function (result) {
		                 $scope.model.entity = result;
		                 ordenarTarifaPadrao( $scope.model.entity.tarifasPadrao );
		                 $scope.listTarifaExcecoesByFiltersAndTipoQuartoId( null, null, null);
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
		 
		     $scope.listTarifaExcecoesByFiltersAndTipoQuartoId = function ( filter, dataInicio, dataFim ){
		    	 tipoQuartoService.listTarifaExcecoesByFiltersAndTipoQuartoId( null, null, null, $scope.model.entity.id, null, {
		    		 callback : function ( result ) {
		    			 $scope.model.tarifasExcecoes = result.content;
		    			 $scope.$apply();
		    		 }, errorHandler : function ( message, exception ) {
		    			 console.log("deu erro")
		    		 }
		    	 })
		     }
		     
		     /**
		      *
		      */
		     $scope.save = function( tipoQuarto ){
		    	 var tipoQuartoForm = angular.element( document.querySelector('#tipoQuartoForm') ).scope()['tipoQuartoForm'];
		    	 if ( tipoQuarto && tipoQuartoForm.$valid ) {
		             if ( !tipoQuarto.id ) {
		            	 tipoQuartoService.insertTipoQuarto ( tipoQuarto, {
		                     callback: function ( result ) {
		                         $rootScope.toast("Tipo de Quarto inserido com sucesso!", "green");
		                         $state.go ( $scope.LIST_TIPO_QUARTO_STATE );
		                         $scope.$apply ();
		                     }, errorHandler: function ( message, exception ) {
		                             $rootScope.toast(message, "red");
		                     }
		                 })
		             }else {
		            	 tipoQuartoService.updateTipoQuarto( tipoQuarto, {
		                     callback: function ( result ) {
		                         $rootScope.toast("As informações do tipo de quarto foram atualizadas", "green");
		                         $state.go ( $scope.LIST_IPO_QUARTO_STATE );
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