'use strict';
define(['angular','angular-route'], function(angular) {
    var app = angular.module('myApp',['ngRoute','ngAnimate','routeResolverServices','pascalprecht.translate','angularShiro', 'ui.bootstrap']);

    app.constant("CONTEXT_PATH", window.SERVLET_CONTEXT_PATH);
    
    app.config(['$routeProvider', 'routeResolverProvider','$controllerProvider','$compileProvider',
            '$provide','$filterProvider','$translateProvider','angularShiroConfigProvider','CONTEXT_PATH', function($routeProvider, routeResolverProvider, $controllerProvider, $compileProvider, $provide, $filterProvider,$translateProvider, shiroConfig, contextPath) {
                app.register = {
                    controller: $controllerProvider.register,
                    directive: $compileProvider.directive,
                    filter: $filterProvider.register,
                    factory: $provide.factory,
                    service: $provide.service
                };
                shiroConfig.setFilter('/**/*','authc');
                shiroConfig.setAuthenticateUrl(contextPath+'/api/authenticate');
                var route = routeResolverProvider.route;
                //app路由
                $routeProvider
                	.when('/error',
                			{
                			templateUrl:contextPath+'/views/error.html'
                			})
                    .when('/user', route.resolve('sysUserListController','list','sysuser','vm', false))
                    .when('/user/add', route.resolve('sysUserAddController','add','sysuser','vm', false))
                    .when('/user/upd/:userId', route.resolve('sysUserUpdateController','update','sysuser','vm', false))
                    
                    .when('/role', route.resolve('sysRoleListController','list','sysrole','vm', false))
                    .when('/role/add', route.resolve('sysRoleAddController','add','sysrole','vm', false))
                    .when('/role/upd/:roleId', route.resolve('sysRoleUpdateController','update','sysrole','vm', false))
                    
                    .when('/allotRole', route.resolve('allotRoleListController','list','allotrole','vm', false))
                    .when('/allotRole/add', route.resolve('allotRoleAddController','add','allotrole','vm', false))
                    .when('/allotRole/upd/:allotRoleId', route.resolve('allotRoleUpdateController','update','allotrole','vm', false))
                    
                    .when('/menu', route.resolve('sysMenuListController','list','sysmenu','vm', false))
                    .when('/menu/add', route.resolve('sysMenuAddController','add','sysmenu','vm', false))
                    .when('/menu/upd/:menuId', route.resolve('sysMenuUpdateController','update','sysmenu','vm', false))
                    
                    .when('/allotmenu', route.resolve('sysAllotMenuListController','list','allotmenu','vm', false))
                    
                	.when('/log',route.resolve('logManagerListController','list','logmanager','vm', false))
                	
                    .when('/subscription',route.resolve('sysSubscriptionListController','list','syssubscription','vm', false))
                    .when('/subscription/add',route.resolve('sysSubscriptionAddController','add','syssubscription','vm', false))
                    .when('/subscription/update/:topicServiceId',route.resolve('sysSubscriptionUpdateController','update','syssubscription','vm', false))
                    .when('/subscription/batchUpdate',route.resolve('sysSubscriptionBatchUpdateController','batchUpdate','syssubscription','vm', false))
                                        
                    .when('/info',route.resolve('appContextPathInfoController','info','info','vm', false))
                    .when('/info/add',route.resolve('appContextPathAddController','add','info','vm', false))
                    .when('/info/update/:service_id',route.resolve('appContextPathUpdateController','update','info','vm', false))
                    .when('/info/appName',route.resolve('appContextPathBatchUpdateIpController','batchupdateip','info','vm', false))
                  
                   
                    .when('/param',route.resolve('paramListController','list','param','vm', false))
                    .when('/param/add',route.resolve('paramAddController','add','param','vm', false))
                    .when('/param/update/:key_id',route.resolve('paramUpdateController','update','param','vm', false))
                    
                    .when('/vehicleProfile/history',route.resolve('vehicleProfileHistoryListController','list','history/vehicle','vm', false))
                   
                    .when('/userProfile/history',route.resolve('userProfileHistoryListController','list','history/user','vm', false))
               
                	.when('/cspApp', route.resolve('cspAppListController','list','ucs/cspapp','vm', false))
                    .when('/cspApp/add', route.resolve('cspAppAddController','add','ucs/cspapp','vm', false))
                    .when('/cspApp/upd/:cspAppId', route.resolve('cspAppUpdateController','update','ucs/cspapp','vm', false))
                    
                    .when('/cspAppGroup', route.resolve('cspAppGroupListController','list','ucs/cspappgroup','vm', false))
                    .when('/cspAppGroup/add', route.resolve('cspAppGroupAddController','add','ucs/cspappgroup','vm', false))
                    .when('/cspAppGroup/upd/:cspAppGroupId', route.resolve('cspAppGroupUpdateController','update','ucs/cspappgroup','vm', false))
                    
                    .when('/cspConfParam', route.resolve('cspConfParamListController','list','ucs/cspconfparam','vm', false))
                    .when('/cspConfParam/add', route.resolve('cspConfParamAddController','add','ucs/cspconfparam','vm', false))
                    .when('/cspConfParam/upd/:cspConfParamId', route.resolve('cspConfParamUpdateController','update','ucs/cspconfparam','vm', false))
                 
                    .when('/vehicleModel', route.resolve('vehicleModelListController','list','dictionary/vehiclemodel','vm', false))
                    .when('/vehicleModel/add', route.resolve('vehicleModelAddController','add','dictionary/vehiclemodel','vm', false))
                    .when('/vehicleModel/upd/:vehicleModelId', route.resolve('vehicleModelUpdateController','update','dictionary/vehiclemodel','vm', false))

                    .when('/servicePowerModeMapping', route.resolve('servicePowerModeMappingListController','list','dictionary/servicepowermodemapping','vm', false))
                    .when('/servicePowerModeMapping/add', route.resolve('servicePowerModeMappingAddController','add','dictionary/servicepowermodemapping','vm', false))
                    .when('/servicePowerModeMapping/upd/:servicePowerModeMappingId', route.resolve('servicePowerModeMappingUpdateController','update','dictionary/servicepowermodemapping','vm', false))

                    .when('/vehicleMatModel', route.resolve('vehicleMatModelListController','list','dictionary/vehiclematmodel','vm', false))
                    .when('/vehicleMatModel/add', route.resolve('vehicleMatModelAddController','add','dictionary/vehiclematmodel','vm', false))
                    .when('/vehicleMatModel/upd/:vehicleMatModelId', route.resolve('vehicleMatModelUpdateController','update','dictionary/vehiclematmodel','vm', false))

                    .when('/vehicleColor', route.resolve('vehicleColorListController','list','dictionary/vehiclecolor','vm', false))
                    .when('/vehicleColor/add', route.resolve('vehicleColorAddController','add','dictionary/vehiclecolor','vm', false))
                    .when('/vehicleColor/upd/:vehicleColorId', route.resolve('vehicleColorUpdateController','update','dictionary/vehiclecolor','vm', false))
                    
                    .when('/vehiclePhoto', route.resolve('vehiclePhotoListController','list','dictionary/vehiclephoto','vm', false))
                     .when('/vehiclePhoto/upd/:vehiclePhotoId', route.resolve('vehiclePhotoUpdateController','update','dictionary/vehiclephoto','vm', false))
                    
                    .when('/tscDealerProfile', route.resolve('tscDealerProfileListController','list','dictionary/dealer','vm', false))
                    .when('/tscDealerProfile/add', route.resolve('tscDealerProfileAddController','add','dictionary/dealer','vm', false))
                    .when('/tscDealerProfile/upd/:tscDealerProfileId', route.resolve('tscDealerProfileUpdateController','update','dictionary/dealer','vm', false))
                    
                    .when('/vehiclePlatform', route.resolve('vehiclePlatformListController','list','dictionary/vehicleplatform','vm', false))
                    .when('/vehiclePlatform/add', route.resolve('vehiclePlatformAddController','add','dictionary/vehicleplatform','vm', false))
                    .when('/vehiclePlatform/upd/:vehiclePlatformId', route.resolve('vehiclePlatformUpdateController','update','dictionary/vehicleplatform','vm', false))

                    .when('/dTC', route.resolve('dTCListController','list','dictionary/dtc','vm', false))
                    .when('/dTC/add', route.resolve('dTCAddController','add','dictionary/dtc','vm', false))
                    .when('/dTC/upd/:dTCId', route.resolve('dTCUpdateController','update','dictionary/dtc','vm', false))

                    .when('/vDSSignal', route.resolve('vDSSignalListController','list','dictionary/vdssignal','vm', false))
                    .when('/vDSSignal/add', route.resolve('vDSSignalAddController','add','dictionary/vdssignal','vm', false))
                    .when('/vDSSignal/upd/:vDSSignalId', route.resolve('vDSSignalUpdateController','update','dictionary/vdssignal','vm', false))
                    
                    .when('/cspSignal', route.resolve('cspSignalListController','list','dictionary/cspsignal','vm', false))
                    .when('/cspSignal/add', route.resolve('cspSignalAddController','add','dictionary/cspsignal','vm', false))
                    .when('/cspSignal/importXLS', route.resolve('cspSignalImportXLSController','importXLS','dictionary/cspsignal','vm', false))
                    .when('/cspSignal/upd/:cspSignalId', route.resolve('cspSignalUpdateController','update','dictionary/cspsignal','vm', false))
                    
                    .when('/tscSignalValidity', route.resolve('tscSignalValidityListController','list','dictionary/tscsignalvalidity','vm', false))
                    .when('/tscSignalValidity/add', route.resolve('tscSignalValidityAddController','add','dictionary/tscsignalvalidity','vm', false))
                    .when('/tscSignalValidity/upd/:tscSignalValidityId', route.resolve('tscSignalValidityUpdateController','update','dictionary/tscsignalvalidity','vm', false))

                    .when('/signalCorrectionFormula', route.resolve('signalCorrectionFormulaListController','list','dictionary/signalcorrectionformula','vm', false))
                    .when('/signalCorrectionFormula/add', route.resolve('signalCorrectionFormulaAddController','add','dictionary/signalcorrectionformula','vm', false))
                    .when('/signalCorrectionFormula/upd/:signalCorrectionFormulaId', route.resolve('signalCorrectionFormulaUpdateController','update','dictionary/signalcorrectionformula','vm', false))

                    .when('/vehicleCapability', route.resolve('vehicleCapabilityListController','list','dictionary/vehiclecapability','vm', false))
                    .when('/vehicleCapability/add', route.resolve('vehicleCapabilityAddController','add','dictionary/vehiclecapability','vm', false))
                    .when('/vehicleCapability/upd/:vehicleCapabilityId', route.resolve('vehicleCapabilityUpdateController','update','dictionary/vehiclecapability','vm', false))
     
                    .when('/vehicleEngine', route.resolve('vehicleEngineListController','list','dictionary/vehicleengine','vm', false))
                    .when('/vehicleEngine/add', route.resolve('vehicleEngineAddController','add','dictionary/vehicleengine','vm', false))
                    .when('/vehicleEngine/upd/:vehicleEngineId', route.resolve('vehicleEngineUpdateController','update','dictionary/vehicleengine','vm', false))
   
                    .when('/cspAllocation', route.resolve('cspAllocationListController','list','dictionary/cspallocation','vm', false))
                    .when('/cspAllocation/add', route.resolve('cspAllocationAddController','add','dictionary/cspallocation','vm', false))
                    .when('/cspAllocation/upd/:cspAllocationId', route.resolve('cspAllocationUpdateController','update','dictionary/cspallocation','vm', false))

                    .when('/messageTemplate', route.resolve('messageTemplateListController','list','template','vm', false))
                    .when('/messageTemplate/add', route.resolve('messageTemplateAddController','add','template','vm', false))
                    .when('/messageTemplate/upd/:messageTemplateId', route.resolve('messageTemplateUpdateController','update','template','vm', false))
                    
                    .when('/table', route.resolve('tableListController','list','table','vm', false))
                    .when('/table/add', route.resolve('tableAddController','add','table','vm', false))
                    .when('/table/upd/:tableId', route.resolve('tableUpdateController','update','table','vm', false))

                    .when('/cspVersion', route.resolve('cspVersionListController','list','cspversion','vm', false))
                    .when('/cspVersion/add', route.resolve('cspVersionAddController','add','cspversion','vm', false))
                    .when('/cspVersion/upd/:cspVersionId', route.resolve('cspVersionUpdateController','update','cspversion','vm', false))

                    .when('/userHistory', route.resolve('tscUserHistoryListController','list','history/user','vm', false))
                    
                    .when('/customerHistory', route.resolve('tscCustomerHistoryListController','list','history/customer','vm', false))
                    
                    .when('/vehicleHistory', route.resolve('tscVehicleHistoryListController','list','history/vehicle','vm', false))

                    .when('/logfetcher', route.resolve('logfetcherController','list','logfetcher','vm', false))
                    
                    .when('/vinConnection', route.resolve('vinConnectionListController','list','vinconnection','vm', false))
                    
                    .when('/cspResourceConf', route.resolve('cspResourceConfListController','list','dictionary/cspresourceconf','vm', false))
                    .when('/cspResourceConf/add', route.resolve('cspResourceConfAddController','add','dictionary/cspresourceconf','vm', false))
                    .when('/cspResourceConf/upd/:cspResourceConfId', route.resolve('cspResourceConfUpdateController','update','dictionary/cspresourceconf','vm', false))
                    
                    .when('/vehicleState', route.resolve('vehicleStateListController','list','dictionary/vehiclestate','vm', false))
                    .when('/vehicleState/add', route.resolve('vehicleStateAddController','add','dictionary/vehiclestate','vm', false))
                    .when('/vehicleState/upd/:vehicleStateVin', route.resolve('vehicleStateUpdateController','update','dictionary/vehiclestate','vm', false))
                    
                    .when('/ecuWarningMessage', route.resolve('ecuWarningMessageListController','list','dictionary/tscecuwarningmessage','vm', false))
                    .when('/ecuWarningMessage/add', route.resolve('ecuWarningMessageAddController','add','dictionary/tscecuwarningmessage','vm', false))
                    .when('/ecuWarningMessage/upd/:id', route.resolve('ecuWarningMessageUpdateController','update','dictionary/tscecuwarningmessage','vm', false))
                    
                    .when('/tscServiceAppGroup', route.resolve('tscServiceAppGroupListController','list','dictionary/tscserviceappgroup','vm', false))
                    .when('/tscServiceAppGroup/add', route.resolve('tscServiceAppGroupAddController','add','dictionary/tscserviceappgroup','vm', false))
                    .when('/tscServiceAppGroup/upd/:id', route.resolve('tscServiceAppGroupUpdateController','update','dictionary/tscserviceappgroup','vm', false))
                    
                    .when('/tscTemLogType', route.resolve('tscTemLogTypeListController','list','dictionary/tsctemlogtype','vm', false))
                    .when('/tscTemLogType/add', route.resolve('tscTemLogTypeAddController','add','dictionary/tsctemlogtype','vm', false))
                    .when('/tscTemLogType/update/:tscTemLogTypeId', route.resolve('tscTemLogTypeUpdateController','update','dictionary/tsctemlogtype','vm', false))
                    
                    .when('/tscTemVendor', route.resolve('tscTemVendorListController','list','dictionary/tsctemvendor','vm', false))
                    .when('/tscTemVendor/add', route.resolve('tscTemVendorAddController','add','dictionary/tsctemvendor','vm', false))
                    .when('/tscTemVendor/update/:tscTemVendorId', route.resolve('tscTemVendorUpdateController','update','dictionary/tsctemvendor','vm', false))
                    
                    .when('/tscErrorCode', route.resolve('tscErrorCodeListController','list','dictionary/tscerrorcode','vm', false))
                    .when('/tscErrorCode/add', route.resolve('tscErrorCodeAddController','add','dictionary/tscerrorcode','vm', false))
                    .when('/tscErrorCode/update/:tscErrorCodeId', route.resolve('tscErrorCodeUpdateController','update','dictionary/tscerrorcode','vm', false))
                    
                    .when('/temErrorCode', route.resolve('temErrorCodeListController','list','dictionary/temerrorcode','vm', false))
                    .when('/temErrorCode/add', route.resolve('temErrorCodeAddController','add','dictionary/temerrorcode','vm', false))
                    .when('/temErrorCode/update/:temErrorCodeId', route.resolve('temErrorCodeUpdateController','update','dictionary/temerrorcode','vm', false))
                     
                	.otherwise('/user', route.resolve('sysUserListController','list','sysuser','vm', false));

                //i18n angular-translate
                $translateProvider.useStaticFilesLoader({
                	prefix: contextPath+'/i18n/',
                    suffix: '.json'
                });
                
                
                $translateProvider.preferredLanguage('zh-CN');
		}
    ]);

    app.controller("indexController" , ['$scope', '$translate', '$http', '$location', 'indexService', 'subject', 'usernamePasswordToken', '$uibModal', 'CONTEXT_PATH',
                                        function($scope, $translate, $http, $location, indexService, subject, usernamePasswordToken, $uibModal, contextPath) {
    	
    	$scope.taken = usernamePasswordToken;
    	$scope.taken.username = "administrator";
    	$scope.taken.password = "123456";
    	$scope.scopelocale = "CN" ;
    	$scope.secondMenus = [
    	    {href:contextPath+"/index/#/user",icon:"entypo-home",translatekey:"'i18n.MENU.HOME'"}
    	];
    	
        $scope.queryLocaleData = function() {
            indexService.queryLocale().then(function(data) {
            	$scope.scopelocale = data;
                $scope.queryMenuData($scope.scopelocale);
                $scope.setLanguage($scope.scopelocale);
            }, function(error) {
                alert(error);
            });
        };
        $scope.queryLocaleData();
        
        $scope.queryMenuData = function(locale) {
            indexService.queryMenus(locale).then(function(data) {
                $scope.menus = data;
                $scope.$broadcast("menuDataPrepared");
            }, function(error) {
                alert(error);
            });
        };

        
        $scope.setLanguage = function(locale) {
    		if(locale == 'CN' ){
        		$translate.use('zh-CN');
    		}else{
        		$translate.use('en-US');
    		}
        }

        
        //shiro
		subject.login($scope.taken).then(function(data) {
		}, function(data) {
		});
        
    }]);
    
    
    app.factory('httpErrorResponseInterceptor', ['$q', '$location','$rootScope',function($q, $location,$rootScope) {
    	return {
    		request: function(config) {
    	      	return config;
    	 	},
    	    // optional method
			requestError: function(rejection) {
				// do something on error
				return $q.reject(rejection);
			},
			response: function(responseData) { 
				return responseData; 
			},
			responseError: function error(response) {
				console.log(response);
				$rootScope.error_response=response;
				switch (response.status) {
					case 401:
						$location.path('/login');
						break;
					case 404:
						$location.path('/404');
						break;
					default:
						$location.path('/error');
				}
				return $q.reject(response);
			}
     	};
	}]);
                                               

    //Http Intercpetor to check auth failures for xhr requests
    app.config(['$httpProvider', 
        function($httpProvider) {
			$httpProvider.interceptors.push('httpErrorResponseInterceptor');
       }
	]);
    
    return app;
});
