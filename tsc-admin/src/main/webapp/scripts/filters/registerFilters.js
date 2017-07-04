'use strict';
define([ 'app' ], function(app) {
	
	app.filter('substringFilter', function() {
		return function(value) {
			if(!value)return value;
			var temp = value;
			if (temp.length > 20) {
				return temp.substr(0, 20) + "...";
			} else {
				return temp;
			}
		}
	});
	
	app.filter('notUseFilter', function() {
		return function(value) {
			if (value == parseInt(0)) {
				return '使用';
			} else {
				return '未使用';
			} 
		}
	});
	
	app.filter('serviceAppgroupFilter', function() {
		return function(value) {
			if (value == parseInt(1)) {
				return '广告';
			} else if(value == parseInt(2)){
				return '远程操作';
			} else if(value == parseInt(3)){
				return '警告';
				
			} else if(value == parseInt(4)){
				return '其他';
			}
		}
	});
	
	app.filter('platformFilter', function() {
		return function(value) {
			if (value == 0) {
				return 'CMA';
			} else {
				return 'Non-CMA';
			} 
		}
	});
	
	app.filter('paramValueScopeFilter', function() {
		return function(value) {
			if (value == 0) {
				return '全局';
			} else {
				return '应用';
			} 
		}
	});
	
	app.filter('trueORfalseFilter', function() {
		return function(value) {
			if (value == 1) {
				return '是';
			} else {
				return '否';
			} 
		}
	});
	
	app.filter('nameReplaceIdFilter', function() {
		return function(data1,data2) {
			for(var i=0 ; i<data1.length;i++){
				for(var j=0 ; j<data2.length;j++){
   					if(data1[i].groupId == data2[j].id){
   						data1[i].groupId = data2[j].appGroupName;
	    			}
    			}
			}
		}
	});
	
	app.filter('colorReplaceIdFilter', function() {
		return function(data1,data2) {
			for(var i=0 ; i<data1.length;i++){
				for(var j=0 ; j<data2.length;j++){
   					if(data1[i].colorCode == data2[j].colorCode){
   						data1[i].colorCode = data2[j].color;
	    			}
    			}
			}
		}
	});
	
	app.filter('appNameReplaceAppIdFilter', function() {
		return function(data1,data2) {
			for(var i=0 ; i<data1.length;i++){
				for(var j=0 ; j<data2.length;j++){
   					if(data1[i].appId == data2[j].id){
   						data1[i].appId = data2[j].appName;
	    			}
    			}
			}
		}
	});
	
	app.filter('vendorNameReplaceTemTypeFilter', function() {
		return function(data1,data2) {
			for(var i=0 ; i<data1.length;i++){
				for(var j=0 ; j<data2.length;j++){
   					if(data1[i].temType == data2[j].id){
   						data1[i].temType = data2[j].vendorName;
	    			}
    			}
			}
		}
	});
	
	app.filter('userNameReplaceUserIdFilter', function() {
		return function(data1,data2) {
			for(var i=0 ; i<data1.length;i++){
				for(var j=0 ; j<data2.length;j++){
   					if(data1[i].userId == data2[j].id){
   						data1[i].userId = data2[j].name;
	    			}
    			}
			}
		}
	});
	
	app.filter('roleNameReplaceRoleIdFilter', function() {
		return function(data1,data2) {
			for(var i=0 ; i<data1.length;i++){
				for(var j=0 ; j<data2.length;j++){
   					if(data1[i].roleId == data2[j].id){
   						data1[i].roleId = data2[j].name;
	    			}
    			}
			}
		}
	});
	
});