define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$filter', '$location', '$uibModal', 'allotRoleService', 'roleService', 'userService', '$window', 'CONTEXT_PATH'];
    var allotRoleListController = function($scope, $http, $filter, $location, $uibModal, allotRoleService, roleService, userService, $window, contextPath) {
    	$scope.secondMenuNode = {index:1, node:{href:contextPath+"/index/#/allotRole",icon:"",translatekey:"'i18n.MENU.ROLE.MANAGER'"}};
    	$scope.$on('$routeChangeSuccess', function(e, current, pre) {
            if($scope.secondMenuNode) {
                var index = current.scope.secondMenuNode.index;            
                $scope.$parent.secondMenus[index] = $scope.secondMenuNode.node;
            }
        })   
        
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
    	
    	allotRoleService.queryList().then(function(data) {
    		$scope.tableData = data.data;
    		if($scope.tableData.length){
    			$filter('userNameReplaceUserIdFilter')($scope.tableData,$scope.userIdItems);
    			$filter('roleNameReplaceRoleIdFilter')($scope.tableData,$scope.roleIdItems);
    		}
    	}, function(error) {
    		throw error;
    	});
    	
    	
		$scope.openDelModal = function(templateUrl,allotRoleId) {
			$scope.allotRoleId = allotRoleId;
			$scope.modalInstance = $uibModal.open({
		    	animation: true,
		    	templateUrl: contextPath+templateUrl,
		    	scope: $scope,
		    	size: "sm"
	    	});
		}
		
		$scope.ok = function() {
			 $scope.modalInstance.close();
	 	    	$http['delete'](contextPath+"/allotRole/delete/",{params: {"id": $scope.allotRoleId}})
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
    allotRoleListController.$inject = injectParams;
    app.register.controller('allotRoleListController', allotRoleListController);
});