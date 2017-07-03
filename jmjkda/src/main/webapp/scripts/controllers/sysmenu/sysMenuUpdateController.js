define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$routeParams', '$location','menuService','CONTEXT_PATH'];
    var sysMenuUpdateController = function($scope, $http, $routeParams, $location, menuService,CONTEXT_PATH) {
        $scope.fields = {};
        $scope.menuId = $routeParams.menuId;
        
    	menuService.queryList().then(function(data) {
    		$scope.pidItems = data.data;
    		if($scope.scopelocale=='US'){
    			for(var i=0;i<$scope.pidItems.length;i++){
    				if($scope.pidItems[i].id == 10){
    					$scope.pidItems[i].id = 0;
    				}
    			}
    		}
    	}, function(error) {
    		throw error;
    	});
        
        menuService.queryById($scope.menuId).then(function(data) {
        	$scope.fields = data;        	
        }, function(error) {
        	throw error;
        });
        
    	$scope.submit = function() {
    		if(!$scope.menuForm.$valid){
    			return false;
    		}
            $http.put(CONTEXT_PATH+"/menu/update", $scope.fields)
                .success(function(data, status, headers, config) {
                    console.log(data)
                    $location.path("/menu");
                })
                .error(function(response, status, headers, config) {
                    console.log(response)
                });
    	};
    	
        $scope.validateBeforSubmit = function(fields) {
            $scope.fields.sex = $scope.fields.sex.value;
        };
    };
    sysMenuUpdateController.$inject = injectParams;
    app.register.controller('sysMenuUpdateController', sysMenuUpdateController);
});