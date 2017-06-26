define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$location','$uibModal','userService','$window', 'CONTEXT_PATH'];
    var sysUserListController = function($scope, $http, $location, $uibModal, userService,$window, contextPath) {
    	$scope.secondMenuNode = {index:1, node:{href:contextPath+"/index/#/user",icon:"",translatekey:"'i18n.MENU.USER.MANAGER'"}};
    	$scope.$on('$routeChangeSuccess', function(e, current, pre) {
            if($scope.secondMenuNode) {
                var index = current.scope.secondMenuNode.index;            
                $scope.$parent.secondMenus[index] = $scope.secondMenuNode.node;
            }
        })   
        
    	userService.queryList().then(function(data) {
    		$scope.tableData = data.data;
    		console.log($scope.tableData);
    	}, function(error) {
    		throw error;
    	});
    	
    	
		$scope.openDelModal = function(templateUrl,userId) {
			$scope.userId = userId;
			$scope.modalInstance = $uibModal.open({
		    	animation: true,
		    	templateUrl: contextPath+templateUrl,
		    	scope: $scope,
		    	size: "sm"
	    	});
		}
		
		$scope.ok = function() {
			 $scope.modalInstance.close();
	 	    	$http['delete'](contextPath+"/user/delete/",{params: {"id": $scope.userId}})
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
    sysUserListController.$inject = injectParams;
    app.register.controller('sysUserListController', sysUserListController);
});