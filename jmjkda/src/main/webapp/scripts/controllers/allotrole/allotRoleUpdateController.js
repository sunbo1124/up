define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$routeParams', '$location', 'allotRoleService', 'userService', 'roleService', 'CONTEXT_PATH'];
    var allotRoleUpdateController = function($scope, $http, $routeParams, $location, allotRoleService, userService, roleService, CONTEXT_PATH) {
        $scope.fields = {};
        $scope.allotRoleId = $routeParams.allotRoleId;

        userService.queryList().then(function(data) {
    		$scope.userIdItems = data.data;
    	}, function(error) {
    		throw error;
    	});
        
        roleService.queryList().then(function(data) {
    		$scope.roleIdItems = data.data;
    	}, function(error) {
    		throw error;
    	});
        
        allotRoleService.queryById($scope.allotRoleId).then(function(data) {
           	$scope.fields = data;  
        }, function(error) {
        	throw error;
        });
        
    	$scope.submit = function() {
            $http.put(CONTEXT_PATH+"/allotRole/update", $scope.fields)
                .success(function(data, status, headers, config) {
                	$location.path("/allotRole");
                    console.log(data)
                })
                .error(function(response, status, headers, config) {
                    console.log(response)
                });
    	};
    	
    };
    allotRoleUpdateController.$inject = injectParams;
    app.register.controller('allotRoleUpdateController', allotRoleUpdateController);
});