define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$location','$uibModal','roleService', 'CONTEXT_PATH'];
    var sysRoleAddController =function($scope, $http, $location, $uibModal, roleService, CONTEXT_PATH) {
        $scope.fields = {};
        $scope.isBuiltinItems = [
                           {name: '是', value: true},
                           {name: '否', value: false}
                       ];
    	$scope.submit = function() {
    		console.log($scope.fields);
            $http.post(CONTEXT_PATH+"/role/add", $scope.fields)
                .success(function(data, status, headers, config) {
                    $location.path("/role");
                })
                .error(function(response, status, headers, config) {
                    console.log(response)
                });
    	};

        $scope.validateBeforSubmit = function(fields) {
            $scope.fields.isBuiltin = $scope.fields.isBuiltin.value;
        };
    };
    sysRoleAddController.$inject = injectParams;
    app.register.controller('sysRoleAddController', sysRoleAddController);
});