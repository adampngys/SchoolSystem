<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>student id: ${userSession.user.std.id }</h1>
<h1>student role: ${userSession.user.role }</h1>
<h1>lecturer info: ${userSession.user.lec.id}</h1>
<h1>lecturer role: ${userSession.user.role }</h1>
</body>
</html>