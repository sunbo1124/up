'use strict';
define(['app'], function(app) {
    app.factory('indexService', ['$http','$q','CONTEXT_PATH', function($http, $q, contextPath) {
        function queryMenus(locale) {
            var defer = $q.defer();
            $http.get(contextPath+'/menu/query',{params: {"locale": locale}})
                .success(function(data, status, headers, config) {
                    defer.resolve(data);
                })
                .error(function(response, status, headers, config) {
                    defer.reject(response);
                });
            return defer.promise;
        }
        
        function queryLocale() {
            var defer = $q.defer();
            $http.get(contextPath+'/login/queryLocale')
                .success(function(data, status, headers, config) {
                    defer.resolve(data);
                })
                .error(function(response, status, headers, config) {
                    defer.reject(response);
                });
            return defer.promise;
        }
        
        return {
            queryMenus: queryMenus,
            queryLocale: queryLocale,
        }
    }]);
});