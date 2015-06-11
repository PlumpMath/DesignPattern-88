<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fmt:requestEncoding value="UTF-8" />

<html>
<head>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/main.css"
	type="text/css">
</head>
<body>

	<h1>
		<fmt:message key="topic.list.title" />
	</h1>

	<c:if test="${not empty messageKey}">
		<div class="message">
			<div class="<c:out value="${messageType}" />">
				<fmt:message key="${messageKey}" />
			</div>
			<br />
		</div>
	</c:if>

	<p>
		<fmt:message key="topic.list.details" />
	</p>


	<a href="<%=request.getContextPath()%>/html/topic/create"><fmt:message
			key="topic.list.create" /></a>
	<br />
	<table class="list">
		<c:forEach items="${topics}" var="topic">
			<tr>
				<td><a
					href="<%=request.getContextPath()%>/html/topic/<c:out value="${topic.id}" />"><c:out
							value="${topic.title}" /></a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<br />
	<a href="<%=request.getContextPath()%>/html/logout"><fmt:message
			key="logout.link" /></a>
</body>
</html>