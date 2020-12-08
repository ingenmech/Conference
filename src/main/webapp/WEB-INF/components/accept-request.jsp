<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="properties.locale" var="loc" />
	<fmt:message bundle="${loc}" key="accept.request.status" var="requestStatus" />
	<fmt:message bundle="${loc}" key="accept.request.accept" var="acceptButton" />
	<fmt:message bundle="${loc}" key="accept.request.reject" var="rejectButton" />
	<fmt:message bundle="${loc}" key="accept.request.remove" var="removeButton" />
</head>
<body>
		<section class="column-main">
    		<div class="accept-form">
    			<form method="POST" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" />
    					<h5>${requestStatus}</h5>
    					<div class="info-gr">
    						<p>${conferenceName}</p>
    						<p><h4>${sectionName}</h4></p>
    					</div>
    					<div class="info-gr">
    						<p>${userLogin}</p>
    						<p><h4>${userTopic}</h4></p>
    					</div>
    					<div class="button-gr">
    						<input type="submit" formaction="adminAcceptRequest" value="${acceptButton}">
    						<input type="submit" formaction="adminRejectRequest" value="${rejectButton}">
    						<input type="submit" formaction="adminDeleteRequest" value="${removeButton}">
    					</div>
    			</form>
    		</div>
    		</div>
    	</section>
</body>
</html>