<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:requestEncoding value="UTF-8" />

<html>
<head>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/main.css"
	type="text/css">
</head>
<body>

<h1><fmt:message key="topic.create.title" /></h1>

<c:if test="${not empty messageKey}">
<div class="message">
<div class="<c:out value="${messageType}" />"><fmt:message key="${messageKey}" /></div>
</div>
<br />
</c:if>

<form:form autocomplete="false" acceptCharset="UTF-8" method="POST">
<table class="form">
          <tr>
              <td class="label"><fmt:message key="topic.create.label" /></td>
              <td><input type="text" name="title" /></td>
          </tr>
          <tr>
              <td colspan="2" align="center">
                  <input type="submit" value="<fmt:message key="topic.create.confirm" />" />
              </td>
          </tr>
      </table>
</form:form>

<a href="<%=request.getContextPath()%>/html/topic"><fmt:message key="topic.create.list" /></a>
<br />
<a href="<%=request.getContextPath()%>/html/logout"><fmt:message key="logout.link" /></a>
</body>
</html>