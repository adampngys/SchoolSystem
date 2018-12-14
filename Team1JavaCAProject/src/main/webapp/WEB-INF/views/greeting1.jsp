<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Greetings!</title>
</head>

<body>
	<table>

		<tr>
			<th>Name</th>
			<th>Faculty</th>
			<th>Phone</th>
		</tr>

		<c:forEach items="${lecturerlist}" var="index">

			<tr>
				<td>${index.name}</td>

				<td>${index.faculty}</td>

				<td>${index.phone}</td>
			</tr>

		</c:forEach>

	</table>
</body>
</html>