'use strict';
define(['app'], function(app) {
    app.factory('menuService', ['$http','$q','CONTEXT_PATH', function($http, $q, contextPath) {

        function queryList() {
            var defer = $q.defer();
            $http.get(contextPath+'/menu/')
                .success(function(data, status, headers, config) {
                    defer.resolve(data);
                })
                .error(function(response, status, headers, config) {
                    defer.reject(response);
                });
            return defer.promise;
        }
        
        function queryById(menuId) {
        	var defer = $q.defer();
            $http.get(contextPath+'/menu/'+menuId)
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