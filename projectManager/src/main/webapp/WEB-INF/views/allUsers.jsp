<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 <link href='<spring:url value="/resources/css/style.css"/>' rel="stylesheet" />
   <script type="text/javascript" src='<spring:url value="/resources/js/script.js"/>'></script>

</head>
<body>
 <jsp:include page="header.jsp"/>

<section id="pageContent2">
	
	<h3>List of all users</h3>
	${message}
	
	<table class="blueTable">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>UserName</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tfoot>
<tr>
<td colspan="4">
<div class="links"><a href="#">&laquo;</a> <a class="active" href="#">1</a> <a href="#">2</a> <a href="#">3</a> <a href="#">4</a> <a href="#">&raquo;</a></div>
</td>
</tr>
</tfoot>

		<tbody>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.userName}</td>
					<td><a
						href="${pageContext.request.contextPath}/editUser/${user.id}">Edit</a></td>
					<td><a
						href="${pageContext.request.contextPath}/deleteUser/${user.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>


	</table>
	
	</section>
	<!--  </div> -->
	 <jsp:include page="footer.jsp"/>
	
</body>
</html>