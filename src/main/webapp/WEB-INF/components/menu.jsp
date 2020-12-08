<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="properties.locale" var="loc" />
	<fmt:message bundle="${loc}" key="menu.conference" var="list" />
	<fmt:message bundle="${loc}" key="menu.archive" var="archive" />
	<fmt:message bundle="${loc}" key="menu.admin.create" var="create" />
	<fmt:message bundle="${loc}" key="menu.admin.requests" var="adminRequests" />
	<fmt:message bundle="${loc}" key="menu.user.requests" var="userRequests" />
	<fmt:message bundle="${loc}" key="menu.user.send" var="createRequest" />
	<fmt:message bundle="${loc}" key="menu.user.question" var="question" />
	<script type="text/javascript" language="JavaScript" src="${pageContext.request.contextPath}/static/js/hover-btn.js"></script>
</head>
<body>
<section>
	<div class="column-menu">
		<div id="buttons">
			<ul>
					<li>
						<a class="button-menu" href="${pageContext.request.contextPath}/controller?command=getConferences">
						${list}</a>
					</li>
					<li>
						<a class="button-menu" href="#">${archive}</a>
					</li>
				<c:if test="${sessionScope.userRole eq 'ADMIN'}" >
					<li>
						<a class="button-menu" href="${pageContext.request.contextPath}/controller?command=adminCreate">${create}</a>
					</li>
					<li>
						<a class="button-menu" href="${pageContext.request.contextPath}/controller?command=adminGoToAcceptRequest">${adminRequests}</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.userRole eq 'USER'}" >
				  <li>
					  <a class="button-menu" href="${pageContext.request.contextPath}/controller?command=userCreateRequest">${createRequest}</a>
				  </li>
				  <li>
					  <a class="button-menu" href="${pageContext.request.contextPath}/controller?command=userSentRequests">${userRequests}</a>
				  </li>
				  <li>
					  <a class="button-menu" href="#">${question}</a>
				  </li>
			    </c:if>
		    </ul>
	    </div>
    </div>
</section>
</body>
</html>