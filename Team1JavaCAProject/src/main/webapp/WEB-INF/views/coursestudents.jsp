<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />


<c:if test="${fn:length(sCStudent) gt 0}"><p class="message" ><font color = "red" size="4">${message}</font></p>
	<table class="borderAll">
		<tr>
			<th><s:message code="Index" /></th>
			<th><s:message code="Student ID" /></th>
			<th><s:message code="Name" /></th>
			<th><s:message code="NRIC" /></th>
			<th><s:message code="DoB" /></th>
			<th><s:message code="Email" /></th>
			<th><s:message code="Address" /></th>
			<th><s:message code="Phone" /></th>
			<th><s:message code="Grade" /></th>
			<th><s:message code="GPA" /></th>
			<th><s:message code="Action" /></th>

		</tr>
		<c:forEach var="student1" items="${sCStudent}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td class="nowrap">${status.index+1}</td>
				<td class="nowrap">${student1.student.id}</td>
				<td class="nowrap">${student1.student.name}</td>
				<td class="nowrap">${student1.student.nric}</td>
				<td class="nowrap">${student1.student.dob}</td>
				<td class="nowrap">${student1.student.email}</td>
				<td class="nowrap">${student1.student.address}</td>
				<td class="nowrap">${student1.student.phone}</td>
				<td class="nowrap">${student1.grade}</td>
				<td class="nowrap">${student1.gpa}</td>

				<td align="center"><a
					href="${pageContext.request.contextPath}/lecturer/updategrade/${student1.id}.html">
						<s:message code="Update grade" />
				</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<table>
<tr>
		<td align="center"><a
			href="${pageContext.request.contextPath}/lecturer/course.html"> <s:message
					code="Return back to previous menu" />
		</a></td>
	</tr>
</table>