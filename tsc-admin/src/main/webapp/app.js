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
