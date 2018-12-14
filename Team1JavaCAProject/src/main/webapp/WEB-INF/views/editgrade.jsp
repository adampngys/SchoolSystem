<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value='/styles/style.css'/>" rel="stylesheet"
	type="text/css" />
</head>
<body>

	<form:form method="POST" modelAttribute="stdCourse"
		action="${pageContext.request.contextPath}/lecturer/updategrade/${stdCourse.id}.html">
		<center>

			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<th width="45%">Description</th>
					<th width="55%">Data</th>
				<tr>
					<td><s:message code="Course ID" /></td>
					<td><form:input path="course.courseId" readonly="true" /></td>
				</tr>
				<tr>
					<td><s:message code="Student ID" /></td>
					<td><form:input path="student.id" readonly="true" color="grey" /></td>
				</tr>
				<tr>
					<td><s:message code="Name" /></td>
					<td><form:input path="student.name" readonly="true"
							color="grey" /></td>
				</tr>
				<tr>
					<td><s:message code="label.student.nric" /></td>
					<td><form:input path="student.nric" readonly="true"
							color="grey" /></td>
				</tr>
				<tr>

					<td><form:input path="status" type="hidden" readonly="true"
							color="grey" /></td>
				</tr>
				<!-- 				"value=value.replace(/[^\d]/g,'')" -->
				<tr>
					<td><s:message code="Grade" /><font color="red"> *
							Number only </font></td>
					<td><form:input path="grade" type="text"
							onpropertychange="handleChange(this)"
							onkeyup="value=value.replace(/[^\d]/g,'')" /> <form:errors
							path="grade" cssStyle="color: red;" /></td>
				</tr>
				<script>
					function handleChange(input) {

						if (input.value > 100)
							input.value = 100;

					}
				</script>
				<tr>
					<td><s:message code="GPA score" /></td>
					<td><form:input path="gpa" readonly="true" color="grey" /></td>
				</tr>

				<tr>
					<td align="center"><input type="submit" value="Submit">
					</td>



					<td align="center"><input type="reset" value="Reset"></td>
				</tr>
				<tr>
					<td align="center"><a
						href="${pageContext.request.contextPath}/lecturer/viewstudent/${stdCourse.course.courseId}.html">
							<s:message code="Return back to previous menu" />
					</a></td>
				</tr>
			</table>
		</center>

	</form:form>
</body>
</html>