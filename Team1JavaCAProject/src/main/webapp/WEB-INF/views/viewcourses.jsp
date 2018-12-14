<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />

<c:if test="${fn:length(stdCourses) gt 0}">
	<br />
	<br />
	<table class="borderAll">
		<tr>
			<th>Index</th>
			<th>Course ID</th>
			<th>Status</th>
			<th></th>

		</tr>
		<!-- match model attribute name -->
		<c:forEach var="stdCourse" items="${stdCourses}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td class="nowrap">${status.index+1}</td>
				<td class="nowrap">${stdCourse.course.courseId}</td>
				<td class="nowrap">${stdCourse.status}</td>
				<td align="center"><a
					href="${pageContext.request.contextPath}/course/delete/${stdCourse.id}.html">
						Delete </a></td>						
			</tr>
		</c:forEach>
	</table>

</c:if>
<br/>
<c:out value="${message}" />