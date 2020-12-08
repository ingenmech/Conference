<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="properties.locale" var="loc" />
	<fmt:message bundle="${loc}" key="create.conference.name" var="conferenceName" />
	<fmt:message bundle="${loc}" key="create.section.name" var="sectionName" />
	<fmt:message bundle="${loc}" key="create.conference.date" var="conferenceDate" />
	<fmt:message bundle="${loc}" key="create.conference.time" var="conferenceTime" />
	<fmt:message bundle="${loc}" key="create.conference.create" var="conferenceCreate" />
	<fmt:message bundle="${loc}" key="create.conference.holder" var="conferenceHolder" />
	<fmt:message bundle="${loc}" key="create.section.holder" var="sectionHolder" />
	<script type="text/javascript" language="JavaScript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" language="JavaScript" src="${pageContext.request.contextPath}/static/js/add-btn.js"></script>
</head>
<body>
	<section class="column-main">
		<div class="accept">
			<form method="POST" action="${pageContext.request.contextPath}/controller">
				<input type="hidden" name="command" value="adminSaveConference" />
				<div>
					<label for="conference"><h4>${conferenceName}</h4></label>
					<input id="name-conference" type="text" name="conference" placeholder="${conferenceHolder}">
				</div>
				<div class="section">
					<label for="section"><h4>${sectionName}</h4></label>
					<div>
						<input id="section" type="text" name="section" placeholder="${sectionHolder}">
						<button type="button" class="add-button">+</button>
					</div>
				</div>
				<div>
					<label for="date"><h4>${conferenceDate}</h4></label>
					<input id="date" type="date" name="date">
					<label for="time"><h4>${conferenceTime}</h4></label>
					<input id="time" type="time" name="time">
					<input type="submit" value="${conferenceCreate}">
				</div>
			</form>
		</div>
	</section>
</body>
</html>
