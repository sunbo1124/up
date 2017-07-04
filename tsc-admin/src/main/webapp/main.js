'use strict';

requirejs.config({
    baseUrl: '/tsc-admin',
    paths: {
        'jquery': 'bower_components/jquery/dist/jquery.min',
        'angular': 'bower_components/angular/angular.min',
        'angular-route': 'bower_components/angular-route/angular-route.min',
        'bootstrap-js':'bower_components/bootstrap/dist/js/bootstrap.min',
        'app': 'app',
        'route-resolver': 'scripts/routeResolver',
        'bootstrap-treeview': 'bower_components/bootstrap-treeview/public/js/bootstrap-treeview',
        'directive': 'scripts/directives/registerDirectives',
        'filter': 'scripts/filters/registerFilters',
        'datatables': 'bower_components/DataTables/media/js/jquery.dataTables',
        'angular-translate': 'bower_components/angular-translate/angular-translate.min',
        'angular-translate-loader-url': 'bower_components/angular-translate-loader-url/angular-translate-loader-url.min',
        'angular-translate-loader-static-files': 'bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.min',
        'main-gsap': 'assets/js/gsap/main-gsap',
        'resizeable': 'assets/js/resizeable',
        'neon-custom': 'assets/js/neon-custom',
        'joinable':'assets/js/joinable',
        'neon-api':'assets/js/neon-api',
        'select2': 'assets/js/select2/select2.min',
        'table-tools':'assets/js/datatables/TableTools.min',
        'dataTable-bootstrap':'assets/js/dataTables.bootstrap',
        'dataTable-columnFilter':'assets/js/datatables/jquery.dataTables.columnFilter',
        'dataTable-responsive':'assets/js/datatables/responsive/js/datatables.responsive',
        'lodash':'assets/js/datatables/lodash.min',
        'angular-shiro': 'bower_components/angular-shiro/dist/angular-shiro',
        'angular-mocks': 'bower_components/angular-mocks/angular-mocks',
        'ui-bootstrap':'bower_components/angular-bootstrap/ui-bootstrap.min',
        'ui-bootstrap-tpls':'bower_components/angular-bootstrap/ui-bootstrap-tpls.min',
        'angular-animate':'bower_components/angular-animate/angular-animate.min',
        'angular-validation-rule':'bower_components/angular-validation/dist/angular-validation-rule.min',
        'angular-validation':'bower_components/angular-validation/dist/angular-validation.min',
        'library': 'bower_components',
        'indexService': 'scripts/services/indexService',
        'userService': 'scripts/services/userService',
        'roleService':'scripts/services/sysrole/roleService',
        'menuService':'scripts/services/sysmenu/menuService',
        'allotMenuService':'scripts/services/allotmenu/allotMenuService',
        'allotRoleService':'scripts/services/allotrole/allotRoleService',
        'appContextPathService': 'scripts/services/info/appContextPathService'
    },
    shim:{
        'jquery': {
            exports:'jquery'
        },
        'angular':{
            exports:'angular'
        },
        'angular-route': {
            deps:['angular'],
            exports:'angular-route'
        },
        'bootstrap-js': {
            deps:['jquery'],
            exports: 'bootstrap-js'
        },
        'bootstrap-treeview': {
            deps: ['jquery']
        },
        'angular-translate': {
            deps: ['angular']
        },
        'angular-translate-loader-url': {
        	deps: ['angular-translate']
        },
        'angular-translate-loader-static-files': {
        	deps: ['angular-translate']
        },
        'main-gsap': {
        	deps: ['jquery'],
        	exports: 'main-gsap'
        },
        'resizeable': {
        	deps: ['jquery','bootstrap-js','joinable'],
        	exports: 'resizeable'
        },
        'joinable': {
        	deps:['jquery','main-gsap'],
        	exports: 'joinable'
        },
        'neon-custom': {
        	deps:['main-gsap', 'resizeable','joinable','neon-api'],
        	exports: 'neon-custom'
        },
        'neon-api': {
        	deps:['jquery'],
        	exports: 'neon-api'
        },
        'select2': {
        	deps:['jquery'],
        	exports: 'select2'
        },
        'table-tools': {
        	deps:['jquery', 'datatables'],
        	exports: 'table-tools'
        },
        'dataTable-bootstrap': {
        	deps:['jquery', 'datatables'],
        	exports: 'dataTable-bootstrap'
        },
        'dataTable-columnFilter': {
        	deps:['jquery', 'datatables'],
        	exports: 'dataTable-columnFilter'
        },
        'dataTable-responsive': {
        	deps:['jquery', 'datatables'],
        	exports: 'dataTable-responsive'
        },
        'lodash': {
        	deps:['jquery', 'datatables'],
        	exports: 'lodash'
        },
        'angular-shiro': {
        	deps:['angular'],
        	exports: 'angular-shiro'
        },
        'angular-mocks': {
        	deps:['angular'],
        	exports: 'angular-mocks'
        },
        'angular-animate': {
        	deps:['angular'],
        	exports: 'angular-animate'
        },
        'ui-bootstrap': {
        	deps:['angular-animate'],
        	exports: 'ui-bootstrap'
        },
        'ui-bootstrap-tpls': {
        	deps:['angular-animate'],
        	exports: 'ui-bootstrap-tpls'
        },
        'angular-validation': {
        	deps:['angular'],
        	exports: 'angular-validation'
        },
        'angular-validation-rule':{
        	deps:['angular-validation'],
        	exports: 'angular-validation-rule'
        }
    }
});

require(
	[
	 	'angular',
        'app',
        'route-resolver',
        'jquery',
        'bootstrap-js',
        'indexService',
        'userService',
        'appContextPathService',
        'roleService',
        'menuService',
        'allotMenuService',
        'allotRoleService',
        'directive',
        'filter',
        'angular-translate',
        'datatables',
        'angular-translate-loader-static-files',
        'select2',
        'dataTable-bootstrap',
        'dataTable-responsive',
        'lodash',
        'angular-shiro',
        'angular-mocks',
        'ui-bootstrap-tpls',
        'angular-validation-rule'
    ],
    function(angular, app) {
        angular.bootstrap(document, ['myApp']);
    }
);
