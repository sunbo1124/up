'use strict';
define(['app'], function(app) {
    app.factory('allotMenuService', ['$http','$q','CONTEXT_PATH', function($http, $q, contextPath) {
        function queryById(roleId) {
        	var defer = $q.defer();
            $http.get(contextPath+'/allotMenu/'+roleId)
	            .success(function(data, status, headers, config) {
	                defer.resolve(data);
	            })
	            .error(function(response, status, headers, config) {
	                defer.reject(response);
	            });
	            
            return defer.promise;
        }

        return {
        	queryById: queryById
        }
    }]);
});