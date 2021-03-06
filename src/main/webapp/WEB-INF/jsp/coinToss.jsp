<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Cassino - Toss Coin</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Bootstrap theme -->
<link href="/css/bootstrap-theme.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/dashboard.css" rel="stylesheet">
<link href="/css/theme.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Menu <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/findUser">Find User</a></li>
							<li><a href="/coinToss">Coin Toss</a></li>
							<li><a href="#">Something else here</a></li>
							<li role="separator" class="divider"></li>
							<li class="dropdown-header">Nav header</li>
							<li><a href="#">Separated link</a></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><form:form method="GET" action="/deposit">
							<input style="margin-top: 7px" value="Deposit" type="submit"
								class="btn btn-success" />
						</form:form></li>
					<li><a href="/dashboard">Dashboard</a></li>
					<li><a href="/settings">Settings</a></li>
					<li><a href="#">${loggedUser.login}</a></li>
					<li><a href="#">Help</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid centered">
		<div class="row">
			<div style="bottom: 9%" class="sidebar">
				<span>Wran: lel</span>
				<br />

			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">Coin Toss</h1>

				<div class="row placeholders">
					<div class="col-xs-6 col-sm-3 placeholder centered2">
						<div>
							<h3>Your coins: ${loggedUser.coins}</h3>
							<form:form method="POST" action="/coinToss">
								<h2>Red</h2>
								<div class="form-group">
									<input type="hidden" name="choice" value="Red" /> 
									<input
										type="text" placeholder="Enter amount" class="form-control centered2"
										style="width:15%"
										name="coins"
										onkeypress="return event.charCode >= 48 && event.charCode <= 57"
										autofocus />
								</div>
								<br />
								<div class="form-group">
									<input value="Toss" type="submit" class="btn btn-success" />
								</div>
							</form:form>
						</div>
						<div>
							<form:form method="POST" action="/coinToss">
								<h2>Blue</h2>
								<div class="form-group">
									<input type="hidden" name="choice" value="Blue"> <input
										type="text" placeholder="Enter amount" class="form-control centered2"
										style="width:15%"
										name="coins"
										onkeypress="return event.charCode >= 48 && event.charCode <= 57"
										autofocus />
								</div>
								<br />
								<div class="form-group">
									<input value="Toss" type="submit" class="btn btn-success" />
								</div>
							</form:form>
						</div>

						<h2>${result}</h2>
						<h2>${coinsError}</h2>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="/js/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
