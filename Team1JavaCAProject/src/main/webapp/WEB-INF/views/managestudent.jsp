<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />

<%-- <a href="${pageContext.request.contextPath}/student/create">Add
	Student</a> --%>
	
	<form:form  method="get" action="${pageContext.request.contextPath}/student/create">
        <input type="submit" name="Add Student" value="Add Student" >
    </form:form>
<c:if test="${fn:length(students) gt 0}">
	<br />
	<br />
	<table class="borderAll">
		<tr>
			<th><s:message code="Index" /></th>
			<th><s:message code="Student ID" /></th>
			<th><s:message code="Student Name" /></th>
			<th><s:message code="User ID" /></th>
			<th><s:message code="NRIC" /></th>
			<th><s:message code="Address" /></th>
			<th><s:message code="Phone" /></th>
			<th><s:message code="Date of Birth" /></th>
			<th><s:message code="Email" /></th>
			<th><s:message code="Enrolment Date" /></th>
		</tr>
		
		<c:forEach var="student" items="${students}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td class="nowrap">${status.index+1}</td>
				<td class="nowrap">${student.id}</td>
				<td class="nowrap">${student.name}</td>
				<td class="nowrap">${student.userInfo.id}</td>
				<td class="nowrap">${student.nric}</td>
				<td class="nowrap">${student.address}</td>
				<td class="nowrap">${student.phone}</td>
				<td class="nowrap">${student.dob}</td>
				<td class="nowrap">${student.email}</td>
				<td class="nowrap">${student.enrollmentDate}</td>
				<td align="center"><a
					href="${pageContext.request.contextPath}/student/edit/${student.id}.html">
						<s:message code="Edit" />
				</a></td>
				<td><a
					href="${pageContext.request.contextPath}/student/delete/${student.id}.html">
					<s:message
							code="Delete" /></a></td>

			</tr>
			</tr>
		</c:forEach>


</c:if>