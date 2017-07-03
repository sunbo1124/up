define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$location','$uibModal','roleService','menuService','allotMenuService','$window', 'CONTEXT_PATH'];
    var sysAllotMenuListController = function($scope, $http, $location, $uibModal, roleService, menuService, allotMenuService, $window, contextPath) {
    	$scope.secondMenuNode = {index:1, node:{href:contextPath+"/index/#/allotmenu",icon:"",translatekey:"'i18n.MENU.ALLOTMENU.MANAGER'"}};
    	$scope.$on('$routeChangeSuccess', function(e, current, pre) {
            if($scope.secondMenuNode) {
                var index = current.scope.secondMenuNode.index;            
                $scope.$parent.secondMenus[index] = $scope.secondMenuNode.node;
            }
        })   
        $scope.fields = {};
    	
        roleService.queryList().then(function(data) {
    		$scope.tableData = data.data;
    	}, function(error) {
    		throw error;
    	});
    	
    	menuService.queryList().then(function(data) {
    		$scope.ulData = data.data;
    	}, function(error) {
    		throw error;
    	});
    	
		$scope.openDelModal = function(templateUrl) {
			$scope.modalInstance = $uibModal.open({
		    	animation: true,
		    	templateUrl: contextPath+templateUrl,
		    	scope: $scope,
		    	size: "sm"
	    	});
		}
		
		$scope.selectRole = function(roleId) {
			allotMenuService.queryById(roleId).then(function(data) {
	    		$scope.ulData = data.data;
	    	}, function(error) {
	    		throw error;
	    	});
		}

    	$scope.updateSelection = function($event, menuId) {
    		if(!menuId || !$scope.fields.roleId){
    			$scope.modalInstance = $uibModal.open({
    		    	animation: true,
    		    	templateUrl: contextPath+'/views/allotmenu/selectRole.html',
    		    	scope: $scope,
    		    	size: "sm"
    	    	});
    			return ;
    		}
			var checkbox = $event.target;
			var action = (checkbox.checked ? 'add' : 'remove');
			if(action == "remove"){
	 	    	$http['delete'](contextPath+"/allotMenu/delete",{params: {"roleId": $scope.fields.roleId,"menuId":menuId}})
		    	  .success(function(data, status, headers, config) {
		    		  console.log(data);
		 	      })
		 	      .error(function(response, status, headers, config) {
		 	    	  console.log(response)
		 	      });	
			}else{
				$http['delete'](contextPath+"/allotMenu/add",{params: {"roleId": $scope.fields.roleId,"menuId":menuId}})
		    	  .success(function(data, status, headers, config) {
		    		  console.log(data);
		 	      })
		 	      .error(function(response, status, headers, config) {
		 	    	  console.log(response)
		 	      });	
			}
		}
    	
		$scope.ok = function() {
			 $scope.modalInstance.close();
			 $location.path("#/allotMenu");
	 	 };
	 	 
	 	 $scope.cancel = function() {
				$scope.modalInstance.close();
	 	 };
    	
    	
    };
    sysAllotMenuListController.$inject = injectParams;
    app.register.controller('sysAllotMenuListController', sysAllotMenuListController);
});