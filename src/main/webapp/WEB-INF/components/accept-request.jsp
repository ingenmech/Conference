<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	    <c:forEach var="topic" items="${topicList}">
    		<div class="accept-form">
    			<form method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" />
    					<div class="info-gr">
                        	<p>${requestStatus}</p>
                        	<h4>${topic.status}</h4>
                        </div>
    					<div class="info-gr">
    						<p>${topic.conference.name}</p>
    						<p><h4>${topic.section.name}</h4></p>
    					</div>
    					<div class="info-gr" name="topicId" var=${topic.id}>
    						<p>${topic.user.login}</p>
    						<p><h4>${topic.name}</h4></p>
    					</div>
    				<c:set var="status" value="${topic.status}" />
    				<c:if test="${ status eq 'considered'}" var="isConsidered">
    					<div class="button-gr">
    						<input type="submit" formaction="adminAcceptRequest" value="${acceptButton}">
    						<input type="submit" formaction="adminRejectRequest" value="${rejectButton}">
    					</div>
    			    </c:if>
    			    <c:if test="${ isConsidered eq 'false' }">
    					<div class="button-remove">
                        	<input type="submit" formaction="adminRemoveRequest" value="${removeButton}">>
                        </div>
                    </c:if>
    			</form>
    		</div>
    	</c:forEach>
    </section>
</body>
</html>