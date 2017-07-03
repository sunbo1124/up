define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$routeParams', '$location','roleService','CONTEXT_PATH'];
    var sysRoleUpdateController = function($scope, $http, $routeParams, $location, roleService,CONTEXT_PATH) {
        $scope.fields = {};
        $scope.roleId = $routeParams.roleId;
        $scope.isBuiltinItems = [
                                 {name: '是', value: true},
                                 {name: '否', value: false}
                             ];
        
        roleService.queryById($scope.roleId).then(function(data) {
           	$scope.fields = data;  
           	if(data.isBuiltin == true){
           		$scope.fields.isBuiltin = $scope.isBuiltinItems[0];
        	}else{
        		$scope.fields.isBuiltin = $scope.isBuiltinItems[1];
        	}
        }, function(error) {
        	throw error;
        });
        
    	$scope.submit = function() {
    		if(!$scope.menuForm.$valid){
    			return false;
    		}
    		console.log($scope.fields);
            $http.put(CONTEXT_PATH+"/role/update", $scope.fields)
                .success(function(data, status, headers, config) {
                	$location.path("/role");
                    console.log(data)
                })
                .error(function(response, status, headers, config) {
                    console.log(response)
                });
    	};
    	
        $scope.validateBeforSubmit = function(fields) {
            $scope.fields.isBuiltin = $scope.fields.isBuiltin.value;
        };
    };
    sysRoleUpdateController.$inject = injectParams;
    app.register.controller('sysRoleUpdateController', sysRoleUpdateController);
});