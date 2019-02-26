<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <!--  <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="canonical" href="https://html5-templates.com/" />
    <title>Responsive HTML5 Page Layout Template</title>
    <meta name="description" content="Simple HTML5 Page layout template with header, footer, sidebar etc.">  -->
   <link href='<spring:url value="/resources/css/style.css"/>' rel="stylesheet" />
<%--    <script type="text/javascript" src='<spring:url value="/resources/js/script.js"/>'></script> --%>
   <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
</head>

<body>
  <%--  <c:set var="contextPath" value="${pageContext.request.contextPath}"/> --%>
  
	<header>
		<div id="logo"><img  src="<spring:url value="/img/logo.png"/>">Patroclos Software Company</div>
		<spring:url value="allUsers" var="url" htmlEscape="true"/>
		<nav>  
			<ul>
			
			
				<li><a href="${pageContext.request.contextPath}/allUsers">List User</a>
				<li><a href="${url}">Add User 2</a>
				<li><a href="addUser.jsp">Home</a>
				<li><a href="https://html-css-js.com/">P.S</a>
				<li><a href="https://html-css-js.com/css/code/">Reports</a>
				<li><a href="${pageContext.request.contextPath}/addUser">Add User</a>
			</ul>
		</nav>
	</header>
	<section>
		<div id="simbol">
		<img src="<spring:url value="/img/final_div.png"/>">

	
		</div>
	</section>
</body>

</html>