define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$routeParams', '$location','userService','CONTEXT_PATH'];
    var sysUserUpdateController = function($scope, $http, $routeParams, $location, userService,CONTEXT_PATH) {
        $scope.fields = {};
        $scope.userId = $routeParams.userId;
        $scope.sexItems = [
                           {name: '男', value: '0'},
                           {name: '女', value: '1'}
                       ];
        userService.queryById($scope.userId).then(function(data) {
        	$scope.fields = data;   
        	if(data.sex==0){
        		console.log("is 0");
        		$scope.fields.sex = $scope.sexItems[0];
        	}else{
        		console.log("is 1");
        		$scope.fields.sex = $scope.sexItems[1];        		
        	}
        	     	
        }, function(error) {
        	throw error;
        });
        
    	$scope.submit = function() {
    		console.log(CONTEXT_PATH);
    		console.log($scope.userForm.confirmPassword);
    		console.log($scope.userForm.confirmPassword.$modelValue);
    		if(!$scope.userForm.$valid){
    			if($scope.fields.sex==0){
            		$scope.fields.sex = $scope.sexItems[0];
            	}else{
            		$scope.fields.sex = $scope.sexItems[1];        		
            	}
    			return false;
    		}
    		$http.put(CONTEXT_PATH+"/user/update", $scope.fields)
                .success(function(data, status, headers, config) {
                	$location.path("/user/");
                    console.log(data)
                })
                .error(function(response, status, headers, config) {
                    console.log(response)
                });
    	};
    	
        $scope.validateBeforSubmit = function(fields) {
            $scope.fields.sex = $scope.fields.sex.value;
        };
    };
    sysUserUpdateController.$inject = injectParams;
    app.register.controller('sysUserUpdateController', sysUserUpdateController);
});