define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$location','$uibModal','healthRecordService','$window', 'CONTEXT_PATH'];
    var healthRecordListController = function($scope, $http, $location, $uibModal, healthRecordService,$window, contextPath) {
    	$scope.secondMenuNode = {index:1, node:{href:contextPath+"/index/#/healthRecord",icon:"",translatekey:"'i18n.healthRecord.manager'"}};
    	$scope.$on('$routeChangeSuccess', function(e, current, pre) {
            if($scope.secondMenuNode) {
                var index = current.scope.secondMenuNode.index;            
                $scope.$parent.secondMenus[index] = $scope.secondMenuNode.node;
            }
        })   
        
    	healthRecordService.queryList().then(function(data) {
    		$scope.tableData = data.data;
    		console.log($scope.tableData);
    	}, function(error) {
    		throw error;
    	});
    	
    	
		$scope.openDelModal = function(templateUrl,healthRecordId) {
			$scope.healthRecordId = healthRecordId;
			$scope.modalInstance = $uibModal.open({
		    	animation: true,
		    	templateUrl: contextPath+templateUrl,
		    	scope: $scope,
		    	size: "sm"
	    	});
		}
		
		$scope.ok = function() {
			 $scope.modalInstance.close();
	 	    	$http['delete'](contextPath+"/healthRecord/delete/",{params: {"id": $scope.healthRecordId}})
	 	    	  .success(function(data, status, headers, config) {
	 	    		  $window.location.reload();
	 	    		  console.log("page healthRecordIdded")
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
    healthRecordListController.$inject = injectParams;
    app.register.controller('healthRecordListController', healthRecordListController);
});