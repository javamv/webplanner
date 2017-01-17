<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<link rel="stylesheet" href="../assets/css/main.css" />
	<!--[if lte IE 9]><link rel="stylesheet" href="../assets/css/ie9.css" /><![endif]-->
	<noscript><link rel="stylesheet" href="../assets/css/noscript.css" /></noscript>
</head>
<body>.

<!-- Wrapper -->
<div id="wrapper">

	<!-- Header -->
	<header id="header">
		<div class="logo">
			<a href="main"><span class="icon fa-diamond"></span></a>
		</div>
		<div class="content">
			<div class="inner">
				<h1>${topic}</h1>
				<p>${description}</p>
			</div>
		</div>
		<nav>
			<ul>
				<c:if test="${scheduled == false}">
					<li><a href="#schedule">SCHEDULE GOTO</a></li>
				</c:if>
				<c:if test="${scheduled == true}">
					<li><a href="#scheduled">SCHEDULED</a></li>
				</c:if>
				<c:if test="${posted == false}">
					<li><a href="#post">CREATE POST</a></li>
				</c:if>
				<c:if test="${posted == true}">
					<li><a href="#posted">POSTED</a></li>
				</c:if>
				<li><a href="notifyLuxmarketing?id=${id}">MARKETING</a></li>
				<li><a href="notifyTC?id=${id}">TC MAIL LIST</a></li>
			</ul>
		</nav>
	</header>

	<!-- Main -->
	<div id="main">

		<!-- Schedule -->
		<article id="schedule">
			<h2 class="major">${topic}</h2>
			<form method="post" action="createGotoWebinar">
				<div class="field">
					<label for="date">Date (dd-mm-yyyy)</label>
					<input type="text" name="date" id="date" />
				</div>
				<div class="field half first">
					<label for="startTime">Start Time (hh:mm)</label>
					<input type="text" name="startTime" id="startTime" />
				</div>
				<div class="field half">
					<label for="endTime">End Time (hh:mm)</label>
					<input type="text" name="endTime" id="endTime" />
				</div>
				<p hidden>
					<input type="text" name="id" id="webinarId" value="${id}"/>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</p>
				<ul class="actions">
					<li><input type="submit" value="Submit" class="special" /></li>
					<li><input type="reset" value="Reset" /></li>
				</ul>
			</form>
		</article>

		<!-- Scheduled -->
		<article id="scheduled">
			<h2 class="major">GotoWebinar Manager</h2>
			<section>
				<h3 class="major">GotoWebinar Links</h3>
				<ul class="actions">
					<li><a href="${gotoManageLink}" class="button" target="_tab">Manage</a></li>
					<li><a href="${gotoLink}" class="button" target="_tab">Register</a></li>
				</ul>
			</section>
			<section>
				<h3 class="major">Cancel Event</h3>
				<ul class="actions">
					<li><a href="cancelGotoWebinar?id=${id}" class="button special">Cancel</a></li>
				</ul>
			</section>
		</article>

		<!-- Schedule -->
		<article id="post">
			<h2 class="major">${topic}</h2>
			<form method="post" action="createBlogPost" id="authForm">
				<div class="field">
					<label for="startTime">Luxtown Username</label>
					<input type="text" name="username" id="username" />
				</div>
				<div class="field">
					<label for="endTime">Luxtown Password</label>
					<input type="password" name="password" id="password" />
				</div>
				<p hidden>
					<input type="text" name="id" id="id" value="${id}"/>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</p>
				<ul class="actions">
					<li><input type="submit" value="Submit" class="special" /></li>
					<li><input type="reset" value="Reset" /></li>
				</ul>
			</form>
		</article>

		<!-- Posted -->
		<article id="posted">
			<h2 class="major">Blog Manager</h2>
			<section>
				<h3 class="major">Blog Links</h3>
				<ul class="actions">
					<li><a href="${blogLink}" class="button" target="_tab">View</a></li>
					<li><a href="${blogEditLink}" class="button" target="_tab">Edit</a></li>
				</ul>
			</section>
			<section>
				<h3 class="major">Delete Blog</h3>
				<ul class="actions">
					<li><a href="#post" class="button special" id="deletePostButton">Delete</a></li>
				</ul>
			</section>
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