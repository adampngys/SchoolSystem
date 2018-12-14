<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:url value="/css/simple.css" var="ss" />
<link rel="STYLESHEET" type="text/css" href="${ss}" />

<div>
	<h1 align="right" style="font-size: 35px;">
		<spring:message code="Course Application Processing System" />
	</h1>
</div>
<c:if test="${not empty sessionScope.USERSESSION}">
	<c:set var="userid" value="${sessionScope.USERSESSION.user.id}" />
	<c:set var="userrole" value="${sessionScope.USERSESSION.role}" />
</c:if>

<table style="width: 100%">
	<tr style="background-color: #F0F0F0; font-size: 25px; font-weight: normal">
		<td class="${empty userrole ? 'td-home' : ''}" align="center">Home</td>
		<td class="${userrole eq 'admin'? 'td-admin' : ''}" align="center">Admin Log In</td>
		<td class="${userrole eq 'student'? 'td-student' : ''}" align="center">Student Log In</td>
		<td class="${userrole eq 'lecturer'? 'td-lecturer' : ''}" align="center">Lecturer Log In</td>
	</tr>
</table>


