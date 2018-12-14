<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:url value="/css/simple.css" var="style" />
<link rel="STYLESHEET" type="text/css" href="${style}" />
<h2>Login</h2>
<form:form modelAttribute="user" method="POST"
	action="${pageContext.request.contextPath}/authenticate">
	<table class="framed">
		<tr>
			<td><spring:message code="User Id" /></td>
			<td colspan="3"><form:input path="id" size="40" /></td>
		</tr>
		<tr>
			<td><spring:message code="User Password" /></td>
			<td colspan="3"><form:password path="password" size="40" /></td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><form:button name="submit" type="submit" value="s">
					<img
						src="${pageContext.request.contextPath}/image/button_login.gif"
						alt="" align="middle">
				</form:button></td>
			<td><form:button name="clear" type="reset" value="r">
					<img
						src="${pageContext.request.contextPath}/image/button_clear.gif"
						alt="" align="middle">
				</form:button></td>
		</tr>
	</table>
	<c:out value="${errormsg}" />
</form:form>
