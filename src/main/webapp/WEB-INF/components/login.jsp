<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="properties.locale" var="loc" />
        <fmt:message bundle="${loc}" key="message.error.login" var="invalidLogin" />
        <fmt:message bundle="${loc}" key="login.user" var="user" />
        <fmt:message bundle="${loc}" key="login.password" var="password" />
        <fmt:message bundle="${loc}" key="login.button.login" var="btnLogin" />
        <fmt:message bundle="${loc}" key="login.placeholder.login" var="holderLogin" />
        <fmt:message bundle="${loc}" key="login.placeholder.password" var="holderPassword" />
</head>
<body>
	<section class="column-main">
		<div class="form-signin">
			<div class="error">
			   <c:if test="${errorMessage eq 'invalidLogin' }">
				  <h3>${invalidLogin}</h3>
		       </c:if>
			</div>
			<form method="POST" action="${pageContext.request.contextPath}/controller">
				<input type="hidden" name="command" value="login" />
				<label for="login">${user}</label>
				<input type="text" name="login" placeholder="${holderLogin}">
				<label for="password" pattern=".{1,45}" required>${password}</label>
				<input type="password" name="password" placeholder="${holderPassword}" pattern=".{1,45}" required>
				<input type="submit" value="${btnLogin}">
			</form>
		</div>
	</section>
</body>
</html>

