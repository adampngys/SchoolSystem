<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />
</head>
<body>

<form:form method="POST" modelAttribute="lecturer"
	action="${pageContext.request.contextPath}/lecturer/create">
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th width="45%">Description</th>
					<th width="55%">Detail</th>
				</tr>
				  <%-- <tr>
				   <td><s:message code="Lecturer Id" /> *</td>
				   <td><form:input path="lectId" readonly="true" />
				   <form:errors path="lectId" cssStyle="color: red;" /></td>
				 </tr>   --%>
				
				<tr>
				   <td><s:message code="Name" /></td>
				   <td><form:input path="name"/>
				   <form:errors path="name" cssStyle="color: red;" /></td>
				 </tr>
				 
				 <tr>
				   <td><s:message code="User ID" /></td>
				   <td><form:input path="userInfo.id"/>
				   <form:errors path="userInfo.id" cssStyle="color: red;" /></td>
				 </tr>
				 <tr>
				   <td><s:message code="Password" /></td>
				   <td><form:input path="userInfo.password"/>
				   <form:errors path="userInfo.password" cssStyle="color: red;" /></td>
				 </tr>
				 
				<tr>
				   <td><s:message code="Faculty" /> *</td>
				   <td><form:input path="faculty"/>
				   <form:errors path="faculty" cssStyle="color: red;" /></td>
				 </tr>
				<tr>
				   <td><s:message code="Phone" /></td>
				   <td><form:input path="phone"/>
				   <form:errors path="phone" cssStyle="color: red;" /></td>
				 </tr>
				<tr>
				   <td><s:message code="DOB" /></td>
				   <td><form:input path="dob" type="date"/>
				   <form:errors path="dob" cssStyle="color: red;" /></td>
				 </tr>
			
				<tr>
				   <td><s:message code="Email" /></td>
				   <td><form:input path="email"/>
				   <form:errors path="email" cssStyle="color: red;" /></td>
				 </tr>
				
				 <tr>
				 <td><input type="submit" value="Submit"> </td>
				 <td><input type="reset" value="Reset"></td>
				 </tr>
		</table>
		</center>
	
	</form:form>
</body>
</html>