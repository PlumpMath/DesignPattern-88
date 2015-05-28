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
<h1><fmt:message key="login.form.title" /></h1>

<c:if test="${not empty messageKey}">
<div class="message">
<div class="<c:out value="${messageType}" />"><fmt:message key="${messageKey}" /></div>
<br />
</div>
</c:if>


<form:form autocomplete="false" acceptCharset="UTF-8" method="POST" action="./login">
<table class="form">
          <tr>
              <td class="label"><fmt:message key="login.form.email" /></td>
              <td><input type="text" name="login" /></td>
          </tr>
          <tr>
              <td class="label"><fmt:message key="login.form.password" /></td>
              <td><input type="password" name="password" /></td>
          </tr>
          <tr>
              <td colspan="2" align="center">
                  <input type="submit" value="<fmt:message key="login.form.submit" />" />
              </td>
          </tr>
      </table>
	<br /><br />
	<a href="./subscribe"><fmt:message key="login.form.subscribe" /></a>
</form:form>
</body>
</html>