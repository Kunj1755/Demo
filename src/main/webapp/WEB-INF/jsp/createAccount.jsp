<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style type="text/css">
	
	.error{
	color:red;
	font-weight:bold;
	font-size: 15px;
	}
	
	
	</style>
<title>Create an account</title>
</head>
<body>
	<h1>Enter Account details</h1>
	<form:form commandName="aNewAccount" method="POST" action="doCreate">
	
		<table>
		
			<tr><td>
				First Name: <form:input path="firstName" type="text" name="firstname"/>
				<form:errors path="firstName" cssClass="error"></form:errors>
			</td></tr>
			<tr><td>
				Last Name: <form:input path="lastName" type="text" name="lastname"/>
				<form:errors path="lastName" cssClass="error"></form:errors>
			</td></tr>
			<tr><td>
				Address: <form:input path="address" type="text" name="address"/>
				<form:errors path="address" cssClass="error"></form:errors>
			</td></tr>
			<tr><td>
				Age: <form:input path="age" type="text" name="age"/>
				<form:errors path="age" cssClass="error"></form:errors>
			</td></tr>
			<tr><td>
				Email: <form:input path="email" type="text" name="email"/>
				<form:errors path="email" cssClass="error"></form:errors>
			</td></tr>
			<tr><td>
			<input type="submit" value="create"/>
			</td></tr>
			
		</table>
		
	</form:form>

</body>
</html>