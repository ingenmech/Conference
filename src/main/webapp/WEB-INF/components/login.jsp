<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.locale" var="loc"/>
<fmt:message bundle="${loc}" key="message.error.login" var="invalidLogin"/>
<fmt:message bundle="${loc}" key="login.user" var="user"/>
<fmt:message bundle="${loc}" key="login.password" var="password"/>
<fmt:message bundle="${loc}" key="login.button.login" var="btnLogin"/>
<fmt:message bundle="${loc}" key="login.placeholder.login" var="holderLogin"/>
<fmt:message bundle="${loc}" key="login.placeholder.password" var="holderPassword"/>
<c:set var="page" value="signIn" scope="session"/>
<section class="column-main">
    <div class="form-signin">
        <div class="error">
            <%--@elvariable id="errorMessage" type="java.lang.String"--%>
            <c:if test="${errorMessage eq 'invalidLogin' }">
                <h3>${invalidLogin}</h3>
            </c:if>
        </div>
        <form method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="login"/>
            <label for="login">${user}</label>
            <input id="login" type="text" name="login" placeholder="${holderLogin}" maxlength="45" required>
            <label for="password">${password}</label>
            <input id="password" type="password" name="password" placeholder="${holderPassword}" maxlength="45" required>
            <input type="submit" value="${btnLogin}">
        </form>
    </div>
</section>

