'use strict';
define(['app'], function(app) {
    app.factory('roleService', ['$http','$q','CONTEXT_PATH', function($http, $q, contextPath) {

        function queryList() {
            var defer = $q.defer();
            $http.get(contextPath+'/role/')
                .success(function(data, status, headers, config) {
                    defer.resolve(data);
                })
                .error(function(response, status, headers, config) {
                    defer.reject(response);
                });
            return defer.promise;
        }
        
        function queryById(roleId) {
        	var defer = $q.defer();
            $http.get(contextPath+'/role/'+roleId)
	            .success(function(data, status, headers, config) {
	                defer.resolve(data);
	            })
	            .error(function(response, status, headers, config) {
	                defer.reject(response);
	            });
	            
            return defer.promise;
        }

        return {
        	queryList: queryList,
        	queryById: queryById
        }
    }]);
});