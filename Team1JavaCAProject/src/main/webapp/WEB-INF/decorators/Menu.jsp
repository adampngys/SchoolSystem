<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
<c:url value="/css/simple.css" var="ss" />
<link rel="STYLESHEET" type="text/css" href="${ss}" />
<c:if test="${not empty sessionScope.USERSESSION}">
	<c:set var="userid" value="${sessionScope.USERSESSION.user.id}" />
	<c:set var="userrole" value="${sessionScope.USERSESSION.role}" />
</c:if>
<dec:head />
</head>
<body>
	<div align="left" style="font-size: 18px;">
		<ul>
			<c:out value="${userid}" />
			<br />
			<c:out value="${userrole}" />
			<br />
			<c:if test="${not empty userrole}">
				<c:choose>
					<c:when test="${userrole eq 'admin'}">
						<li>
						<spring:url value="/student/list" var="stdlist"
							htmlEscape="true" /> <a href="${stdlist}"><spring:message
								code="Manage Students" /></a>
						</li>
						<br />
						<li>
						<spring:url value="/lecturer/list" var="leclist"
							htmlEscape="true" /> <a href="${leclist}"><spring:message
								code="Manage Lecturers" /></a>
						</li>
						<br />
						<li>
						<spring:url value="/course/list" var="courselist"
							htmlEscape="true" /> <a href="${courselist}"><spring:message
								code="Manage Courses" /></a>
						</li>
						<br />
						<li>
						<spring:url value="/enrolment/list" var="enlist"
							htmlEscape="true" /> <a href="${enlist}"><spring:message
								code="Manage Enrolment" /></a>
						</li>
						<br />
						<li>
						<spring:url value="/logout" var = "logout" htmlEscape = "true" />
						<a href = "${logout}">
						<spring:message code = "Logout" />
						</a>
						</li>
					</c:when>
					<c:when test="${userrole eq 'lecturer'}">
						<li>
						<spring:url value="/lecturer/course" var="lcourse"
							htmlEscape="true" /> <a href="${lcourse}"><spring:message
								code="View Courses Taught" /></a>
						</li>
						<br />
						<li>
						<spring:url value="/logout" var = "logout" htmlEscape = "true" />
						<a href = "${logout}">
						<spring:message code = "Logout" />
						</a>
						</li>
					</c:when>
					<c:when test="${userrole eq 'student'}">
						<li>
						<spring:url value="/course/grade" var="grades"
							htmlEscape="true" /> <a href="${grades}"><spring:message
								code="Grades and GPA" /></a>
						</li>
						<br />
						<li>
						<spring:url value="/course/viewcourse" var="clist"
							htmlEscape="true" /> <a href="${clist}"><spring:message
								code="View Applied Courses" /></a>
						</li>
						<br />
						<li>
						<spring:url value="/course/courselist" var="clist"
							htmlEscape="true" /> <a href="${clist}"><spring:message
								code="Enroll for a Course" /></a>
						</li>	
						<br />
						<li>
						<spring:url value="/logout" var = "logout" htmlEscape = "true" />
						<a href = "${logout}">
						<spring:message code = "Logout" />
						</a>
						</li>
					</c:when>
				</c:choose>
			</c:if>
		</ul>
	</div>
	<br />
	<br />
	<br />
</body>