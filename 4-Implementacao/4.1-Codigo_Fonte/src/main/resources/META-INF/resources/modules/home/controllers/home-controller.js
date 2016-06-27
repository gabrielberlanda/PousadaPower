(function ( angular ) {
    'use strict';
    
/**
 * 
 * @param $scope
 * @param $state
 */
angular.module('home')
	   .controller('HomeController', function( $rootScope, $scope, $state, $importService, $mdToast, $mdDialog, $mdSidenav  ) {

    /*-------------------------------------------------------------------
     * 		 				 	ATTRIBUTES
     *-------------------------------------------------------------------*/
    //----STATES
		   

	//-----
	/**
	 * 
	 */
	$scope.menuSideNavId = "menuSideNav";

	/*-------------------------------------------------------------------
     * 		 				  	POST CONSTRUCT
     *-------------------------------------------------------------------*/
	
    /*-------------------------------------------------------------------
     * 		 				 	  HANDLERS
     *-------------------------------------------------------------------*/
    /**
     * 
     */
    $scope.toggleMenuSideNavHandler = function() {
    	console.log("toggleMenuSideNavHandler");
    	$mdSidenav($scope.menuSideNavId).toggle();
    };
    
    
    var DialogController = ["$scope", "$mdDialog", "$sce", "confirmConfig", function ($scope, $mdDialog, $sce, confirmConfig) {
        $scope.confirmConfig = confirmConfig;
        $scope.trustAsHtml = $sce.trustAsHtml;

        $scope.close = function(){
            $mdDialog.cancel();
        }

        $scope.closeOk = function(){
            $mdDialog.hide();
        }
    }]
    
    /**
    *
    * @param ev
    * @param confirmConfig
    * @param controller Controle a ser utilizado na popup
    * @returns {promise}
    */
   $rootScope.confirmDialog = function( ev, confirmConfig ) {

       var confirmConfigDefaults = {
           title   : null,
           content : null,
           ok      : "Confirmar",
           cancel  : "Cancelar",
       }

       var config = {
           controller          : arguments[2] ? arguments[2] : DialogController,
           targetEvent         : ev,
           templateUrl         : './static/templates/confirm.dialog.tmpl.html',
           parent              : angular.element(document.body),
           clickOutsideToClose : true,
           locals              : {
               confirmConfig : angular.merge( confirmConfigDefaults, confirmConfig )
           }
       }

       return $mdDialog.show( config );
   };

   /**
    * 
    */
   $rootScope.confirmLogOutDialog = function ( ev, confirmConfig ) {
		var confirmConfig = {
	             title   : $translate("logOut"),
	             content : $translate("logOutMessage"),
	         }
        $rootScope.confirmDialog( null, confirmConfig )
        .then(function() {
       	 window.location = "./logout";
        },function() {
       	 
        });
   }

   /**
    *
    * @param object
    * @returns {promise}
    */
   $rootScope.popupDialog = function( ev, page, controller, popupConfirm, data  ) {

   	var popupConfigDefaults = {
           title   : null,
           content : null,
           ok      : "Selecionar",
           cancel  : "Fechar",
       }

       var config = {
           controller          : controller,
           targetEvent         : ev,
           templateUrl         : page,
           parent              : angular.element(document.body),
           clickOutsideToClose : popupConfirm.clickOutsideToClose != null ? popupConfirm.clickOutsideToClose : true,
           locals              : {
               "popupConfig"	: angular.merge( popupConfigDefaults, popupConfirm ),
               "data"          : data
           }
       }

       return $mdDialog.show( config );
   };

   /**
    *
    * @param message
    * @param type
    * @param delay
    */
   $rootScope.toast = function( message, type ) {

       $mdToast.show({
           template: "<md-toast class=\"toast"+type + "\">"+
               "<span flex>" + message + "</span>" +
               "<md-button aria-label=\"close\" class=\"md-icon-button\" style=\"position: absolute;top: 0;right: -4px;\" aria ng-click=\"$mdToast.hide()\">"+
               	"<md-icon md-font-set=\"zmdi zmdi-close zmdi-hc-fw\" style=\"font-size:18px\"></md-icon>"+
               "</md-button>"+
               "</md-toast>",
           hideDelay: arguments[2] ? arguments[2] : 3000,
           position: "top right left"
       });

   }

   /**
    * 
    */
   $rootScope.hasAuthority = function  ( authority ) {
   	return $rootScope.$user.authorities.filter( function ( authorityFound ) {
   		return authorityFound.$name == authority;
   	}).length > 0;
   }
   
   /**
    *
    */
   $rootScope.openCustomMenu = function( id, width, height ){

   	$(id).css('width', width)
   		 .css('height', height)
   		 .find('md-menu-content')
   		 	.css('width', width);

   	var closeMenu = function(){
   		$(id).css('width', 0)
	       		 .css('height', 0);

   		$('#block-menu').hide();
   	};

   	$('#block-menu').show().bind( 'click', closeMenu );
   	$(id).bind( 'click', closeMenu );

   }
   
	/**
	*
	*/
	var requestCount = 0;
	
	/**
	 * 
	 */
	dwr.engine.setPreHook(function(){
		requestCount++;
		setTimeout( function(){
			if( requestCount > 0 ){
				$rootScope.progressState = true;
			} else {
				$rootScope.progressState = false;
			}
			$rootScope.$apply();
		}, 300);
	});
	
	/**
	*
	*/
	dwr.engine.setPostHook(function(){
		requestCount--;
		if( requestCount > 0 ){
			$rootScope.progressState = true;
		} else {
			$rootScope.progressState = false;
		}
	});
   

	/**
	 *
	 */
   dwr.engine.setErrorHandler(defaultErrorHandler);
   function defaultErrorHandler(message, exception) {
   	if ( exception.name == "dwr.engine.incompleteReply" ) {
   		message = $translate("serverTemporarilyUnavailable")
   	}
   	$rootScope.toast ( message, "red");
   }

    
});

}(window.angular));