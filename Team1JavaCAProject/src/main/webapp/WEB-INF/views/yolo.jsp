<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:url value="/css/simple.css" var="style" />
<link rel="STYLESHEET" type="text/css" href="${style}" />
<c:if test="${not empty sessionScope.USERSESSION}">
	<c:set var="userid" value="${sessionScope.USERSESSION.user.id}" />
	<c:set var="userrole" value="${sessionScope.USERSESSION.role}" />
</c:if>
<h2>
	Welcome
	<c:out value="${userid}" /> --- <c:out value="${userrole}" />
</h2>