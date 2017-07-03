'use strict';
define(['app'], function(app) {
    app.factory('appContextPathService', ['$http','$q','CONTEXT_PATH', function($http, $q, contextPath) {

        function queryList() {
            var defer = $q.defer();
            $http.get(contextPath+'/info/')
                .success(function(data, status, headers, config) {
                    defer.resolve(data);
                })
                .error(function(response, status, headers, config) {
                    defer.reject(response);
                });
            return defer.promise;
        }
        
        function queryAppName() {
            var defer = $q.defer();
            $http.get(contextPath+'/info/appName')
                .success(function(data, status, headers, config) {
                    defer.resolve(data);
                })
                .error(function(response, status, headers, config) {
                    defer.reject(response);
                });
            return defer.promise;
        }
        
        function queryById(id) {
        	var defer = $q.defer();
            $http.get(contextPath+'/info/'+id)
	            .success(function(data, status, headers, config) {
	                defer.resolve(data);
	            })
	            .error(function(response, status, headers, config) {
	                defer.reject(response);
	            });
            return defer.promise;
        }
        
        return {
        	queryById: queryById,
        	queryAppName: queryAppName,
        	queryList: queryList
        }
    }]);
});