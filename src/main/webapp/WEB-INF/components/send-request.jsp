	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="ctg" uri="/WEB-INF/tld/toJson.tld" %>
	<%@ taglib prefix="cp" uri="/WEB-INF/tld/parserLocalDateTime.tld" %>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
		<fmt:setLocale value="${sessionScope.locale}" />
		<fmt:setBundle basename="properties.locale" var="loc" />
		<fmt:message bundle="${loc}" key="request.choose.conference" var="chooseConference" />
		<fmt:message bundle="${loc}" key="request.choose.section" var="chooseSection" />
		<fmt:message bundle="${loc}" key="request.choose.topic" var="chooseTopic" />
		<fmt:message bundle="${loc}" key="request.holder.topic" var="holderTopic" />
		<fmt:message bundle="${loc}" key="request.send.request" var="sendRequest" />
		<script type="text/javascript" language="JavaScript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script type="text/javascript" language="JavaScript" src="${pageContext.request.contextPath}/static/js/choose-section.js"></script>
<section class="column-main">
	<div class="accept">	
		<form method="POST" action="${pageContext.request.contextPath}/controller">
				<input type="hidden" name="command" value="userSendRequest" />
				<label for="conference"><h4>${chooseConference}</h4></label>
			<select id="conference" name="conference">
				<c:forEach var="conferences" items="${conferenceList}" varStatus="status">
					<option value="${conferences.id}">
						<cp:parse-local-date pattern="dd/MM/yyyy HH:mm" dateTime="${conferences.date}" /> - 
  				         <c:out value="${conferences.name}" />
					</option>
					<c:if test="${status.count eq 1}">
						<c:set var="confSections" value="${conferences.sections}" />
					</c:if>
			    </c:forEach>
			</select>
			<c:set var="listJson" >
			    <ctg:object-to-json object="${conferenceList}"/>
		    </c:set>
             <input id="sections" type="hidden" value='${listJson}'>
			    <label for="section"><h4>${chooseSection}</h4></label>
			<select id="section" name="section">
				<c:forEach var="section" items="${confSections}">
					<option value="${section.id}">
						<c:out value="${section.name}" />
					</option>
				</c:forEach>
			</select>
			    <label for="topic"><h4>${chooseTopic}</h4></label>
			    <input id="topic" type="text" name="topic" placeholder="${holderTopic}" maxlength="${topicLength}" required>
			    <input type="submit" value="${sendRequest}">
		</form>
	</div>
</section>
