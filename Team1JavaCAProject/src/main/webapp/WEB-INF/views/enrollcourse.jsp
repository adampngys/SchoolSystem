<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"
	type="text/css" />


<c:if test="${fn:length(courses) gt 0}">
	<br />
	<br />
	<table class="borderAll">
		<tr>
			<th>Index</th>
			<th>Course ID</th>
			<th>Course Name</th>
			<th>Start Date</th>
			<th>Duration</th>
			<th>Size</th>
			<th>Credit</th>
			<th>Vacancy</th>
			<th></th>

		</tr>
		<!-- match model attribute name -->
		
		<c:set var="now_value" value="<%=new java.util.Date()%>"/>
			<fmt:formatDate var="now" value="${now_value}"
			pattern="yyyy-MM-dd" />
			
		<c:forEach var="course" items="${courses}" varStatus="status">
			<tr class="${status.index%2==0?'even':'odd'}">
				<td class="nowrap">${status.index+1}</td>
				<td class="nowrap">${course.courseId}</td>
				<td class="nowrap">${course.name}</td>
				<td class="nowrap"><fmt:formatDate value="${course.startDate}"
						pattern="dd.MM.yyyy" /></td>
				<td class="nowrap">${course.duration}</td>
				<td class="nowrap">${course.size}</td>
				<td class="nowrap">${course.credit}</td>
				<td class="nowrap">${course.vacancy}</td>
				<c:choose>
					<c:when test="${course.vacancy<=0}">
						<td>Sorry, the course is full.</td>
					</c:when>

					<c:when test="${now gt course.startDate}">
						<td>Sorry, the course has already started.</td>
					</c:when>
					<c:otherwise>
						<td align="center"><a
							href="${pageContext.request.contextPath}/course/add/${course.courseId}">
								Submit </a></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
</c:if>
<div id="pagination">

	<c:url value="/course/courselist" var="prev">
		<c:param name="page" value="${page-1}" />
	</c:url>
	<c:if test="${page > 1}">
		<a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
	</c:if>

	<c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
		<c:choose>
			<c:when test="${page == i.index}">
				<span>${i.index}</span>
			</c:when>
			<c:otherwise>
				<c:url value="/course/courselist" var="url">
					<c:param name="page" value="${i.index}" />
				</c:url>
				<a href='<c:out value="${url}" />'>${i.index}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:url value="/course/courselist" var="next">
		<c:param name="page" value="${page + 1}" />
	</c:url>
	<c:if test="${page + 1 <= maxPages}">
		<a href='<c:out value="${next}" />' class="pn next">Next</a>
	</c:if>
</div>
