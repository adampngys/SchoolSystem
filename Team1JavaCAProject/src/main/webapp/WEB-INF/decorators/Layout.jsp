<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="dec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<%@include file="Header.jsp"%>
<c:url value="/css/simple.css" var="ss" />
<link rel="STYLESHEET" type="text/css" href="${ss}" />
<dec:head />
</head>
<body>
	<table style="width: 100%">
		<tr>
			<td>
			<td style="width: 180; border: 1;" valign="top">
				<div>
					<%@ include file="Menu.jsp"%>
				</div>
			</td>
			<td valign="top">
				<div>
					<dec:body />
					<br /><br /><br />
				</div>
			</td>
		</tr>
	</table>

	<!-- ======== Footer ======== -->
	<div id="footer" align="center">
		<hr>
		<small> Copyright © GDipSA47 Team 1 2018 </small>
	</div>
</body>
</html>
