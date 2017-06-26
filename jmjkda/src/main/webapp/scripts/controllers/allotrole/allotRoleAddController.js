define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$location','$uibModal', 'userService', 'roleService', 'CONTEXT_PATH'];
    var allotRoleAddController =function($scope, $http, $location, $uibModal, userService, roleService, CONTEXT_PATH) {
        $scope.fields = {};
        
        userService.queryNoAllotRoleList().then(function(data) {
    		$scope.userIdItems = data.data;
    	}, function(error) {
    		throw error;
    	});
        
        roleService.queryList().then(function(data) {
    		$scope.roleIdItems = data.data;
    	}, function(error) {
    		throw error;
    	});
        
    	$scope.submit = function() {
    		if(!$scope.allotRoleForm.$valid){
    			return false;
    		}
            $http.post(CONTEXT_PATH+"/allotRole/add", $scope.fields)
                .success(function(data, status, headers, config) {
                    $location.path("/allotRole");
                })
                .error(function(response, status, headers, config) {
                    console.log(response)
                });
    	};

        $scope.validateBeforSubmit = function(fields) {
            $scope.fields.isBuiltin = $scope.fields.isBuiltin.value;
        };
    };
    allotRoleAddController.$inject = injectParams;
    app.register.controller('allotRoleAddController', allotRoleAddController);
});