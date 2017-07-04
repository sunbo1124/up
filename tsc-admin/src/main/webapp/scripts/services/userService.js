'use strict';
define(['app'], function(app) {
    app.factory('userService', ['$http','$q','CONTEXT_PATH', function($http, $q, contextPath) {

        function queryList() {
            var defer = $q.defer();
            $http.get(contextPath+'/user/')
                .success(function(data, status, headers, config) {
                    defer.resolve(data);
                })
                .error(function(response, status, headers, config) {
                    defer.reject(response);
                });
            return defer.promise;
        }
        
        function queryById(userId) {
        	var defer = $q.defer();
            $http.get(contextPath+'/user/'+userId)
	            .success(function(data, status, headers, config) {
	                defer.resolve(data);
	            })
	            .error(function(response, status, headers, config) {
	                defer.reject(response);
	            });
	            
            return defer.promise;
        }
        
        function queryNoAllotRoleList() {
            var defer = $q.defer();
            $http.get(contextPath+'/user/noAllotRole/')
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
        	queryById: queryById,
        	queryNoAllotRoleList:queryNoAllotRoleList,
        }
    }]);
});