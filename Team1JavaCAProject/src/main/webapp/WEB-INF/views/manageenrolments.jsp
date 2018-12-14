<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />

<c:if test="${fn:length(stdcourses) gt 0}">
	<br />
	<br />
	<table class="borderAll">
		<tr>
			<th><s:message code="Index" /></th>
			<th><s:message code="Std ID" /></th>
			<th><s:message code="Std Name" /></th>
			<th><s:message code="Course Id" /></th>
			<th><s:message code="Course Name" /></th>
			<th><s:message code="Status" /></th>
			<th><s:message code="" /></th>
			<th><s:message code="" /></th>
		</tr>

		<c:forEach var="stdCourse" items="${stdcourses}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td class="nowrap">${status.index+1}</td>
				<td class="nowrap">${stdCourse.student.id}</td>
				<td class="nowrap">${stdCourse.student.name}</td>
				<td class="nowrap">${stdCourse.course.courseId}</td>
				<td class="nowrap">${stdCourse.course.name}</td>
				<td class="nowrap">${stdCourse.status}</td>
				<c:choose>
					<c:when test="${stdCourse.course.vacancy==0}">
						<td>Sorry, course is full</td>
					</c:when>
					<c:otherwise>
						<td align="center"><a
							href="${pageContext.request.contextPath }/enrolment/approve/${stdCourse.id}">Approve</a></td>
					</c:otherwise>
				</c:choose>
				<td align="center"><a
					href="${pageContext.request.contextPath }/enrolment/reject/${stdCourse.id}">Reject</a></td>
			</tr>
		</c:forEach>
		<br>
		<br>
	</table>
</c:if>
