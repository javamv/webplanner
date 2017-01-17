<!DOCTYPE HTML>
<!--
	Dimension by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
	<title>Promo Planner</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="../assets/css/main.css" />
	<!--[if lte IE 9]><link rel="stylesheet" href="../assets/css/ie9.css" /><![endif]-->
	<noscript><link rel="stylesheet" href="../assets/css/noscript.css" /></noscript>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js">
	</script>
	<script src="https://apis.google.com/js/client:platform.js?onload=start" async defer>
	</script>
	<script>
    function start() {
      gapi.load('auth2', function() {
        auth2 = gapi.auth2.init({
          client_id: '1006649864845-iborip4tjnfg7pu6ub53dmln4dkqgutl.apps.googleusercontent.com',
          // Scopes to request in addition to 'profile' and 'email'
          //scope: 'additional_scope'
        });
      });
    }
  </script>

</head>
<body>

<!-- Wrapper -->
<div id="wrapper">

	<!-- Header -->
	<header id="header">
		<div class="logo"><span class="icon fa-diamond"></span></div>
		<div class="content">
			<div class="inner">
				<h1>Webinar Planner</h1>
				<p>A fully responsive tool designed for automation of the webinar promotional activities</p>
				<form method="post" action="auth" hidden>
					<p hidden>
						<input type="text" name="userId" id="userId" value="${userId}"/>
					</p>
				</form>
			</div>
		</div>

		<button id="signinButton">Sign in with Google</button>
		<script>
		  $('#signinButton').click(function() {
			// signInCallback defined in step 6.
			auth2.grantOfflineAccess({'redirect_uri': 'postmessage'}).then(signInCallback);
		  });
		</script>

		<script>
			function signInCallback(authResult) {
			  if (authResult['code']) {

				// Hide the sign-in button now that the user is authorized, for example:
				$('#signinButton').attr('style', 'display: none');

				// Send the code to the server
				$.post("auth",
                    {
                    	${_csrf.parameterName}:"${_csrf.token}",
                        code:authResult['code']
                    },
                    function(data, status){
						window.location.href = "main";
                    });
              }
			}
			</script>


	</header>

	<!-- Footer -->
	<footer id="footer">
		<p class="copyright">&copy; Untitled. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
	</footer>

</div>

<!-- BG -->
<div id="bg"></div>

<!-- Scripts -->
<!--<script src="../assets/js/jquery.min.js"></script>-->
<script src="../assets/js/skel.min.js"></script>
<script src="../assets/js/util.js"></script>
<script src="../assets/js/main.js"></script>

</body>
</html>