<!DOCTYPE HTML>
<!--
	Dimension by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
	<title>Dimension by HTML5 UP</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="assets/css/main.css" />
	<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
	<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<meta name="google-signin-client_id" content="1006649864845-iborip4tjnfg7pu6ub53dmln4dkqgutl.apps.googleusercontent.com">
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
			</div>
		</div>
		<div class="g-signin2" data-onsuccess="onSignIn"></div>
        <script>
            function onSignIn(googleUser) {
                var profile = googleUser.getBasicProfile();
                console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
                console.log('Name: ' + profile.getName());
                console.log('Image URL: ' + profile.getImageUrl());
                console.log('Email: ' + profile.getEmail());
                if (profile != null) {
                    $.post("api/auth",
                    {
                        id: profile.getId(),
                        name: profile.getName(),
                        image: profile.getImageUrl(),
                        email: profile.getEmail()
                    },
                    function(data, status){
                        window.location.href = "api/main?id="+data;
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
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/skel.min.js"></script>
<script src="assets/js/util.js"></script>
<script src="assets/js/main.js"></script>

</body>
</html>