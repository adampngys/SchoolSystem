<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />
	
	<form:form  method="get" action="${pageContext.request.contextPath}/course/create">
        <input type="submit" name="Add Course" value="Add Course" >
    </form:form>

<%-- <a href="${pageContext.request.contextPath}/course/create">Add
	Course</a> --%>
<c:if test="${fn:length(courses) gt 0}">
<p class="message" ><font color = "red" size="4">${message}</font></p>
	<br />
	<br /> 
	<table class="borderAll">
		<tr>
			<th><s:message code="Index" /></th>
			<th><s:message code="Course Id"/></th>
			<th><s:message code="Name" /></th>
			<th><s:message code="Start Date" /></th>
			<th><s:message code="Duration" /></th>
			<th><s:message code="Size" /></th>
			<th><s:message code="Credit" /></th>
			<th><s:message code="Vacancy" /></th>
			<%-- <th><s:message code="label.course.address" /></th>
			<th><s:message code="label.course.userName" /></th>
			<th><s:message code="label.course.password" /></th> --%>
			<th><s:message code="" /></th>
			<th><s:message code="" /></th>
		</tr>

<c:forEach var="course" items="${courses}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td class="nowrap">${status.index+1}</td>
				<td class="nowrap">${course.courseId}</td>
				<td class="nowrap">${course.name}</td>			
				<td class="nowrap"><fmt:formatDate value="${course.startDate}" pattern="yyyy-MM-dd" /></td> 
				<td class="nowrap">${course.duration}</td>			
				<td class="nowrap">${course.size}</td>
				<td class="nowrap">${course.credit}</td>
				<td class="nowrap">${course.vacancy}</td>
				
				<%-- <td align="center"><input type="submit" action="${pageContext.request.contextPath}/course/edit/${course.courseId}.html">
				<s:message code="Edit" /></td>
				<td align="center"><input type="submit" action="${pageContext.request.contextPath}/course/delete/${course.courseId}.html">
				<s:message code="Delete" /></td> --%>
				 <td align="center"><a
					href="${pageContext.request.contextPath}/course/edit/${course.courseId}.html">
						<s:message code="Edit" />
				</a></td>
				<td><a
					href="${pageContext.request.contextPath}/course/delete/${course.courseId}.html">
					<s:message code="Delete" /></a></td> 

			</tr>
		</c:forEach>
	</table>
 </c:if> 