define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$location','$uibModal','userService', 'CONTEXT_PATH'];
    var sysUserAddController =function($scope, $http, $location, $uibModal, userService, CONTEXT_PATH) {
        $scope.fields = {};
        $scope.sexItems = [
            {name: '男', value: '0'},
            {name: '女', value: '1'}
        ];
        $scope.exits = false;
        $scope.checkLoginName = function(){
        	var loginName = $scope.userForm.loginName.$modelValue;
        	console.log($scope.userForm.loginName.$modelValue);
        	console.log(loginName);
        	$http.post(CONTEXT_PATH+"/user/queryLoginName",loginName)
        		.success(function(data,status,status,config){
        			console.log(data);
        			if(data==1){
        				$scope.exits = true;
        			}
        			if(data==0){
        				$scope.exits = false;
        			}
        			
        		})
        		.error(function(response,status,headers,config){
        			console.log(response);
        		})
        };
        
    	$scope.submit = function() {
    		console.log($scope.fields);
    		
    		if(!$scope.userForm.$valid){
    			return false;
    		}
            $http.post(CONTEXT_PATH+"/user/", $scope.fields)
                .success(function(data, status, headers, config) {
                	console.log(data);
                	console.log(status);
                	console.log(headers);
                	console.log(config);
                    $location.path("/user");
                })
                .error(function(response, status, headers, config) {
                    console.log(response)
                });
    	};

        $scope.validateBeforSubmit = function(fields) {
            $scope.fields.sex = $scope.fields.sex.value;
        };
    };
    sysUserAddController.$inject = injectParams;
    app.register.controller('sysUserAddController', sysUserAddController);
});