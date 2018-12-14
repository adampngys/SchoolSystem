<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
	
	<script>

		function handleChange(input) {
			 
		    if (input.value.length==0) input.value = 0;
		   
		    
		  }
	   

</script>
</head>
<body>

	<form:form method="POST" modelAttribute="course"
		action="${pageContext.request.contextPath}/course/edit/${course.courseId}.html">
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th width="45%">Description</th>
					<th width="55%">Detail</th>
				</tr>
				<%-- <tr>
				   <td><s:message code="Course Id" /> * Read only</td>
				   <td><form:input path="courseId"  readonly="true"/></td>
				 </tr> --%>
				<tr>
					<td><s:message code="Name" /> * Read only</td>
					<td><form:input path="name" readonly="true" /> <form:errors
							path="name" cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td><s:message code="Start Date" /></td>
					<td><form:input path="startDate" /> <form:errors
							path="startDate" cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td><s:message code="Duration" /></td>
					<td><form:input path="duration" onpropertychange="handleChange(this)"/> <form:errors
							path="duration" cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td><s:message code="Size" /></td>
					<td><form:input path="size" onpropertychange="handleChange(this)"/> <form:errors path="size"
							cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td><s:message code="Credit" /></td>
					<td><form:input path="credit" onpropertychange="handleChange(this)" /> <form:errors
							path="credit" cssStyle="color: red;" /></td>
				</tr>
				<tr>
					<td><s:message code="Vacancy" /></td>
					<td><form:input path="vacancy" onpropertychange="handleChange(this)" /> <form:errors
							path="vacancy" cssStyle="color: red;" /></td>
				</tr>

				<tr>
					<td><input type="submit" value="Submit"></td>
					<td><input type="reset" value="Reset"></td>
				</tr>
			</table>
		</center>

	</form:form>
</body>
</html>