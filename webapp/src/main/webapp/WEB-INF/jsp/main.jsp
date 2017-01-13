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
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<meta name="google-signin-client_id" content="1006649864845-iborip4tjnfg7pu6ub53dmln4dkqgutl.apps.googleusercontent.com">
</head>
<body>

<!-- Wrapper -->
<div id="wrapper">

	<!-- Header -->
	<header id="header">
		<div>
			<a href="#profile"><img src="${userImage}" height="100" width="100"/></a>
		</div>
		<div class="content">
			<div class="inner">
				<h1>Webinar Planner</h1>
				<p>A fully responsive tool designed for automation of the webinar promotional activities</p>
			</div>
		</div>
		<nav>
			<ul>
				<li><a href="#create">CREATE</a></li>
				<li><a href="#update" id="updateButton">UPDATE</a></li>
			</ul>
		</nav>
	</header>

	<!-- Main -->
	<div id="main">

		<!-- Create -->
		<article id="create">
			<h2 class="major">New Webinar</h2>
			<form method="post" action="createWebinar">
				<div class="field">
					<label for="topic_eng">Topic Name</label>
					<input type="text" name="topic_eng" id="topic_eng" />
				</div>
				<div class="field">
                    <label for="imageLink">Image Link</label>
                    <input type="text" name="imageLink" id="imageLink" />
                </div>
                <div class="field">
                    <label for="language">Language</label>
                    <input type="text" name="language" id="language" />
                </div>
                <div class="field">
                    <label for="targetAudience">Target Audience</label>
                    <input type="text" name="targetAudience" id="targetAudience" />
                </div>
				<div class="field">
					<label for="description_eng">Description</label>
					<textarea name="description_eng" id="description_eng" rows="3"></textarea>
				</div>
                <p hidden>
                    <input type="text" name="userId" id="userId" value="${userId}"/>
                </p>
				<ul class="actions">
					<li><input type="submit" value="Submit" class="special" /></li>
					<li><input type="reset" value="Reset" /></li>
				</ul>
			</form>
			<!--								<ul class="icons">
                                                <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
                                                <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
                                                <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
                                                <li><a href="#" class="icon fa-github"><span class="label">GitHub</span></a></li>
                                            </ul>-->
		</article>

		<!-- Update -->
		<article id="update">
			<h2 class="major">Select Existing Webinars</h2>
			<section>
				<h3 class="major">Table</h3>
				<h4>Default</h4>
				<div class="table-wrapper">
					<table>
						<thead>
						<tr>
							<th>Topic</th>
							<th>Planned Date</th>
						</tr>
						</thead>
						<tbody id="webinarList">
						</tfoot>
					</table>
				</div>
            </section>
		</article>

        <article id="profile">
            <h2 class="major">Profile Update</h2>
            <form method="post" action="profile">
                <div class="field">
                    <label for="luxEmail">Luxoft E-mail</label>
                    <input type="text" name="luxEmail" id="luxEmail" value="${luxEmail}"/>
                </div>
                <div class="field">
                    <label for="signature">E-mail signature</label>
                    <textarea name="signature" id="signature" rows="3">${signature}</textarea>
                </div>
                <p hidden>
                    <input type="text" name="userId" id="userId" value="${userId}"/>
                </p>
                <ul class="actions">
                    <li><input type="submit" value="Submit" class="special" /></li>
                    <li><input type="reset" value="Reset" /></li>
                </ul>
            </form>
            <div class="g-signin2" data-onsuccess="onSignIn" hidden></div>
            <div>
                <a onclick="signOut();">Sign out</a>
            <div>
            <script>
              function signOut() {
                var auth2 = gapi.auth2.getAuthInstance();
                auth2.signOut();
                window.location.href = "../#";
              }
            </script>
        </article>

	</div>

	<!-- Footer -->
	<footer id="footer">
		<p class="copyright">&copy; Untitled. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
	</footer>

</div>

<!-- BG -->
<div id="bg"></div>

<!-- Scripts -->
<script src="../assets/js/jquery.min.js"></script>
<script src="../assets/js/skel.min.js"></script>
<script src="../assets/js/util.js"></script>
<script src="../assets/js/main.js"></script>

</body>
</html>