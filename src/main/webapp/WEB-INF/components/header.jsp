<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Conference</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="properties.locale" var="loc" />
    <fmt:message bundle="${loc}" key="header.login" var="login" />
    <fmt:message bundle="${loc}" key="header.logout" var="logout" />
    <fmt:message bundle="${loc}" key="header.language" var="language" />
    <fmt:message bundle="${loc}" key="header.dropdown.ru" var="rus" />
    <fmt:message bundle="${loc}" key="header.dropdown.eng" var="eng" />
    <fmt:message bundle="${loc}" key="header.dropdown.by" var="by" />
</head>
<body>
	<header class="header">
     <div class="logo">
         <a href="${pageContext.request.contextPath}/controller?command=main">Conference</a>
     </div>
     <div class="sign-in">
        <c:if test="${sessionScope.userRole eq 'GUEST'}">
           <a class="sign-in-btn" href="${pageContext.request.contextPath}/controller?command=signIn">${login}</a>
        </c:if>
        <c:if test="${sessionScope.userRole eq 'USER' or sessionScope.userRole eq 'ADMIN'}">
              <a class="sign-in-btn" href="${pageContext.request.contextPath}/controller?command=logout">${logout}</a>
        </c:if>
     </div>
     <div  class="dropdown">
        <a href="javascript:void(0)" class="dropbtn">${language}</a>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/controller?command=en">${eng}</a>
            <a href="${pageContext.request.contextPath}/controller?command=ru">${rus}</a>
            <a href="${pageContext.request.contextPath}/controller?command=by">${by}</a>
        </div>
     </div>
</header>
</body>
</html>