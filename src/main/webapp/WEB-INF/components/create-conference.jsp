<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.locale" var="loc"/>
<fmt:message bundle="${loc}" key="create.conference.name" var="conferenceName"/>
<fmt:message bundle="${loc}" key="create.section.name" var="sectionName"/>
<fmt:message bundle="${loc}" key="create.conference.date" var="conferenceDate"/>
<fmt:message bundle="${loc}" key="create.conference.time" var="conferenceTime"/>
<fmt:message bundle="${loc}" key="create.conference.create" var="conferenceCreate"/>
<fmt:message bundle="${loc}" key="create.conference.holder" var="conferenceHolder"/>
<fmt:message bundle="${loc}" key="create.section.holder" var="sectionHolder"/>
<fmt:message bundle="${loc}" key="create.conference.message" var="message"/>
<c:set var="page" value="adminCreate" scope="session"/>
<script type="text/javascript" language="JavaScript"
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" language="JavaScript"
        src="${pageContext.request.contextPath}/static/js/add-btn.js"></script>
<section class="column-main">
    <div class="accept">
        <form method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="adminSaveConference"/>
            <div>
                <label for="name-conference">${conferenceName}</label>
                <input id="name-conference" type="text" name="conference" placeholder="${conferenceHolder}"
                       maxlength="150" required>
            </div>
            <div class="section">
                <label for="section">${sectionName}</label>
                <div>
                    <input id="section" type="text" name="section" placeholder="${sectionHolder}"
                           maxlength="150" required>
                    <button type="button" class="add-button">+</button>
                </div>
            </div>
            <div>
                <label for="date">${conferenceDate}</label>
                <input id="date" type="date" name="date" required>
                <label for="time">${conferenceTime}</label>
                <input id="time" type="time" name="time" required>
                <input id="sendConf" type="submit" value="${conferenceCreate}">
            </div>
        </form>
    </div>
</section>
