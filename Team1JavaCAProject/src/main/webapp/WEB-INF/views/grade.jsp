<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />


<c:if test="${fn:length(stdCourses) gt 0}">
	<br />
	<br />
	<table class="borderAll">
		<tr>
			<th>Index</th>
			<th>Course ID</th>
			<th>Grade</th>
			<th>GPA</th>
			<th>Credit</th>
			
		</tr>
		<!-- match model attribute name -->
		<c:forEach var="stdCourse" items="${stdCourses}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td align="center">${status.index+1}</td>
				<td align="center">${stdCourse.course.courseId}</td>
				<td align="center">${stdCourse.grade}</td>
				<td align="center">${stdCourse.gpa}</td>
				<td align="center">${stdCourse.course.credit}</td>
			</tr>
		</c:forEach>
	</table>
</c:if>