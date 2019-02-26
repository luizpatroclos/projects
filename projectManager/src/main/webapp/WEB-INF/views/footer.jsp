<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="canonical" href="https://html5-templates.com/" />
    <title>Responsive HTML5 Page Layout Template</title>
    <meta name="description" content="Simple HTML5 Page layout template with header, footer, sidebar etc.">
   <link href='<spring:url value="/resources/css/style.css"/>' rel="stylesheet" />
   <script type="text/javascript" src='<spring:url value="/resources/js/script.js"/>'></script>
</head>

<body>

	<section>
		<div id="simbol">
		<img src="<spring:url value="/img/final_div.png"/>">
	
		</div>
	</section>
	<footer>
		<p>&copy; Copyright 2019 -  Patroclos Data Processing Systems | <a href="https://github.com/luizpatroclos" target="_blank" rel="nofollow">Git Hub</a></p>
		<address>
			Contact: <a href="mailto:luiz.patroclos@gmail.com">Mail me</a>
		</address>
	</footer>


</body>

</html>