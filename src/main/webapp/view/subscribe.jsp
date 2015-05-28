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

<h1><fmt:message key="user.form.title" /></h1>

<c:if test="${not empty messageKey}">
<div class="message">
<div class="<c:out value="${messageType}" />"><fmt:message key="${messageKey}" /></div>
</div>
<br />
</c:if>

<form:form autocomplete="false" acceptCharset="UTF-8" method="POST">
<table class="form">
          <tr>
              <td class="label"><fmt:message key="user.form.firstName" /></td>
              <td><input type="text" name="firstName" /></td>
          </tr>
          <tr>
              <td class="label"><fmt:message key="user.form.name" /></td>
              <td><input type="text" name="name" /></td>
          </tr>
          <tr>
              <td class="label"><fmt:message key="user.form.email" /></td>
              <td><input type="text" name="email" /></td>
          </tr>
          <tr>
              <td class="label"><fmt:message key="user.form.password" /></td>
              <td><input type="password" name="password" /></td>
          </tr>
          <tr>
              <td class="label"><fmt:message key="user.form.password.confirmation" /></td>
              <td><input type="password" name="passwordConfirmation" /></td>
          </tr>
          <tr>
              <td colspan="2" align="center">
                  <input type="submit" value="<fmt:message key="user.form.submit" />" />
              </td>
          </tr>
      </table>
</form:form>

<a href="../"><fmt:message key="login.link" /></a>
</body>
</html>