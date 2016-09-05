var addTarifaExcecaoPopupController = ["$scope", "$mdDialog", "popupConfig", "data", "$rootScope", "$http", "$translate", "$importService", function( $scope, $mdDialog, popupConfig, data, $rootScope, $http, $translate, $importService ) {
 
    /*-------------------------------------------------------------------
     *                          ATTRIBUTES
     *-------------------------------------------------------------------*/
 
    $importService("tipoQuartoService");
     /**
      *
      */
    $scope.model = {
    	entity : data.tarifaExcecao && data.tarifaExcecao.id ? data.tarifaExcecao : {},
        popupConfig  : popupConfig,
        data         : data,
 
        pageRequest : {//PageImpl
            content : null,
            pageable : {//PageRequest
                page : 1,
                size : 5,
                sort : {
                    direction: 'ASC', properties: ['id']
                },
            }
        }
 
    };
 
    /*-------------------------------------------------------------------
     *                            HANDLERS
     *-------------------------------------------------------------------*/
 
    /**
     *
     */
    $scope.close = function(){
        $mdDialog.cancel();
    }
    
    $scope.listTarifasByDataInicioAndDataFim = function () {
    	if ( $scope.model.entity.dataInicio && $scope.model.entity.dataFim ) {
    		tipoQuartoService.listTarifasByDataInicioAndDataFim( $scope.model.entity.dataInicio, $scope.model.entity.dataFim, {
    			callback : function ( result ) {
    				console.log( "Tarifas -> ", result );
    				var tarifas = result;
    				if ( !$scope.model.entity.tarifas || !$scope.model.entity.tarifas.length )
    				{
    					$scope.model.entity.tarifas = tarifas;
    				} else {
    					angular.forEach( tarifas, function ( tarifa, index ) {
    						for ( var k = 0; k < $scope.model.entity.tarifas.length; k++ ) {
    							if ( $scope.model.entity.tarifas[k].dia == tarifa.dia ) {
    								tarifas[index] = $scope.model.entity.tarifas[k];
    								break;
    							}
    						}
    					})
    					$scope.model.entity.tarifas = tarifas;
    				}
    				$scope.model.entity.tarifas = ordenarTarifaPadrao ( $scope.model.entity.tarifas );
    				
    			}, errorHandler : function ( message, excepetion ) {
    				
    			}
    		})
    	}
    }
 
    function ordenarTarifaPadrao ( tarifas )
    {
   	 angular.forEach( tarifas, function ( tarifa, key ){
   		switch (tarifa.dia) {
			case "SEGUNDA":
				tarifa.indice = 0;
				tarifa.label="Segunda-Feira";
				break;
			case "TERCA":
				tarifa.indice = 1;
				tarifa.label="Terça-Feira";
				break;
			case "QUARTA":
				tarifa.indice = 2;
				tarifa.label="Quarta-Feira";
				break;
			case "QUINTA":
				tarifa.indice = 3;
				tarifa.label="Quinta-Feira";
				break;
			case "SEXTA":
				tarifa.indice = 4;
				tarifa.label="Sexta-Feira";
				break;
			case "SABADO":
				tarifa.indice = 5;
				tarifa.label="Sábado";
				break;
			case "DOMINGO":
				tarifa.indice = 6;
				tarifa.label="Domingo";
				break;
			default:
				break;
			}
   	 })
   	 
   	 return tarifas.sort( function(a,b) {
   		 return a.indice - b.indice;
   	 })
    }
    
  	 $scope.iniciarTarifas = function () 
   	 {
   		 if ( $scope.model.entity.tarifas && $scope.model.entity.tarifas.length ) {
   			$scope.model.entity.tarifas = ordenarTarifaPadrao( $scope.model.entity.tarifas );
   		 }
   	 }
  	 
    /**
     * 
     */
    $scope.save = function( tarifaExcecao ){
     var tarifaExcecaoForm = angular.element( document.querySelector('#tarifaExcecaoForm') ).scope()['tarifaExcecaoForm'];
     if(tarifaExcecao && tarifaExcecaoForm.$valid) {
          
         if(!tarifaExcecao.id){
            tipoQuartoService.insertTarifaExcecao ( tarifaExcecao, {
                 callback: function (result) {
                    $mdDialog.hide( result );
                 }
             })  
         }else {
        	 tipoQuartoService.updateTarifaExcecao( tarifaExcecao, {
                 callback: function ( result ) {
                    $mdDialog.hide( tarifaExcecao );
                 }
             })
         }
     }else {
         $rootScope.toast( "Formulário inválido" ,"red");
     }
    }
 
    
}];