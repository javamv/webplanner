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
	<title>Dimension by HTML5 UP</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
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
			<a href="main?id=${userId}"><span class="icon fa-diamond"></span></a>
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
					<li><a href="${gotoLink}" target="_tab">SCHEDULED</a></li>
				</c:if>
				<c:if test="${posted == false}">
				    <li><a href="createBlogPost?id=${id}">CREATE POST</a></li>
				</c:if>
				<c:if test="${posted == true}">
                    <li><a href="${blogLink}" target="_tab">POSTED</a></li>
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
					<label for="date">Date</label>
					<input type="text" name="date" id="date" />
				</div>
				<div class="field half first">
					<label for="startTime">Start Time</label>
					<input type="text" name="startTime" id="startTime" />
				</div>
				<div class="field half">
					<label for="endTime">End Time</label>
					<input type="text" name="endTime" id="endTime" />
				</div>
				<p hidden>
					<input type="text" name="id" id="webinarId" value="${id}"/>
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