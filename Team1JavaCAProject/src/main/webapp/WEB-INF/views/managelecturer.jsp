<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />

<%-- <a href="${pageContext.request.contextPath}/lecturer/create">Add
	Lecturer</a> --%>
	
	<form:form  method="get" action="${pageContext.request.contextPath}/lecturer/create">
        <input type="submit" name="Add Lecturer" value="Add Lecturer" >
    </form:form>
    
<c:if test="${fn:length(lecturers) gt 0}">
	<br />
	<br />
	<table class="borderAll">
		<tr>
			<th><s:message code="Index" /></th>
			<%-- <th><s:message code="Lecturer ID" /></th> --%>
			<th><s:message code="User ID" /></th>
			<th><s:message code="Name" /></th>
			<th><s:message code="Faculty" /></th>
			<th><s:message code="Phone" /></th>
			<th><s:message code="DOB" /></th>
			<th><s:message code="Email" /></th>
			<th><s:message code="" /></th>
			<th><s:message code="" /></th>
			
		</tr>
		
		<c:forEach var="lecturer" items="${lecturers}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td class="nowrap">${status.index+1}</td>
				<%-- <td class="nowrap">${lecturer.lect_id_pk}</td>  --%>
				<td class="nowrap">${lecturer.userInfo.id}</td>
				<td class="nowrap">${lecturer.name}</td>
				
				<td class="nowrap">${lecturer.faculty}</td>
				<td class="nowrap">${lecturer.phone}</td>
				<td class="nowrap"><fmt:formatDate value="${lecturer.dob}" pattern="dd/MM/yyyy" /></td> 
				<td class="nowrap">${lecturer.email}</td>
				
				 <td align="center"><a
					href="${pageContext.request.contextPath}/lecturer/edit/${lecturer.lectId}">
						<s:message code="Edit" />
				</a></td>
				<td><a
					href="${pageContext.request.contextPath}/lecturer/delete/${lecturer.lectId}">
					<s:message
							code="Delete" /></a></td> 

			</tr>
			</tr>
		</c:forEach>


</c:if>