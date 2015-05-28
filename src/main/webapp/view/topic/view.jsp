<%@page import="com.forum.webapp.web.models.Message"%>
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
		<fmt:message key="topic.view.title">
			<fmt:param value="${topic.title}" />
		</fmt:message>
	</h1>

	<c:if test="${not empty messageKey}">
		<div class="message">
			<div class="<c:out value="${messageType}" />">
				<fmt:message key="${messageKey}" />
			</div>
		</div>
		<br />
	</c:if>

	<a href="<%=request.getContextPath()%>/html/topic"><fmt:message
			key="topic.view.list" /></a>
	<br />
	<table class="list">
		<c:if test="${empty topicMessages}">
		<tr>
				<td class="header" colspan="2"><fmt:message key="topic.view.empty" /></td>
		</tr>
		</c:if>
		<c:forEach items="${topicMessages}" var="message">
			<fmt:formatDate value="${message.dateAndTime}" type="date"
				dateStyle="short" var="messageDate" />
			<fmt:formatDate value="${message.dateAndTime}" type="time"
				dateStyle="short" var="messageTime" />
			<tr>
				<td class="header">
					<fmt:message key="topic.view.header" >
						<fmt:param value="${message.owner.firstName}" />
						<fmt:param value="${message.owner.name}" />
						<fmt:param value="${messageDate}" />
						<fmt:param value="${messageTime}" />
					</fmt:message></td>
				<td class="content">
					<%-- Corriger la faille ci-dessous : --%>
					<c:set var="messageText"  value="${message.text}" scope="request" />
					<%= request.getAttribute("messageText")%>
					<%-- Fin du code à corriger. --%>
				</td>
			</tr>
		</c:forEach>

		<form autocomplete="false" acceptCharset="UTF-8" method="POST"
			action="<%=request.getContextPath()%>/html/message">
			<tr>
				<td class="label"><fmt:message key="topic.view.createmessage" /></td>
				<td><input type="hidden" name="topicId" value="<c:out value="${topic.id}" />" />
				<textarea name="text" rows="8" style="width:100%"></textarea>
				<br />
				<input type="submit"
					value="<fmt:message key="topic.view.post" />" />
				</td>
			</tr>
		</form>
	</table>


<br />
<a href="<%=request.getContextPath()%>/html/logout"><fmt:message key="logout.link" /></a>
</body>
</html>