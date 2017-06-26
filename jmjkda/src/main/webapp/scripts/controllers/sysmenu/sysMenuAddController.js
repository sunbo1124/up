define(['app'], function(app) {
    var injectParams = ['$scope','$http', '$location','$uibModal','menuService', 'CONTEXT_PATH'];
    var sysMenuAddController =function($scope, $http, $location, $uibModal, menuService, CONTEXT_PATH) {
        $scope.fields = {};  
 
    	menuService.queryList().then(function(data) {
    		$scope.pidItems = data.data;
    	}, function(error) {
    		throw error;
    	});
    	
    	$scope.submit = function() {
    		if(!$scope.menuForm.$valid){
    			return false;
    		}
    		$scope.fields.pid = $scope.fields.pid.value;
            $http.post(CONTEXT_PATH+"/menu/add", $scope.fields)
                .success(function(data, status, headers, config) {
                	$location.path('/menu');
                })
                .error(function(response, status, headers, config) {
                    console.log(response)
                });
    	};
    	
    };
    sysMenuAddController.$inject = injectParams;
    app.register.controller('sysMenuAddController', sysMenuAddController);
});