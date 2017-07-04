define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$location','$uibModal','menuService','$window', 'CONTEXT_PATH'];
    var sysMenuListController = function($scope, $http, $location, $uibModal, menuService,$window, contextPath) {
    	
    	$scope.secondMenuNode = {index:1, node:{href:contextPath+"/index/#/menu",icon:"",translatekey:"'i18n.MENU.MENU.MANAGER'"}};

    	$scope.$on('$routeChangeSuccess', function(e, current, pre) {
            if($scope.secondMenuNode) {
                var index = current.scope.secondMenuNode.index;            
                $scope.$parent.secondMenus[index] = $scope.secondMenuNode.node;
            }
        })   
    	
    	menuService.queryList().then(function(data) {
    		$scope.tableData = data.data;
    		if(data.data.length){
    			for(var i=0;i<$scope.tableData.length;i++){
    				if($scope.tableData[i].type == 1){
    					$scope.tableData[i].type = '菜单';
    				}else if($scope.tableData[i].type == 0){
    					$scope.tableData[i].type = '按鈕';
    				}
    			}
    		}
    	}, function(error) {
    		throw error;
    	});
    	
		$scope.openDelModal = function(templateUrl,menuId) {
			$scope.menuId = menuId;
			$scope.modalInstance = $uibModal.open({
		    	animation: true,
		    	templateUrl: contextPath+templateUrl,
		    	scope: $scope,
		    	size: "sm"
	    	});
		}
		
		$scope.ok = function() {
			 $scope.modalInstance.close();
	 	    	$http['delete'](contextPath+"/menu/delete/",{params: {"id": $scope.menuId}})
	 	    	  .success(function(data, status, headers, config) {
	 	    		  $window.location.reload();
		 	      })
		 	      .error(function(response, status, headers, config) {
		 	    	  console.log(response)
		 	      });
	 	 };
	 	 
		$scope.cancel = function() {
			$scope.modalInstance.close();
		 };
    };
    sysMenuListController.$inject = injectParams;
    app.register.controller('sysMenuListController', sysMenuListController);
});