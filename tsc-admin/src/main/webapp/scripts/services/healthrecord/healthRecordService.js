'use strict';
define(['app'], function(app) {
    app.factory('healthRecordService', ['$http','$q','CONTEXT_PATH', function($http, $q, contextPath) {

        function queryList() {
            var defer = $q.defer();
            $http.get(contextPath+'/healthRecord/')
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
            $http.get(contextPath+'/healthRecord/'+roleId)
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