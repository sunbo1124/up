<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>CSP TSC Admin</title>
    
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Neon Admin Panel" />
	<script type="text/javascript">
		window.SERVLET_CONTEXT_PATH = "<%=request.getContextPath()%>";
	</script>
	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/font-icons/entypo/css/entypo.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/bower_components/font-awesome/css/font-awesome.min.css" type="text/css"/>
	<!-- <link href="<%=request.getContextPath() %>/bower_components/bootstrap/dist/css/bootstrap.min.css" type="text/css" rel="stylesheet" /> -->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.css" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/neon-core.css" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/neon-theme.css" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/neon-forms.css" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/custom.css" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/prism/prism.css" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/js/select2/select2-bootstrap.css" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/js/select2/select2.css" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/bower_components/flag-icon-css/css/flag-icon.min.css" type="text/css"/>
	<!--[if lt IE 9]><script src="assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<style>
		html,body {
			font-family: "微软雅黑";
		}
		
		.pagination > li > a, .pagination > li > span {
		    position: relative;
		    float: left;
		    padding: 6px 12px;
		    line-height: 1;
		    text-decoration: none;
		    background-color: #ffffff;
		    border: 1px solid #dddddd;
		    margin-left: -1px;
		}
		
		.dataTables_wrapper table thead tr th {
		    font-weight: bold;
		    outline: none;
		    cursor: default;
		    font-size: 12px;
		    -moz-transition: all 300ms ease-in-out;
		    -o-transition: all 300ms ease-in-out;
		    -webkit-transition: all 300ms ease-in-out;
		    transition: all 300ms ease-in-out;
		}
		
		.page-body .datatable.table tbody td, .page-body .datatable.table tbody th {
		    vertical-align: middle;
		    font-size: 10px;
		}
		
	</style>
</head>
<body class="page-body">
	<!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
	<div class="page-container">
		<div menu-directive id="menu_directive"></div>
		<div class="main-content">
			<div class="row">
				<!-- Profile Info and Notifications -->
				<div class="col-md-6 col-sm-4 clearfix hidden-xs">
					<ul class="list-inline links-list pull-right">
						<!-- Profile Info -->
						<li class="sep"></li>
						<li>
							<span ng-bind=" 'i18n.MENU.LoginName' | translate" ></span> <span> : &nbsp;&nbsp;<B>administartor</B></span>
						</li>
						<li class="sep"></li>
						<li>
							<a href="<%=request.getContextPath()%>/login/" >
								<span ng-bind=" 'i18n.LOGOUT' | translate"/> <i class="entypo-logout right"></i>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<hr />
			<ol class="breadcrumb bc-3">
				<li ng-repeat="sm in secondMenus">
					<a href="{{sm.href}}"><i class="{{sm.icon}}"></i><span ng-bind="{{sm.translatekey}} | translate"></span></a>
				</li>
			</ol>
			<div class="row">
				<div class="col-md-12">
					<div ng-view>
					
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script data-main="<%=request.getContextPath() %>/main" src="<%=request.getContextPath() %>/bower_components/requirejs/require.js"></script>
	<script data-main="<%=request.getContextPath() %>/main" src="<%=request.getContextPath() %>/dwr/engine.js"></script>
	<script src="<%=request.getContextPath() %>/bower_components/prism/prism.js"></script>

</html>
