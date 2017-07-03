define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$location','$uibModal','roleService','$window', 'CONTEXT_PATH'];
    var sysRoleListController = function($scope, $http, $location, $uibModal, roleService,$window, contextPath) {
    	$scope.secondMenuNode = {index:1, node:{href:contextPath+"/index/#/role",icon:"",translatekey:"'i18n.MENU.ROLE.MANAGER'"}};
    	$scope.$on('$routeChangeSuccess', function(e, current, pre) {
            if($scope.secondMenuNode) {
                var index = current.scope.secondMenuNode.index;            
                $scope.$parent.secondMenus[index] = $scope.secondMenuNode.node;
            }
        })   
        
    	roleService.queryList().then(function(data) {
    		$scope.tableData = data.data;
    		console.log($scope.tableData);
    	}, function(error) {
    		throw error;
    	});
    	
    	
		$scope.openDelModal = function(templateUrl,roleId) {
			$scope.roleId = roleId;
			$scope.modalInstance = $uibModal.open({
		    	animation: true,
		    	templateUrl: contextPath+templateUrl,
		    	scope: $scope,
		    	size: "sm"
	    	});
		}
		
		$scope.ok = function() {
			 $scope.modalInstance.close();
	 	    	$http['delete'](contextPath+"/role/delete/",{params: {"id": $scope.roleId}})
	 	    	  .success(function(data, status, headers, config) {
	 	    		  $window.location.reload();
	 	    		  console.log("page reloaded")
	 	    		  console.log(data);
		 	      })
		 	      .error(function(response, status, headers, config) {
		 	    	  console.log(response)
		 	      });
	 	 };
	 	$scope.cancel = function() {
 			$scope.modalInstance.close();
 		};
    };
    sysRoleListController.$inject = injectParams;
    app.register.controller('sysRoleListController', sysRoleListController);
});