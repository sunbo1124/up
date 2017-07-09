define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$location','$uibModal','healthRecordService', 'CONTEXT_PATH'];
    var healthRecordAddController =function($scope, $http, $location, $uibModal, healthRecordService, CONTEXT_PATH) {
        $scope.fields = {};
        
        $scope.sexItems = [
                           {name: '男', value: '男'},
                           {name: '女', value: '女'}
                       ];
        
        $scope.bloodTypeItems = [
                                     {name: 'A型', value: 'A型'},
                                     {name: 'B型', value: 'B型'},
                                     {name: 'AB型', value: 'AB型'},
                                     {name: '不详', value: '不详'}
                                 ];
        
        $scope.selectionPermanentType = function($event, menuId) {
        	var checkbox = $event.target;
        	var action = (checkbox.checked ? 'add' : 'remove');
        	$scope.fields.permanentType = $scope.fields.permanentType;
        	if(action == "remove"){
        		var arr=$scope.fields.permanentType.split(",");
        		$scope.fields.permanentType = "";
        		for(var i=0;i<arr.length;i++){
        			if(arr[i]!=menuId){
        				if($scope.fields.permanentType==""){
        					$scope.fields.permanentType = arr[i];
        				}else{
        					$scope.fields.permanentType = $scope.fields.permanentType + "," + arr[i];
        				}
        			}
        		}
        	}else{
        		if($scope.fields.permanentType){
        			$scope.fields.permanentType = $scope.fields.permanentType + ","+ menuId;
        		}else{
        			$scope.fields.permanentType = menuId;
        		}
        	}
        }
        
        $scope.selectionPayType = function($event, menuId) {
        	var checkbox = $event.target;
        	var action = (checkbox.checked ? 'add' : 'remove');
        	$scope.fields.payType = $scope.fields.payType;
        	if(action == "remove"){
        		var arr=$scope.fields.payType.split(",");
        		$scope.fields.payType = "";
        		for(var i=0;i<arr.length;i++){
        			if(arr[i]!=menuId){
        				if($scope.fields.payType==""){
        					$scope.fields.payType = arr[i];
        				}else{
        					$scope.fields.payType = $scope.fields.payType + "," + arr[i];
        				}
        			}
        		}
        	}else{
        		if($scope.fields.payType){
        			$scope.fields.payType = $scope.fields.payType + ","+ menuId;
        		}else{
        			$scope.fields.payType = menuId;
        		}
        	}
        }
        
    	$scope.submit = function() {
    		console.log($scope.fields.permanentType.checked );
            $http.post(CONTEXT_PATH+"/healthRecord/add", $scope.fields)
                .success(function(data, status, headers, config) {
                    $location.path("/healthRecord");
                })
                .error(function(response, status, headers, config) {
                    console.log(response)
                });
    	};

        $scope.validateBeforSubmit = function(fields) {
            $scope.fields.isBuiltin = $scope.fields.isBuiltin.value;
        };
    };
    healthRecordAddController.$inject = injectParams;
    app.register.controller('healthRecordAddController', healthRecordAddController);
});