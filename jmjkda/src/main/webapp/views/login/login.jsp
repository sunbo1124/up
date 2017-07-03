<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Neon Admin Panel" />
	<meta name="author" content="" />
	
	<title>TSC | Login</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/font-icons/entypo/css/entypo.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/neon-core.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/neon-theme.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/neon-forms.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/custom.css">
	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/js/select2/select2-bootstrap.css" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/js/select2/select2.css" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/bower_components/flag-icon-css/css/flag-icon.min.css" type="text/css"/>
	<script src="<%=request.getContextPath() %>/assets/js/jquery-1.11.0.min.js"></script>
	<!--[if lt IE 9]><script src="assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body class="page-body login-page login-form-fall">
<script type="text/javascript">
	var baseurl = "<%=request.getContextPath() %>";
</script>
<div class="login-container">
	<div class="login-header login-caret">
		<div class="login-content">
			<a href="index.html" class="logo" style="font-size: 28px;color:white;">
				Geely Telematics Service Controller Portal
			</a>
			<!-- progress bar indicator -->
			<div class="login-progressbar-indicator">
				<h3>43%</h3>
				<span>logging in...</span>
			</div>
		</div>
	</div>
	<div class="login-progressbar">
		<div></div>
	</div>
	<div class="login-form">
		<div class="login-content">
			<div class="form-login-error">
				<h3>Invalid login</h3>
				<p><strong>LoginName</strong> or <strong>Password</strong> error.</p>
			</div>
			<form method="post" role="form" id="form_login">
			
			<div>
				<label>
					<strong>Language:</strong>
				</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label>
					<span id="cn" class="flag-icon flag-icon-cn" style="border-style:solid;border-width:2px;"  onclick="changeStyle('cn')"/>
					<input type="radio" id="locale1" name="locale" checked="checked" value="CN" hidden >
				</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label>
					<span id="us" class="flag-icon flag-icon-gb" onclick="changeStyle(us)"></span>
					<input type="radio" id="locale2" name="locale" value="US" hidden>
				</label>
			</div>
			<br/>
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">
							<i class="entypo-user"></i>
						</div>
						<input type="text" class="form-control" name="username" id="username" placeholder="Username" autocomplete="off" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">
							<i class="entypo-key"></i>
						</div>
						<input type="password" class="form-control" name="password" id="password" placeholder="Password" autocomplete="off" />
					</div>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary btn-block btn-login">
						<i class="entypo-login"></i>
						Login In
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
function changeStyle(id){
	if(id == 'cn'){
		var obj1 = document.getElementById("cn");
		var obj2 = document.getElementById("us");
		
	}else{
		var obj1 = document.getElementById("us");
		var obj2 = document.getElementById("cn");
	}
	obj1.style.borderStyle="solid";
	obj1.style.borderWidth="2px";
	obj2.style.borderStyle="";
	obj2.style.borderWidth="";
}
</script>
<script src="<%=request.getContextPath() %>/assets/js/gsap/main-gsap.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/bootstrap.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/joinable.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/resizeable.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/neon-api.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/neon-login.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/neon-custom.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/neon-demo.js"></script>
</html>