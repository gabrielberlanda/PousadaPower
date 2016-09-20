(function ( angular ) {
    'use strict';
    
/**
 * 
 * @param $scope
 * @param $state
 */
angular.module('home')
	   .controller('QuartoController', function( $q, $rootScope, $scope, $state, $importService, $mdToast, $mdDialog, $mdSidenav, $translate, $timeout, $filter ) {

	   
		    $importService("quartoService");
		    $importService("tipoQuartoService");
		 
		    /*-------------------------------------------------------------------
		     *                          ATTRIBUTES
		     *-------------------------------------------------------------------*/
		    $scope.NEW_QUARTO_STATE        = "quarto.novo";
		    $scope.EDIT_QUARTO_STATE      = "quarto.editar";
		    $scope.DETAIL_QUARTO_STATE   	 = "quarto.detalhe";
		    $scope.LIST_QUARTO_STATE     	 = "quarto.lista";
		 
		     /**
		      *
		      */
		    $scope.model = {
		    	status : [
		    	          {
		    	        	  value : "LIMPO",
		    	        	  label : "Limpo"
		    	          },
		    	          {
		    	        	  value : "SUJO",
		    	        	  label : "Sujo"
		    	          }], 
		        entity : {
		        	status : "LIMPO"
		        },
		        file : null,
		        fileRender : null,
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
		            $scope.listQuartosByFilters ( $scope.model.query.filter.name );
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
		        	status : "LIMPO"
		        };
		        
		        $scope.model.file = null;
		        
		        $scope.model.fileRender = null;
		 
		        switch( toState.name ){
		            case $scope.LIST_QUARTO_STATE :
		                $scope.listQuartosByFilters( null );
		            break;
		            case $scope.EDIT_QUARTO_STATE :
		                $scope.findQuartoById( toParams.id );
		            break;
		            case $scope.DETAIL_QUARTO_STATE :
		                $scope.findQuartoById ( toParams.id );
		            break;
		        };
		 
		     });
		 
		     /**
		      *
		      * @param ev
		      * @param user
		      */
		     $scope.changeToEdit = function( ev, quarto ) {
		         $state.go( $scope.EDIT_QUARTO_STATE, {id: quarto.id} );
		     }
		 
		     /**
		      *
		      * @param ev
		      * @param user
		      */
		     $scope.changeToDetail = function( ev, quarto ) {
		         var tagName = ev.target.tagName.toLowerCase();
		         if ( tagName != "button" && tagName != "md-icon" ) {
		             $state.go( $scope.DETAIL_QUARTO_STATE, {id: quarto.id} );
		         }
		     }
		 
		     /**
		     *
		     */
		    $scope.listQuartosByFilters = function ( filter ) {
		 
		         var pageable = angular.copy($scope.model.pageRequest.pageable);
		         if ( pageable.page > 0 ) {
		             pageable.page = pageable.page -1;
		         }
		 
		         quartoService.listQuartosByFilters( filter, pageable, {
		             callback: function (result) {
		            	 console.log(result.content);
		            	 $scope.model.pageRequest.content = result.content; 

		                $scope.$apply();
		             }
		         });
		 
		     };
		 

		     $scope.listTipoQuartosByFilters = function( filter ) {
		    	 var deferred = $q.defer();
		    	 
		         tipoQuartoService.listTipoQuartosByFilters ( filter, null, {
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
		    $scope.findQuartoById = function ( id ) {
		    	id = parseInt(id);
		    	quartoService.findQuartoById( id, {
		             callback: function (result) {
            			$scope.model.entity = result;
            			$scope.$apply( function() {
            				$scope.photoRender( $scope.model.entity );
            			});
            		}
		         });
		 
		     };
		 
		     $scope.removeQuarto = function( ev, quarto ) {
		 
		         var confirmConfig = {
		             title   : "Remover Quarto",
		             content : "Você realmente deseja remover o quarto " + quarto.nome + "?",
		             ok      : "Remover"
		         }
		 
		         $rootScope.confirmDialog( ev, confirmConfig )
		 
		             .then(function() {
		            	 quartoService.removeQuarto ( quarto.id, {
		                     callback: function ( result ){
		                         $rootScope.toast("Quarto removido com sucesso!", "green");
		                         if ( $state.current.name == $scope.DETAIL_QUARTO_STATE ) {
		                             $state.go( $scope.LIST_QUARTO_STATE );
		                         }
		                         if ( $scope.model.pageRequest.content.indexOf(quarto) > -1 ) {
		                             $scope.model.pageRequest.content.splice( $scope.model.pageRequest.content.indexOf(quarto), 1);
		 
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
		     $scope.save = function( quarto ){
		    	 var quartoForm = angular.element( document.querySelector('#quartoForm') ).scope()['quartoForm'];
		    	 if ( quarto && quartoForm.$valid ) {
		             if ( !quarto.id ) {
		            	 quartoService.insertQuarto ( quarto, $scope.model.file, {
		                     callback: function ( result ) {
		                         $rootScope.toast("Quarto inserido com sucesso!", "green");
		                         $state.go ( $scope.LIST_QUARTO_STATE );
		                         $scope.$apply ();
		                     }, errorHandler: function ( message, exception ) {
		                             $rootScope.toast(message, "red");
		                     }
		                 })
		             }else {
		            	 quartoService.updateQuarto( quarto, $scope.model.file,{
		                     callback: function ( result ) {
		                         $rootScope.toast("As informações do quarto foram atualizadas", "green");
		                         $state.go ( $scope.LIST_QUARTO_STATE );
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
            $scope.removeFile = function ( equipmentId ) {
            	quartoService.removeEquipmentFile ( equipmentId, {
            		callback : function ( result ) {
            			$rootScope.toast("Arquivo removido com sucesso", "green");
            			$scope.model.fileRender = null;
            			$scope.$apply();
            		}
            	})
            }
            
            /**
             * 
             */
            $scope.photoRender = function( equipment ){
            	quartoService.photoRender( equipment.id, {
        			callback: function ( result ) {
        				$scope.model.fileRender = result;
            			$scope.$apply();
        			}
        		});
            }
            
			/**
			 * 
			 */
        	$scope.uploadFile = function( file ){
            	$scope.$apply(function(){
            		var fileExtension = file.value.split('.').pop().toUpperCase();
            		if ( fileExtension == "GIF" || fileExtension == "JPG" || fileExtension == "JPEG" ||
        				fileExtension == "BMP" || fileExtension == "PNG" ) {
 
            			$scope.model.entity.metaFileName = file.value.replace( "C:\\fakepath\\","");
            			$scope.model.file = file;
            		} else {
            			$rootScope.toast( "Tipo de arquivo não suportado", "red" );
            			$scope.model.entity.metaFileName = null;
            			$scope.model.file = null;
            		}
            	});
            }
		    /**
		     *
		     */
//		    $scope.onOrderChange = function (order) {
//		        if ( order[0] == "-" ) {
//		            order = order.replace("-","");
//		            $scope.model.pageRequest.pageable.sort.direction = "DESC";
//		        } else {
//		            $scope.model.pageRequest.pageable.sort.direction = "ASC";
//		        }
//		 
//		        $scope.model.pageRequest.pageable.sort.properties[0] = order;
//		        $scope.listProdutosByFilters ( $scope.model.query.filter.name );
//		    };
//		 
//		 
//		    $scope.onPaginationChange = function (page, limit) {
//		        $scope.model.pageRequest.pageable.page = page ;
//		        $scope.model.pageRequest.pageable.size = limit;
//		    };
		 
});

}(window.angular));