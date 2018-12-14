<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />

<script>
	Calendar c = Calendar.getInstance();
	c.setTime(course1.course.startDate);
	//Date sDate=c.setDate(Calendar.DAY_OF_MONTH + LecturerCourse.course.duration)
	c.add(Calendar.DAY_OF_MONTH, course1.course.duration);
	Date
	sDate = c.getTime();
	return sDate;
</script>


<c:if test="${fn:length(lCCourse) gt 0}">
	<table class="borderAll">
		<tr>
			<th><s:message code="Index" /></th>
			<th><s:message code="Lecturer Name" /></th>
			<th><s:message code="Course ID" /></th>
			<th><s:message code="Course Name" /></th>
			<th><s:message code="Course Start Date" /></th>
			<th><s:message code="Course End Date" /></th>
			<th><s:message code="Course Duration" /></th>
			<th><s:message code="Course Size" /></th>
			<th><s:message code="Course Vacancy" /></th>
			<th><s:message code="Action" /></th>

		</tr>
		<c:forEach var="course1" items="${lCCourse}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td class="nowrap">${status.index+1}</td>
				<td class="nowrap">${course1.lecturer.name}</td>
				<td class="nowrap">${course1.course.courseId}</td>
				<td class="nowrap">${course1.course.name}</td>
				<td class="nowrap"><fmt:formatDate value="${course1.course.startDate}" pattern="dd-MM-yyyy" /></td>
				<td class="nowrap"><fmt:formatDate value="${course1.endDate}" pattern="dd-MM-yyyy" /></td>
				<td class="nowrap">${course1.course.duration}</td>
				<td class="nowrap">${course1.course.size}</td>
				<td class="nowrap">${course1.course.vacancy}</td>
				<td align="center"><a
					href="${pageContext.request.contextPath}/lecturer/viewstudent/${course1.course.courseId}.html">
						<s:message code="View enrolled students" />
				</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>