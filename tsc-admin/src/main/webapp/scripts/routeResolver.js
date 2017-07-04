'use strict';

define(['angular'], function (angular) {

    var routeResolver = function () {

        this.$get = function () {
            return this;
        };

        this.routeConfig = function () {
        
            console.log(window.SERVLET_CONTEXT_PATH)
      
            var contextPath = window.SERVLET_CONTEXT_PATH;
            var viewsDirectory = contextPath+'/views/',
                controllersDirectory = contextPath+'/scripts/controllers/',

            setBaseDirectories = function (viewsDir, controllersDir) {
                viewsDirectory = viewsDir;
                controllersDirectory = controllersDir;
            },

            getViewsDirectory = function () {
                return viewsDirectory;
            },

            getControllersDirectory = function () {
                return controllersDirectory;
            };

            return {
                setBaseDirectories: setBaseDirectories,
                getControllersDirectory: getControllersDirectory,
                getViewsDirectory: getViewsDirectory
            };
        }();

        this.route = function (routeConfig) {
            /**
             *
             * @param controllerName controller 名
             * @param templateName views/{path}/{templateName} HTML（模板名）名
             * @param path scripts/controllers/{path} 目录名
             * @param controllerAs
             * @param secure
             * @returns {{}}
             */
            var resolve = function (controllerName, templateName, path, controllerAs, secure) {
                if (!path) path = '';
                var routeDef = {};
                routeDef.templateUrl = routeConfig.getViewsDirectory() + path + '/' + templateName + '.html';
                routeDef.controller = controllerName;
                if (controllerAs) routeDef.controllerAs = controllerAs;
                routeDef.secure = (secure) ? secure : false;
                routeDef.resolve = {
                    load: ['$q', '$rootScope', function ($q, $rootScope) {
                        var dependencies = [routeConfig.getControllersDirectory() + path + '/' +routeDef.controller + '.js'];
                        return resolveDependencies($q, $rootScope, dependencies);
                    }]
                };
                return routeDef;
            };

            var resolveDependencies = function ($q, $rootScope, dependencies) {
                var defer = $q.defer();
                require(dependencies, function () {
                    defer.resolve();
                    $rootScope.$apply()
                });

                return defer.promise;
            };

            return {
                resolve: resolve
            }
        }(this.routeConfig);

    };

    var servicesApp = angular.module('routeResolverServices', []);

    //Must be a provider since it will be injected into module.config()    
    servicesApp.provider('routeResolver', routeResolver);
});
